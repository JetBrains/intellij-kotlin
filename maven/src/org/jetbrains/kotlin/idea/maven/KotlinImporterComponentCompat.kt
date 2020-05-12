/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.maven

import com.intellij.openapi.module.Module
import org.jetbrains.kotlin.idea.util.application.getServiceSafe

// BUNCH: 192
internal val Module.kotlinImporterComponent: KotlinImporterComponent
    get() = this.getServiceSafe()
