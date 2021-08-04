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

package org.jetbrains.uast.kotlin

import com.intellij.psi.*
import org.jetbrains.kotlin.psi.*
import org.jetbrains.uast.*
import org.jetbrains.uast.kotlin.psi.UastFakeLightMethod

class KotlinUMethodWithFakeLightDelegate internal constructor(
    val original: KtFunction,
    fakePsi: UastFakeLightMethod,
    givenParent: UElement?
) : KotlinUMethod(fakePsi, original, givenParent) {

    constructor(original: KtFunction, containingLightClass: PsiClass, givenParent: UElement?)
            : this(original, UastFakeLightMethod(original, containingLightClass), givenParent)

    override val uAnnotations: List<UAnnotation>
        get() = original.annotationEntries.mapNotNull { it.toUElementOfType() }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as KotlinUMethodWithFakeLightDelegate
        if (original != other.original) return false
        return true
    }

    override fun hashCode(): Int = original.hashCode()
}
