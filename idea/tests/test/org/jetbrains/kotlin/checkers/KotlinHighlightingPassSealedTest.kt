/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.checkers

import com.intellij.testFramework.LightProjectDescriptor
import org.jetbrains.kotlin.idea.test.JUnit3RunnerWithInners
import org.jetbrains.kotlin.idea.test.TestRoot
import org.jetbrains.kotlin.test.TestMetadata
import org.junit.runner.RunWith


@TestRoot("idea/tests")
@TestMetadata("testData/checker/sealed")
@RunWith(JUnit3RunnerWithInners::class)
class KotlinHighlightingPassSealedTest : AbstractKotlinHighlightingPassTest() {

    fun testOutsideOfPackageInheritors() {
        doTest(
            "SealedOutsidePackageInheritors.kt", // opened in the test editor
            "SealedDeclaration.kt"
        )
    }

    fun testWhenExhaustiveness() {
        doTest(
            "SealedInheritors.kt", // opened in the test editor
            "SealedDeclaration.kt"
        )
    }

    override fun getProjectDescriptor(): LightProjectDescriptor = getProjectDescriptorFromTestName()
}