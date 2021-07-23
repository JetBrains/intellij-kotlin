/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.uast.kotlin

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiType
import org.jetbrains.kotlin.idea.frontend.api.KtTypeArgumentWithVariance
import org.jetbrains.kotlin.idea.frontend.api.analyseForUast
import org.jetbrains.kotlin.idea.frontend.api.types.*
import org.jetbrains.kotlin.idea.references.mainReference
import org.jetbrains.kotlin.load.kotlin.TypeMappingMode
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType
import org.jetbrains.kotlin.types.typeUtil.TypeNullability
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UExpression
import org.jetbrains.uast.UastErrorType
import org.jetbrains.uast.kotlin.internal.toPsiMethod
import org.jetbrains.uast.kotlin.psi.UastKotlinPsiParameterBase

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

    override fun getArgumentForParameter(ktCallElement: KtCallElement, index: Int, parent: UElement): UExpression? {
        TODO("Not yet implemented")
    }

    override fun getImplicitReturn(ktLambdaExpression: KtLambdaExpression, parent: UElement): KotlinUImplicitReturnExpression? {
        val lastExpression = ktLambdaExpression.bodyExpression?.statements?.lastOrNull() ?: return null
        // Skip _explicit_ return.
        if (lastExpression is KtReturnExpression) return null
        analyseForUast(ktLambdaExpression) {
            // TODO: Should check an explicit, expected return type as well
            //  e.g., val y: () -> Unit = { 1 } // the lambda return type is Int, but we won't add an implicit return here.
            val returnType = ktLambdaExpression.functionLiteral.getAnonymousFunctionSymbol().annotatedType.type
            val returnUnitOrNothing = returnType.isUnit || returnType.isNothing
            return if (returnUnitOrNothing) null else
                KotlinUImplicitReturnExpression(parent).apply {
                    returnExpression = baseKotlinConverter.convertOrEmpty(lastExpression, this)
                }
        }
    }

    override fun getImplicitParameters(ktLambdaExpression: KtLambdaExpression, parent: UElement): List<KotlinUParameter> {
        analyseForUast(ktLambdaExpression) {
            return ktLambdaExpression.functionLiteral.getAnonymousFunctionSymbol().valueParameters.map { p ->
                KotlinUParameter(
                    UastKotlinPsiParameterBase(
                        name = p.name.asString(),
                        // TODO: implicit parameter type
                        type = UastErrorType,
                        parent = ktLambdaExpression,
                        ktOrigin = ktLambdaExpression,
                        language = ktLambdaExpression.language,
                        isVarArgs = p.isVararg,
                        ktDefaultValue = null
                    ),
                    null,
                    parent
                )
            }
        }
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

    override fun getCommonSupertype(left: KtExpression, right: KtExpression, uExpression: UExpression): PsiType? {
        val ktElement = uExpression.sourcePsi as? KtExpression ?: return null
        analyseForUast(ktElement) {
            return commonSuperType(listOf(left, right), ktElement, TypeMappingMode.DEFAULT_UAST)
        }
    }

    override fun getType(ktExpression: KtExpression, parent: UElement): PsiType? {
        analyseForUast(ktExpression) {
            return ktExpression.getPsiType(TypeMappingMode.DEFAULT_UAST)
        }
    }

    override fun getType(ktDeclaration: KtDeclaration, parent: UElement): PsiType? {
        analyseForUast(ktDeclaration) {
            return ktDeclaration.getPsiType(TypeMappingMode.DEFAULT_UAST)
        }
    }

    override fun getFunctionType(ktFunction: KtFunction, parent: UElement): PsiType? {
        analyseForUast(ktFunction) {
            return ktFunction.getPsiType(TypeMappingMode.DEFAULT_UAST)
        }
    }

    override fun getFunctionalInterfaceType(uLambdaExpression: KotlinULambdaExpression): PsiType? {
        // TODO("Not yet implemented")
        return null
    }

    override fun nullability(psiElement: PsiElement): TypeNullability? {
        if (psiElement is KtTypeReference) {
            analyseForUast(psiElement) {
                psiElement.getKtType()?.nullability()?.let { return it }
            }
        }
        if (psiElement is KtCallableDeclaration) {
            psiElement.typeReference?.let { typeReference ->
                analyseForUast(typeReference) {
                    typeReference.getKtType()?.nullability()?.let { return it }
                }
            }
        }
        if (psiElement is KtProperty) {
            psiElement.initializer?.let { propertyInitializer ->
                analyseForUast(propertyInitializer) {
                    propertyInitializer.getKtType().nullability()?.let { return it }
                }
            }
            psiElement.delegateExpression?.let { delegatedExpression ->
                analyseForUast(delegatedExpression) {
                    val typeArgument = (delegatedExpression.getKtType() as? KtNonErrorClassType)?.typeArguments?.firstOrNull()
                    (typeArgument as? KtTypeArgumentWithVariance)?.type?.nullability()?.let { return it }
                }
            }
        }
        psiElement.getParentOfType<KtProperty>(false)?.let { property ->
            property.typeReference?.let { typeReference ->
                analyseForUast(typeReference) {
                    typeReference.getKtType()?.nullability()
                }
            } ?:
            property.initializer?.let { propertyInitializer ->
                analyseForUast(propertyInitializer) {
                    propertyInitializer.getKtType().nullability()
                }
            }
        }?.let { return it }
        return null
    }

    private fun KtType.nullability(): TypeNullability? {
        if (this !is KtTypeWithNullability) return null
        return when (this.nullability) {
            KtTypeNullability.NON_NULLABLE -> TypeNullability.NOT_NULL
            KtTypeNullability.NULLABLE -> TypeNullability.NULLABLE
        }
    }

    override fun evaluate(uExpression: UExpression): Any? {
        val ktExpression = uExpression.sourcePsi as? KtExpression ?: return null
        analyseForUast(ktExpression) {
            return ktExpression.evaluate()?.value
        }
    }
}
