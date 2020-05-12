/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea

import com.intellij.internal.statistic.beans.MetricEvent
import com.intellij.internal.statistic.eventLog.FeatureUsageData
import com.intellij.internal.statistic.service.fus.collectors.ProjectUsagesCollector
import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.idea.codeInsight.KotlinCodeInsightSettings
import org.jetbrains.kotlin.idea.codeInsight.KotlinCodeInsightWorkspaceSettings
import org.jetbrains.kotlin.idea.core.script.settings.KotlinScriptingSettings

class IDESettingsFUSCollector : ProjectUsagesCollector() {
    override fun getMetrics(project: Project): Set<MetricEvent> {
        val metrics = mutableSetOf<MetricEvent>()

        val scriptingAutoReloadEnabled = KotlinScriptingSettings.getInstance(project).isAutoReloadEnabled
        metrics.add(MetricEvent("scriptingAutoReloadEnabled", flagUsage(scriptingAutoReloadEnabled)))

        val settings: KotlinCodeInsightSettings = KotlinCodeInsightSettings.getInstance()
        val projectSettings: KotlinCodeInsightWorkspaceSettings = KotlinCodeInsightWorkspaceSettings.getInstance(project)

        metrics.add(MetricEvent("addUnambiguousImportsOnTheFly", flagUsage(settings.addUnambiguousImportsOnTheFly)))
        metrics.add(MetricEvent("optimizeImportsOnTheFly", flagUsage(projectSettings.optimizeImportsOnTheFly)))

        return metrics
    }

    private fun flagUsage(enabled: Boolean): FeatureUsageData {
        return FeatureUsageData()
            .addData("enabled", enabled)
            .addData("pluginVersion", KotlinPluginUtil.getPluginVersion())
    }

    override fun getGroupId() = "kotlin.ide.settings"
    override fun getVersion(): Int = 2
}
