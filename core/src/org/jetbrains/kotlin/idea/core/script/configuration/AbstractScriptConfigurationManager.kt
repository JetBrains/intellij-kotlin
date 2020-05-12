/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.core.script.configuration

import com.intellij.ProjectTopics
import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.openapi.extensions.Extensions
import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.roots.ModuleRootEvent
import com.intellij.openapi.roots.ModuleRootListener
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElementFinder
import com.intellij.psi.search.GlobalSearchScope
import org.jetbrains.kotlin.idea.core.script.KotlinScriptDependenciesClassFinder
import org.jetbrains.kotlin.idea.core.script.ScriptConfigurationManager
import org.jetbrains.kotlin.idea.core.script.ScriptDependenciesModificationTracker
import org.jetbrains.kotlin.idea.core.script.configuration.listener.ScriptChangesNotifier
import org.jetbrains.kotlin.idea.core.script.configuration.listener.ScriptConfigurationUpdater
import org.jetbrains.kotlin.idea.core.script.configuration.loader.ScriptConfigurationLoader
import org.jetbrains.kotlin.idea.core.script.configuration.utils.ScriptClassRootsCache
import org.jetbrains.kotlin.idea.core.script.configuration.utils.getKtFile
import org.jetbrains.kotlin.idea.core.script.debug
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.scripting.definitions.isNonScript
import org.jetbrains.kotlin.scripting.resolve.ScriptCompilationConfigurationWrapper
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

abstract class ScriptingSupport {
    abstract fun isRelated(file: VirtualFile): Boolean

    abstract fun clearCaches()
    abstract fun hasCachedConfiguration(file: KtFile): Boolean
    abstract fun getOrLoadConfiguration(virtualFile: VirtualFile, preloadedKtFile: KtFile? = null): ScriptCompilationConfigurationWrapper?

    abstract val updater: ScriptConfigurationUpdater

    private val classpathRootsLock = ReentrantLock()

    @Volatile
    private var _classpathRoots: ScriptClassRootsCache? = null
    val classpathRoots: ScriptClassRootsCache
        get() {
            val value1 = _classpathRoots
            if (value1 != null) return value1

            classpathRootsLock.withLock {
                val value2 = _classpathRoots
                if (value2 != null) return value2

                val value3 = recreateRootsCache()
                value3.saveClassRootsToStorage()
                _classpathRoots = value3
                return value3
            }
        }

    protected abstract fun recreateRootsCache(): ScriptClassRootsCache

    fun clearClassRootsCaches(project: Project) {
        debug { "class roots caches cleared" }

        classpathRootsLock.withLock {
            _classpathRoots = null
        }

        val kotlinScriptDependenciesClassFinder =
            Extensions.getArea(project).getExtensionPoint(PsiElementFinder.EP_NAME).extensions
                .filterIsInstance<KotlinScriptDependenciesClassFinder>()
                .single()

        kotlinScriptDependenciesClassFinder.clearCache()

        ScriptDependenciesModificationTracker.getInstance(project).incModificationCount()
    }

    companion object {
        val SCRIPTING_SUPPORT: ExtensionPointName<ScriptingSupport> =
            ExtensionPointName.create("org.jetbrains.kotlin.scripting.idea.scriptingSupport")
    }
}

class CompositeManager(val project: Project) : ScriptConfigurationManager {
    @Suppress("unused")
    private val notifier = ScriptChangesNotifier(project, updater)

    // todo public for tests
    val managers = ScriptingSupport.SCRIPTING_SUPPORT.getPoint(project).extensionList

    private fun getRelatedManager(file: VirtualFile): ScriptingSupport = managers.first { it.isRelated(file) }
    private fun getRelatedManager(file: KtFile): ScriptingSupport =
        getRelatedManager(file.originalFile.virtualFile)

    private fun getOrLoadConfiguration(
        virtualFile: VirtualFile,
        preloadedKtFile: KtFile? = null
    ): ScriptCompilationConfigurationWrapper? =
        getRelatedManager(virtualFile).getOrLoadConfiguration(virtualFile, preloadedKtFile)

