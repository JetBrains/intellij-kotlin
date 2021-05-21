/*
 * Copyright 2000-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.caches.project

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.*
import com.intellij.openapi.roots.impl.libraries.LibraryEx
import com.intellij.openapi.roots.libraries.Library
import com.intellij.openapi.util.Condition
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.util.containers.ContainerUtil
import com.intellij.util.containers.MultiMap
import org.jetbrains.kotlin.idea.configuration.IdeBuiltInsLoadingState
import org.jetbrains.kotlin.idea.core.util.CachedValue
import org.jetbrains.kotlin.idea.core.util.getValue
import org.jetbrains.kotlin.idea.klib.AbstractKlibLibraryInfo
import org.jetbrains.kotlin.idea.project.isHMPPEnabled
import org.jetbrains.kotlin.platform.SimplePlatform
import org.jetbrains.kotlin.idea.project.isHMPPEnabled
import org.jetbrains.kotlin.platform.TargetPlatform
import org.jetbrains.kotlin.platform.konan.NativePlatformUnspecifiedTarget
import org.jetbrains.kotlin.platform.konan.NativePlatformWithTarget
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

internal typealias LibrariesAndSdks = Pair<List<LibraryInfo>, List<SdkInfo>>

interface LibraryDependenciesCache {
    companion object {
        fun getInstance(project: Project) = ServiceManager.getService(project, LibraryDependenciesCache::class.java)!!
    }

    fun getLibrariesAndSdksUsedWith(libraryInfo: LibraryInfo): LibrariesAndSdks
}

class LibraryDependenciesCacheImpl(private val project: Project) : LibraryDependenciesCache {
    private val cache by CachedValue(project) {
        CachedValueProvider.Result(
            ContainerUtil.createConcurrentWeakMap<LibraryInfo, LibrariesAndSdks>(),
            ProjectRootManager.getInstance(project)
        )
    }

    private val moduleDependenciesCache by CachedValue(project) {
        CachedValueProvider.Result(
            ContainerUtil.createConcurrentWeakMap<Module, LibrariesAndSdks>(),
            ProjectRootManager.getInstance(project)
        )
    }

    override fun getLibrariesAndSdksUsedWith(libraryInfo: LibraryInfo): LibrariesAndSdks =
        cache.getOrPut(libraryInfo) { computeLibrariesAndSdksUsedWith(libraryInfo) }


    //NOTE: used LibraryRuntimeClasspathScope as reference
    private fun computeLibrariesAndSdksUsedWith(libraryInfo: LibraryInfo): LibrariesAndSdks {
        val libraries = LinkedHashSet<LibraryInfo>()
        val sdks = LinkedHashSet<SdkInfo>()

        val platform = libraryInfo.platform

        for (module in getLibraryUsageIndex().getModulesLibraryIsUsedIn(libraryInfo)) {
            val (moduleLibraries, moduleSdks) = moduleDependenciesCache.getOrPut(module) {
                computeLibrariesAndSdksUsedIn(module)
            }

            moduleLibraries.filter { compatiblePlatforms(platform, it.platform) }.forEach { libraries.add(it) }
            moduleSdks.filter { compatiblePlatforms(platform, it.platform) }.forEach { sdks.add(it) }
        }

        return Pair(libraries.toList(), sdks.toList())
    }

    private fun computeLibrariesAndSdksUsedIn(module: Module): LibrariesAndSdks {
        val libraries = LinkedHashSet<LibraryInfo>()
        val sdks = LinkedHashSet<SdkInfo>()

        val processedModules = HashSet<Module>()
        val condition = Condition<OrderEntry> { orderEntry ->
            orderEntry.safeAs<ModuleOrderEntry>()?.let {
                it.module?.run { this !in processedModules } ?: false
            } ?: true
        }

        ModuleRootManager.getInstance(module).orderEntries().recursively().satisfying(condition).process(object : RootPolicy<Unit>() {
            override fun visitModuleSourceOrderEntry(moduleSourceOrderEntry: ModuleSourceOrderEntry, value: Unit) {
                processedModules.add(moduleSourceOrderEntry.ownerModule)
            }

            override fun visitLibraryOrderEntry(libraryOrderEntry: LibraryOrderEntry, value: Unit) {
                libraryOrderEntry.library.safeAs<LibraryEx>()?.takeIf { !it.isDisposed }?.let {
                    libraries += createLibraryInfo(project, it)
                }
            }

            override fun visitJdkOrderEntry(jdkOrderEntry: JdkOrderEntry, value: Unit) {
                jdkOrderEntry.jdk?.let { jdk ->
                    sdks += SdkInfo(project, jdk)
                }
            }
        }, Unit)

        return Pair(libraries.toList(), sdks.toList())
    }

        return filteredLibraries to sdks
    }

    private fun getLibraryUsageIndex(): LibraryUsageIndex {
        return CachedValuesManager.getManager(project).getCachedValue(project) {
            CachedValueProvider.Result(LibraryUsageIndex(), ProjectRootModificationTracker.getInstance(project))
        }!!
    }

    /*
    * When built-ins are created from module dependencies (as opposed to loading them from classloader)
    * we must resolve Kotlin standard library containing some of the built-ins declarations in the same
    * resolver for project as JDK. This comes from the following requirements:
    * - JvmBuiltins need JDK and standard library descriptors -> resolver for project should be able to
    *   resolve them
    * - Builtins are created in BuiltinsCache -> module descriptors should be resolved under lock of the
    *   SDK resolver to prevent deadlocks
    * This means we have to maintain dependencies of the standard library manually or effectively drop
    * resolver for SDK otherwise. Libraries depend on superset of their actual dependencies because of
    * the inability to get real dependencies from IDEA model. So moving stdlib with all dependencies
    * down is a questionable option.
    */
    private fun filterForBuiltins(libraryInfo: LibraryInfo, dependencyLibraries: Set<DependencyCandidate>): Set<DependencyCandidate> {
        return if (!IdeBuiltInsLoadingState.isFromClassLoader && libraryInfo.isCoreKotlinLibrary(project)) {
            dependencyLibraries.filterTo(mutableSetOf()) { dep ->
                dep.libraries.any { it.isCoreKotlinLibrary(project) }
            }
        } else {
            dependencyLibraries
        }
    }

    private inner class LibraryUsageIndex {
        val modulesLibraryIsUsedIn: MultiMap<Library, Module> = MultiMap.createSet()

        init {
            for (module in ModuleManager.getInstance(project).modules) {
                for (entry in ModuleRootManager.getInstance(module).orderEntries) {
                    if (entry is LibraryOrderEntry) {
                        val library = entry.library
                        if (library != null) {
                            modulesLibraryIsUsedIn.putValue(library, module)
                        }
                    }
                }
            }
        }

        fun getModulesLibraryIsUsedIn(libraryInfo: LibraryInfo) = sequence<Module> {
            val ideaModelInfosCache = getIdeaModelInfosCache(project)
            for (module in modulesLibraryIsUsedIn[libraryInfo.library]) {
                val mappedModuleInfos = ideaModelInfosCache.getModuleInfosForModule(module)
                if (mappedModuleInfos.any { it.platform.canDependOn(libraryInfo, module.isHMPPEnabled) }) {
                    yield(module)
                }
            }
        }
    }
}

