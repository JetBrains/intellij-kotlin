/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.slicer

import com.intellij.psi.PsiCall
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.slicer.SliceUsage
import com.intellij.usageView.UsageInfo
import org.jetbrains.kotlin.asJava.namedUnwrappedElement
import org.jetbrains.kotlin.builtins.functions.FunctionInvokeDescriptor
import org.jetbrains.kotlin.cfg.pseudocode.instructions.Instruction
import org.jetbrains.kotlin.cfg.pseudocode.instructions.eval.*
import org.jetbrains.kotlin.cfg.pseudocode.instructions.jumps.ReturnValueInstruction
import org.jetbrains.kotlin.cfg.pseudocodeTraverser.TraversalOrder
import org.jetbrains.kotlin.cfg.pseudocodeTraverser.traverse
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.descriptors.impl.AnonymousFunctionDescriptor
import org.jetbrains.kotlin.descriptors.impl.SyntheticFieldDescriptor
import org.jetbrains.kotlin.idea.caches.resolve.*
import org.jetbrains.kotlin.idea.findUsages.handlers.SliceUsageProcessor
import org.jetbrains.kotlin.idea.refactoring.changeSignature.KotlinValVar
import org.jetbrains.kotlin.idea.refactoring.changeSignature.toValVar
import org.jetbrains.kotlin.idea.references.KtPropertyDelegationMethodsReference
import org.jetbrains.kotlin.idea.references.ReferenceAccess
import org.jetbrains.kotlin.idea.references.readWriteAccessWithFullExpression
import org.jetbrains.kotlin.idea.search.declarationsSearch.HierarchySearchRequest
import org.jetbrains.kotlin.idea.search.declarationsSearch.searchOverriders
import org.jetbrains.kotlin.idea.util.actualsForExpected
import org.jetbrains.kotlin.idea.util.isExpectDeclaration
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.forEachDescendantOfType
import org.jetbrains.kotlin.psi.psiUtil.getParentOfTypeAndBranch
import org.jetbrains.kotlin.psi.psiUtil.getStrictParentOfType
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.model.DefaultValueArgument
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument
import org.jetbrains.kotlin.resolve.descriptorUtil.isExtension
import org.jetbrains.kotlin.resolve.lazy.BodyResolveMode
import org.jetbrains.kotlin.resolve.scopes.receivers.ExpressionReceiver
import org.jetbrains.kotlin.resolve.scopes.receivers.ImplicitReceiver
import org.jetbrains.kotlin.resolve.source.getPsi
import org.jetbrains.kotlin.util.OperatorNameConventions

