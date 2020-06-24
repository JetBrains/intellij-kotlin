/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.checkers

import com.intellij.codeInspection.ex.EntryPointsManagerBase
import com.intellij.testFramework.LightProjectDescriptor
import org.junit.internal.runners.JUnit38ClassRunner
import org.jetbrains.kotlin.test.TestMetadata
import org.jetbrains.kotlin.test.TestRoot
import org.junit.runner.RunWith

@TestRoot("idea")
@TestMetadata("testData/checker/custom")
@RunWith(JUnit38ClassRunner::class)
class PsiCheckerCustomTest : AbstractPsiCheckerTest() {

    @TestMetadata("noUnusedParameterWhenCustom.kt")
    fun testNoUnusedParameterWhenCustom() {
        val testAnnotation = "MyTestAnnotation"
        EntryPointsManagerBase.getInstance(project).ADDITIONAL_ANNOTATIONS.add(testAnnotation)
        try {
            doTest("noUnusedParameterWhenCustom.kt")
        } finally {
            EntryPointsManagerBase.getInstance(project).ADDITIONAL_ANNOTATIONS.remove(testAnnotation)
        }
    }

    fun testConflictingOverloadsMultifile1() {
        doTest(
            "conflictingOverloadsMultifile1a.kt",
            "conflictingOverloadsMultifile1b.kt"
        )
    }

    fun testConflictingOverloadsMultifile2() {
        doTest(
            "conflictingOverloadsMultifile2a.kt",
            "conflictingOverloadsMultifile2b.kt"
        )
    }

    override fun getProjectDescriptor(): LightProjectDescriptor = getProjectDescriptorFromTestName()
}