/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.debugger.evaluate;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.jetbrains.kotlin.test.TestRoot;
import org.junit.runner.RunWith;

/*
 * This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}.
 * DO NOT MODIFY MANUALLY.
 */
@SuppressWarnings("all")
@TestRoot("idea")
@TestDataPath("$CONTENT_ROOT")
public class CodeFragmentHighlightingTestGenerated extends AbstractCodeFragmentHighlightingTest {
    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/checker/codeFragments")
    public static class CodeFragments extends AbstractCodeFragmentHighlightingTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("anonymousObject.kt")
        public void testAnonymousObject() throws Exception {
            runTest("testData/checker/codeFragments/anonymousObject.kt");
        }

        @TestMetadata("binaryExpression.kt")
        public void testBinaryExpression() throws Exception {
            runTest("testData/checker/codeFragments/binaryExpression.kt");
        }

        @TestMetadata("blockCodeFragment.kt")
        public void testBlockCodeFragment() throws Exception {
            runTest("testData/checker/codeFragments/blockCodeFragment.kt");
        }

        @TestMetadata("callExpression.kt")
        public void testCallExpression() throws Exception {
            runTest("testData/checker/codeFragments/callExpression.kt");
        }

        @TestMetadata("classHeader.kt")
        public void testClassHeader() throws Exception {
            runTest("testData/checker/codeFragments/classHeader.kt");
        }

        @TestMetadata("classHeaderWithTypeArguments.kt")
        public void testClassHeaderWithTypeArguments() throws Exception {
            runTest("testData/checker/codeFragments/classHeaderWithTypeArguments.kt");
        }

        @TestMetadata("contextElementAsStatement.kt")
        public void testContextElementAsStatement() throws Exception {
            runTest("testData/checker/codeFragments/contextElementAsStatement.kt");
        }

        @TestMetadata("elementAtIfWithoutBraces.kt")
        public void testElementAtIfWithoutBraces() throws Exception {
            runTest("testData/checker/codeFragments/elementAtIfWithoutBraces.kt");
        }

        @TestMetadata("elementAtWhenBranch.kt")
        public void testElementAtWhenBranch() throws Exception {
            runTest("testData/checker/codeFragments/elementAtWhenBranch.kt");
        }

        @TestMetadata("localVariables.kt")
        public void testLocalVariables() throws Exception {
            runTest("testData/checker/codeFragments/localVariables.kt");
        }

        @TestMetadata("localVariablesOnReturn.kt")
        public void testLocalVariablesOnReturn() throws Exception {
            runTest("testData/checker/codeFragments/localVariablesOnReturn.kt");
        }

        @TestMetadata("primaryConstructor.kt")
        public void testPrimaryConstructor() throws Exception {
            runTest("testData/checker/codeFragments/primaryConstructor.kt");
        }

        @TestMetadata("primaryConstructorLocal.kt")
        public void testPrimaryConstructorLocal() throws Exception {
            runTest("testData/checker/codeFragments/primaryConstructorLocal.kt");
        }

        @TestMetadata("privateFunArgumentsResolve.kt")
        public void testPrivateFunArgumentsResolve() throws Exception {
            runTest("testData/checker/codeFragments/privateFunArgumentsResolve.kt");
        }

        @TestMetadata("privateFunTypeArguments.kt")
        public void testPrivateFunTypeArguments() throws Exception {
            runTest("testData/checker/codeFragments/privateFunTypeArguments.kt");
        }

        @TestMetadata("privateMember.kt")
        public void testPrivateMember() throws Exception {
            runTest("testData/checker/codeFragments/privateMember.kt");
        }

        @TestMetadata("privateMembers.kt")
        public void testPrivateMembers() throws Exception {
            runTest("testData/checker/codeFragments/privateMembers.kt");
        }

        @TestMetadata("protectedMember.kt")
        public void testProtectedMember() throws Exception {
            runTest("testData/checker/codeFragments/protectedMember.kt");
        }

        @TestMetadata("secondaryConstructor.kt")
        public void testSecondaryConstructor() throws Exception {
            runTest("testData/checker/codeFragments/secondaryConstructor.kt");
        }

        @TestMetadata("secondaryConstructorWithoutBraces.kt")
        public void testSecondaryConstructorWithoutBraces() throws Exception {
            runTest("testData/checker/codeFragments/secondaryConstructorWithoutBraces.kt");
        }

        @TestMetadata("simpleNameExpression.kt")
        public void testSimpleNameExpression() throws Exception {
            runTest("testData/checker/codeFragments/simpleNameExpression.kt");
        }

        @TestMetadata("smartCasts.kt")
        public void testSmartCasts() throws Exception {
            runTest("testData/checker/codeFragments/smartCasts.kt");
        }

        @TestMetadata("startingFromReturn.kt")
        public void testStartingFromReturn() throws Exception {
            runTest("testData/checker/codeFragments/startingFromReturn.kt");
        }

        @TestMetadata("unusedEquals.kt")
        public void testUnusedEquals() throws Exception {
            runTest("testData/checker/codeFragments/unusedEquals.kt");
        }

        @TestMetadata("withoutBodyFunction.kt")
        public void testWithoutBodyFunction() throws Exception {
            runTest("testData/checker/codeFragments/withoutBodyFunction.kt");
        }

        @TestMetadata("withoutBodyProperty.kt")
        public void testWithoutBodyProperty() throws Exception {
            runTest("testData/checker/codeFragments/withoutBodyProperty.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/checker/codeFragments/imports")
    public static class Imports extends AbstractCodeFragmentHighlightingTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTestWithImport, this, testDataFilePath);
        }

        @TestMetadata("hashMap.kt")
        public void testHashMap() throws Exception {
            runTest("testData/checker/codeFragments/imports/hashMap.kt");
        }
    }
}
