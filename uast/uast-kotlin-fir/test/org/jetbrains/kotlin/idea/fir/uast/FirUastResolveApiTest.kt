/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.uast

import com.intellij.testFramework.TestDataPath
import org.jetbrains.kotlin.idea.test.JUnit3RunnerWithInners
import org.jetbrains.kotlin.test.TestMetadata
import org.jetbrains.uast.*
import org.jetbrains.kotlin.idea.fir.uast.env.kotlin.AbstractFirUastTest
import org.jetbrains.uast.test.common.kotlin.UastResolveApiTestBase
import org.junit.runner.RunWith

@RunWith(JUnit3RunnerWithInners::class)
class FirUastResolveApiTest : AbstractFirUastTest() {
    override val isFirUastPlugin: Boolean = true

    override fun check(filePath: String, file: UFile) {
        // Bogus
    }

    @TestMetadata("plugins/uast-kotlin-fir/testData/declaration")
    @TestDataPath("\$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners::class)
    class Declaration : AbstractFirUastTest(), UastResolveApiTestBase {
        override val isFirUastPlugin: Boolean = true

        override fun check(filePath: String, file: UFile) {
            // Bogus
        }

        @TestMetadata("doWhile.kt")
        fun testDoWhile() {
            doCheck("../uast-kotlin-fir/testData/declaration/doWhile.kt", ::checkCallbackForDoWhile)
        }

        @TestMetadata("if.kt")
        fun testIf() {
            doCheck("../uast-kotlin-fir/testData/declaration/if.kt", ::checkCallbackForIf)
        }

        // TODO: once call is supported, test labeledExpression.kt for labeled this and super
    }

    @TestMetadata("../uast-kotlin/testData")
    @TestDataPath("\$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners::class)
    class Legacy : AbstractFirUastTest(), UastResolveApiTestBase {
        override val isFirUastPlugin: Boolean = true

        override fun check(filePath: String, file: UFile) {
            // Bogus
        }

        @TestMetadata("MethodReference.kt")
        fun testMethodReference() {
            doCheck("../uast-kotlin/testData/MethodReference.kt", ::checkCallbackForMethodReference)
        }

        @TestMetadata("Imports.kt")
        fun testImports() {
            doCheck("../uast-kotlin/testData/Imports.kt", ::checkCallbackForImports)
        }

        @TestMetadata("ReceiverFun.kt")
        fun testReceiverFun() {
            doCheck("../uast-kotlin/testData/ReceiverFun.kt", ::checkCallbackForReceiverFun)
        }
    }
}
