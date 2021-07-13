/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.uast.kotlin

import org.jetbrains.kotlin.psi.KtAnonymousInitializer
import org.jetbrains.kotlin.psi.KtBlockExpression
import org.jetbrains.uast.UBlockExpression
import org.jetbrains.uast.UElement
import org.jetbrains.uast.getContainingUClass

open class KotlinUBlockExpression(
    override val sourcePsi: KtBlockExpression,
    givenParent: UElement?
) : KotlinAbstractUExpression(givenParent), UBlockExpression, KotlinUElementWithType {
    override val expressions by lz {
        sourcePsi.statements.map { baseResolveProviderService.baseKotlinConverter.convertOrEmpty(it, this) }
    }

    override fun convertParent(): UElement? {
        val directParent = super.convertParent()
        if (directParent is UnknownKotlinExpression && directParent.sourcePsi is KtAnonymousInitializer) {
            val containingUClass = directParent.getContainingUClass() ?: return directParent
            containingUClass.methods.find {
                it is BaseKotlinConstructorUMethod && it.isPrimary || it is BaseKotlinSecondaryConstructorWithInitializersUMethod
            }?.let {
                return it.uastBody
            }
        }
        return directParent
    }
}
