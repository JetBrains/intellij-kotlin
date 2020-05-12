/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.scripting.gradle

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.service
import com.intellij.openapi.externalSystem.util.ExternalSystemApiUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.registry.Registry
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*
import com.intellij.psi.impl.source.tree.LeafPsiElement
import org.gradle.util.GradleVersion
import org.jetbrains.kotlin.idea.util.application.runReadAction
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtScriptInitializer
import org.jetbrains.kotlin.psi.psiUtil.getChildrenOfType
import org.jetbrains.plugins.gradle.settings.GradleLocalSettings
import org.jetbrains.plugins.gradle.settings.GradleExecutionSettings
import org.jetbrains.plugins.gradle.settings.GradleProjectSettings
import org.jetbrains.plugins.gradle.settings.GradleSettings
import org.jetbrains.plugins.gradle.util.GradleConstants

private val sections = arrayListOf("buildscript", "plugins", "initscript", "pluginManagement")

fun isGradleKotlinScript(virtualFile: VirtualFile) = virtualFile.name.endsWith(".gradle.kts")

fun isInAffectedGradleProjectFiles(project: Project, filePath: String): Boolean {
    if (filePath.endsWith("/gradle.properties")) return true
    if (filePath.endsWith("/gradle-wrapper.properties")) return true

    if (filePath.endsWith(".gradle") || filePath.endsWith(".gradle.kts")) {
        if (ApplicationManager.getApplication().isUnitTestModeWithoutAffectedGradleProjectFilesCheck) {
            return true
        }

        return filePath.substringBeforeLast("/") in project.service<GradleScriptInputsWatcher>().getGradleProjectsRoots()
    }

    return false
}

fun getGradleScriptInputsStamp(
    project: Project,
    file: VirtualFile,
    givenKtFile: KtFile? = null,
    givenTimeStamp: Long = System.currentTimeMillis()
): GradleKotlinScriptConfigurationInputs? {
    if (!isGradleKotlinScript(file)) return null

    return runReadAction {
        val ktFile = givenKtFile ?: PsiManager.getInstance(project).findFile(file) as? KtFile

        if (ktFile != null) {
            val result = StringBuilder()
            ktFile.script?.blockExpression
                ?.getChildrenOfType<KtScriptInitializer>()
                ?.forEach {
                    val call = it.children.singleOrNull() as? KtCallExpression
                    val callRef = call?.firstChild?.text ?: return@forEach
                    if (callRef in sections) {
                        result.append(callRef)
                        val lambda = call.lambdaArguments.singleOrNull()
                        lambda?.accept(object : PsiRecursiveElementVisitor(false) {
                            override fun visitElement(element: PsiElement) {
                                super.visitElement(element)
                                when (element) {
                                    is PsiWhiteSpace -> if (element.text.contains("\n")) result.append("\n")
                                    is PsiComment -> {
                                    }
                                    is LeafPsiElement -> result.append(element.text)
                                }
                            }
                        })
                        result.append("\n")
                    }
                }

            GradleKotlinScriptConfigurationInputs(result.toString(), givenTimeStamp)
        } else null
    }
}

const val minimal_gradle_version_supported = "6.0"

fun kotlinDslScriptsModelImportSupported(currentGradleVersion: String): Boolean {
    return GradleVersion.version(currentGradleVersion) >= GradleVersion.version(minimal_gradle_version_supported)
}

fun useScriptConfigurationFromImportOnly(): Boolean {
    return Registry.`is`("kotlin.gradle.scripts.useIdeaProjectImport", false)
}

fun getGradleVersion(project: Project, externalProjectPath: String): String? {
    val localVersion = GradleLocalSettings.getInstance(project).getGradleVersion(externalProjectPath)
    if (localVersion != null) return localVersion

    return GradleSettings.getInstance(project).getLinkedProjectSettings(externalProjectPath)?.resolveGradleVersion()?.version
}

fun getGradleVersion(project: Project): String? {
    val projectSettings = getGradleProjectSettings(project).firstOrNull()

    if (projectSettings != null) {
        return getGradleVersion(projectSettings.externalProjectPath)
    }
    return null
}

fun getJavaHomeForGradleProject(project: Project): String? {
    val projectSettings = getGradleProjectSettings(project).firstOrNull() ?: return null

    val gradleExeSettings = ExternalSystemApiUtil.getExecutionSettings<GradleExecutionSettings>(
        project,
        projectSettings.externalProjectPath,
        GradleConstants.SYSTEM_ID
    )
    return gradleExeSettings.javaHome
}

fun getGradleProjectSettings(project: Project): Collection<GradleProjectSettings> {
    val gradleSettings = ExternalSystemApiUtil.getSettings(project, GradleConstants.SYSTEM_ID) as GradleSettings
    return gradleSettings.getLinkedProjectsSettings()
}
