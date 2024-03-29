/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.configuration

import com.intellij.openapi.externalSystem.model.DataNode
import com.intellij.openapi.externalSystem.model.ProjectKeys
import com.intellij.openapi.externalSystem.model.project.ModuleData
import com.intellij.openapi.externalSystem.model.project.ProjectData
import com.intellij.openapi.externalSystem.service.project.IdeModifiableModelsProvider
import com.intellij.openapi.externalSystem.service.project.manage.AbstractProjectDataService
import com.intellij.openapi.externalSystem.util.ExternalSystemApiUtil
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.DependencyScope
import com.intellij.openapi.roots.ExportableOrderEntry
import com.intellij.openapi.roots.ModifiableRootModel
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArguments
import org.jetbrains.kotlin.config.JvmTarget
import org.jetbrains.kotlin.config.KotlinModuleKind
import org.jetbrains.kotlin.gradle.KotlinCompilation
import org.jetbrains.kotlin.gradle.KotlinModule
import org.jetbrains.kotlin.gradle.KotlinPlatform
import org.jetbrains.kotlin.gradle.KotlinSourceSet
import org.jetbrains.kotlin.idea.facet.*
import org.jetbrains.kotlin.idea.inspections.gradle.findAll
import org.jetbrains.kotlin.idea.inspections.gradle.findKotlinPluginVersion
import org.jetbrains.kotlin.idea.platform.IdePlatformKindTooling
import org.jetbrains.kotlin.idea.roots.migrateNonJvmSourceFolders
import org.jetbrains.kotlin.idea.roots.populateNonJvmSourceRootTypes
import org.jetbrains.kotlin.platform.TargetPlatform
import org.jetbrains.kotlin.platform.impl.JvmIdePlatformKind
import org.jetbrains.kotlin.platform.impl.NativeIdePlatformKind
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms
import org.jetbrains.kotlin.platform.jvm.isJvm
import org.jetbrains.kotlin.platform.konan.NativePlatforms
import org.jetbrains.plugins.gradle.model.data.BuildScriptClasspathData
import org.jetbrains.plugins.gradle.model.data.GradleSourceSetData
import org.jetbrains.plugins.gradle.util.GradleConstants

class KotlinSourceSetDataService : AbstractProjectDataService<GradleSourceSetData, Void>() {
    override fun getTargetDataKey() = GradleSourceSetData.KEY

    override fun postProcess(
        toImport: MutableCollection<out DataNode<GradleSourceSetData>>,
        projectData: ProjectData?,
        project: Project,
        modelsProvider: IdeModifiableModelsProvider
    ) {
        for (nodeToImport in toImport) {
            val mainModuleData = ExternalSystemApiUtil.findParent(
                nodeToImport,
                ProjectKeys.MODULE
            ) ?: continue
            val sourceSetData = nodeToImport.data
            val kotlinSourceSet = nodeToImport.kotlinSourceSet ?: continue
            val ideModule = modelsProvider.findIdeModule(sourceSetData) ?: continue
            val platform = kotlinSourceSet.actualPlatforms
            val rootModel = modelsProvider.getModifiableRootModel(ideModule)

            if (platform.platforms.any { it != KotlinPlatform.JVM && it != KotlinPlatform.ANDROID }) {
                migrateNonJvmSourceFolders(rootModel)
                populateNonJvmSourceRootTypes(nodeToImport, ideModule)
            }

            configureFacet(sourceSetData, kotlinSourceSet, mainModuleData, ideModule, modelsProvider)?.let { facet ->
                GradleProjectImportHandler.getInstances(project).forEach { it.importBySourceSet(facet, nodeToImport) }
            }

            if (kotlinSourceSet.isTestModule) {
                assignTestScope(rootModel)
            }
        }
    }

    private fun assignTestScope(rootModel: ModifiableRootModel) {
        rootModel
            .orderEntries
            .asSequence()
            .filterIsInstance<ExportableOrderEntry>()
            .filter { it.scope == DependencyScope.COMPILE }
            .forEach { it.scope = DependencyScope.TEST }
    }

