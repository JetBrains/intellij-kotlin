/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

/**
 * This TestCase implements possibility to test import with different versions of gradle and different
 * versions of gradle kotlin plugin
 */
package org.jetbrains.kotlin.idea.codeInsight.gradle

import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.kotlin.gradle.ProjectInfo
import org.junit.Rule
import org.junit.runners.Parameterized


abstract class MultiplePluginVersionGradleImportingTestCase : KotlinGradleImportingTestCase() {
    @Rule
    @JvmField
    var pluginVersionMatchingRule = PluginTargetVersionsRule()

    @JvmField
    @Parameterized.Parameter(1)
    var gradleKotlinPluginVersion: String = ""


    companion object {

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

    override fun configureByFiles(properties: Map<String, String>?): List<VirtualFile> {
        val unitedProperties = HashMap(properties ?: emptyMap())
        unitedProperties["kotlin_plugin_version"] = gradleKotlinPluginVersion

        val defaultRepositories = repositories()
        unitedProperties["kotlin_plugin_repositories"] = defaultRepositories
        unitedProperties["kts_kotlin_plugin_repositories"] = defaultRepositories
        return super.configureByFiles(unitedProperties)
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

