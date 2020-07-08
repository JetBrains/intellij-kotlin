/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.frontend.api.fir.symbols

import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol
import org.jetbrains.kotlin.idea.fir.getOrBuildFirOfType
import org.jetbrains.kotlin.idea.frontend.api.Invalidatable
import org.jetbrains.kotlin.idea.frontend.api.InvalidatableByValidityToken
import org.jetbrains.kotlin.idea.frontend.api.fir.KtSymbolByFirBuilder
import org.jetbrains.kotlin.idea.frontend.api.fir.utils.weakRef
import org.jetbrains.kotlin.idea.frontend.api.symbols.*
import org.jetbrains.kotlin.idea.frontend.api.withValidityAssertion
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.psi.*

internal class FirKtSymbolProvider(
    override val token: Invalidatable,
    firSymbolProvider: FirSymbolProvider,
    private val firSymbolBuilder: KtSymbolByFirBuilder
) : KtSymbolProvider(), InvalidatableByValidityToken {
    private val firSymbolProvider by weakRef(firSymbolProvider)

    override fun getParameterSymbol(psi: KtParameter): KtParameterSymbol = withValidityAssertion {
        firSymbolBuilder.buildParameterSymbol(psi.getOrBuildFirOfType())
    }

    override fun getFunctionSymbol(psi: KtNamedFunction): KtFunctionSymbol = withValidityAssertion {
        firSymbolBuilder.buildFunctionSymbol(psi.getOrBuildFirOfType())
    }

    override fun getConstructorSymbol(psi: KtConstructor<*>): KtConstructorSymbol = withValidityAssertion {
        firSymbolBuilder.buildConstructorSymbol(psi.getOrBuildFirOfType())
    }

    override fun getTypeParameterSymbol(psi: KtTypeParameter): KtTypeParameterSymbol = withValidityAssertion {
        firSymbolBuilder.buildTypeParameterSymbol(psi.getOrBuildFirOfType())
    }

    override fun getTypeAliasSymbol(psi: KtTypeAlias): KtTypeAliasSymbol = withValidityAssertion {
        firSymbolBuilder.buildTypeAliasSymbol(psi.getOrBuildFirOfType())
    }

    override fun getEnumEntrySymbol(psi: KtEnumEntry): KtEnumEntrySymbol = withValidityAssertion {
        firSymbolBuilder.buildEnumEntrySymbol(psi.getOrBuildFirOfType())
    }

    override fun getAnonymousFunctionSymbol(psi: KtNamedFunction): KtAnonymousFunctionSymbol = withValidityAssertion {
        firSymbolBuilder.buildAnonymousFunctionSymbol(psi.getOrBuildFirOfType())
    }

    override fun getAnonymousFunctionSymbol(psi: KtLambdaExpression): KtAnonymousFunctionSymbol = withValidityAssertion {
        firSymbolBuilder.buildAnonymousFunctionSymbol(psi.getOrBuildFirOfType())
    }

    override fun getVariableSymbol(psi: KtProperty): KtVariableSymbol = withValidityAssertion {
        firSymbolBuilder.buildVariableSymbol(psi.getOrBuildFirOfType())
    }

    override fun getClassOrObjectSymbol(psi: KtClassOrObject): KtClassOrObjectSymbol = withValidityAssertion {
        firSymbolBuilder.buildClassSymbol(psi.getOrBuildFirOfType())
    }

    override fun getClassOrObjectSymbolByClassId(classId: ClassId): KtClassOrObjectSymbol? = withValidityAssertion {
        val symbol = firSymbolProvider.getClassLikeSymbolByFqName(classId) as? FirRegularClassSymbol ?: return null
        firSymbolBuilder.buildClassSymbol(symbol.fir)
    }
}