    companion object {
        private val KotlinModule.kind
            get() = when (this) {
                is KotlinCompilation -> KotlinModuleKind.COMPILATION_AND_SOURCE_SET_HOLDER
                is KotlinSourceSet -> KotlinModuleKind.SOURCE_SET_HOLDER
                else -> KotlinModuleKind.DEFAULT
            }

        fun configureFacet(
            moduleData: ModuleData,
            kotlinSourceSet: KotlinSourceSetInfo,
            mainModuleNode: DataNode<ModuleData>,
            ideModule: Module,
            modelsProvider: IdeModifiableModelsProvider
        ): KotlinFacet? {
            val compilerVersion = mainModuleNode
                .findAll(BuildScriptClasspathData.KEY)
                .firstOrNull()
                ?.data
                ?.let { findKotlinPluginVersion(it) }// ?: return null TODO: Fix in CLion or our plugin KT-27623

            val platformKinds = kotlinSourceSet.actualPlatforms.platforms //TODO(auskov): fix calculation of jvm target
                .map { IdePlatformKindTooling.getTooling(it).kind }
                .flatMap { platformKind ->
                    when (platformKind) {
                        is JvmIdePlatformKind -> {
                            val jvmTarget = JvmTarget.fromString(moduleData.targetCompatibility ?: "") ?: JvmTarget.DEFAULT
                            JvmPlatforms.jvmPlatformByTargetVersion(jvmTarget)
                        }
                        is NativeIdePlatformKind -> {
                            NativePlatforms.nativePlatformByTargetNames(moduleData.konanTargets)
                        }
                        else -> platformKind.defaultPlatform
                    }
                }
                .distinct()
                .toSet()

            val platform = TargetPlatform(platformKinds)

            val compilerArguments = kotlinSourceSet.compilerArguments
            // Used ID is the same as used in org/jetbrains/kotlin/idea/configuration/KotlinGradleSourceSetDataService.kt:280
            // because this DataService was separated from KotlinGradleSourceSetDataService for MPP projects only
            val id = if (compilerArguments?.multiPlatform == true) GradleConstants.SYSTEM_ID.id else null
            val kotlinFacet = ideModule.getOrCreateFacet(modelsProvider, false, id)
            kotlinFacet.configureFacet(
                compilerVersion,
                platform,
                modelsProvider,
                mainModuleNode.isHmpp,
                if (platform.isJvm()) mainModuleNode.pureKotlinSourceFolders.toList() else emptyList(),
                kotlinSourceSet.dependsOn
            )

            val defaultCompilerArguments = kotlinSourceSet.defaultCompilerArguments
            if (compilerArguments != null) {
                applyCompilerArgumentsToFacet(
                    compilerArguments,
                    defaultCompilerArguments,
                    kotlinFacet,
                    modelsProvider
                )
            }

            adjustClasspath(kotlinFacet, kotlinSourceSet.dependencyClasspath)

            kotlinFacet.noVersionAutoAdvance()

            with(kotlinFacet.configuration.settings) {
                kind = kotlinSourceSet.kotlinModule.kind

                isTestModule = kotlinSourceSet.isTestModule
                externalSystemRunTasks = ArrayList(kotlinSourceSet.externalSystemRunTasks)

                externalProjectId = kotlinSourceSet.gradleModuleId

                sourceSetNames = kotlinSourceSet.sourceSetIdsByName.values.mapNotNull { sourceSetId ->
                    val node = mainModuleNode.findChildModuleById(sourceSetId) ?: return@mapNotNull null
                    val data = node.data as? ModuleData ?: return@mapNotNull null
                    modelsProvider.findIdeModule(data)?.name
                }

                if (kotlinSourceSet.isTestModule) {
                    testOutputPath = (kotlinSourceSet.compilerArguments as? K2JSCompilerArguments)?.outputFile
                    productionOutputPath = null
                } else {
                    productionOutputPath = (kotlinSourceSet.compilerArguments as? K2JSCompilerArguments)?.outputFile
                    testOutputPath = null
                }
            }

            return kotlinFacet
        }
    }
}

private const val KOTLIN_NATIVE_TARGETS_PROPERTY = "konanTargets"

var ModuleData.konanTargets: Set<String>
    get() {
        val value = getProperty(KOTLIN_NATIVE_TARGETS_PROPERTY) ?: return emptySet()
        return if (value.isNotEmpty()) value.split(',').toSet() else emptySet()
    }
    set(value) = setProperty(KOTLIN_NATIVE_TARGETS_PROPERTY, value.takeIf { it.isNotEmpty() }?.joinToString(","))
