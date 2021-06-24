/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.codeinsight

import org.jetbrains.kotlin.idea.codeInsight.OverrideImplementWithLibTest
import org.jetbrains.kotlin.idea.core.overrideImplement.KtClassMember
import org.jetbrains.kotlin.idea.fir.invalidateCaches
import org.jetbrains.kotlin.idea.test.TestRoot
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.test.TestMetadata
import org.junit.internal.runners.JUnit38ClassRunner
import org.junit.runner.RunWith

@TestRoot("idea/tests")
@TestMetadata("testData/codeInsight/overrideImplement/withLib")
@RunWith(JUnit38ClassRunner::class)
internal class FirOverrideImplementWithLibTest : OverrideImplementWithLibTest<KtClassMember>(), FirOverrideImplementTestMixIn {
    override fun isFirPlugin(): Boolean = true

    override fun tearDown() {
        // If we pass something as a context, then the DependencyListForCliModule will be built with dependencies from the previous test
        // and will not be reinitialized later on. Because of that the first test might pass, but the other ones probably won't
        project.invalidateCaches(context = null)
        super.tearDown()
    }
}

