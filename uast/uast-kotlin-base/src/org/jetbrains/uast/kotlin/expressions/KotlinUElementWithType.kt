/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.uast.kotlin

import com.intellij.psi.PsiType
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.uast.UExpression

interface KotlinUElementWithType : UExpression {
    val baseResolveProviderService: BaseKotlinUastResolveProviderService

    override fun getExpressionType(): PsiType? {
        val ktElement = sourcePsi as? KtExpression ?: return null
        return baseResolveProviderService.getType(ktElement, this)
    }
}
