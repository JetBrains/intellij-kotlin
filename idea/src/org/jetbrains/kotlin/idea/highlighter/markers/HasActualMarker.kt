/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.idea.highlighter.markers

import com.intellij.ide.util.DefaultPsiElementCellRenderer
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.idea.KotlinBundle
import org.jetbrains.kotlin.idea.core.toDescriptor
import org.jetbrains.kotlin.idea.util.actualsForExpected
import org.jetbrains.kotlin.psi.KtDeclaration

@Suppress("DuplicatedCode")
fun getPlatformActualTooltip(declaration: KtDeclaration): String? {
    val actualDeclarations = declaration.actualsForExpected().mapNotNull { it.toDescriptor() }
    val modulesString = getModulesStringForExpectActualMarkerTooltip(actualDeclarations) ?: return null

    return KotlinBundle.message("highlighter.prefix.text.has.actuals.in", modulesString)
}

fun KtDeclaration.allNavigatableActualDeclarations(): Set<KtDeclaration> =
    actualsForExpected() + findMarkerBoundDeclarations().flatMap { it.actualsForExpected().asSequence() }

class ActualExpectedPsiElementCellRenderer : DefaultPsiElementCellRenderer() {
    override fun getContainerText(element: PsiElement?, name: String?) = ""
}

fun KtDeclaration.navigateToActualTitle() = KotlinBundle.message("highlighter.title.choose.actual.for", name.toString())

fun KtDeclaration.navigateToActualUsagesTitle() = KotlinBundle.message("highlighter.title.actuals.for", name.toString())

fun buildNavigateToActualDeclarationsPopup(element: PsiElement?): NavigationPopupDescriptor? {
    return element?.markerDeclaration?.let {
        val navigatableActualDeclarations = it.allNavigatableActualDeclarations()
        if (navigatableActualDeclarations.isEmpty()) return null
        return NavigationPopupDescriptor(
            navigatableActualDeclarations,
            it.navigateToActualTitle(),
            it.navigateToActualUsagesTitle(),
            ActualExpectedPsiElementCellRenderer()
        )
    }
}
