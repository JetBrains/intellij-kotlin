/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.uast.kotlin

import com.intellij.openapi.util.registry.Registry
import com.intellij.psi.*
import org.jetbrains.annotations.TestOnly
import org.jetbrains.kotlin.asJava.LightClassUtil
import org.jetbrains.kotlin.asJava.classes.KtLightClass
import org.jetbrains.kotlin.asJava.elements.*
import org.jetbrains.kotlin.asJava.findFacadeClass
import org.jetbrains.kotlin.asJava.toLightClass
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.utils.addToStdlib.safeAs
import org.jetbrains.uast.*
import org.jetbrains.uast.expressions.UInjectionHost
import org.jetbrains.uast.kotlin.expressions.*
import org.jetbrains.uast.kotlin.psi.*

internal object KotlinConverter : BaseKotlinConverter {

    var forceUInjectionHost = Registry.`is`("kotlin.uast.force.uinjectionhost", false)
        @TestOnly
        set(value) {
            field = value
        }

    override fun convertExpression(
        expression: KtExpression,
        givenParent: UElement?,
        requiredTypes: Array<out Class<out UElement>>
    ): UExpression? {
        fun <P : PsiElement> build(ctor: (P, UElement?) -> UExpression): () -> UExpression? {
            return {
                @Suppress("UNCHECKED_CAST")
                ctor(expression as P, givenParent)
            }
        }

        return with(requiredTypes) { when (expression) {
            is KtVariableDeclaration -> expr<UDeclarationsExpression>(build(::convertVariablesDeclaration))

            is KtStringTemplateExpression -> {
                when {
                    forceUInjectionHost || requiredTypes.contains(UInjectionHost::class.java) -> {
                        expr<UInjectionHost> { KotlinStringTemplateUPolyadicExpression(expression, givenParent) }
                    }
                    expression.entries.isEmpty() -> {
                        expr<ULiteralExpression> { KotlinStringULiteralExpression(expression, givenParent, "") }
                    }
                    expression.entries.size == 1 -> {
                        convertStringTemplateEntry(expression.entries[0], givenParent, requiredTypes)
                    }
                    else -> {
                        expr<KotlinStringTemplateUPolyadicExpression> {
                            KotlinStringTemplateUPolyadicExpression(expression, givenParent)
                        }
                    }
                }
            }
            is KtDestructuringDeclaration -> expr<UDeclarationsExpression> {
                val declarationsExpression = KotlinUDestructuringDeclarationExpression(givenParent, expression)
                declarationsExpression.apply {
                    val tempAssignment = KotlinULocalVariable(
                        UastKotlinPsiVariable.create(expression, declarationsExpression),
                        expression,
                        declarationsExpression
                    )
                    val destructuringAssignments = expression.entries.mapIndexed { i, entry ->
                        val psiFactory = KtPsiFactory(expression.project)
                        val initializer = psiFactory.createAnalyzableExpression("${tempAssignment.name}.component${i + 1}()",
                                                                                expression.containingFile)
                        initializer.destructuringDeclarationInitializer = true
                        KotlinULocalVariable(
                            UastKotlinPsiVariable.create(entry, tempAssignment.javaPsi, declarationsExpression, initializer),
                            entry,
                            declarationsExpression
                        )
                    }
                    declarations = listOf(tempAssignment) + destructuringAssignments
                }
            }
            is KtLabeledExpression -> expr<ULabeledExpression>(build(::KotlinULabeledExpression))
            is KtClassLiteralExpression -> expr<UClassLiteralExpression>(build(::KotlinUClassLiteralExpression))
            is KtObjectLiteralExpression -> expr<UObjectLiteralExpression>(build(::KotlinUObjectLiteralExpression))
            is KtDotQualifiedExpression -> expr<UQualifiedReferenceExpression>(build(::KotlinUQualifiedReferenceExpression))
            is KtSafeQualifiedExpression -> expr<UQualifiedReferenceExpression>(build(::KotlinUSafeQualifiedExpression))
            is KtSimpleNameExpression -> expr<USimpleNameReferenceExpression>(build(::KotlinUSimpleReferenceExpression))
            is KtCallExpression -> expr<UCallExpression>(build(::KotlinUFunctionCallExpression))
            is KtCollectionLiteralExpression -> expr<UCallExpression>(build(::KotlinUCollectionLiteralExpression))
            is KtBinaryExpression -> {
                if (expression.operationToken == KtTokens.ELVIS) {
                    expr<UExpressionList>(build(::createElvisExpression))
                }
                else expr<UBinaryExpression>(build(::KotlinUBinaryExpression))
            }
            is KtParenthesizedExpression -> expr<UParenthesizedExpression>(build(::KotlinUParenthesizedExpression))
            is KtPrefixExpression -> expr<UPrefixExpression>(build(::KotlinUPrefixExpression))
            is KtPostfixExpression -> expr<UPostfixExpression>(build(::KotlinUPostfixExpression))
            is KtThisExpression -> expr<UThisExpression>(build(::KotlinUThisExpression))
            is KtSuperExpression -> expr<USuperExpression>(build(::KotlinUSuperExpression))
            is KtCallableReferenceExpression -> expr<UCallableReferenceExpression>(build(::KotlinUCallableReferenceExpression))
            is KtIsExpression -> expr<UBinaryExpressionWithType>(build(::KotlinUTypeCheckExpression))
            is KtIfExpression -> expr<UIfExpression>(build(::KotlinUIfExpression))
            is KtWhileExpression -> expr<UWhileExpression>(build(::KotlinUWhileExpression))
            is KtDoWhileExpression -> expr<UDoWhileExpression>(build(::KotlinUDoWhileExpression))
            is KtForExpression -> expr<UForEachExpression>(build(::KotlinUForEachExpression))
            is KtWhenExpression -> expr<USwitchExpression>(build(::KotlinUSwitchExpression))
            is KtBreakExpression -> expr<UBreakExpression>(build(::KotlinUBreakExpression))
            is KtContinueExpression -> expr<UContinueExpression>(build(::KotlinUContinueExpression))
            is KtReturnExpression -> expr<UReturnExpression>(build(::KotlinUReturnExpression))
            is KtThrowExpression -> expr<UThrowExpression>(build(::KotlinUThrowExpression))
            is KtBlockExpression -> expr<UBlockExpression> {
                if (expression.parent is KtFunctionLiteral
                    && expression.parent.parent is KtLambdaExpression
                    && givenParent !is KotlinULambdaExpression
                ) {
                    KotlinULambdaExpression(expression.parent.parent as KtLambdaExpression, givenParent).body
                } else
                    KotlinUBlockExpression(expression, givenParent)
            }
            is KtConstantExpression -> expr<ULiteralExpression>(build(::KotlinULiteralExpression))
            is KtTryExpression -> expr<UTryExpression>(build(::KotlinUTryExpression))
            is KtArrayAccessExpression -> expr<UArrayAccessExpression>(build(::KotlinUArrayAccessExpression))
            is KtLambdaExpression -> expr<ULambdaExpression>(build(::KotlinULambdaExpression))
            is KtBinaryExpressionWithTypeRHS -> expr<UBinaryExpressionWithType>(build(::KotlinUBinaryExpressionWithType))
            is KtClassOrObject -> expr<UDeclarationsExpression> {
                expression.toLightClass()?.let { lightClass ->
                    KotlinUDeclarationsExpression(givenParent).apply {
                        declarations = listOf(KotlinUClass.create(lightClass, this))
                    }
                } ?: UastEmptyExpression(givenParent)
            }
            is KtFunction -> if (expression.name.isNullOrEmpty()) {
                expr<ULambdaExpression>(build(::createLocalFunctionLambdaExpression))
            }
            else {
                expr<UDeclarationsExpression>(build(::createLocalFunctionDeclaration))
            }
            is KtAnnotatedExpression -> {
                expression.baseExpression
                    ?.let { convertExpression(it, givenParent, requiredTypes) }
                    ?: expr<UExpression>(build(::UnknownKotlinExpression))
            }

            else -> expr<UExpression>(build(::UnknownKotlinExpression))
        }}
    }

