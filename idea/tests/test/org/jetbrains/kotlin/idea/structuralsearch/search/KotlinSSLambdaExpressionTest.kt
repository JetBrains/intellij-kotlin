package org.jetbrains.kotlin.idea.structuralsearch.search

import org.jetbrains.kotlin.idea.structuralsearch.KotlinSSResourceInspectionTest

class KotlinSSLambdaExpressionTest : KotlinSSResourceInspectionTest() {
    override fun getBasePath(): String = "lambdaExpression"

    fun testEmpty() { doTest("{}") }

    fun testBody() { doTest("{ '_Expr+ }") }

    fun testExplicitIt() { doTest("{ it -> '_Expr+ }") }

    fun testIdentity() { doTest("{ '_x -> '_x }") }

    fun testAnnotated() { doTest("@Ann { println() }") }
}