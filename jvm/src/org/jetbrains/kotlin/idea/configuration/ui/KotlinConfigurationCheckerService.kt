/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.idea.configuration.ui

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationsConfiguration
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.externalSystem.service.project.manage.ProjectDataImportListener
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import org.jetbrains.kotlin.idea.KotlinJvmBundle
import org.jetbrains.kotlin.idea.configuration.getModulesWithKotlinFiles
import org.jetbrains.kotlin.idea.configuration.notifyOutdatedBundledCompilerIfNecessary
import org.jetbrains.kotlin.idea.core.KotlinPluginDisposable
import org.jetbrains.kotlin.idea.project.getAndCacheLanguageLevelByDependencies
import org.jetbrains.kotlin.idea.util.application.getServiceSafe
import java.util.concurrent.atomic.AtomicInteger

class KotlinConfigurationCheckerStartupActivity : StartupActivity {
    override fun runActivity(project: Project) {
        NotificationsConfiguration.getNotificationsConfiguration()
            .register(
                KotlinConfigurationCheckerService.CONFIGURE_NOTIFICATION_GROUP_ID,
                NotificationDisplayType.STICKY_BALLOON, true
            )

        val connection = project.messageBus.connect(KotlinPluginDisposable.getInstance(project))
        connection.subscribe(ProjectDataImportListener.TOPIC, ProjectDataImportListener {
            notifyOutdatedBundledCompilerIfNecessary(project)
        })

        KotlinConfigurationCheckerService.getInstance(project).performProjectPostOpenActions()
    }
}

class KotlinConfigurationCheckerService(val project: Project) {
    private val syncDepth = AtomicInteger()

    fun performProjectPostOpenActions() {
        val task = object : Task.Backgroundable(project, KotlinJvmBundle.message("configure.kotlin.language.settings"), false) {
            override fun run(indicator: ProgressIndicator) {
                val ktModules = getModulesWithKotlinFiles(project)
                indicator.isIndeterminate = false
                for ((idx, module) in ktModules.withIndex()) {
                    if (project.isDisposed) return
                    indicator.fraction = 1.0 * idx / ktModules.size
                    runReadAction {
                        if (module.isDisposed) return@runReadAction
                        indicator.text2 = KotlinJvmBundle.message("configure.kotlin.language.settings.0.module", module.name)
                        module.getAndCacheLanguageLevelByDependencies()
                    }
                }
            }
        }
        ProgressManager.getInstance().run(task)
    }

    val isSyncing: Boolean get() = syncDepth.get() > 0

    fun syncStarted() {
        syncDepth.incrementAndGet()
    }

    fun syncDone() {
        syncDepth.decrementAndGet()
    }

    companion object {
        const val CONFIGURE_NOTIFICATION_GROUP_ID = "Configure Kotlin in Project"

        fun getInstance(project: Project): KotlinConfigurationCheckerService = project.getServiceSafe()

    }
}
