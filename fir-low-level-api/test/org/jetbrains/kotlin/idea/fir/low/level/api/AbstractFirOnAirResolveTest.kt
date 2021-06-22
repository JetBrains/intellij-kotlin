/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.low.level.api

import org.jetbrains.kotlin.fir.FirRenderer
import org.jetbrains.kotlin.fir.render
import org.jetbrains.kotlin.idea.fir.low.level.api.api.FirModuleResolveState
import org.jetbrains.kotlin.idea.fir.low.level.api.api.LowLevelFirApiFacadeForResolveOnAir
import org.jetbrains.kotlin.idea.fir.low.level.api.compiler.based.FrontendApiSingleTestDataFileTest
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtFileAnnotationList
import org.jetbrains.kotlin.test.KotlinTestUtils
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.TestServices
import org.jetbrains.kotlin.idea.test.util.findElementByCommentPrefix

abstract class AbstractFirOnAirResolveTest : FrontendApiSingleTestDataFileTest() {

    override fun doTest(ktFile: KtFile, module: TestModule, resolveState: FirModuleResolveState, testServices: TestServices) {

        fun fixUpAnnotations(element: KtElement): KtElement = when (element) {
            is KtAnnotated -> element.annotationEntries.firstOrNull() ?: element
            is KtFileAnnotationList -> element.annotationEntries.first()
            else -> element
        }

        val place = (ktFile.findElementByCommentPrefix("/*PLACE*/") as KtElement).let(::fixUpAnnotations)
        val onAir = (ktFile.findElementByCommentPrefix("/*ONAIR*/") as KtElement).let(::fixUpAnnotations)

        check(place::class == onAir::class)

        check(resolveState is FirModuleResolveStateImpl)
        val firElement = LowLevelFirApiFacadeForResolveOnAir.onAirResolveElement(resolveState, place, onAir)
        val rendered = firElement.render(FirRenderer.RenderMode.WithResolvePhases)
        KotlinTestUtils.assertEqualsToFile(testDataFileSibling(".txt"), rendered)
    }
}