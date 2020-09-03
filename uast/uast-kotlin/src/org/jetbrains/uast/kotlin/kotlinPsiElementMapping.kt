/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.uast.kotlin

import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.asJava.classes.KtLightClass
import org.jetbrains.kotlin.asJava.elements.*
import org.jetbrains.kotlin.psi.*
import org.jetbrains.uast.*
import org.jetbrains.uast.expressions.UInjectionHost
import org.jetbrains.uast.kotlin.psi.*
import org.jetbrains.uast.psi.UElementWithLocation
import org.jetbrains.uast.util.ClassSet
import org.jetbrains.uast.util.ClassSetImpl


internal fun canConvert(element: PsiElement, targets: Array<out Class<out UElement>>): Boolean {
    val elementCls = element.javaClass
    val originalCls = element.originalElement.javaClass
    return targets.any { getPossibleSourceTypes(it).let { elementCls in it || originalCls in it } }
}

internal fun getPossibleSourceTypes(uastType: Class<out UElement>) =
    possibleSourceTypes[uastType] ?: error("Kotlin UAST possibleSourceTypes misses value for $uastType")

/**
 * For every [UElement] subtype states from which [PsiElement] subtypes it can be obtained.
 *
 * This map is machine generated by `KotlinUastMappingsAccountantOverLargeProjectTest`
 */
