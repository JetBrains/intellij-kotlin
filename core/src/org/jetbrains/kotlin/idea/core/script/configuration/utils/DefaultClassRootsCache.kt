/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.core.script.configuration.utils

import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.containers.ConcurrentFactoryMap
import org.jetbrains.kotlin.idea.core.script.ScriptConfigurationManager
import org.jetbrains.kotlin.scripting.resolve.ScriptCompilationConfigurationWrapper
import java.io.File

internal class DefaultClassRootsCache(
    project: Project,
    private val all: Map<VirtualFile, ScriptCompilationConfigurationWrapper>
) : ScriptClassRootsCache(project, extractRoots(all, project)) {

    override val fileToConfiguration: (VirtualFile) -> ScriptCompilationConfigurationWrapper?
        get() = { all[it] }

    override fun contains(file: VirtualFile): Boolean = file in all

    override val rootsCacheKey = ScriptClassRootsStorage.Companion.Key("default")

    private val scriptsSdksCache: Map<VirtualFile, Sdk?> =
        ConcurrentFactoryMap.createWeakMap { file ->
            return@createWeakMap getScriptSdk(
                all[file]?.javaHome
            ) ?: ScriptConfigurationManager.getScriptDefaultSdk(project)
        }

    override fun getScriptSdk(file: VirtualFile): Sdk? = scriptsSdksCache[file]

    override val firstScriptSdk: Sdk? by lazy {
        val firstCachedScript = all.keys.firstOrNull() ?: return@lazy null
        return@lazy getScriptSdk(firstCachedScript)
    }

    companion object {
        fun extractRoots(
            project: Project,
            configuration: ScriptCompilationConfigurationWrapper
        ): ScriptClassRootsStorage.Companion.ScriptClassRoots {
            val scriptSdk =
                getScriptSdkOfDefault(
                    configuration.javaHome,
                    project
                )
            if (scriptSdk != null && !scriptSdk.isAlreadyIndexed(project)) {
                return ScriptClassRootsStorage.Companion.ScriptClassRoots(
                    toStringValues(
                        configuration.dependenciesClassPath
                    ),
                    toStringValues(
                        configuration.dependenciesSources
                    ),
                    setOf(scriptSdk)
                )
            }

            return ScriptClassRootsStorage.Companion.ScriptClassRoots(
                toStringValues(
                    configuration.dependenciesClassPath
                ),
                toStringValues(
                    configuration.dependenciesSources
                ),
                emptySet()
            )
        }

        fun extractRoots(
            all: Map<VirtualFile, ScriptCompilationConfigurationWrapper>,
            project: Project
        ): ScriptClassRootsStorage.Companion.ScriptClassRoots {
            val classpath = mutableSetOf<File>()
            val sources = mutableSetOf<File>()
            val sdks = mutableSetOf<Sdk>()

            for ((_, configuration) in all) {
                val scriptSdk =
                    getScriptSdk(configuration.javaHome)
                if (scriptSdk != null && !scriptSdk.isAlreadyIndexed(project)) {
                    sdks.add(scriptSdk)
                }

                classpath.addAll(configuration.dependenciesClassPath)
                sources.addAll(configuration.dependenciesSources)
            }

            return ScriptClassRootsStorage.Companion.ScriptClassRoots(
                toStringValues(classpath),
                toStringValues(sources),
                sdks
            )
        }
    }
}