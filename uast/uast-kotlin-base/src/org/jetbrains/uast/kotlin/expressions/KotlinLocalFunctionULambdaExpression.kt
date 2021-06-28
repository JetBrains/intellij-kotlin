package org.jetbrains.uast.kotlin.expressions

import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.uast.UElement
import org.jetbrains.uast.ULambdaExpression
import org.jetbrains.uast.kotlin.KotlinAbstractUExpression

abstract class KotlinLocalFunctionULambdaExpression(
    override val sourcePsi: KtFunction,
    givenParent: UElement?
) : KotlinAbstractUExpression(givenParent), ULambdaExpression