/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:Suppress("UnstableApiUsage")

package org.jetbrains.kotlin.idea.scripting.gradle

import com.intellij.diff.util.DiffUtil
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.externalSystem.importing.ImportSpecBuilder
import com.intellij.openapi.externalSystem.util.ExternalSystemApiUtil
import com.intellij.openapi.externalSystem.util.ExternalSystemUtil
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.testFramework.LightVirtualFileBase
import org.jetbrains.kotlin.idea.KotlinIcons
import org.jetbrains.kotlin.idea.KotlinIdeaGradleBundle
import org.jetbrains.kotlin.idea.scripting.gradle.importing.KotlinDslScriptModelResolver
import org.jetbrains.kotlin.psi.UserDataProperty
import org.jetbrains.kotlin.scripting.definitions.findScriptDefinition
import org.jetbrains.plugins.gradle.service.project.GradlePartialResolverPolicy
import org.jetbrains.plugins.gradle.service.project.GradleProjectResolverExtension
import org.jetbrains.plugins.gradle.settings.GradleProjectSettings
import org.jetbrains.plugins.gradle.util.GradleConstants
import java.util.function.Predicate

fun runPartialGradleImport(project: Project) {
    val gradleSettings = ExternalSystemApiUtil.getSettings(project, GradleConstants.SYSTEM_ID)
    val projectSettings = gradleSettings.getLinkedProjectsSettings()
        .filterIsInstance<GradleProjectSettings>()
        .firstOrNull() ?: return

    ExternalSystemUtil.refreshProject(
        projectSettings.externalProjectPath,
        ImportSpecBuilder(project, GradleConstants.SYSTEM_ID)
            .projectResolverPolicy(
                GradlePartialResolverPolicy(Predicate<GradleProjectResolverExtension?> { it is KotlinDslScriptModelResolver })
            )
    )
}

private var Project.notificationPanel: Boolean?
        by UserDataProperty<Project, Boolean>(Key.create("load.script.configuration.panel"))


fun showNotificationForProjectImport(project: Project, callback: () -> Unit) {
    project.notificationPanel = true
    (ActionManager.getInstance().getAction("LoadConfigurationAction") as LoadConfigurationAction).onClick = callback
}

fun hideNotificationForProjectImport(project: Project): Boolean {
    project.notificationPanel = false
    return true
}

class LoadConfigurationAction : AnAction(
    KotlinIdeaGradleBundle.message("action.text.load.script.configurations"),
    KotlinIdeaGradleBundle.message("action.description.load.script.configurations"),
    KotlinIcons.LOAD_SCRIPT_CONFIGURATION
) {

    var onClick = {}

    override fun actionPerformed(e: AnActionEvent) {
        onClick()
    }

    override fun update(e: AnActionEvent) {
        ensureValidActionVisibility(e)

        e.presentation.description = KotlinIdeaGradleBundle.message("action.description.load.script.configurations")
    }

    private fun ensureValidActionVisibility(e: AnActionEvent) {
        if (e.project?.notificationPanel != true) {
            e.presentation.isVisible = false
            return
        }
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        when {
            DiffUtil.isDiffEditor(editor) -> e.presentation.isVisible = false
            !editor.isScriptEditor() -> e.presentation.isVisible = false
        }
    }

    private fun Editor.isScriptEditor(): Boolean {
        val documentManager = FileDocumentManager.getInstance()
        val virtualFile = documentManager.getFile(document)
        if (virtualFile is LightVirtualFileBase) return false
        if (virtualFile == null || !virtualFile.isValid) return false

        val project = project ?: return false
        return virtualFile.findScriptDefinition(project) != null
    }
}