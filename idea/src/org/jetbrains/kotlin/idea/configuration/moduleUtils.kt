/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.configuration

import com.intellij.openapi.module.Module
import com.intellij.openapi.util.Key
import org.jetbrains.kotlin.idea.KotlinVersionVerbose
import org.jetbrains.kotlin.idea.util.buildNumber
import org.jetbrains.kotlin.psi.UserDataProperty

var Module.externalCompilerVersion: String? by UserDataProperty(Key.create("EXTERNAL_COMPILER_VERSION"))

fun Module.findExternalKotlinCompilerVersion(): KotlinVersionVerbose? {
    val externalCompilerVersion = (if (getBuildSystemType() == BuildSystemType.JPS) {
        buildNumber
    } else {
        this.externalCompilerVersion
    }) ?: return null

    return KotlinVersionVerbose.parse(externalCompilerVersion)
}

