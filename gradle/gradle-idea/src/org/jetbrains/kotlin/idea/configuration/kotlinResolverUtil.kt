/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.configuration

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.Notification
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.updateSettings.impl.pluginsAdvertisement.installAndEnable
import com.intellij.openapi.updateSettings.impl.pluginsAdvertisement.notificationGroup
import com.intellij.util.PlatformUtils
import org.jetbrains.kotlin.idea.KotlinIdeaGradleBundle

private var isNativeDebugSuggestionEnabled
    get() =
        PropertiesComponent.getInstance().getBoolean("isNativeDebugSuggestionEnabled", true)
    set(value) {
        PropertiesComponent.getInstance().setValue("isNativeDebugSuggestionEnabled", value)
    }

fun suggestNativeDebug(projectPath: String) {
    if (!PlatformUtils.isIdeaUltimate()) return

    val pluginId = PluginId.getId("com.intellij.nativeDebug")
    if (PluginManagerCore.isPluginInstalled(pluginId)) return

    if (!isNativeDebugSuggestionEnabled) return

    val projectManager = ProjectManager.getInstance()
    val project = projectManager.openProjects.firstOrNull { it.basePath == projectPath } ?: return

    val installAction = object : NotificationAction(
        KotlinIdeaGradleBundle.message("action.text.install")
    ) {
        override fun actionPerformed(e: AnActionEvent, notification: Notification) {
            installAndEnable(setOf(pluginId)) { notification.expire() }
        }
    }

    val dontShowAction = object : NotificationAction(
        KotlinIdeaGradleBundle.message("action.text.dontShowAgain")
    ) {
        override fun actionPerformed(e: AnActionEvent, notification: Notification) {
            isNativeDebugSuggestionEnabled = false
            notification.expire()
        }
    }

    val notification = notificationGroup.createNotification(
        KotlinIdeaGradleBundle.message("title.plugin.suggestion"),
        KotlinIdeaGradleBundle.message("notification.text.native.debug.provides.debugger.for.kotlin.native")
    )
    notification.addActions(listOf(installAction, dontShowAction))
    notification.notify(project)
}