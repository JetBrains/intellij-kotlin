/*
 * Copyright 2010-2021 JetBrains s.r.o.
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

package org.jetbrains.kotlin.idea.search.ideaExtensions

import com.intellij.psi.PsiMethod
import com.intellij.psi.search.searches.OverridingMethodsSearch
import com.intellij.util.Processor
import com.intellij.util.QueryExecutor
import org.jetbrains.kotlin.asJava.elements.KtLightMethod
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
import org.jetbrains.kotlin.idea.caches.resolve.unsafeResolveToDescriptor
import org.jetbrains.kotlin.idea.search.declarationsSearch.findOverridingMethodsInKotlin
import org.jetbrains.kotlin.idea.util.application.runReadAction
import org.jetbrains.kotlin.psi.KtCallableDeclaration

class KotlinOverridingMethodsWithGenericsSearcher : QueryExecutor<PsiMethod, OverridingMethodsSearch.SearchParameters> {
    override fun execute(p: OverridingMethodsSearch.SearchParameters, consumer: Processor<in PsiMethod>): Boolean {
        val method = p.method
        if (method !is KtLightMethod) return true

        val declaration = method.kotlinOrigin as? KtCallableDeclaration ?: return true

        val callDescriptor = runReadAction { declaration.unsafeResolveToDescriptor() }
        if (callDescriptor !is CallableDescriptor) return true

        // Java overriding method search can't find overloads with primitives types, so
        // we do additional search for such methods.
        if (!callDescriptor.valueParameters.any { it.type.constructor.declarationDescriptor is TypeParameterDescriptor }) return true

        val parentClass = runReadAction { method.containingClass }
        return findOverridingMethodsInKotlin(parentClass, declaration, p, consumer)
    }
}
