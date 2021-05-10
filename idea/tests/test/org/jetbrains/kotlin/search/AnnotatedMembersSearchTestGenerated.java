/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.search;

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
@TestMetadata("testData/search/annotations")
public class AnnotatedMembersSearchTestGenerated extends AbstractAnnotatedMembersSearchTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("annotationAliased.kt")
    public void testAnnotationAliased() throws Exception {
        runTest("testData/search/annotations/annotationAliased.kt");
    }

    @TestMetadata("testAmbiguousNestedNonAnnotationClass.kt")
    public void testTestAmbiguousNestedNonAnnotationClass() throws Exception {
        runTest("testData/search/annotations/testAmbiguousNestedNonAnnotationClass.kt");
    }

    @TestMetadata("testAmbiguousNestedPrivateAnnotationClass.kt")
    public void testTestAmbiguousNestedPrivateAnnotationClass() throws Exception {
        runTest("testData/search/annotations/testAmbiguousNestedPrivateAnnotationClass.kt");
    }

    @TestMetadata("testAnnotationsOnClass.kt")
    public void testTestAnnotationsOnClass() throws Exception {
        runTest("testData/search/annotations/testAnnotationsOnClass.kt");
    }

    @TestMetadata("testAnnotationsOnFunction.kt")
    public void testTestAnnotationsOnFunction() throws Exception {
        runTest("testData/search/annotations/testAnnotationsOnFunction.kt");
    }

    @TestMetadata("testAnnotationsOnPropertiesAndParameters.kt")
    public void testTestAnnotationsOnPropertiesAndParameters() throws Exception {
        runTest("testData/search/annotations/testAnnotationsOnPropertiesAndParameters.kt");
    }

    @TestMetadata("testAnnotationsOnPropertyAccessor.kt")
    public void testTestAnnotationsOnPropertyAccessor() throws Exception {
        runTest("testData/search/annotations/testAnnotationsOnPropertyAccessor.kt");
    }

    @TestMetadata("testAnnotationsWithParameters.kt")
    public void testTestAnnotationsWithParameters() throws Exception {
        runTest("testData/search/annotations/testAnnotationsWithParameters.kt");
    }

    @TestMetadata("testDefaultImport.kt")
    public void testTestDefaultImport() throws Exception {
        runTest("testData/search/annotations/testDefaultImport.kt");
    }

    @TestMetadata("testNestedClassAsAnnotation.kt")
    public void testTestNestedClassAsAnnotation() throws Exception {
        runTest("testData/search/annotations/testNestedClassAsAnnotation.kt");
    }

    @TestMetadata("testNestedPrivateAnnotationClass.kt")
    public void testTestNestedPrivateAnnotationClass() throws Exception {
        runTest("testData/search/annotations/testNestedPrivateAnnotationClass.kt");
    }

    @TestMetadata("testTypeAlias.kt")
    public void testTestTypeAlias() throws Exception {
        runTest("testData/search/annotations/testTypeAlias.kt");
    }
}
