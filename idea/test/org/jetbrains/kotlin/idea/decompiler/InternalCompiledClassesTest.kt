/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.decompiler

import com.intellij.psi.ClassFileViewProvider
import com.intellij.testFramework.LightProjectDescriptor
import org.jetbrains.kotlin.idea.test.PluginTestCaseBase
import org.jetbrains.kotlin.idea.test.SdkAndMockLibraryProjectDescriptor
import org.jetbrains.kotlin.test.JUnit3WithIdeaConfigurationRunner
import org.junit.Ignore
import org.junit.runner.RunWith

@RunWith(JUnit3WithIdeaConfigurationRunner::class)
@Ignore("has to be deleted as it seems a compiler test")
class InternalCompiledClassesTest : AbstractInternalCompiledClassesTest() {
    private val TEST_DATA_PATH = PluginTestCaseBase.getTestDataPathBase() + "/decompiler/internalClasses"

    fun testSyntheticClassesAreInvisible() = doTestNoPsiFilesAreBuiltForSyntheticClasses()

    fun testLocalClassesAreInvisible() = doTestNoPsiFilesAreBuiltForLocalClass()

    fun testInnerClassIsInvisible() = doTestNoPsiFilesAreBuiltFor("inner or nested class") {
        ClassFileViewProvider.isInnerClass(this)
    }

    override fun getProjectDescriptor(): LightProjectDescriptor {
        return SdkAndMockLibraryProjectDescriptor(TEST_DATA_PATH, /* withSources = */ false)
    }
}
