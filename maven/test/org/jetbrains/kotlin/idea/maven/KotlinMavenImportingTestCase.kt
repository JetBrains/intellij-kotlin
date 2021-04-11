/*
 * Copyright 2010-2015 JetBrains s.r.o.
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
package org.jetbrains.kotlin.idea.maven

import com.intellij.testFramework.RunAll
import com.intellij.util.ThrowableRunnable
import org.jetbrains.idea.maven.MavenImportingTestCase
import org.jetbrains.kotlin.config.ResourceKotlinRootType
import org.jetbrains.kotlin.config.SourceKotlinRootType
import org.jetbrains.kotlin.config.TestResourceKotlinRootType
import org.jetbrains.kotlin.config.TestSourceKotlinRootType
import org.jetbrains.kotlin.idea.test.KotlinSdkCreationChecker

abstract class KotlinMavenImportingTestCase : MavenImportingTestCase() {
    private var sdkCreationChecker: KotlinSdkCreationChecker? = null

    override fun setUp() {
        super.setUp()
        sdkCreationChecker = KotlinSdkCreationChecker()
    }

    override fun tearDown() {
        RunAll.runAll(
            ThrowableRunnable<Throwable> { sdkCreationChecker!!.removeNewKotlinSdk() },
            ThrowableRunnable<Throwable> { super.tearDown() },
        )
    }

    protected fun assertKotlinSources(moduleName: String, vararg expectedSources: String) {
        doAssertContentFolders(moduleName, SourceKotlinRootType, *expectedSources)
    }

    protected fun assertKotlinResources(moduleName: String, vararg expectedSources: String) {
        doAssertContentFolders(moduleName, ResourceKotlinRootType, *expectedSources)
    }

    protected fun assertKotlinTestSources(moduleName: String, vararg expectedSources: String) {
        doAssertContentFolders(moduleName, TestSourceKotlinRootType, *expectedSources)
    }

    protected fun assertKotlinTestResources(moduleName: String, vararg expectedSources: String) {
        doAssertContentFolders(moduleName, TestResourceKotlinRootType, *expectedSources)
    }
}