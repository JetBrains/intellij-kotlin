/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.idea.core.util

import com.intellij.openapi.progress.ProgressManager
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Lock

internal inline fun <T> Lock.withCheckCanceledLock(action: () -> T): T {
    while (!tryLock(100, TimeUnit.MILLISECONDS)) {
        ProgressManager.checkCanceled()
    }
    try {
        return action()
    } finally {
        unlock()
    }
}
