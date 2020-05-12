/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.idea.debugger.coroutine.data

import com.intellij.debugger.engine.DebugProcessImpl
import com.intellij.debugger.engine.JVMStackFrameInfoProvider
import com.intellij.debugger.jdi.LocalVariableProxyImpl
import com.intellij.debugger.jdi.StackFrameProxyImpl
import com.intellij.debugger.memory.utils.StackFrameItem
import com.intellij.debugger.ui.impl.watch.StackFrameDescriptorImpl
import com.intellij.xdebugger.frame.XCompositeNode
import com.intellij.xdebugger.frame.XNamedValue
import com.intellij.xdebugger.frame.XStackFrame
import com.intellij.xdebugger.frame.XValueChildrenList
import org.jetbrains.kotlin.idea.debugger.coroutine.KotlinDebuggerCoroutinesBundle
import org.jetbrains.kotlin.idea.debugger.stackFrame.KotlinStackFrame


/**
 * Coroutine exit frame represented by a stack frames
 * invokeSuspend():-1
 * resumeWith()
 *
 */

class CoroutinePreflightStackFrame(
    val coroutineInfoData: CoroutineInfoData,
    private val stackFrameDescriptorImpl: StackFrameDescriptorImpl,
    val threadPreCoroutineFrames: List<StackFrameProxyImpl>,
    val mode: SuspendExitMode,
    private val firstFrameVariables: List<XNamedValue> = coroutineInfoData.topFrameVariables()
) : KotlinStackFrame(stackFrameDescriptorImpl), JVMStackFrameInfoProvider {

    override fun computeChildren(node: XCompositeNode) {
        val childrenList = XValueChildrenList()
        firstFrameVariables.forEach {
            childrenList.add(it)
        }
        node.addChildren(childrenList, false)
        super.computeChildren(node)
    }

    override fun getVisibleVariables(): List<LocalVariableProxyImpl> {
        // skip restored variables
        return super.getVisibleVariables().filter { v -> !firstFrameVariables.any { it.name == v.name() } }
    }

    override fun isInLibraryContent() = false

    override fun isSynthetic() = false

    fun restoredStackTrace() =
        coroutineInfoData.restoredStackTrace(mode)
}

enum class SuspendExitMode {
    SUSPEND_LAMBDA, SUSPEND_METHOD_PARAMETER, SUSPEND_METHOD, UNKNOWN, NONE;

    fun isCoroutineFound() =
        this == SUSPEND_LAMBDA || this == SUSPEND_METHOD_PARAMETER

    fun isSuspendMethodParameter() =
        this == SUSPEND_METHOD_PARAMETER
}

class CreationCoroutineStackFrame(debugProcess: DebugProcessImpl, item: StackFrameItem) : CoroutineStackFrame(debugProcess, item) {
    override fun getCaptionAboveOf() = KotlinDebuggerCoroutinesBundle.message("coroutine.dump.creation.trace")

    override fun hasSeparatorAbove(): Boolean =
        true
}

open class CoroutineStackFrame(debugProcess: DebugProcessImpl, val item: StackFrameItem, private val realStackFrame: XStackFrame? = null) :
    StackFrameItem.CapturedStackFrame(debugProcess, item) {

    override fun computeChildren(node: XCompositeNode) {
        if (realStackFrame != null)
            realStackFrame.computeChildren(node)
        else
            super.computeChildren(node)
    }

    override fun getCaptionAboveOf() = "CoroutineExit"

    override fun hasSeparatorAbove(): Boolean =
        false
}
