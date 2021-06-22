/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.frontend.api.components

import org.jetbrains.kotlin.idea.frontend.api.KtTypeArgument
import org.jetbrains.kotlin.idea.frontend.api.KtTypeArgumentWithVariance
import org.jetbrains.kotlin.idea.frontend.api.symbols.KtClassOrObjectSymbol
import org.jetbrains.kotlin.idea.frontend.api.types.KtClassType
import org.jetbrains.kotlin.idea.frontend.api.types.KtType
import org.jetbrains.kotlin.idea.frontend.api.types.KtTypeNullability
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.types.Variance

abstract class KtTypeCreator : KtAnalysisSessionComponent() {
    abstract fun buildClassType(builder: KtClassTypeBuilder): KtClassType
}

interface KtTypeCreatorMixIn : KtAnalysisSessionMixIn


inline fun KtTypeCreatorMixIn.buildClassType(
    classId: ClassId,
    build: KtClassTypeBuilder.() -> Unit = {}
): KtClassType =
    analysisSession.typesCreator.buildClassType(KtClassTypeBuilder.ByClassId(classId).apply(build))

inline fun KtTypeCreatorMixIn.buildClassType(
    symbol: KtClassOrObjectSymbol,
    build: KtClassTypeBuilder.() -> Unit = {}
): KtClassType =
    analysisSession.typesCreator.buildClassType(KtClassTypeBuilder.BySymbol(symbol).apply(build))


sealed class KtTypeBuilder

sealed class KtClassTypeBuilder : KtTypeBuilder() {
    private val _arguments = mutableListOf<KtTypeArgument>()

    var nullability: KtTypeNullability = KtTypeNullability.NON_NULLABLE

    val arguments: List<KtTypeArgument> get() = _arguments

    fun argument(argument: KtTypeArgument) {
        _arguments += argument
    }

    fun argument(type: KtType, variance: Variance = Variance.INVARIANT) {
        _arguments += KtTypeArgumentWithVariance(type, variance, type.token)
    }

    class ByClassId(val classId: ClassId) : KtClassTypeBuilder()
    class BySymbol(val symbol: KtClassOrObjectSymbol) : KtClassTypeBuilder()
}