    override fun convertDeclaration(
        element: PsiElement,
        givenParent: UElement?,
        requiredTypes: Array<out Class<out UElement>>
    ): UElement? {
        val original = element.originalElement

        fun <P : PsiElement> build(ctor: (P, UElement?) -> UElement): () -> UElement? = {
            @Suppress("UNCHECKED_CAST")
            ctor(original as P, givenParent)
        }

        fun <P : PsiElement, K : KtElement> buildKt(ktElement: K, ctor: (P, K, UElement?) -> UElement): () -> UElement? = {
            @Suppress("UNCHECKED_CAST")
            ctor(original as P, ktElement, givenParent)
        }

        fun <P : PsiElement, K : KtElement> buildKtOpt(ktElement: K?, ctor: (P, K?, UElement?) -> UElement): () -> UElement? = {
            @Suppress("UNCHECKED_CAST")
            ctor(original as P, ktElement, givenParent)
        }

        fun Array<out Class<out UElement>>.convertToUField(original: PsiField, kotlinOrigin: KtElement?): UElement? =
            if (original is PsiEnumConstant)
                el<UEnumConstant>(buildKtOpt(kotlinOrigin, ::KotlinUEnumConstant))
            else
                el<UField>(buildKtOpt(kotlinOrigin, ::KotlinUField))

        return with(requiredTypes) {
            when (original) {
                is KtLightMethod -> el<UMethod>(build(KotlinUMethod::create))
                is UastFakeLightMethod -> el<UMethod> {
                    val ktFunction = original.original
                    if (ktFunction.isLocal)
                        convertDeclaration(ktFunction, givenParent, requiredTypes)
                    else
                        KotlinUMethodWithFakeLightDelegate(ktFunction, original, givenParent)
                }
                is UastFakeLightPrimaryConstructor ->
                    convertFakeLightConstructorAlternatives(original, givenParent, requiredTypes).firstOrNull()
                is KtLightClass -> when (original.kotlinOrigin) {
                    is KtEnumEntry -> el<UEnumConstant> {
                        convertEnumEntry(original.kotlinOrigin as KtEnumEntry, givenParent)
                    }
                    else -> el<UClass> { KotlinUClass.create(original, givenParent) }
                }
                is KtLightField -> convertToUField(original, original.kotlinOrigin)
                is KtLightFieldForSourceDeclarationSupport ->
                    // KtLightFieldForDecompiledDeclaration is not a KtLightField
                    convertToUField(original, original.kotlinOrigin)
                is KtLightParameter -> el<UParameter>(buildKtOpt(original.kotlinOrigin, ::KotlinUParameter))
                is UastKotlinPsiParameter -> el<UParameter>(buildKt(original.ktParameter, ::KotlinUParameter))
                is UastKotlinPsiParameterBase<*> -> el<UParameter> {
                    original.ktOrigin.safeAs<KtTypeReference>()?.let { convertReceiverParameter(it) }
                }
                is UastKotlinPsiVariable -> el<ULocalVariable>(buildKt(original.ktElement, ::KotlinULocalVariable))

                is KtEnumEntry -> el<UEnumConstant> {
                    convertEnumEntry(original, givenParent)
                }
                is KtClassOrObject -> convertClassOrObject(original, givenParent, this).firstOrNull()
                is KtFunction ->
                    if (original.isLocal) {
                        el<ULambdaExpression> {
                            val parent = original.parent
                            if (parent is KtLambdaExpression) {
                                KotlinULambdaExpression(parent, givenParent) // your parent is the ULambdaExpression
                            } else if (original.name.isNullOrEmpty()) {
                                createLocalFunctionLambdaExpression(original, givenParent)
                            } else {
                                val uDeclarationsExpression = createLocalFunctionDeclaration(original, givenParent)
                                val localFunctionVar = uDeclarationsExpression.declarations.single() as KotlinLocalFunctionUVariable
                                localFunctionVar.uastInitializer
                            }
                        }
                    } else {
                        el<UMethod> {
                            val lightMethod = LightClassUtil.getLightClassMethod(original)
                            if (lightMethod != null)
                                convertDeclaration(lightMethod, givenParent, requiredTypes)
                            else {
                                val ktLightClass = getLightClassForFakeMethod(original) ?: return null
                                KotlinUMethodWithFakeLightDelegate(original, ktLightClass, givenParent)
                            }
                        }
                    }

                is KtPropertyAccessor -> el<UMethod> {
                    val lightMethod = LightClassUtil.getLightClassAccessorMethod(original) ?: return null
                    convertDeclaration(lightMethod, givenParent, requiredTypes)
                }

                is KtProperty ->
                    if (original.isLocal) {
                        convertPsiElement(original, givenParent, requiredTypes)
                    } else {
                        convertNonLocalProperty(original, givenParent, requiredTypes).firstOrNull()
                    }

                is KtParameter -> convertParameter(original, givenParent, this).firstOrNull()

                is KtFile -> convertKtFile(original, givenParent, this).firstOrNull()
                is FakeFileForLightClass -> el<UFile> { KotlinUFile(original.navigationElement, kotlinUastPlugin) }
                is KtAnnotationEntry -> el<UAnnotation>(build(::convertAnnotation))
                is KtCallExpression ->
                    if (requiredTypes.isAssignableFrom(KotlinUNestedAnnotation::class.java) &&
                        !requiredTypes.isAssignableFrom(UCallExpression::class.java)
                    ) {
                        el<UAnnotation> { KotlinUNestedAnnotation.create(original, givenParent) }
                    } else null
                is KtLightAnnotationForSourceEntry -> convertDeclarationOrElement(original.kotlinOrigin, givenParent, requiredTypes)
                is KtDelegatedSuperTypeEntry -> el<KotlinSupertypeDelegationUExpression> {
                    KotlinSupertypeDelegationUExpression(original, givenParent)
                }
                else -> null
            }
        }
    }

