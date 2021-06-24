/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.uast.kotlin.expressions

import org.jetbrains.kotlin.psi.KtBinaryExpression
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UastBinaryOperator
import org.jetbrains.uast.kotlin.KotlinAbstractUBinaryExpression

class FirKotlinUBinaryExpression(
    override val sourcePsi: KtBinaryExpression,
    givenParent: UElement?
) : KotlinAbstractUBinaryExpression(sourcePsi, givenParent) {

    override fun handleBitwiseOperators(): UastBinaryOperator {
        val other = UastBinaryOperator.OTHER
        val resolvedOperator = resolveOperator() ?: return other
        return BITWISE_OPERATORS[resolvedOperator.name] ?: other
    }
}
