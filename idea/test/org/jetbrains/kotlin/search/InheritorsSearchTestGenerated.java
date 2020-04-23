/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.search;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("kotlin/idea/testData/search/inheritance")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class InheritorsSearchTestGenerated extends AbstractInheritorsSearchTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInInheritance() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("kotlin/idea/testData/search/inheritance"), Pattern.compile("^(.+)\\.kt$"), null, true);
    }

    @TestMetadata("annotationClass.kt")
    public void testAnnotationClass() throws Exception {
        runTest("kotlin/idea/testData/search/inheritance/annotationClass.kt");
    }

    @TestMetadata("enum.kt")
    public void testEnum() throws Exception {
        runTest("kotlin/idea/testData/search/inheritance/enum.kt");
    }

    @TestMetadata("interfaces.kt")
    public void testInterfaces() throws Exception {
        runTest("kotlin/idea/testData/search/inheritance/interfaces.kt");
    }

    @TestMetadata("object.kt")
    public void testObject() throws Exception {
        runTest("kotlin/idea/testData/search/inheritance/object.kt");
    }

    @TestMetadata("simpleClass.kt")
    public void testSimpleClass() throws Exception {
        runTest("kotlin/idea/testData/search/inheritance/simpleClass.kt");
    }

    @TestMetadata("testInheritanceFromJavaClass.kt")
    public void testTestInheritanceFromJavaClass() throws Exception {
        runTest("kotlin/idea/testData/search/inheritance/testInheritanceFromJavaClass.kt");
    }

    @TestMetadata("testInheritanceFromKotlinClass.kt")
    public void testTestInheritanceFromKotlinClass() throws Exception {
        runTest("kotlin/idea/testData/search/inheritance/testInheritanceFromKotlinClass.kt");
    }
}
