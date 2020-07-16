/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.decompiler.textBuilder

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiRecursiveElementVisitor
import org.jetbrains.kotlin.idea.test.*
import org.jetbrains.kotlin.psi.psiUtil.getElementTextWithContext
import org.jetbrains.kotlin.test.KotlinCompilerStandalone
import org.jetbrains.kotlin.test.KotlinTestUtils
import java.io.File

abstract class AbstractDecompiledTextBaseTest(
    private val baseDirectory: String,
    private val isJsLibrary: Boolean = false,
    private val allowKotlinPackage: Boolean = false,
    private val withRuntime: Boolean = false
) : KotlinLightCodeInsightFixtureTestCase() {
    protected companion object {
        const val TEST_PACKAGE = "test"
    }

    protected abstract fun getFileToDecompile(): VirtualFile

    protected abstract fun checkPsiFile(psiFile: PsiFile)

    protected abstract fun textToCheck(psiFile: PsiFile): String

    protected open fun checkStubConsistency(file: VirtualFile, decompiledText: String) {}

    protected val mockSourcesBase = File(PluginTestCaseBase.getTestDataPathBase(), baseDirectory)

    private lateinit var mockLibraryFacility: MockLibraryFacility

    override fun setUp() {
        super.setUp()

        mockLibraryFacility = MockLibraryFacility(
            source = File(mockSourcesBase, getTestName(false)),
            attachSources = false,
            platform = run {
                if (isJsLibrary)
                    KotlinCompilerStandalone.Platform.JavaScript(MockLibraryFacility.MOCK_LIBRARY_NAME, TEST_PACKAGE)
                else
                    KotlinCompilerStandalone.Platform.Jvm()
            },
            options = if (allowKotlinPackage) listOf("-Xallow-kotlin-package") else emptyList()
        )

        mockLibraryFacility.setUp(module)
    }

    override fun tearDown() {
        mockLibraryFacility.tearDown(module)
        super.tearDown()
    }

    fun doTest(path: String) {
        val fileToDecompile = getFileToDecompile()
        val psiFile = PsiManager.getInstance(project).findFile(fileToDecompile)!!
        checkPsiFile(psiFile)

        val checkedText = textToCheck(psiFile)

        KotlinTestUtils.assertEqualsToFile(File("$path.expected.kt"), checkedText)

        checkStubConsistency(fileToDecompile, checkedText)

        checkThatFileWasParsedCorrectly(psiFile)
    }

    private fun checkThatFileWasParsedCorrectly(clsFile: PsiFile) {
        clsFile.accept(object : PsiRecursiveElementVisitor() {
            override fun visitErrorElement(element: PsiErrorElement) {
                fail("Decompiled file should not contain error elements!\n${element.getElementTextWithContext()}")
            }
        })
    }
}
