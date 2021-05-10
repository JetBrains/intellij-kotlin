/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.navigation;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.idea.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.idea.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.jetbrains.kotlin.idea.test.TestRoot;
import org.junit.runner.RunWith;

/*
 * This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}.
 * DO NOT MODIFY MANUALLY.
 */
@SuppressWarnings("all")
@TestRoot("idea/tests")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
@TestMetadata("testData/navigation/gotoSuper/multiModule")
public class KotlinGotoSuperMultiModuleTestGenerated extends AbstractKotlinGotoSuperMultiModuleTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("actualClass")
    public void testActualClass() throws Exception {
        runTest("testData/navigation/gotoSuper/multiModule/actualClass/");
    }

    @TestMetadata("actualFunction")
    public void testActualFunction() throws Exception {
        runTest("testData/navigation/gotoSuper/multiModule/actualFunction/");
    }

    @TestMetadata("actualProperty")
    public void testActualProperty() throws Exception {
        runTest("testData/navigation/gotoSuper/multiModule/actualProperty/");
    }
}
