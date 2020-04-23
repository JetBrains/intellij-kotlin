/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.completion.test;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("kotlin/completion/testData/multiPlatform")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class MultiPlatformCompletionTestGenerated extends AbstractMultiPlatformCompletionTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInMultiPlatform() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("kotlin/completion/testData/multiPlatform"), Pattern.compile("^([^\\.]+)$"), null, false);
    }

    @TestMetadata("classInCommon")
    public void testClassInCommon() throws Exception {
        runTest("kotlin/completion/testData/multiPlatform/classInCommon/");
    }

    @TestMetadata("classInPlatform")
    public void testClassInPlatform() throws Exception {
        runTest("kotlin/completion/testData/multiPlatform/classInPlatform/");
    }

    @TestMetadata("functionInCommon")
    public void testFunctionInCommon() throws Exception {
        runTest("kotlin/completion/testData/multiPlatform/functionInCommon/");
    }

    @TestMetadata("functionInPlatform")
    public void testFunctionInPlatform() throws Exception {
        runTest("kotlin/completion/testData/multiPlatform/functionInPlatform/");
    }
}
