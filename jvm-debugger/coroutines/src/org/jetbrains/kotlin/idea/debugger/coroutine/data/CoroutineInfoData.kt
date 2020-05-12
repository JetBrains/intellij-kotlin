/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.debugger.coroutine.data

import com.sun.jdi.ObjectReference
import com.sun.jdi.ThreadReference
import org.jetbrains.kotlin.idea.debugger.coroutine.proxy.mirror.*
import org.jetbrains.kotlin.idea.debugger.coroutine.util.logger

/**
 * Represents state of a coroutine.
 * @see `kotlinx.coroutines.debug.CoroutineInfo`
 */
data class CoroutineInfoData(
    val key: CoroutineNameIdState,
    val stackTrace: List<CoroutineStackFrameItem>,
    val creationStackTrace: List<CreationCoroutineStackFrameItem>,
    val activeThread: ThreadReference? = null, // for suspended coroutines should be null
    val lastObservedFrame: ObjectReference? = null
) {
    fun isSuspended() = key.state == State.SUSPENDED

    fun isCreated() = key.state == State.CREATED

    fun isEmptyStack() = stackTrace.isEmpty()

    fun isRunning() = key.state == State.RUNNING

    fun topRestoredFrame() = stackTrace.firstOrNull()

    fun topFrameVariables() = stackTrace.firstOrNull()?.spilledVariables ?: emptyList()

    fun restoredStackTrace(mode: SuspendExitMode): List<CoroutineStackFrameItem> =
        if (stackTrace.isNotEmpty() && stackTrace.first().isInvokeSuspend())
            stackTrace.drop(1)
        else if (mode == SuspendExitMode.SUSPEND_METHOD_PARAMETER)
            stackTrace.drop(1)
        else
            stackTrace

    companion object {
        val log by logger
        const val DEFAULT_COROUTINE_NAME = "coroutine"
        const val DEFAULT_COROUTINE_STATE = "UNKNOWN"
    }
}

data class CoroutineNameIdState(val name: String, val id: String, val state: State, val dispatcher: String?) {
    companion object {
        fun instance(mirror: MirrorOfCoroutineInfo): CoroutineNameIdState =
            CoroutineNameIdState(
                mirror.context?.name ?: "coroutine",
                "${mirror.sequenceNumber}",
                State.valueOf(mirror.state ?: "UNKNOWN"),
                mirror.context?.dispatcher
            )
    }
}

enum class State {
    RUNNING,
    SUSPENDED,
    CREATED,
    UNKNOWN,
    SUSPENDED_COMPLETING,
    SUSPENDED_CANCELLING,
    CANCELLED,
    COMPLETED,
    NEW
}
