/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem

import org.jetbrains.annotations.NonNls
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.GradleByNameTaskAccessIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.GradleConfigureTaskIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.GradleIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.irsList
import org.jetbrains.kotlin.tools.projectWizard.plugins.printer.GradlePrinter

fun runTaskIrs(@NonNls mainClass: String, classPath: BuildSystemIR? = null) = irsList {
    +ApplicationPluginIR(mainClass)
    +ApplicationConfigurationIR(mainClass)
    if (classPath != null) {
        +GradleConfigureTaskIR(GradleByNameTaskAccessIR("run", "JavaExec")) {
            "classpath" assign classPath
        }
    }
}

class ApplicationConfigurationIR(private val mainClass: String): GradleIR, FreeIR {
    override fun GradlePrinter.renderGradle() {
        sectionCall("application", needIndent = true) {
            when (dsl) {
                GradlePrinter.GradleDsl.KOTLIN -> {
                    call("mainClass.set") { +mainClass.quotified}
                }
                GradlePrinter.GradleDsl.GROOVY -> {
                    assignment("mainClassName") { +mainClass.quotified}
                }
            }
        }
    }
}