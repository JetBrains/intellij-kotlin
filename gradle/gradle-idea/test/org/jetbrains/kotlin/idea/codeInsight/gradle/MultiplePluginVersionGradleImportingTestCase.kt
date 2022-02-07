/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

/**
 * This TestCase implements possibility to test import with different versions of gradle and different
 * versions of gradle kotlin plugin
 */
package org.jetbrains.kotlin.idea.codeInsight.gradle

import org.jetbrains.kotlin.gradle.ProjectInfo
import org.junit.Rule
import org.junit.runners.Parameterized


abstract class MultiplePluginVersionGradleImportingTestCase : KotlinGradleImportingTestCase() {
    @Rule
    @JvmField
    var pluginVersionMatchingRule = PluginTargetVersionsRule()

    @JvmField
    @Parameterized.Parameter(1)
    var gradleKotlinPluginVersionParameter: String = ""

    val gradleKotlinPluginVersion: String get() = gradleKotlinPluginVersionParameter
        .takeUnless { it == "master" }
        ?: masterKotlinPluginVersion

    companion object {
        val masterKotlinPluginVersion: String = System.getenv("KOTLIN_GRADLE_PLUGIN_VERSION") ?: "1.6.255-SNAPSHOT"

        fun masterKotlinPluginVersionParameters() = listOf<Array<Any>>(
            arrayOf("6.8.2", "master"),
            arrayOf("7.0.2", "master")
        )

        @JvmStatic
        @Suppress("ACCIDENTAL_OVERRIDE")
        @Parameterized.Parameters(name = "{index}: Gradle-{0}, KotlinGradlePlugin-{1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("4.9", "1.3.30"),
                arrayOf("4.9", "1.3.72"),
                arrayOf("5.6.4", "1.3.72"),
                arrayOf("6.7.1", "1.4.20")
            )
        }
    }

    private fun repositories(): String {
        return listOf(
            "mavenCentral()",
            "mavenLocal()",
            "google()",
            "gradlePluginPortal()",
            "jcenter()"
        ).joinToString("\n")
    }

    fun androidProperties(): Map<String, String> = mapOf(
        "android_gradle_plugin_version" to "4.0.2",
        "compile_sdk_version" to "30",
        "build_tools_version" to "28.0.3",
    )

    val isHmppEnabledByDefault get() = gradleKotlinPluginVersion == masterKotlinPluginVersion

    fun hmppProperties(): Map<String, String> =
        if (isHmppEnabledByDefault) {
            mapOf(
                "enable_hmpp_flags" to "",
                "disable_hmpp_flags" to "kotlin.mpp.hierarchicalStructureSupport=false"
            )
        } else {
            mapOf(
                "enable_hmpp_flags" to """
                    kotlin.mpp.enableGranularSourceSetsMetadata=true
                    kotlin.native.enableDependencyPropagation=false
                    kotlin.mpp.enableHierarchicalCommonization=true
                """.trimIndent(),
                "disable_hmpp_flags" to ""
            )
        }

    override val defaultProperties: Map<String, String>
        get() = super.defaultProperties.toMutableMap().apply {
            put("kotlin_plugin_version", gradleKotlinPluginVersion)
            put("kotlin_plugin_repositories", repositories())
            put("kts_kotlin_plugin_repositories", repositories())

            putAll(hmppProperties())
            putAll(androidProperties())
        }

    protected open fun checkProjectStructure(
        exhaustiveModuleList: Boolean = true,
        exhaustiveSourceSourceRootList: Boolean = true,
        exhaustiveDependencyList: Boolean = true,
        body: ProjectInfo.() -> Unit = {}
    ) {
        org.jetbrains.kotlin.gradle.checkProjectStructure(
            myProject,
            projectPath,
            exhaustiveModuleList,
            exhaustiveSourceSourceRootList,
            exhaustiveDependencyList,
            false,
            body
        )
    }
}

