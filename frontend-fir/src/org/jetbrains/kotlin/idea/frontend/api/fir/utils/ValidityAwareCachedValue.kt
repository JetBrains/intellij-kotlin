/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.frontend.api.fir.utils

import org.jetbrains.kotlin.idea.frontend.api.ValidityOwner
import org.jetbrains.kotlin.idea.frontend.api.ValidityOwnerByValidityToken
import org.jetbrains.kotlin.idea.frontend.api.assertIsValid
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

// all access are considered to be from a single thread
// which should be checked in invalidatable.assertIsValid()
class ValidityAwareCachedValue<T>(
    private val validityOwner: ValidityOwner,
    private val init: () -> T
) : ReadOnlyProperty<Any, T> {
    var value: Any? = UNITITIALIZED

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        validityOwner.assertIsValid()
        if (value === UNITITIALIZED) {
            value = init()
        }
        return value as T
    }
}

internal fun <T> ValidityOwnerByValidityToken.cached(init: () -> T) = ValidityAwareCachedValue(token, init)

internal object UNITITIALIZED