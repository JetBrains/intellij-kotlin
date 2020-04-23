/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.codeInsight.generate;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("kotlin/idea/testData/codeInsight/generate/secondaryConstructors")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class CodeInsightActionTestGenerated extends AbstractCodeInsightActionTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInSecondaryConstructors() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("kotlin/idea/testData/codeInsight/generate/secondaryConstructors"), Pattern.compile("^(.+)\\.kt$"), null, true);
    }

    @TestMetadata("empty.kt")
    public void testEmpty() throws Exception {
        runTest("kotlin/idea/testData/codeInsight/generate/secondaryConstructors/empty.kt");
    }

    @TestMetadata("emptyExists.kt")
    public void testEmptyExists() throws Exception {
        runTest("kotlin/idea/testData/codeInsight/generate/secondaryConstructors/emptyExists.kt");
    }

    @TestMetadata("javaSupers.kt")
    public void testJavaSupers() throws Exception {
        runTest("kotlin/idea/testData/codeInsight/generate/secondaryConstructors/javaSupers.kt");
    }

    @TestMetadata("javaSupersWithGenerics.kt")
    public void testJavaSupersWithGenerics() throws Exception {
        runTest("kotlin/idea/testData/codeInsight/generate/secondaryConstructors/javaSupersWithGenerics.kt");
    }

    @TestMetadata("primaryExists.kt")
    public void testPrimaryExists() throws Exception {
        runTest("kotlin/idea/testData/codeInsight/generate/secondaryConstructors/primaryExists.kt");
    }

    @TestMetadata("properties.kt")
    public void testProperties() throws Exception {
        runTest("kotlin/idea/testData/codeInsight/generate/secondaryConstructors/properties.kt");
    }

    @TestMetadata("propertiesWithSupers.kt")
    public void testPropertiesWithSupers() throws Exception {
        runTest("kotlin/idea/testData/codeInsight/generate/secondaryConstructors/propertiesWithSupers.kt");
    }

    @TestMetadata("supers.kt")
    public void testSupers() throws Exception {
        runTest("kotlin/idea/testData/codeInsight/generate/secondaryConstructors/supers.kt");
    }

    @TestMetadata("supersAllExist.kt")
    public void testSupersAllExist() throws Exception {
        runTest("kotlin/idea/testData/codeInsight/generate/secondaryConstructors/supersAllExist.kt");
    }

    @TestMetadata("supersSomeExist.kt")
    public void testSupersSomeExist() throws Exception {
        runTest("kotlin/idea/testData/codeInsight/generate/secondaryConstructors/supersSomeExist.kt");
    }

    @TestMetadata("supersWithGenerics.kt")
    public void testSupersWithGenerics() throws Exception {
        runTest("kotlin/idea/testData/codeInsight/generate/secondaryConstructors/supersWithGenerics.kt");
    }

    @TestMetadata("supersWithVarargs.kt")
    public void testSupersWithVarargs() throws Exception {
        runTest("kotlin/idea/testData/codeInsight/generate/secondaryConstructors/supersWithVarargs.kt");
    }
}
