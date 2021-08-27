package org.jetbrains.kotlin.idea.run

import com.intellij.execution.application.JvmMainMethodRunConfigurationOptions
import com.intellij.execution.configurations.ConfigurationTypeUtil.findConfigurationType
import com.intellij.execution.configurations.JavaRunConfigurationModule
import com.intellij.execution.configurations.SimpleConfigurationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.NotNullLazyValue
import org.jetbrains.kotlin.idea.KotlinIcons
import org.jetbrains.kotlin.idea.KotlinRunConfigurationsBundle.message

class KotlinRunConfigurationType : SimpleConfigurationType(
    "JetRunConfigurationType",
    message("language.name.kotlin"),
    message("language.name.kotlin"),
    NotNullLazyValue.createValue { KotlinIcons.SMALL_LOGO }
) {

    override fun createTemplateConfiguration(project: Project) =
        KotlinRunConfiguration("", JavaRunConfigurationModule(project, true), this)

    override fun getOptionsClass() = JvmMainMethodRunConfigurationOptions::class.java

    companion object {
        val instance: KotlinRunConfigurationType
            get() = findConfigurationType(KotlinRunConfigurationType::class.java)
    }
}
