/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.stubs;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("kotlin/idea/testData/multiFileHighlighting")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class MultiFileHighlightingTestGenerated extends AbstractMultiFileHighlightingTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInMultiFileHighlighting() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("kotlin/idea/testData/multiFileHighlighting"), Pattern.compile("^(.+)\\.kt$"), null, false);
    }

    @TestMetadata("annotatedParameter.kt")
    public void testAnnotatedParameter() throws Exception {
        runTest("kotlin/idea/testData/multiFileHighlighting/annotatedParameter.kt");
    }

    @TestMetadata("copyResolveBeforeParams.kt")
    public void testCopyResolveBeforeParams() throws Exception {
        runTest("kotlin/idea/testData/multiFileHighlighting/copyResolveBeforeParams.kt");
    }

    @TestMetadata("delegatesReference.kt")
    public void testDelegatesReference() throws Exception {
        runTest("kotlin/idea/testData/multiFileHighlighting/delegatesReference.kt");
    }

    @TestMetadata("enumReference.kt")
    public void testEnumReference() throws Exception {
        runTest("kotlin/idea/testData/multiFileHighlighting/enumReference.kt");
    }

    @TestMetadata("missingDependencyClass.kt")
    public void testMissingDependencyClass() throws Exception {
        runTest("kotlin/idea/testData/multiFileHighlighting/missingDependencyClass.kt");
    }

    @TestMetadata("referencesFunWithUnspecifiedType.kt")
    public void testReferencesFunWithUnspecifiedType() throws Exception {
        runTest("kotlin/idea/testData/multiFileHighlighting/referencesFunWithUnspecifiedType.kt");
    }

    @TestMetadata("topLevelMembersReference.kt")
    public void testTopLevelMembersReference() throws Exception {
        runTest("kotlin/idea/testData/multiFileHighlighting/topLevelMembersReference.kt");
    }
}
