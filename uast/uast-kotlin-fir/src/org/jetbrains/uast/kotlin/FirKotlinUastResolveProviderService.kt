/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.uast.kotlin

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiType
import org.jetbrains.kotlin.idea.frontend.api.analyseForUast
import org.jetbrains.kotlin.idea.references.mainReference
import org.jetbrains.kotlin.load.kotlin.TypeMappingMode
import org.jetbrains.kotlin.psi.*
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UExpression
import org.jetbrains.uast.kotlin.internal.toPsiMethod

interface FirKotlinUastResolveProviderService : BaseKotlinUastResolveProviderService {
    override val baseKotlinConverter: BaseKotlinConverter
        get() = FirKotlinConverter

    override fun convertParent(uElement: UElement): UElement? {
        // TODO
        return null
    }

    override fun convertParent(uElement: UElement, parent: PsiElement?): UElement? {
        TODO("Not yet implemented")
    }

    override fun resolveCall(ktElement: KtElement): PsiMethod? {
        when (ktElement) {
            is KtBinaryExpression -> {
                analyseForUast(ktElement) {
                    return ktElement.resolveCall()?.toPsiMethod()
                }
            }
            is KtUnaryExpression -> {
                analyseForUast(ktElement) {
                    return ktElement.resolveCall()?.toPsiMethod()
                }
            }
            else ->
                return null
        }
    }

    override fun resolveToDeclaration(ktExpression: KtExpression): PsiElement? {
        when (ktExpression) {
            is KtExpressionWithLabel -> {
                analyseForUast(ktExpression) {
                    return ktExpression.getTargetLabel()?.mainReference?.resolve()
                }
            }
            is KtReferenceExpression -> {
                analyseForUast(ktExpression) {
                    return ktExpression.mainReference.resolve()
                }
            }
            else ->
                return null
        }
    }

    override fun resolveToType(ktTypeReference: KtTypeReference, source: UElement): PsiType? {
        analyseForUast(ktTypeReference) {
            return ktTypeReference.getPsiType(TypeMappingMode.DEFAULT_UAST)
        }
    }

    override fun getDoubleColonReceiverType(ktDoubleColonExpression: KtDoubleColonExpression, source: UElement): PsiType? {
        analyseForUast(ktDoubleColonExpression) {
            return ktDoubleColonExpression.getReceiverPsiType(TypeMappingMode.DEFAULT_UAST)
        }
    }

    override fun getExpressionType(uExpression: UExpression): PsiType? {
        val ktExpression = uExpression.sourcePsi as? KtExpression ?: return null
        analyseForUast(ktExpression) {
            return ktExpression.getPsiType(TypeMappingMode.DEFAULT_UAST)
        }
    }

    override fun getType(ktExpression: KtExpression, parent: UElement): PsiType? {
        analyseForUast(ktExpression) {
            return ktExpression.getPsiType(TypeMappingMode.DEFAULT_UAST)
        }
    }

    override fun getType(ktDeclaration: KtDeclaration, parent: UElement): PsiType? {
        // TODO("Not yet implemented")
        return null
    }

    override fun getFunctionType(ktFunction: KtFunction, parent: UElement): PsiType? {
        // TODO("Not yet implemented")
        return null
    }

    override fun evaluate(uExpression: UExpression): Any? {
        val ktExpression = uExpression.sourcePsi as? KtExpression ?: return null
        analyseForUast(ktExpression) {
            return ktExpression.evaluate()?.value
        }
    }
}
