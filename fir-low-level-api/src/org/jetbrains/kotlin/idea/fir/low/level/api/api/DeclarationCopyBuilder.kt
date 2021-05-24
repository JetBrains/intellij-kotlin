/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.low.level.api.api

import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.declarations.builder.*
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid
import org.jetbrains.kotlin.idea.fir.low.level.api.lazy.resolve.RawFirNonLocalDeclarationBuilder
import org.jetbrains.kotlin.idea.fir.low.level.api.lazy.resolve.RawFirReplacement
import org.jetbrains.kotlin.idea.fir.low.level.api.providers.firIdeProvider
import org.jetbrains.kotlin.psi.*

internal object DeclarationCopyBuilder {
    fun createDeclarationCopy(
        state: FirModuleResolveState,
        nonLocalDeclaration: KtDeclaration,
        replacement: RawFirReplacement
    ): FirDeclaration {

        return when (nonLocalDeclaration) {
            is KtNamedFunction -> createFunctionCopy(
                nonLocalDeclaration,
                state,
                replacement
            )
            is KtProperty -> createPropertyCopy(
                nonLocalDeclaration,
                state,
                replacement
            )
            is KtClassOrObject -> createClassCopy(
                nonLocalDeclaration,
                state,
                replacement
            )
            is KtTypeAlias -> createTypeAliasCopy(
                nonLocalDeclaration,
                state,
                replacement
            )
            else -> error("Unsupported declaration ${nonLocalDeclaration::class.simpleName}")
        }
    }

    private fun createFunctionCopy(
        rootNonLocalDeclaration: KtNamedFunction,
        state: FirModuleResolveState,
        replacement: RawFirReplacement,
    ): FirSimpleFunction {

        val originalFunction = rootNonLocalDeclaration.getOrBuildFirOfType<FirSimpleFunction>(state)
        val builtFunction = createCopy(rootNonLocalDeclaration, originalFunction, replacement)

        val functionBlock = rootNonLocalDeclaration.bodyBlockExpression
        if (functionBlock == null || !PsiTreeUtil.isAncestor(functionBlock, replacement.from, true)) {
            return builtFunction
        }

        // right now we can't resolve builtFunction header properly, as it built right in air,
        // without file, which is now required for running stages other then body resolve, so we
        // take original function header (which is resolved) and copy replacing body with body from builtFunction
        return buildSimpleFunctionCopy(originalFunction) {
            body = builtFunction.body
            symbol = builtFunction.symbol
            initDeclaration(originalFunction, builtFunction, state)
        }.apply { reassignAllReturnTargets(builtFunction) }
    }

    private fun createClassCopy(
        rootNonLocalDeclaration: KtClassOrObject,
        state: FirModuleResolveState,
        replacement: RawFirReplacement,
    ): FirRegularClass {
        val originalFirClass = rootNonLocalDeclaration.getOrBuildFirOfType<FirRegularClass>(state)
        val builtClass = createCopy(rootNonLocalDeclaration, originalFirClass, replacement)

        val classBody = rootNonLocalDeclaration.body
        if (classBody == null || !PsiTreeUtil.isAncestor(classBody, replacement.from, true)) {
            return builtClass
        }

        return buildRegularClassCopy(originalFirClass) {
            declarations.clear()
            declarations.addAll(builtClass.declarations)
            symbol = builtClass.symbol
            initDeclaration(originalFirClass, builtClass, state)
            resolvePhase = minOf(originalFirClass.resolvePhase, FirResolvePhase.IMPORTS) //TODO move into initDeclaration?
        }
    }

    private fun createTypeAliasCopy(
        rootNonLocalDeclaration: KtTypeAlias,
        state: FirModuleResolveState,
        replacement: RawFirReplacement,
    ): FirTypeAlias {
        val originalFirTypeAlias = rootNonLocalDeclaration.getOrBuildFirOfType<FirTypeAlias>(state)
        return createCopy(rootNonLocalDeclaration, originalFirTypeAlias, replacement)
    }

    private fun createPropertyCopy(
        rootNonLocalDeclaration: KtProperty,
        state: FirModuleResolveState,
        replacement: RawFirReplacement,
    ): FirProperty {
        val originalProperty = rootNonLocalDeclaration.getOrBuildFirOfType<FirProperty>(state)
        val builtProperty = createCopy(rootNonLocalDeclaration, originalProperty, replacement)

        val insideGetterBody = rootNonLocalDeclaration.getter?.bodyBlockExpression?.let {
            PsiTreeUtil.isAncestor(it, replacement.from, true)
        } ?: false
        if (!insideGetterBody) {
            val insideSetterBody = rootNonLocalDeclaration.setter?.bodyBlockExpression?.let {
                PsiTreeUtil.isAncestor(it, replacement.from, true)
            } ?: false

            if (!insideSetterBody) return builtProperty
        }

        val originalSetter = originalProperty.setter
        val builtSetter = builtProperty.setter

        // setter has a header with `value` parameter, and we want it type to be resolved
        val copySetter = if (originalSetter != null && builtSetter != null) {
            buildPropertyAccessorCopy(originalSetter) {
                body = builtSetter.body
                symbol = builtSetter.symbol
                initDeclaration(originalSetter, builtSetter, state)
            }.apply { reassignAllReturnTargets(builtSetter) }
        } else {
            builtSetter
        }

        return buildPropertyCopy(originalProperty) {
            symbol = builtProperty.symbol
            initializer = builtProperty.initializer

            getter = builtProperty.getter
            setter = copySetter

            initDeclaration(originalProperty, builtProperty, state)
        }
    }

    private fun FirDeclarationBuilder.initDeclaration(
        originalDeclaration: FirDeclaration,
        builtDeclaration: FirDeclaration,
        state: FirModuleResolveState
    ) {
        resolvePhase = minOf(originalDeclaration.resolvePhase, FirResolvePhase.DECLARATIONS)
        source = builtDeclaration.source
        moduleData = state.rootModuleSession.moduleData
    }

    internal inline fun <reified D : FirDeclaration> createCopy(
        rootNonLocalDeclaration: KtDeclaration,
        originalFirDeclaration: D,
        replacement: RawFirReplacement? = null,
    ): D {
        return RawFirNonLocalDeclarationBuilder.build(
            session = originalFirDeclaration.moduleData.session,
            baseScopeProvider = originalFirDeclaration.moduleData.session.firIdeProvider.kotlinScopeProvider,
            designation = originalFirDeclaration.collectDesignation(),
            rootNonLocalDeclaration = rootNonLocalDeclaration,
            replacement = replacement,
        ) as D
    }

    private fun FirFunction<*>.reassignAllReturnTargets(from: FirFunction<*>) {
        this.accept(object : FirVisitorVoid() {
            override fun visitElement(element: FirElement) {
                if (element is FirReturnExpression && element.target.labeledElement == from) {
                    element.target.bind(this@reassignAllReturnTargets)
                }
                element.acceptChildren(this)
            }
        })
    }
}
