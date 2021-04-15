/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.idea.core.util

import com.intellij.openapi.progress.ProgressManager
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantReadWriteLock

internal inline fun <T> ReentrantReadWriteLock.write(action: () -> T): T {
    val rl = readLock()

    val readCount = if (writeHoldCount == 0) readHoldCount else 0
    repeat(readCount) { rl.unlock() }

    val wl = writeLock()
    while (!wl.tryLock(100, TimeUnit.MILLISECONDS)) {
        ProgressManager.checkCanceled()
    }
    try {
        return action()
    } finally {
        repeat(readCount) { rl.lock() }
        wl.unlock()
    }
}

internal inline fun <T> Lock.withLock(action: () -> T): T {
    while (!tryLock(100, TimeUnit.MILLISECONDS)) {
        ProgressManager.checkCanceled()
    }
    try {
        return action()
    } finally {
        unlock()
    }
}