class InflowSlicer(
    element: KtElement,
    processor: SliceUsageProcessor,
    parentUsage: KotlinSliceUsage
) : Slicer(element, processor, parentUsage) {

    override fun processChildren(forcedExpressionMode: Boolean) {
        if (forcedExpressionMode) {
            (element as? KtExpression)?.let { processExpression(it) }
            return
        }

        when (element) {
            is KtProperty -> processProperty(element)

            // include overriders only when invoked on the parameter declaration
            is KtParameter -> processParameter(parameter = element, includeOverriders = parentUsage.parent == null)

            is KtDeclarationWithBody -> element.processBody()

            is KtTypeReference -> {
                val parent = element.parent
                require(parent is KtCallableDeclaration)
                require(element == parent.receiverTypeReference)
                // include overriders only when invoked on receiver type in the declaration
                processExtensionReceiver(parent, includeOverriders = parentUsage.parent == null)
            }

            is KtExpression -> processExpression(element)
        }
    }

    private fun processProperty(property: KtProperty) {
        if (property.hasDelegateExpression()) {
            val getter = (property.unsafeResolveToDescriptor() as VariableDescriptorWithAccessors).getter ?: return
            val bindingContext = property.analyzeWithContent()
            val delegateGetterResolvedCall = bindingContext[BindingContext.DELEGATED_PROPERTY_RESOLVED_CALL, getter]
            delegateGetterResolvedCall?.resultingDescriptor?.originalSource?.getPsi()?.passToProcessor()
            return
        }

        property.initializer?.passToProcessor()

        property.getter?.processBody()

        val isDefaultGetter = property.getter?.bodyExpression == null
        val isDefaultSetter = property.setter?.bodyExpression == null
        if (isDefaultGetter) {
            if (isDefaultSetter) {
                property.processPropertyAssignments()
            } else {
                property.setter!!.processBackingFieldAssignments()
            }
        }
    }

    private fun processParameter(parameter: KtParameter, includeOverriders: Boolean) {
        if (!canProcessParameter(parameter)) return

        val function = parameter.ownerFunction ?: return

        if (function is KtPropertyAccessor && function.isSetter) {
            function.property.processPropertyAssignments()
            return
        }

        if (function is KtNamedFunction
            && function.name == OperatorNameConventions.SET_VALUE.asString()
            && function.hasModifier(KtTokens.OPERATOR_KEYWORD)
        ) {

            ReferencesSearch.search(function, analysisScope)
                .filterIsInstance<KtPropertyDelegationMethodsReference>()
                .mapNotNull { it.element.parent as? KtProperty }
                .forEach { it.processPropertyAssignments() }
        }

        val parameterDescriptor = parameter.resolveToParameterDescriptorIfAny(BodyResolveMode.FULL) ?: return

        if (function is KtFunction) {
            processCalls(function, includeOverriders, ArgumentSliceProducer(parameterDescriptor))
        }

        if (parameter.valOrVarKeyword.toValVar() == KotlinValVar.Var) {
            processAssignments(parameter, analysisScope)
        }
    }

    private fun processExtensionReceiver(declaration: KtCallableDeclaration, includeOverriders: Boolean) {
        processCalls(declaration, includeOverriders, ReceiverSliceProducer)
    }

    private fun processExpression(expression: KtExpression) {
        val lambda = when (expression) {
            is KtLambdaExpression -> expression.functionLiteral
            is KtNamedFunction -> expression.takeIf { expression.name == null }
            else -> null
        }
        if (lambda != null) {
            if (behaviour is LambdaResultInflowBehaviour) {
                lambda.passToProcessor(behaviour.originalBehaviour)
            }
            return
        }

        val pseudocode = pseudocodeCache[expression] ?: return
        val expressionValue = pseudocode.getElementValue(expression) ?: return
        when (val createdAt = expressionValue.createdAt) {
            is ReadValueInstruction -> {
                if (createdAt.target == AccessTarget.BlackBox) {
                    val originalElement = expressionValue.element as? KtExpression ?: return
                    if (originalElement != expression) {
                        processExpression(originalElement)
                    }
                    return
                }

                val accessedDescriptor = createdAt.target.accessedDescriptor ?: return
                val accessedDeclaration = accessedDescriptor.originalSource.getPsi()
                when (accessedDescriptor) {
                    is SyntheticFieldDescriptor -> {
                        val property = accessedDeclaration as? KtProperty ?: return
                        if (accessedDescriptor.propertyDescriptor.setter?.isDefault != false) {
                            property.processPropertyAssignments()
                        } else {
                            property.setter?.processBackingFieldAssignments()
                        }
                    }

                    is ReceiverParameterDescriptor -> {
                        //TODO: handle non-extension receivers?
                        val callable = accessedDescriptor.containingDeclaration as? CallableDescriptor ?: return
                        when (val declaration = callable.originalSource.getPsi()) {
                            is KtFunctionLiteral -> {
                                declaration.passToProcessorAsValue(LambdaCallsBehaviour(ReceiverSliceProducer, behaviour))
                            }

                            is KtCallableDeclaration -> {
                                declaration.receiverTypeReference?.passToProcessor()
                            }
                        }
                    }

                    is ValueParameterDescriptor -> {
                        if (accessedDeclaration == null) {
                            val anonymousFunction = accessedDescriptor.containingDeclaration as? AnonymousFunctionDescriptor
                            if (anonymousFunction != null && accessedDescriptor.name.asString() == "it") {
                                val functionLiteral = anonymousFunction.source.getPsi() as KtFunctionLiteral
                                val sliceTransformer = ArgumentSliceProducer(anonymousFunction.valueParameters.first())
                                processCalls(functionLiteral, false, sliceTransformer)
                            }
                        } else {
                            accessedDeclaration.passDeclarationToProcessorWithOverriders()
                        }
                    }

                    else -> {
                        accessedDeclaration?.passDeclarationToProcessorWithOverriders()
                    }
                }
            }

            is MergeInstruction -> createdAt.passInputsToProcessor()

            is MagicInstruction -> when (createdAt.kind) {
                MagicKind.NOT_NULL_ASSERTION, MagicKind.CAST -> createdAt.passInputsToProcessor()

                MagicKind.BOUND_CALLABLE_REFERENCE, MagicKind.UNBOUND_CALLABLE_REFERENCE -> {
                    val callableRefExpr = expressionValue.element as? KtCallableReferenceExpression ?: return
                    val bindingContext = expression.analyze(BodyResolveMode.PARTIAL)
                    val referencedDescriptor = bindingContext[BindingContext.REFERENCE_TARGET, callableRefExpr.callableReference] ?: return
                    val referencedDeclaration = (referencedDescriptor as? DeclarationDescriptorWithSource)?.originalSource?.getPsi()
                        ?: return
                    if (behaviour is LambdaResultInflowBehaviour) {
                        referencedDeclaration.passToProcessor(behaviour.originalBehaviour)
                    }
                }

                else -> return
            }

            is CallInstruction -> {
                val resolvedCall = createdAt.resolvedCall
                val resultingDescriptor = resolvedCall.resultingDescriptor
                if (resultingDescriptor is FunctionInvokeDescriptor) {
                    (resolvedCall.dispatchReceiver as? ExpressionReceiver)?.expression
                        ?.passToProcessorAsValue(LambdaResultInflowBehaviour(behaviour))
                } else {
                    resultingDescriptor.originalSource.getPsi()?.passDeclarationToProcessorWithOverriders()
                }
            }
        }
    }

    private fun KtProperty.processPropertyAssignments() {
        val accessSearchScope = if (isVar) {
            analysisScope
        } else {
            val containerScope = LocalSearchScope(getStrictParentOfType<KtDeclaration>() ?: return)
            analysisScope.intersectWith(containerScope)
        }
        processAssignments(this, accessSearchScope)
    }

    private fun processAssignments(variable: KtCallableDeclaration, accessSearchScope: SearchScope) {
        fun processVariableAccess(usageInfo: UsageInfo) {
            val refElement = usageInfo.element ?: return
            val refParent = refElement.parent

            val rhsValue = when {
                refElement is KtExpression -> {
                    val (accessKind, accessExpression) = refElement.readWriteAccessWithFullExpression(true)
                    if (accessKind == ReferenceAccess.WRITE && accessExpression is KtBinaryExpression && accessExpression.operationToken == KtTokens.EQ) {
                        accessExpression.right
                    } else {
                        accessExpression
                    }
                }

                refParent is PsiCall -> refParent.argumentList?.expressions?.getOrNull(0)

                else -> null
            }
            rhsValue?.passToProcessorAsValue()
        }

        processVariableAccesses(variable, accessSearchScope, AccessKind.WRITE_WITH_OPTIONAL_READ, ::processVariableAccess)
    }

    private fun KtPropertyAccessor.processBackingFieldAssignments() {
        forEachDescendantOfType<KtBinaryExpression> body@{
            if (it.operationToken != KtTokens.EQ) return@body
            val lhs = it.left?.let { expression -> KtPsiUtil.safeDeparenthesize(expression) } ?: return@body
            val rhs = it.right ?: return@body
            if (!lhs.isBackingFieldReference()) return@body
            rhs.passToProcessor()
        }
    }

    private fun KtExpression.isBackingFieldReference(): Boolean {
        return this is KtSimpleNameExpression &&
                getReferencedName() == SyntheticFieldDescriptor.NAME.asString() &&
                resolveToCall()?.resultingDescriptor is SyntheticFieldDescriptor
    }

    private fun KtDeclarationWithBody.processBody() {
        val bodyExpression = bodyExpression ?: return
        val pseudocode = pseudocodeCache[bodyExpression] ?: return
        pseudocode.traverse(TraversalOrder.FORWARD) { instr ->
            if (instr is ReturnValueInstruction && instr.subroutine == this) {
                (instr.returnExpressionIfAny?.returnedExpression ?: instr.element as? KtExpression)?.passToProcessorAsValue()
            }
        }
    }

    private fun Instruction.passInputsToProcessor() {
        inputValues.forEach {
            if (it.createdAt != null) {
                it.element?.passToProcessorAsValue()
            }
        }
    }

    private fun PsiElement.passDeclarationToProcessorWithOverriders() {
        passToProcessor()

        HierarchySearchRequest(this, analysisScope)
            .searchOverriders()
            .forEach { it.namedUnwrappedElement?.passToProcessor() }

        if (this is KtCallableDeclaration && isExpectDeclaration()) {
            resolveToDescriptorIfAny(BodyResolveMode.FULL)
                ?.actualsForExpected()
                ?.forEach {
                    (it as? DeclarationDescriptorWithSource)?.originalSource?.getPsi()?.passToProcessor()
                }
        }
    }

    @Suppress("DataClassPrivateConstructor") // we have modifier data to get equals&hashCode only
    private data class ArgumentSliceProducer private constructor(
        private val parameterIndex: Int,
        private val isExtension: Boolean
    ) : SliceProducer
    {
        constructor(parameterDescriptor: ValueParameterDescriptor) : this(
            parameterDescriptor.index,
            parameterDescriptor.containingDeclaration.isExtension
        )

        override fun produce(
            usage: UsageInfo,
            behaviour: KotlinSliceUsage.SpecialBehaviour?,
            parent: SliceUsage
        ): Collection<SliceUsage>? {
            val element = usage.element ?: return emptyList()
            val argumentExpression = extractArgumentExpression(element) ?: return emptyList()
            return listOf(KotlinSliceUsage(argumentExpression, parent, behaviour, forcedExpressionMode = true))
        }

        private fun extractArgumentExpression(refElement: PsiElement): PsiElement? {
            val refParent = refElement.parent
            return when {
                refElement is KtExpression -> {
                    val callElement = refElement as? KtCallElement
                        ?: refElement.getParentOfTypeAndBranch { calleeExpression }
                        ?: return null
                    val resolvedCall = callElement.resolveToCall() ?: return null
                    val resultingDescriptor = resolvedCall.resultingDescriptor
                    val parameterIndexToUse = parameterIndex +
                            (if (isExtension && resultingDescriptor.extensionReceiverParameter == null) 1 else 0)
                    val parameterDescriptor = resultingDescriptor.valueParameters[parameterIndexToUse]
                    val resolvedArgument = resolvedCall.valueArguments[parameterDescriptor] ?: return null
                    when (resolvedArgument) {
                        is DefaultValueArgument -> (parameterDescriptor.source.getPsi() as? KtParameter)?.defaultValue
                        is ExpressionValueArgument -> resolvedArgument.valueArgument?.getArgumentExpression()
                        else -> null
                    }
                }

                refParent is PsiCall -> refParent.argumentList?.expressions?.getOrNull(parameterIndex + (if (isExtension) 1 else 0))

                refElement is PsiMethod -> refElement.parameterList.parameters.getOrNull(parameterIndex + (if (isExtension) 1 else 0))

                else -> null
            }
        }
    }

    private object ReceiverSliceProducer : SliceProducer {
        override fun produce(
            usage: UsageInfo,
            behaviour: KotlinSliceUsage.SpecialBehaviour?,
            parent: SliceUsage
        ): Collection<SliceUsage>? {
            val refElement = usage.element ?: return emptyList()
            when (refElement) {
                is KtExpression -> {
                    val resolvedCall = refElement.resolveToCall() ?: return emptyList()
                    when (val receiver = resolvedCall.extensionReceiver) {
                        is ExpressionReceiver -> {
                            return listOf(KotlinSliceUsage(receiver.expression, parent, behaviour, forcedExpressionMode = true))
                        }

                        is ImplicitReceiver -> {
                            val callableDescriptor = receiver.declarationDescriptor as? CallableDescriptor ?: return emptyList()
                            when (val callableDeclaration = callableDescriptor.originalSource.getPsi()) {
                                is KtFunctionLiteral -> {
                                    val newBehaviour = LambdaCallsBehaviour(ReceiverSliceProducer, behaviour)
                                    return listOf(KotlinSliceUsage(callableDeclaration, parent, newBehaviour, forcedExpressionMode = true))
                                }

                                is KtCallableDeclaration -> {
                                    val receiverTypeReference = callableDeclaration.receiverTypeReference ?: return emptyList()
                                    return listOf(KotlinSliceUsage(receiverTypeReference, parent, behaviour, false))
                                }

                                else -> return emptyList()
                            }
                        }

                        else -> return emptyList()
                    }
                }

                else -> {
                    val argument = (refElement.parent as? PsiCall)?.argumentList?.expressions?.getOrNull(0) ?: return emptyList()
                    return listOf(KotlinSliceUsage(argument, parent, behaviour, true))
                }
            }
        }

        override fun equals(other: Any?) = other === this
        override fun hashCode() = 0
    }
}
