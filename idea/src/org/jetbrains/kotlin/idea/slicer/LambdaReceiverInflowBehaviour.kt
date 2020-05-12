/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.slicer

import org.jetbrains.kotlin.idea.findUsages.handlers.SliceUsageProcessor
import org.jetbrains.kotlin.psi.KtElement

object LambdaReceiverInflowBehaviour : KotlinSliceAnalysisMode.Behaviour {
    override fun processUsages(element: KtElement, parent: KotlinSliceUsage, uniqueProcessor: SliceUsageProcessor) {
        InflowSlicer(element, uniqueProcessor, parent).processChildren(parent.forcedExpressionMode)
    }

    override val slicePresentationPrefix: String
        get() = TODO()

    override val testPresentationPrefix: String
        get() = "[LAMBDA RECEIVER IN] "

    override fun equals(other: Any?) = other === this
    override fun hashCode() = 0
}