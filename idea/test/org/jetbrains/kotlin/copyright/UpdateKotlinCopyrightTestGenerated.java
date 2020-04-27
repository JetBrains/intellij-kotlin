/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.copyright;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("idea/testData/copyright")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class UpdateKotlinCopyrightTestGenerated extends AbstractUpdateKotlinCopyrightTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInCopyright() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("idea/testData/copyright"), Pattern.compile("^(.+)\\.(kt|kts)$"), null, true);
    }

    @TestMetadata("ClassDocComment.kt")
    public void testClassDocComment() throws Exception {
        runTest("idea/testData/copyright/ClassDocComment.kt");
    }

    @TestMetadata("Empty.kt")
    public void testEmpty() throws Exception {
        runTest("idea/testData/copyright/Empty.kt");
    }

    @TestMetadata("MultiComments.kt")
    public void testMultiComments() throws Exception {
        runTest("idea/testData/copyright/MultiComments.kt");
    }

    @TestMetadata("NoPackage.kt")
    public void testNoPackage() throws Exception {
        runTest("idea/testData/copyright/NoPackage.kt");
    }

    @TestMetadata("Script.kts")
    public void testScript() throws Exception {
        runTest("idea/testData/copyright/Script.kts");
    }

    @TestMetadata("Simple.kt")
    public void testSimple() throws Exception {
        runTest("idea/testData/copyright/Simple.kt");
    }
}
