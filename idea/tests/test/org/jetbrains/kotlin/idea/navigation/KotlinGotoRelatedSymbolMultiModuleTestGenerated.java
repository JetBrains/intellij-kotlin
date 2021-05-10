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
@TestMetadata("testData/navigation/relatedSymbols/multiModule")
public class KotlinGotoRelatedSymbolMultiModuleTestGenerated extends AbstractKotlinGotoRelatedSymbolMultiModuleTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("fromActualMemberFunToExpect")
    public void testFromActualMemberFunToExpect() throws Exception {
        runTest("testData/navigation/relatedSymbols/multiModule/fromActualMemberFunToExpect/");
    }

    @TestMetadata("fromActualMemberValToExpect")
    public void testFromActualMemberValToExpect() throws Exception {
        runTest("testData/navigation/relatedSymbols/multiModule/fromActualMemberValToExpect/");
    }

    @TestMetadata("fromExpectMemberFunToActuals")
    public void testFromExpectMemberFunToActuals() throws Exception {
        runTest("testData/navigation/relatedSymbols/multiModule/fromExpectMemberFunToActuals/");
    }

    @TestMetadata("fromExpectMemberValToActuals")
    public void testFromExpectMemberValToActuals() throws Exception {
        runTest("testData/navigation/relatedSymbols/multiModule/fromExpectMemberValToActuals/");
    }

    @TestMetadata("fromNestedActualClassToExpect")
    public void testFromNestedActualClassToExpect() throws Exception {
        runTest("testData/navigation/relatedSymbols/multiModule/fromNestedActualClassToExpect/");
    }

    @TestMetadata("fromNestedExpectClassToActuals")
    public void testFromNestedExpectClassToActuals() throws Exception {
        runTest("testData/navigation/relatedSymbols/multiModule/fromNestedExpectClassToActuals/");
    }

    @TestMetadata("fromTopLevelActualClassToExpect")
    public void testFromTopLevelActualClassToExpect() throws Exception {
        runTest("testData/navigation/relatedSymbols/multiModule/fromTopLevelActualClassToExpect/");
    }

    @TestMetadata("fromTopLevelActualFunToExpect")
    public void testFromTopLevelActualFunToExpect() throws Exception {
        runTest("testData/navigation/relatedSymbols/multiModule/fromTopLevelActualFunToExpect/");
    }

    @TestMetadata("fromTopLevelActualValToExpect")
    public void testFromTopLevelActualValToExpect() throws Exception {
        runTest("testData/navigation/relatedSymbols/multiModule/fromTopLevelActualValToExpect/");
    }

    @TestMetadata("fromTopLevelExpectClassToActuals")
    public void testFromTopLevelExpectClassToActuals() throws Exception {
        runTest("testData/navigation/relatedSymbols/multiModule/fromTopLevelExpectClassToActuals/");
    }

    @TestMetadata("fromTopLevelExpectFunToActuals")
    public void testFromTopLevelExpectFunToActuals() throws Exception {
        runTest("testData/navigation/relatedSymbols/multiModule/fromTopLevelExpectFunToActuals/");
    }

    @TestMetadata("fromTopLevelExpectValToActuals")
    public void testFromTopLevelExpectValToActuals() throws Exception {
        runTest("testData/navigation/relatedSymbols/multiModule/fromTopLevelExpectValToActuals/");
    }
}
