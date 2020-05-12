/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.scripting.gradle.importing

import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskId
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskNotificationListenerAdapter
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskType
import org.jetbrains.kotlin.idea.framework.GRADLE_SYSTEM_ID
import org.jetbrains.kotlin.idea.scripting.gradle.getJavaHomeForGradleProject

class KotlinDslSyncListener : ExternalSystemTaskNotificationListenerAdapter() {
    override fun onEnd(id: ExternalSystemTaskId) {
        if (id.type != ExternalSystemTaskType.RESOLVE_PROJECT || id.projectSystemId != GRADLE_SYSTEM_ID) {
            return
        }

        val project = id.findProject() ?: return

        val models: List<KotlinDslScriptModel> = project.kotlinDslModels
        project.kotlinDslModels = arrayListOf()

        if (models.isEmpty()) return

        val javaHome = getJavaHomeForGradleProject(project)

        saveScriptModels(project, id, javaHome, models)
    }
}