internal data class DependencyCandidate(
    /**
     * Identifier for the library this candidate belongs to.
     * E.g. a library may consist out of multiple fragments (supporting different sets of platforms).
     * All those fragments will share the same [containingLibraryId].
     *
     * `null` if the dependency itself is a library, not a fragment (e.g. Java libraries do not have fragments)
     */
    val containingLibraryId: String?,
    val platform: TargetPlatform,
    val libraries: List<LibraryInfo>
) {
    companion object {
        fun fromLibraryOrNull(project: Project, library: Library): DependencyCandidate? {
            val libraryInfos = createLibraryInfo(project, library)
            val libraryInfo = libraryInfos.firstOrNull() ?: return null
            return DependencyCandidate(
                containingLibraryIdOrNull(libraryInfo),
                platform = libraryInfo.platform,
                libraries = libraryInfos
            )
        }

        private fun containingLibraryIdOrNull(libraryInfo: LibraryInfo): String? {
            return (libraryInfo as? AbstractKlibLibraryInfo)?.uniqueName
        }
    }
}

internal fun chooseCompatibleDependencies(
    platform: TargetPlatform,
    candidates: Set<DependencyCandidate>
): Set<DependencyCandidate> {
    val candidatesByContainingLibraryId = candidates.groupBy { it.containingLibraryId }
    return candidatesByContainingLibraryId.map { (containingLibraryId, candidates) ->
        /* Library Candidate is not part of a group of fragments */
        if (containingLibraryId == null) {
            return@map candidates.filter { candidate -> platform isSubsetCompatibleTo candidate.platform }
        }

        val chosenPlatforms = chooseTargetPlatformsForDependencyCompatibility(platform, candidates.map { it.platform }.toSet())
        candidates.filter { candidate -> candidate.platform in chosenPlatforms }
    }.flatten().toSet()
}

