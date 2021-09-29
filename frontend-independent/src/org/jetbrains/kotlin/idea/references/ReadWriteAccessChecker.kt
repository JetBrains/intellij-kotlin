/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.references

import com.intellij.openapi.components.service
import org.jetbrains.kotlin.psi.KtBinaryExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.references.ReferenceAccess

interface ReadWriteAccessChecker {
    fun readWriteAccessWithFullExpressionByResolve(assignment: KtBinaryExpression): Pair<ReferenceAccess, KtExpression>?

    @Suppress("DEPRECATION")
    fun readWriteAccessWithFullExpression(
        targetExpression: KtExpression,
        useResolveForReadWrite: Boolean
    ): Pair<ReferenceAccess, KtExpression> =
        if (useResolveForReadWrite)
            targetExpression.readWriteAccessWithFullExpressionWithPossibleResolve(::readWriteAccessWithFullExpressionByResolve)
        else
            targetExpression.readWriteAccessWithFullExpressionWithPossibleResolve(readWriteAccessWithFullExpressionByResolve = { null })

    companion object {
        fun getInstance(): ReadWriteAccessChecker = service()
    }
}

fun KtExpression.readWriteAccessWithFullExpression(useResolveForReadWrite: Boolean): Pair<ReferenceAccess, KtExpression> =
    ReadWriteAccessChecker.getInstance().readWriteAccessWithFullExpression(this, useResolveForReadWrite)

fun KtExpression.readWriteAccess(useResolveForReadWrite: Boolean): ReferenceAccess =
    ReadWriteAccessChecker.getInstance().readWriteAccessWithFullExpression(this, useResolveForReadWrite).first