@Suppress("DEPRECATION", "RemoveExplicitTypeArguments", "DuplicatedCode")
private val possibleSourceTypes = mapOf<Class<*>, ClassSet<PsiElement>>(
    UAnchorOwner::class.java to ClassSetImpl<PsiElement>(
        KtAnnotationEntry::class.java,
        KtCallExpression::class.java,
        KtClass::class.java,
        KtDestructuringDeclarationEntry::class.java,
        KtEnumEntry::class.java,
        KtFile::class.java,
        KtLightAnnotationForSourceEntry::class.java,
        KtLightClass::class.java,
        KtLightDeclaration::class.java,
        KtLightField::class.java,
        KtLightFieldForSourceDeclarationSupport::class.java,
        KtLightParameter::class.java,
        KtNamedFunction::class.java,
        KtObjectDeclaration::class.java,
        KtParameter::class.java,
        KtPrimaryConstructor::class.java,
        KtProperty::class.java,
        KtPropertyAccessor::class.java,
        KtSecondaryConstructor::class.java,
        KtTypeReference::class.java,
        UastFakeLightMethod::class.java,
        UastFakeLightPrimaryConstructor::class.java,
        UastKotlinPsiParameter::class.java,
        UastKotlinPsiParameterBase::class.java,
        UastKotlinPsiVariable::class.java
    ),
    UAnnotated::class.java to ClassSetImpl<PsiElement>(
        FakeFileForLightClass::class.java,
        KtAnnotatedExpression::class.java,
        KtArrayAccessExpression::class.java,
        KtBinaryExpression::class.java,
        KtBinaryExpressionWithTypeRHS::class.java,
        KtBlockExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtBreakExpression::class.java,
        KtCallExpression::class.java,
        KtCallableReferenceExpression::class.java,
        KtClass::class.java,
        KtClassBody::class.java,
        KtClassInitializer::class.java,
        KtClassLiteralExpression::class.java,
        KtCollectionLiteralExpression::class.java,
        KtConstantExpression::class.java,
        KtConstructorCalleeExpression::class.java,
        KtConstructorDelegationCall::class.java,
        KtConstructorDelegationReferenceExpression::class.java,
        KtContinueExpression::class.java,
        KtDelegatedSuperTypeEntry::class.java,
        KtDestructuringDeclaration::class.java,
        KtDestructuringDeclarationEntry::class.java,
        KtDoWhileExpression::class.java,
        KtDotQualifiedExpression::class.java,
        KtEnumEntry::class.java,
        KtEnumEntrySuperclassReferenceExpression::class.java,
        KtEscapeStringTemplateEntry::class.java,
        KtFile::class.java,
        KtForExpression::class.java,
        KtFunctionLiteral::class.java,
        KtIfExpression::class.java,
        KtIsExpression::class.java,
        KtLabelReferenceExpression::class.java,
        KtLabeledExpression::class.java,
        KtLambdaArgument::class.java,
        KtLambdaExpression::class.java,
        KtLightAnnotationForSourceEntry::class.java,
        KtLightClass::class.java,
        KtLightDeclaration::class.java,
        KtLightField::class.java,
        KtLightFieldForSourceDeclarationSupport::class.java,
        KtLightParameter::class.java,
        KtLiteralStringTemplateEntry::class.java,
        KtNameReferenceExpression::class.java,
        KtNamedFunction::class.java,
        KtObjectDeclaration::class.java,
        KtObjectLiteralExpression::class.java,
        KtOperationReferenceExpression::class.java,
        KtParameter::class.java,
        KtParameterList::class.java,
        KtParenthesizedExpression::class.java,
        KtPostfixExpression::class.java,
        KtPrefixExpression::class.java,
        KtPrimaryConstructor::class.java,
        KtProperty::class.java,
        KtPropertyAccessor::class.java,
        KtReturnExpression::class.java,
        KtSafeQualifiedExpression::class.java,
        KtScript::class.java,
        KtScriptInitializer::class.java,
        KtSecondaryConstructor::class.java,
        KtSimpleNameStringTemplateEntry::class.java,
        KtStringTemplateExpression::class.java,
        KtSuperExpression::class.java,
        KtSuperTypeCallEntry::class.java,
        KtThisExpression::class.java,
        KtThrowExpression::class.java,
        KtTryExpression::class.java,
        KtTypeAlias::class.java,
        KtTypeParameter::class.java,
        KtTypeReference::class.java,
        KtWhenConditionInRange::class.java,
        KtWhenConditionIsPattern::class.java,
        KtWhenConditionWithExpression::class.java,
        KtWhenEntry::class.java,
        KtWhenExpression::class.java,
        KtWhileExpression::class.java,
        UastFakeLightMethod::class.java,
        UastFakeLightPrimaryConstructor::class.java,
        UastKotlinPsiParameter::class.java,
        UastKotlinPsiParameterBase::class.java,
        UastKotlinPsiVariable::class.java
    ),
    UAnnotation::class.java to ClassSetImpl<PsiElement>(
        KtAnnotationEntry::class.java,
        KtCallExpression::class.java,
        KtLightAnnotationForSourceEntry::class.java
    ),
    UAnnotationEx::class.java to ClassSetImpl<PsiElement>(
        KtAnnotationEntry::class.java,
        KtCallExpression::class.java,
        KtLightAnnotationForSourceEntry::class.java
    ),
    UAnnotationMethod::class.java to ClassSetImpl<PsiElement>(
        KtLightDeclaration::class.java,
        KtParameter::class.java
    ),
    UAnonymousClass::class.java to ClassSetImpl<PsiElement>(
        KtLightClass::class.java,
        KtObjectDeclaration::class.java
    ),
    UArrayAccessExpression::class.java to ClassSetImpl<PsiElement>(
        KtArrayAccessExpression::class.java,
        KtBlockStringTemplateEntry::class.java
    ),
    UBinaryExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBinaryExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtStringTemplateExpression::class.java,
        KtWhenConditionInRange::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UBinaryExpressionWithType::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBinaryExpressionWithTypeRHS::class.java,
        KtIsExpression::class.java,
        KtWhenConditionIsPattern::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UBlockExpression::class.java to ClassSetImpl<PsiElement>(
        KtBlockExpression::class.java
    ),
    UBreakExpression::class.java to ClassSetImpl<PsiElement>(
        KtBreakExpression::class.java
    ),
    UCallExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtCallExpression::class.java,
        KtCollectionLiteralExpression::class.java,
        KtConstructorDelegationCall::class.java,
        KtEnumEntry::class.java,
        KtLightAnnotationForSourceEntry::class.java,
        KtLightField::class.java,
        KtObjectLiteralExpression::class.java,
        KtStringTemplateExpression::class.java,
        KtSuperTypeCallEntry::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UCallExpressionEx::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtCallExpression::class.java,
        KtCollectionLiteralExpression::class.java,
        KtConstructorDelegationCall::class.java,
        KtEnumEntry::class.java,
        KtLightAnnotationForSourceEntry::class.java,
        KtLightField::class.java,
        KtObjectLiteralExpression::class.java,
        KtStringTemplateExpression::class.java,
        KtSuperTypeCallEntry::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UCallableReferenceExpression::class.java to ClassSetImpl<PsiElement>(
        KtCallableReferenceExpression::class.java
    ),
    UCatchClause::class.java to ClassSetImpl<PsiElement>(
        KtCatchClause::class.java
    ),
    UClass::class.java to ClassSetImpl<PsiElement>(
        KtClass::class.java,
        KtFile::class.java,
        KtLightClass::class.java,
        KtObjectDeclaration::class.java
    ),
    UClassInitializer::class.java to ClassSetImpl<PsiElement>(
    ),
    UClassInitializerEx::class.java to ClassSetImpl<PsiElement>(
    ),
    UClassLiteralExpression::class.java to ClassSetImpl<PsiElement>(
        KtClassLiteralExpression::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UClassTypeSpecific::class.java to ClassSetImpl<PsiElement>(
        KtClass::class.java,
        KtFile::class.java,
        KtLightClass::class.java,
        KtObjectDeclaration::class.java
    ),
    UComment::class.java to ClassSetImpl<PsiElement>(
    ),
    UContinueExpression::class.java to ClassSetImpl<PsiElement>(
        KtContinueExpression::class.java
    ),
    UDeclaration::class.java to ClassSetImpl<PsiElement>(
        KtClass::class.java,
        KtDestructuringDeclarationEntry::class.java,
        KtEnumEntry::class.java,
        KtFile::class.java,
        KtLightClass::class.java,
        KtLightDeclaration::class.java,
        KtLightField::class.java,
        KtLightFieldForSourceDeclarationSupport::class.java,
        KtLightParameter::class.java,
        KtNamedFunction::class.java,
        KtObjectDeclaration::class.java,
        KtParameter::class.java,
        KtPrimaryConstructor::class.java,
        KtProperty::class.java,
        KtPropertyAccessor::class.java,
        KtSecondaryConstructor::class.java,
        KtTypeReference::class.java,
        UastFakeLightMethod::class.java,
        UastFakeLightPrimaryConstructor::class.java,
        UastKotlinPsiParameter::class.java,
        UastKotlinPsiParameterBase::class.java,
        UastKotlinPsiVariable::class.java
    ),
    UDeclarationEx::class.java to ClassSetImpl<PsiElement>(
        KtDestructuringDeclarationEntry::class.java,
        KtEnumEntry::class.java,
        KtLightField::class.java,
        KtLightFieldForSourceDeclarationSupport::class.java,
        KtLightParameter::class.java,
        KtParameter::class.java,
        KtProperty::class.java,
        KtTypeReference::class.java,
        UastKotlinPsiParameter::class.java,
        UastKotlinPsiParameterBase::class.java,
        UastKotlinPsiVariable::class.java
    ),
    UDeclarationsExpression::class.java to ClassSetImpl<PsiElement>(
        KtClass::class.java,
        KtDestructuringDeclaration::class.java,
        KtEnumEntry::class.java,
        KtFunctionLiteral::class.java,
        KtLightDeclaration::class.java,
        KtNamedFunction::class.java,
        KtObjectDeclaration::class.java,
        KtParameterList::class.java,
        KtPrimaryConstructor::class.java,
        KtSecondaryConstructor::class.java
    ),
    UDoWhileExpression::class.java to ClassSetImpl<PsiElement>(
        KtDoWhileExpression::class.java
    ),
    UElement::class.java to ClassSetImpl<PsiElement>(
        FakeFileForLightClass::class.java,
        KtAnnotatedExpression::class.java,
        KtAnnotationEntry::class.java,
        KtArrayAccessExpression::class.java,
        KtBinaryExpression::class.java,
        KtBinaryExpressionWithTypeRHS::class.java,
        KtBlockExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtBreakExpression::class.java,
        KtCallExpression::class.java,
        KtCallableReferenceExpression::class.java,
        KtCatchClause::class.java,
        KtClass::class.java,
        KtClassBody::class.java,
        KtClassInitializer::class.java,
        KtClassLiteralExpression::class.java,
        KtCollectionLiteralExpression::class.java,
        KtConstantExpression::class.java,
        KtConstructorCalleeExpression::class.java,
        KtConstructorDelegationCall::class.java,
        KtConstructorDelegationReferenceExpression::class.java,
        KtContinueExpression::class.java,
        KtDelegatedSuperTypeEntry::class.java,
        KtDestructuringDeclaration::class.java,
        KtDestructuringDeclarationEntry::class.java,
        KtDoWhileExpression::class.java,
        KtDotQualifiedExpression::class.java,
        KtEnumEntry::class.java,
        KtEnumEntrySuperclassReferenceExpression::class.java,
        KtEscapeStringTemplateEntry::class.java,
        KtFile::class.java,
        KtForExpression::class.java,
        KtFunctionLiteral::class.java,
        KtIfExpression::class.java,
        KtImportDirective::class.java,
        KtIsExpression::class.java,
        KtLabelReferenceExpression::class.java,
        KtLabeledExpression::class.java,
        KtLambdaArgument::class.java,
        KtLambdaExpression::class.java,
        KtLightAnnotationForSourceEntry::class.java,
        KtLightClass::class.java,
        KtLightDeclaration::class.java,
        KtLightField::class.java,
        KtLightFieldForSourceDeclarationSupport::class.java,
        KtLightParameter::class.java,
        KtLiteralStringTemplateEntry::class.java,
        KtNameReferenceExpression::class.java,
        KtNamedFunction::class.java,
        KtObjectDeclaration::class.java,
        KtObjectLiteralExpression::class.java,
        KtOperationReferenceExpression::class.java,
        KtParameter::class.java,
        KtParameterList::class.java,
        KtParenthesizedExpression::class.java,
        KtPostfixExpression::class.java,
        KtPrefixExpression::class.java,
        KtPrimaryConstructor::class.java,
        KtProperty::class.java,
        KtPropertyAccessor::class.java,
        KtReturnExpression::class.java,
        KtSafeQualifiedExpression::class.java,
        KtScript::class.java,
        KtScriptInitializer::class.java,
        KtSecondaryConstructor::class.java,
        KtSimpleNameStringTemplateEntry::class.java,
        KtStringTemplateExpression::class.java,
        KtSuperExpression::class.java,
        KtSuperTypeCallEntry::class.java,
        KtThisExpression::class.java,
        KtThrowExpression::class.java,
        KtTryExpression::class.java,
        KtTypeAlias::class.java,
        KtTypeParameter::class.java,
        KtTypeReference::class.java,
        KtWhenConditionInRange::class.java,
        KtWhenConditionIsPattern::class.java,
        KtWhenConditionWithExpression::class.java,
        KtWhenEntry::class.java,
        KtWhenExpression::class.java,
        KtWhileExpression::class.java,
        LeafPsiElement::class.java,
        UastFakeLightMethod::class.java,
        UastFakeLightPrimaryConstructor::class.java,
        UastKotlinPsiParameter::class.java,
        UastKotlinPsiParameterBase::class.java,
        UastKotlinPsiVariable::class.java
    ),
    UElementWithLocation::class.java to ClassSetImpl<PsiElement>(
    ),
    UEnumConstant::class.java to ClassSetImpl<PsiElement>(
        KtEnumEntry::class.java,
        KtLightField::class.java
    ),
    UEnumConstantEx::class.java to ClassSetImpl<PsiElement>(
        KtEnumEntry::class.java,
        KtLightField::class.java
    ),
    UExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtArrayAccessExpression::class.java,
        KtBinaryExpression::class.java,
        KtBinaryExpressionWithTypeRHS::class.java,
        KtBlockExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtBreakExpression::class.java,
        KtCallExpression::class.java,
        KtCallableReferenceExpression::class.java,
        KtClass::class.java,
        KtClassBody::class.java,
        KtClassInitializer::class.java,
        KtClassLiteralExpression::class.java,
        KtCollectionLiteralExpression::class.java,
        KtConstantExpression::class.java,
        KtConstructorCalleeExpression::class.java,
        KtConstructorDelegationCall::class.java,
        KtConstructorDelegationReferenceExpression::class.java,
        KtContinueExpression::class.java,
        KtDelegatedSuperTypeEntry::class.java,
        KtDestructuringDeclaration::class.java,
        KtDoWhileExpression::class.java,
        KtDotQualifiedExpression::class.java,
        KtEnumEntry::class.java,
        KtEnumEntrySuperclassReferenceExpression::class.java,
        KtEscapeStringTemplateEntry::class.java,
        KtForExpression::class.java,
        KtFunctionLiteral::class.java,
        KtIfExpression::class.java,
        KtIsExpression::class.java,
        KtLabelReferenceExpression::class.java,
        KtLabeledExpression::class.java,
        KtLambdaArgument::class.java,
        KtLambdaExpression::class.java,
        KtLightAnnotationForSourceEntry::class.java,
        KtLightDeclaration::class.java,
        KtLightField::class.java,
        KtLiteralStringTemplateEntry::class.java,
        KtNameReferenceExpression::class.java,
        KtNamedFunction::class.java,
        KtObjectDeclaration::class.java,
        KtObjectLiteralExpression::class.java,
        KtOperationReferenceExpression::class.java,
        KtParameter::class.java,
        KtParameterList::class.java,
        KtParenthesizedExpression::class.java,
        KtPostfixExpression::class.java,
        KtPrefixExpression::class.java,
        KtPrimaryConstructor::class.java,
        KtProperty::class.java,
        KtPropertyAccessor::class.java,
        KtReturnExpression::class.java,
        KtSafeQualifiedExpression::class.java,
        KtScript::class.java,
        KtScriptInitializer::class.java,
        KtSecondaryConstructor::class.java,
        KtSimpleNameStringTemplateEntry::class.java,
        KtStringTemplateExpression::class.java,
        KtSuperExpression::class.java,
        KtSuperTypeCallEntry::class.java,
        KtThisExpression::class.java,
        KtThrowExpression::class.java,
        KtTryExpression::class.java,
        KtTypeAlias::class.java,
        KtTypeParameter::class.java,
        KtTypeReference::class.java,
        KtWhenConditionInRange::class.java,
        KtWhenConditionIsPattern::class.java,
        KtWhenConditionWithExpression::class.java,
        KtWhenEntry::class.java,
        KtWhenExpression::class.java,
        KtWhileExpression::class.java
    ),
    UExpressionList::class.java to ClassSetImpl<PsiElement>(
        KtBinaryExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtClassBody::class.java,
        KtDelegatedSuperTypeEntry::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UField::class.java to ClassSetImpl<PsiElement>(
        KtEnumEntry::class.java,
        KtLightField::class.java,
        KtLightFieldForSourceDeclarationSupport::class.java,
        KtParameter::class.java,
        KtProperty::class.java
    ),
    UFieldEx::class.java to ClassSetImpl<PsiElement>(
        KtLightFieldForSourceDeclarationSupport::class.java,
        KtParameter::class.java,
        KtProperty::class.java
    ),
    UFile::class.java to ClassSetImpl<PsiElement>(
        FakeFileForLightClass::class.java,
        KtFile::class.java
    ),
    UForEachExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtForExpression::class.java
    ),
    UForExpression::class.java to ClassSetImpl<PsiElement>(
    ),
    UIdentifier::class.java to ClassSetImpl<PsiElement>(
        LeafPsiElement::class.java
    ),
    UIfExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtIfExpression::class.java
    ),
    UImportStatement::class.java to ClassSetImpl<PsiElement>(
        KtImportDirective::class.java
    ),
    UInjectionHost::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtStringTemplateExpression::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UInstanceExpression::class.java to ClassSetImpl<PsiElement>(
        KtBlockStringTemplateEntry::class.java,
        KtSimpleNameStringTemplateEntry::class.java,
        KtSuperExpression::class.java,
        KtThisExpression::class.java
    ),
    UJumpExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBreakExpression::class.java,
        KtContinueExpression::class.java,
        KtReturnExpression::class.java
    ),
    ULabeled::class.java to ClassSetImpl<PsiElement>(
        KtBlockStringTemplateEntry::class.java,
        KtLabeledExpression::class.java,
        KtSimpleNameStringTemplateEntry::class.java,
        KtSuperExpression::class.java,
        KtThisExpression::class.java
    ),
    ULabeledExpression::class.java to ClassSetImpl<PsiElement>(
        KtLabeledExpression::class.java
    ),
    ULambdaExpression::class.java to ClassSetImpl<PsiElement>(
        KtFunctionLiteral::class.java,
        KtLambdaArgument::class.java,
        KtLambdaExpression::class.java,
        KtLightDeclaration::class.java,
        KtNamedFunction::class.java,
        KtPrimaryConstructor::class.java
    ),
    ULiteralExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtConstantExpression::class.java,
        KtEscapeStringTemplateEntry::class.java,
        KtLiteralStringTemplateEntry::class.java,
        KtStringTemplateExpression::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    ULocalVariable::class.java to ClassSetImpl<PsiElement>(
        KtDestructuringDeclarationEntry::class.java,
        KtProperty::class.java,
        UastKotlinPsiVariable::class.java
    ),
    ULocalVariableEx::class.java to ClassSetImpl<PsiElement>(
        KtDestructuringDeclarationEntry::class.java,
        KtProperty::class.java,
        UastKotlinPsiVariable::class.java
    ),
    ULoopExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtDoWhileExpression::class.java,
        KtForExpression::class.java,
        KtWhileExpression::class.java
    ),
    UMethod::class.java to ClassSetImpl<PsiElement>(
        KtClass::class.java,
        KtLightDeclaration::class.java,
        KtNamedFunction::class.java,
        KtParameter::class.java,
        KtPrimaryConstructor::class.java,
        KtProperty::class.java,
        KtPropertyAccessor::class.java,
        KtSecondaryConstructor::class.java,
        UastFakeLightMethod::class.java,
        UastFakeLightPrimaryConstructor::class.java
    ),
    UMethodTypeSpecific::class.java to ClassSetImpl<PsiElement>(
        KtClass::class.java,
        KtLightDeclaration::class.java,
        KtNamedFunction::class.java,
        KtParameter::class.java,
        KtPrimaryConstructor::class.java,
        KtProperty::class.java,
        KtPropertyAccessor::class.java,
        KtSecondaryConstructor::class.java,
        UastFakeLightMethod::class.java,
        UastFakeLightPrimaryConstructor::class.java
    ),
    UMultiResolvable::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtAnnotationEntry::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtCallExpression::class.java,
        KtCallableReferenceExpression::class.java,
        KtCollectionLiteralExpression::class.java,
        KtConstructorDelegationCall::class.java,
        KtDotQualifiedExpression::class.java,
        KtEnumEntry::class.java,
        KtImportDirective::class.java,
        KtLightAnnotationForSourceEntry::class.java,
        KtLightField::class.java,
        KtObjectLiteralExpression::class.java,
        KtPostfixExpression::class.java,
        KtSafeQualifiedExpression::class.java,
        KtSimpleNameStringTemplateEntry::class.java,
        KtStringTemplateExpression::class.java,
        KtSuperExpression::class.java,
        KtSuperTypeCallEntry::class.java,
        KtThisExpression::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UNamedExpression::class.java to ClassSetImpl<PsiElement>(
    ),
    UObjectLiteralExpression::class.java to ClassSetImpl<PsiElement>(
        KtObjectLiteralExpression::class.java,
        KtSuperTypeCallEntry::class.java
    ),
    UParameter::class.java to ClassSetImpl<PsiElement>(
        KtLightParameter::class.java,
        KtParameter::class.java,
        KtTypeReference::class.java,
        UastKotlinPsiParameter::class.java,
        UastKotlinPsiParameterBase::class.java
    ),
    UParameterEx::class.java to ClassSetImpl<PsiElement>(
        KtLightParameter::class.java,
        KtParameter::class.java,
        KtTypeReference::class.java,
        UastKotlinPsiParameter::class.java,
        UastKotlinPsiParameterBase::class.java
    ),
    UParenthesizedExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtParenthesizedExpression::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UPolyadicExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBinaryExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtStringTemplateExpression::class.java,
        KtWhenConditionInRange::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UPostfixExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtPostfixExpression::class.java
    ),
    UPrefixExpression::class.java to ClassSetImpl<PsiElement>(
        KtBlockStringTemplateEntry::class.java,
        KtPrefixExpression::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UQualifiedReferenceExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtDotQualifiedExpression::class.java,
        KtSafeQualifiedExpression::class.java,
        KtStringTemplateExpression::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UReferenceExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtCallableReferenceExpression::class.java,
        KtDotQualifiedExpression::class.java,
        KtEnumEntrySuperclassReferenceExpression::class.java,
        KtLabelReferenceExpression::class.java,
        KtNameReferenceExpression::class.java,
        KtOperationReferenceExpression::class.java,
        KtSafeQualifiedExpression::class.java,
        KtSimpleNameStringTemplateEntry::class.java,
        KtStringTemplateExpression::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UResolvable::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtAnnotationEntry::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtCallExpression::class.java,
        KtCallableReferenceExpression::class.java,
        KtCollectionLiteralExpression::class.java,
        KtConstructorDelegationCall::class.java,
        KtDotQualifiedExpression::class.java,
        KtEnumEntry::class.java,
        KtEnumEntrySuperclassReferenceExpression::class.java,
        KtImportDirective::class.java,
        KtLabelReferenceExpression::class.java,
        KtLightAnnotationForSourceEntry::class.java,
        KtLightField::class.java,
        KtNameReferenceExpression::class.java,
        KtObjectLiteralExpression::class.java,
        KtOperationReferenceExpression::class.java,
        KtPostfixExpression::class.java,
        KtSafeQualifiedExpression::class.java,
        KtSimpleNameStringTemplateEntry::class.java,
        KtStringTemplateExpression::class.java,
        KtSuperExpression::class.java,
        KtSuperTypeCallEntry::class.java,
        KtThisExpression::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UReturnExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtReturnExpression::class.java
    ),
    USimpleNameReferenceExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtEnumEntrySuperclassReferenceExpression::class.java,
        KtLabelReferenceExpression::class.java,
        KtNameReferenceExpression::class.java,
        KtOperationReferenceExpression::class.java,
        KtSimpleNameStringTemplateEntry::class.java,
        KtStringTemplateExpression::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    USuperExpression::class.java to ClassSetImpl<PsiElement>(
        KtSuperExpression::class.java
    ),
    USwitchClauseExpression::class.java to ClassSetImpl<PsiElement>(
        KtWhenEntry::class.java
    ),
    USwitchClauseExpressionWithBody::class.java to ClassSetImpl<PsiElement>(
        KtWhenEntry::class.java
    ),
    USwitchExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtWhenExpression::class.java
    ),
    UThisExpression::class.java to ClassSetImpl<PsiElement>(
        KtBlockStringTemplateEntry::class.java,
        KtSimpleNameStringTemplateEntry::class.java,
        KtThisExpression::class.java
    ),
    UThrowExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtThrowExpression::class.java
    ),
    UTryExpression::class.java to ClassSetImpl<PsiElement>(
        KtBlockStringTemplateEntry::class.java,
        KtTryExpression::class.java
    ),
    UTypeReferenceExpression::class.java to ClassSetImpl<PsiElement>(
        KtTypeReference::class.java
    ),
    UUnaryExpression::class.java to ClassSetImpl<PsiElement>(
        KtAnnotatedExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtPostfixExpression::class.java,
        KtPrefixExpression::class.java,
        KtWhenConditionWithExpression::class.java
    ),
    UVariable::class.java to ClassSetImpl<PsiElement>(
        KtDestructuringDeclarationEntry::class.java,
        KtEnumEntry::class.java,
        KtLightField::class.java,
        KtLightFieldForSourceDeclarationSupport::class.java,
        KtLightParameter::class.java,
        KtParameter::class.java,
        KtProperty::class.java,
        KtTypeReference::class.java,
        UastKotlinPsiParameter::class.java,
        UastKotlinPsiParameterBase::class.java,
        UastKotlinPsiVariable::class.java
    ),
    UVariableEx::class.java to ClassSetImpl<PsiElement>(
        KtDestructuringDeclarationEntry::class.java,
        KtEnumEntry::class.java,
        KtLightField::class.java,
        KtLightFieldForSourceDeclarationSupport::class.java,
        KtLightParameter::class.java,
        KtParameter::class.java,
        KtProperty::class.java,
        KtTypeReference::class.java,
        UastKotlinPsiParameter::class.java,
        UastKotlinPsiParameterBase::class.java,
        UastKotlinPsiVariable::class.java
    ),
    UWhileExpression::class.java to ClassSetImpl<PsiElement>(
        KtWhileExpression::class.java
    ),
    UYieldExpression::class.java to ClassSetImpl<PsiElement>(
    ),
    UastEmptyExpression::class.java to ClassSetImpl<PsiElement>(
        KtBinaryExpression::class.java,
        KtBlockStringTemplateEntry::class.java,
        KtClass::class.java,
        KtEnumEntry::class.java,
        KtLightAnnotationForSourceEntry::class.java,
        KtObjectDeclaration::class.java,
        KtStringTemplateExpression::class.java
    ),
    UnknownKotlinExpression::class.java to ClassSetImpl<PsiElement>(
        KtClassInitializer::class.java,
        KtConstructorCalleeExpression::class.java,
        KtConstructorDelegationReferenceExpression::class.java,
        KtLightDeclaration::class.java,
        KtParameter::class.java,
        KtPropertyAccessor::class.java,
        KtScript::class.java,
        KtScriptInitializer::class.java,
        KtTypeAlias::class.java,
        KtTypeParameter::class.java
    )
)

