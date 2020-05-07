/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.test

import com.intellij.ide.startup.impl.StartupManagerImpl
import com.intellij.openapi.startup.StartupManager
import com.intellij.openapi.vfs.newvfs.impl.VfsRootAccess
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase
import com.intellij.util.ThrowableRunnable
import org.jetbrains.kotlin.test.KotlinTestUtils
import org.jetbrains.kotlin.test.TestMetadata
import java.io.File
import java.nio.file.Paths
import kotlin.reflect.full.findAnnotation

abstract class KotlinLightPlatformCodeInsightFixtureTestCase : LightPlatformCodeInsightFixtureTestCase() {
    override fun setUp() {
        super.setUp()
        enableKotlinOfficialCodeStyle(project)
        VfsRootAccess.allowRootAccess(KotlinTestUtils.getHomeDirectory())
        invalidateLibraryCache(project)
    }

    override fun tearDown() = runAll(
        ThrowableRunnable { disableKotlinOfficialCodeStyle(project) },
        ThrowableRunnable { VfsRootAccess.disallowRootAccess(KotlinTestUtils.getHomeDirectory()) },
        ThrowableRunnable { super.tearDown() },
    )

    protected fun testDataFile(fileName: String): File = File(testDataPath, fileName)

    protected fun testDataFile(): File = testDataFile(fileName())

    protected fun testPath(fileName: String = fileName()): String = testDataFile(fileName).toString()

    protected fun testPath(): String = testPath(fileName())

    protected open fun fileName(): String = KotlinTestUtils.getTestDataFileName(this::class.java, this.name) ?: (getTestName(false) + ".kt")

    override fun getTestDataPath(): String {
        val relativeToKotlinIdeRoot = this::class.findAnnotation<TestMetadata>()?.value ?: return super.getTestDataPath()
        return Paths.get(KotlinTestUtils.getHomeDirectory(), relativeToKotlinIdeRoot).toString()
    }
}