    internal fun convertFakeLightConstructorAlternatives(
        original: UastFakeLightPrimaryConstructor,
        givenParent: UElement?,
        expectedTypes: Array<out Class<out UElement>>
    ): Sequence<UElement> {
        return expectedTypes.accommodate(
            alternative { convertDeclaration(original.original, givenParent, expectedTypes) as? UClass },
            alternative { KotlinConstructorUMethod(original.original, original, original.original, givenParent) }
        )
    }

    private fun getLightClassForFakeMethod(original: KtFunction): KtLightClass? {
        if (original.isLocal) return null
        return getContainingLightClass(original)
    }

    fun convertParameter(
        element: KtParameter,
        givenParent: UElement?,
        expectedTypes: Array<out Class<out UElement>>
    ): Sequence<UElement> = expectedTypes.accommodate(
        alternative uParam@{
            val lightMethod = when (val ownerFunction = element.ownerFunction) {
                is KtFunction -> LightClassUtil.getLightClassMethod(ownerFunction)
                    ?: getLightClassForFakeMethod(ownerFunction)
                        ?.takeIf { !it.isAnnotationType }
                        ?.let { UastFakeLightMethod(ownerFunction, it) }
                is KtPropertyAccessor -> LightClassUtil.getLightClassAccessorMethod(ownerFunction)
                else -> null
            } ?: return@uParam null
            val lightParameter = lightMethod.parameterList.parameters.find { it.name == element.name } ?: return@uParam null
            KotlinUParameter(lightParameter, element, givenParent)
        },
        alternative catch@{
            val uCatchClause = element.parent?.parent?.safeAs<KtCatchClause>()?.toUElementOfType<UCatchClause>() ?: return@catch null
            uCatchClause.parameters.firstOrNull { it.sourcePsi == element }
        },
        *convertToPropertyAlternatives(LightClassUtil.getLightClassPropertyMethods(element), givenParent)
    )

    fun convertKtFile(
        element: KtFile,
        givenParent: UElement?,
        requiredTypes: Array<out Class<out UElement>>
    ): Sequence<UElement> = requiredTypes.accommodate(
        alternative { KotlinUFile(element, kotlinUastPlugin) },
        alternative { element.findFacadeClass()?.let { KotlinUClass.create(it, givenParent) } }
    )

    internal fun KtPsiFactory.createAnalyzableExpression(text: String, context: PsiElement): KtExpression =
        createAnalyzableProperty("val x = $text", context).initializer ?: error("Failed to create expression from text: '$text'")

    internal fun KtPsiFactory.createAnalyzableProperty(text: String, context: PsiElement): KtProperty =
        createAnalyzableDeclaration(text, context)

    internal fun <TDeclaration : KtDeclaration> KtPsiFactory.createAnalyzableDeclaration(text: String, context: PsiElement): TDeclaration {
        val file = createAnalyzableFile("dummy.kt", text, context)
        val declarations = file.declarations
        assert(declarations.size == 1) { "${declarations.size} declarations in $text" }
        @Suppress("UNCHECKED_CAST")
        return declarations.first() as TDeclaration
    }
}
