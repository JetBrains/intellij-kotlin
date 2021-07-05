/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.uast

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.idea.fir.low.level.api.api.KotlinOutOfBlockModificationTrackerFactory
import org.jetbrains.kotlin.idea.frontend.api.InvalidWayOfUsingAnalysisSession
import org.jetbrains.kotlin.idea.frontend.api.KtAnalysisSessionProvider

@OptIn(InvalidWayOfUsingAnalysisSession::class)
internal fun Project.invalidateAllCachesForUastTests() {
    service<KotlinOutOfBlockModificationTrackerFactory>().incrementModificationsCount()
    service<KtAnalysisSessionProvider>().clearCaches()
}