    override fun getConfiguration(file: KtFile) = getOrLoadConfiguration(file.originalFile.virtualFile, file)

    override fun hasConfiguration(file: KtFile): Boolean =
        getRelatedManager(file).hasCachedConfiguration(file)

    override val updater: ScriptConfigurationUpdater
        get() = object : ScriptConfigurationUpdater {
            override fun ensureUpToDatedConfigurationSuggested(file: KtFile) =
                getRelatedManager(file).updater.ensureUpToDatedConfigurationSuggested(file)

            override fun ensureConfigurationUpToDate(files: List<KtFile>): Boolean =
                files.groupBy { getRelatedManager(it) }.all { (manager, files) ->
                    manager.updater.ensureConfigurationUpToDate(files)
                }

            override fun suggestToUpdateConfigurationIfOutOfDate(file: KtFile) =
                getRelatedManager(file).updater.suggestToUpdateConfigurationIfOutOfDate(file)
        }

    init {
        val connection = project.messageBus.connect(project)
        connection.subscribe(ProjectTopics.PROJECT_ROOTS, object : ModuleRootListener {
            override fun rootsChanged(event: ModuleRootEvent) {
                managers.forEach {
                    it.clearClassRootsCaches(project)
                }
            }
        })
    }

    /**
     * Returns script classpath roots
     * Loads script configuration if classpath roots don't contain [file] yet
     */
    private fun getActualClasspathRoots(file: VirtualFile): ScriptClassRootsCache {
        val classpathRoots = getRelatedManager(file).classpathRoots
        if (classpathRoots.contains(file)) {
            return classpathRoots
        }

        getOrLoadConfiguration(file)

        return getRelatedManager(file).classpathRoots
    }

    override fun getScriptSdk(file: VirtualFile): Sdk? =
        getActualClasspathRoots(file).getScriptSdk(file)

    override fun getFirstScriptsSdk(): Sdk? {
        managers.forEach {
            it.classpathRoots.firstScriptSdk?.let { sdk -> return sdk }
        }

        return null
    }

    override fun getScriptDependenciesClassFilesScope(file: VirtualFile): GlobalSearchScope =
        getActualClasspathRoots(file).getScriptDependenciesClassFilesScope(file)

    override fun getAllScriptsDependenciesClassFilesScope(): GlobalSearchScope =
        GlobalSearchScope.union(managers.map { it.classpathRoots.allDependenciesClassFilesScope })

    override fun getAllScriptDependenciesSourcesScope(): GlobalSearchScope =
        GlobalSearchScope.union(managers.map { it.classpathRoots.allDependenciesSourcesScope })

    override fun getAllScriptsDependenciesClassFiles(): List<VirtualFile> =
        managers.flatMap { it.classpathRoots.allDependenciesClassFiles }

    override fun getAllScriptDependenciesSources(): List<VirtualFile> =
        managers.flatMap { it.classpathRoots.allDependenciesSources }

    override fun forceReloadConfiguration(file: VirtualFile, loader: ScriptConfigurationLoader): ScriptCompilationConfigurationWrapper? {
        val ktFile = project.getKtFile(file, null) ?: return null

        return managers.firstIsInstanceOrNull<DefaultScriptingSupport>()?.forceReloadConfiguration(ktFile, loader)
    }

    ///////////////////
    // Adapters for deprecated API
    //

    @Deprecated("Use getScriptClasspath(KtFile) instead")
    override fun getScriptClasspath(file: VirtualFile): List<VirtualFile> {
        val ktFile = project.getKtFile(file) ?: return emptyList()
        return getScriptClasspath(ktFile)
    }

    override fun getScriptClasspath(file: KtFile): List<VirtualFile> =
        ScriptConfigurationManager.toVfsRoots(getConfiguration(file)?.dependenciesClassPath.orEmpty())

    private fun clearCaches() {
        managers.forEach {
            it.clearCaches()
        }
    }

    override fun clearConfigurationCachesAndRehighlight() {
        ScriptDependenciesModificationTracker.getInstance(project).incModificationCount()

        clearCaches()

        ScriptingSupportHelper.updateHighlighting(project) {
            !it.isNonScript()
        }
    }
}