/**
 * @param platforms All available platforms to choose from
 * @return Set of [TargetPlatform] that can be used as dependency for a library with [from] platform.
 *
 * e.g.
 * abc && abcd, abc, ab, a -> abcd, abc
 * abc && abcd, ab, ad, a -> abcd, ab
 * abc && abcd, ab, ac, ad, a -> abcd, (ab or ac, but stable)
 * abc && abcd -> abcd
 */
private fun chooseTargetPlatformsForDependencyCompatibility(
    from: TargetPlatform, platforms: Set<TargetPlatform>
): Set<TargetPlatform> {
    val compatiblePlatforms = platforms.filter { platform -> from isSubsetCompatibleTo platform }.toSet()
    val isComplete = compatiblePlatforms.any { platform -> from isSupersetCompatibleTo platform }

    /*
    Contains a TargetPlatform that represents the same set of SimplePlatform's
    The set of "TargetPlatforms" is considered complete
    */
    if (isComplete) {
        return compatiblePlatforms
    }

    /*
    Current platforms in [compatiblePlatforms] all support more platforms than
    the request [from] TargetPlatform(a, b) to TargetPlatform(a, b, c)

    We try to find the greatest lower bound to ensure that we find at least one superset compatible platform.
    Usually, it's not fine to have dependency from TargetPlatform(a, b, c) to TargetPlatform(a, b) because the latter might
    provide additional platform-specific APIs.

    The reason why it's OK to have here is because we're working with binary-to-binary dependencies.
    All references in from are already resolved, so we just should provide a library against which from would
    successfully link (so having extra APIs is fine, they won't be used anyways)"
    */
    val greatestLowerBound = platforms
        .filter { platform -> from isSupersetCompatibleTo platform }
        /* Sort remaining candidates to ensure as stable as possible choice for "maxByOrNull" */
        .sortedWith(TargetPlatformComparator)
        .maxByOrNull { candidate -> candidate.componentPlatforms.size }
        ?: return compatiblePlatforms.toSet()

    return mutableSetOf<TargetPlatform>().apply {
        addAll(compatiblePlatforms)
        add(greatestLowerBound)
    }
}

private object TargetPlatformComparator :
    Comparator<TargetPlatform> by compareBy<TargetPlatform>({ platform -> platform.componentPlatforms.size })
        .thenComparing({ platform -> platform.componentPlatforms.sortedWith(SimplePlatformComparator).joinToString() })

private object SimplePlatformComparator :
    Comparator<SimplePlatform> by compareBy<SimplePlatform>({ it.targetName })
        .thenComparing<String>({ it.platformName })

/**
 * @return true if all platforms in [from] have a compatible match in [to]
 *
 * e.g.
 * a, b, c -> a, b, c, d #true (all platforms available in "to")
 * a, b, c -> a, b #false (*not* all platforms available in "to")
 */
private fun isCompatible(from: TargetPlatform, to: TargetPlatform): Boolean {
    return from.componentPlatforms.all { fromSimplePlatform ->
        to.componentPlatforms.any { toSimplePlatform -> isCompatible(fromSimplePlatform, toSimplePlatform) }
    }
}


private fun isCompatible(from: SimplePlatform, to: SimplePlatform): Boolean {
    return when {
        from == to -> true
        from is NativePlatformWithTarget && to is NativePlatformUnspecifiedTarget -> true
        else -> false
    }
}

/**
 * @return true: if this represents a subset of platforms to [other]
 */
private infix fun TargetPlatform.isSubsetCompatibleTo(other: TargetPlatform): Boolean {
    return isCompatible(this, other)
}

/**
 * @return true: if this represents a superset of platforms to [other]
 */
private infix fun TargetPlatform.isSupersetCompatibleTo(other: TargetPlatform): Boolean {
    return isCompatible(other, this)
}