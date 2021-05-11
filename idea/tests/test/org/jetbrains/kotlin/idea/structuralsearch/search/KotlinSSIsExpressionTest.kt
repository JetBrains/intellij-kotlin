/*
 * Copyright 2000-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.structuralsearch.search

import org.jetbrains.kotlin.idea.structuralsearch.KotlinSSResourceInspectionTest

class KotlinSSIsExpressionTest : KotlinSSResourceInspectionTest() {
    override fun getBasePath(): String = "isExpression"

    fun testIs() { doTest("'_ is '_") }

    fun testNegatedIs() { doTest("'_ !is '_") }
}