/*
 * Copyright 2000-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.structuralsearch.search

import org.jetbrains.kotlin.idea.structuralsearch.KotlinSSResourceInspectionTest

class KotlinSSTypeReferenceTest : KotlinSSResourceInspectionTest() {
    override fun getBasePath(): String = "typeReference"

    fun testAny() { doTest("fun '_('_ : '_) { '_* }") }

    fun testFqType() { doTest("fun '_('_ : kotlin.Int) { '_* }") }

    fun testFunctionType() { doTest("fun '_('_ : ('_) -> '_) { '_* }") }

    fun testNullableType() { doTest("fun '_('_ : '_ ?) { '_* }") }
    
    fun testFqTextFilter() { doTest("""fun '_('_ : '_:[regex( kotlin\.Int )])""") }

    fun testStandaloneNullable() { doTest("Int?") }

    fun testStandaloneParameter() { doTest("Array<Int>") }
}