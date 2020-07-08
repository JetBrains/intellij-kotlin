/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.tools.projectWizard.moduleConfigurators


import org.jetbrains.annotations.NonNls
import org.jetbrains.kotlin.tools.projectWizard.KotlinNewProjectWizardBundle
import org.jetbrains.kotlin.tools.projectWizard.core.Reader
import org.jetbrains.kotlin.tools.projectWizard.core.entity.settings.ModuleConfiguratorSetting
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.BuildSystemIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.KotlinBuildSystemPluginIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.GradleIRListBuilder
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.irsList
import org.jetbrains.kotlin.tools.projectWizard.phases.GenerationPhase
import org.jetbrains.kotlin.tools.projectWizard.plugins.kotlin.ModuleType
import org.jetbrains.kotlin.tools.projectWizard.plugins.kotlin.ModulesToIrConversionData
import org.jetbrains.kotlin.tools.projectWizard.settings.buildsystem.Module
import org.jetbrains.kotlin.tools.projectWizard.settings.buildsystem.ModuleKind

interface JSConfigurator : ModuleConfiguratorWithModuleType, ModuleConfiguratorWithSettings {
    override val moduleType: ModuleType get() = ModuleType.js

    override fun getConfiguratorSettings(): List<ModuleConfiguratorSetting<*, *>> =
        super.getConfiguratorSettings() + kind

    fun Reader.isApplication(module: Module): Boolean =
        settingsValue(module, kind) == JsTargetKind.APPLICATION

    fun Reader.hasCssSupport(module: Module): Boolean =
        settingsValue(module, cssSupport)

    fun GradleIRListBuilder.browserSubTarget(module: Module, reader: Reader) {
        "browser" {
            if (reader.isApplication(module)) {
                applicationSupport()
            }
            if (reader.hasCssSupport(module)) {
                if (reader.isApplication(module)) {
                    applicationCssSupport()
                }
                testCssSupport()
            }
        }
    }

    fun GradleIRListBuilder.nodejsSubTarget(module: Module, reader: Reader) {
        "nodejs" {
            if (reader.isApplication(module)) {
                applicationSupport()
            }
        }
    }

    companion object : ModuleConfiguratorSettings() {
        val kind by enumSetting<JsTargetKind>(
            KotlinNewProjectWizardBundle.message("module.configurator.js.target.settings.kind"),
            GenerationPhase.PROJECT_GENERATION
        ) {
            defaultValue = value(JsTargetKind.APPLICATION)
        }

        val cssSupport by booleanSetting(
            KotlinNewProjectWizardBundle.message("module.configurator.js.css"),
            GenerationPhase.PROJECT_GENERATION
        ) {
            defaultValue = value(true)
        }
    }
}

object JsSingleplatformModuleConfigurator : JSConfigurator, ModuleConfiguratorWithTests, SinglePlatformModuleConfigurator,
    ModuleConfiguratorWithSettings {
    override fun getConfiguratorSettings(): List<ModuleConfiguratorSetting<*, *>> =
        super<ModuleConfiguratorWithTests>.getConfiguratorSettings() +
                super<JSConfigurator>.getConfiguratorSettings() +
                JSConfigurator.cssSupport

    override val moduleKind = ModuleKind.singleplatformJs

    @NonNls
    override val suggestedModuleName = "js"

    @NonNls
    override val id = "jsSinglepaltform"
    override val text = KotlinNewProjectWizardBundle.message("module.configurator.js")

    override fun defaultTestFramework(): KotlinTestFramework = KotlinTestFramework.JS

    override val canContainSubModules = false

    override fun createKotlinPluginIR(configurationData: ModulesToIrConversionData, module: Module): KotlinBuildSystemPluginIR? =
        KotlinBuildSystemPluginIR(
            KotlinBuildSystemPluginIR.Type.js,
            version = configurationData.kotlinVersion
        )

    override fun createBuildFileIRs(
        reader: Reader,
        configurationData: ModulesToIrConversionData,
        module: Module
    ): List<BuildSystemIR> = irsList {
        "kotlin" {
            "js" {
                browserSubTarget(module, reader)
            }
        }
    }
}

fun GradleIRListBuilder.applicationSupport() {
    +"binaries.executable()"
}

fun GradleIRListBuilder.applicationCssSupport() {
    "webpackTask" {
        +"cssSupport.enabled = true"
    }
    "runTask" {
        +"cssSupport.enabled = true"
    }
}

fun GradleIRListBuilder.testCssSupport() {
    "testTask" {
        "useKarma" {
            +"useChromeHeadless()"
            +"cssSupport.enabled = true"
        }
    }
}