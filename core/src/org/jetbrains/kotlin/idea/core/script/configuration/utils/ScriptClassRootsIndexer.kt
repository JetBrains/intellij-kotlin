/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.core.script.configuration.utils

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.TransactionGuard
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ex.ProjectRootManagerEx
import com.intellij.openapi.util.EmptyRunnable
import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.kotlin.idea.core.script.ScriptDependenciesModificationTracker
import org.jetbrains.kotlin.idea.core.script.debug
import org.jetbrains.kotlin.idea.util.application.runWriteAction
import org.jetbrains.kotlin.scripting.resolve.ScriptCompilationConfigurationWrapper
import java.util.concurrent.atomic.AtomicInteger

/**
 * Utility for postponing indexing of new roots to the end of some bulk operation.
 */
class ScriptClassRootsIndexer(val project: Project) {
    private var newRootsPresent: Boolean = false
    private val concurrentTransactions = AtomicInteger()

    @Synchronized
    fun markNewRoot() {
        checkInTransaction()
        newRootsPresent = true
    }

    fun checkInTransaction() {
        check(concurrentTransactions.get() > 0)
    }

    inline fun <T> transaction(body: () -> T): T {
        startTransaction()
        return try {
            body()
        } finally {
            commit()
        }
    }

    fun startTransaction() {
        concurrentTransactions.incrementAndGet()
    }

    fun commit() {
        concurrentTransactions.decrementAndGet()

        // run indexing even in inner transaction
        // (outer transaction may be async, so it would be better to not wait it)
        startIndexingIfNeeded()
    }

    @Synchronized
    private fun startIndexingIfNeeded() {
        if (!newRootsPresent) return
        newRootsPresent = false

        val doNotifyRootsChanged = Runnable {
            runWriteAction {
                if (project.isDisposed) return@runWriteAction

                debug { "roots change event" }

                ProjectRootManagerEx.getInstanceEx(project)?.makeRootsChange(EmptyRunnable.getInstance(), false, true)
                ScriptDependenciesModificationTracker.getInstance(project).incModificationCount()
            }
        }

        if (ApplicationManager.getApplication().isUnitTestMode) {
            TransactionGuard.submitTransaction(project, doNotifyRootsChanged)
        } else {
            TransactionGuard.getInstance().submitTransactionLater(project, doNotifyRootsChanged)
        }
    }
}
