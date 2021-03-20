/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.frontend.api.symbols;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("idea/idea-frontend-fir/testData/symbols/symbolByPsi")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class SymbolByPsiTestGenerated extends AbstractSymbolByPsiTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInSymbolByPsi() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("idea/idea-frontend-fir/testData/symbols/symbolByPsi"), Pattern.compile("^(.+)\\.kt$"), null, true);
    }

    @TestMetadata("annotations.kt")
    public void testAnnotations() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/annotations.kt");
    }

    @TestMetadata("anonymousObject.kt")
    public void testAnonymousObject() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/anonymousObject.kt");
    }

    @TestMetadata("class.kt")
    public void testClass() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/class.kt");
    }

    @TestMetadata("classMembes.kt")
    public void testClassMembes() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/classMembes.kt");
    }

    @TestMetadata("classPrimaryConstructor.kt")
    public void testClassPrimaryConstructor() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/classPrimaryConstructor.kt");
    }

    @TestMetadata("classSecondaryConstructors.kt")
    public void testClassSecondaryConstructors() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/classSecondaryConstructors.kt");
    }

    @TestMetadata("classWithTypeParams.kt")
    public void testClassWithTypeParams() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/classWithTypeParams.kt");
    }

    @TestMetadata("enum.kt")
    public void testEnum() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/enum.kt");
    }

    @TestMetadata("extensionFunction.kt")
    public void testExtensionFunction() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/extensionFunction.kt");
    }

    @TestMetadata("function.kt")
    public void testFunction() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/function.kt");
    }

    @TestMetadata("functionWithTypeParams.kt")
    public void testFunctionWithTypeParams() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/functionWithTypeParams.kt");
    }

    @TestMetadata("implicitReturn.kt")
    public void testImplicitReturn() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/implicitReturn.kt");
    }

    @TestMetadata("localDeclarations.kt")
    public void testLocalDeclarations() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/localDeclarations.kt");
    }

    @TestMetadata("memberFunctions.kt")
    public void testMemberFunctions() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/memberFunctions.kt");
    }

    @TestMetadata("memberProperties.kt")
    public void testMemberProperties() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/memberProperties.kt");
    }

    @TestMetadata("topLevelFunctions.kt")
    public void testTopLevelFunctions() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/topLevelFunctions.kt");
    }

    @TestMetadata("topLevelProperties.kt")
    public void testTopLevelProperties() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/topLevelProperties.kt");
    }

    @TestMetadata("typeAnnotations.kt")
    public void testTypeAnnotations() throws Exception {
        runTest("idea/idea-frontend-fir/testData/symbols/symbolByPsi/typeAnnotations.kt");
    }
}
