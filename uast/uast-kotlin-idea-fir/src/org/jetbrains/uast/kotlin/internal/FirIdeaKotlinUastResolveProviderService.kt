/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.uast.kotlin.internal

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiType
import org.jetbrains.kotlin.psi.KtDoubleColonExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UExpression
import org.jetbrains.uast.kotlin.BaseKotlinConverter
import org.jetbrains.uast.kotlin.BaseKotlinUastResolveProviderService

class FirIdeaKotlinUastResolveProviderService : BaseKotlinUastResolveProviderService {
    override fun isJvmElement(psiElement: PsiElement): Boolean = psiElement.isJvmElement
    override val baseKotlinConverter: BaseKotlinConverter
        get() = TODO("Not yet implemented")

    override fun convertParent(uElement: UElement): UElement? {
        TODO("Not yet implemented")
    }

    override fun getReferenceVariants(ktExpression: KtExpression, nameHint: String): Sequence<PsiElement> {
        TODO("Not yet implemented")
    }

    override fun resolveToDeclaration(ktExpression: KtExpression): PsiElement? {
        TODO("Not yet implemented")
    }

    override fun resolveToType(ktTypeReference: KtTypeReference, source: UElement): PsiType? {
        TODO("Not yet implemented")
    }

    override fun getDoubleColonReceiverType(ktDoubleColonExpression: KtDoubleColonExpression, source: UElement): PsiType? {
        TODO("Not yet implemented")
    }

    override fun getExpressionType(uExpression: UExpression): PsiType? {
        TODO("Not yet implemented")
    }

    override fun evaluate(uExpression: UExpression): Any? {
        TODO("Not yet implemented")
    }
}
