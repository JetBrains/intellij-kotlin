/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.debugger.test;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@RunWith(JUnit3RunnerWithInners.class)
public class SelectExpressionForDebuggerTestGenerated extends AbstractSelectExpressionForDebuggerTest {
    @TestMetadata("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class SelectExpression extends AbstractSelectExpressionForDebuggerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInSelectExpression() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression"), Pattern.compile("^(.+)\\.kt$"), null, false);
        }

        @TestMetadata("annotation.kt")
        public void testAnnotation() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/annotation.kt");
        }

        @TestMetadata("arrayExpression.kt")
        public void testArrayExpression() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/arrayExpression.kt");
        }

        @TestMetadata("binaryExpression.kt")
        public void testBinaryExpression() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/binaryExpression.kt");
        }

        @TestMetadata("call.kt")
        public void testCall() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/call.kt");
        }

        @TestMetadata("companionObjectCall.kt")
        public void testCompanionObjectCall() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/companionObjectCall.kt");
        }

        @TestMetadata("companionObjectCall2.kt")
        public void testCompanionObjectCall2() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/companionObjectCall2.kt");
        }

        @TestMetadata("expressionInPropertyInitializer.kt")
        public void testExpressionInPropertyInitializer() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/expressionInPropertyInitializer.kt");
        }

        @TestMetadata("extensionFun.kt")
        public void testExtensionFun() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/extensionFun.kt");
        }

        @TestMetadata("firstCallInChain.kt")
        public void testFirstCallInChain() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/firstCallInChain.kt");
        }

        @TestMetadata("fullyQualified.kt")
        public void testFullyQualified() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/fullyQualified.kt");
        }

        @TestMetadata("funArgument.kt")
        public void testFunArgument() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/funArgument.kt");
        }

        @TestMetadata("functionLiteral.kt")
        public void testFunctionLiteral() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/functionLiteral.kt");
        }

        @TestMetadata("getConvention.kt")
        public void testGetConvention() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/getConvention.kt");
        }

        @TestMetadata("imports.kt")
        public void testImports() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/imports.kt");
        }

        @TestMetadata("infixCall.kt")
        public void testInfixCall() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/infixCall.kt");
        }

        @TestMetadata("infixCallArgument.kt")
        public void testInfixCallArgument() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/infixCallArgument.kt");
        }

        @TestMetadata("isExpression.kt")
        public void testIsExpression() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/isExpression.kt");
        }

        @TestMetadata("javaStaticMehtodCall.kt")
        public void testJavaStaticMehtodCall() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/javaStaticMehtodCall.kt");
        }

        @TestMetadata("keyword.kt")
        public void testKeyword() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/keyword.kt");
        }

        @TestMetadata("modifier.kt")
        public void testModifier() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/modifier.kt");
        }

        @TestMetadata("nameArgument.kt")
        public void testNameArgument() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/nameArgument.kt");
        }

        @TestMetadata("objectMethodCall.kt")
        public void testObjectMethodCall() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/objectMethodCall.kt");
        }

        @TestMetadata("package.kt")
        public void testPackage() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/package.kt");
        }

        @TestMetadata("param.kt")
        public void testParam() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/param.kt");
        }

        @TestMetadata("propertyCall.kt")
        public void testPropertyCall() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/propertyCall.kt");
        }

        @TestMetadata("propertyDeclaration.kt")
        public void testPropertyDeclaration() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/propertyDeclaration.kt");
        }

        @TestMetadata("qualifiedExpressionProperty.kt")
        public void testQualifiedExpressionProperty() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/qualifiedExpressionProperty.kt");
        }

        @TestMetadata("qualifiedExpressionReceiver.kt")
        public void testQualifiedExpressionReceiver() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/qualifiedExpressionReceiver.kt");
        }

        @TestMetadata("qualifiedExpressionSelector.kt")
        public void testQualifiedExpressionSelector() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/qualifiedExpressionSelector.kt");
        }

        @TestMetadata("super.kt")
        public void testSuper() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/super.kt");
        }

        @TestMetadata("superSelector.kt")
        public void testSuperSelector() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/superSelector.kt");
        }

        @TestMetadata("this.kt")
        public void testThis() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/this.kt");
        }

        @TestMetadata("thisSelector.kt")
        public void testThisSelector() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/thisSelector.kt");
        }

        @TestMetadata("thisWithLabel.kt")
        public void testThisWithLabel() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/thisWithLabel.kt");
        }

        @TestMetadata("unaryExpression.kt")
        public void testUnaryExpression() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/unaryExpression.kt");
        }

        @TestMetadata("userType.kt")
        public void testUserType() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/userType.kt");
        }

        @TestMetadata("userTypeGeneric.kt")
        public void testUserTypeGeneric() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/userTypeGeneric.kt");
        }

        @TestMetadata("userTypeQualified.kt")
        public void testUserTypeQualified() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/userTypeQualified.kt");
        }
    }

    @TestMetadata("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class DisallowMethodCalls extends AbstractSelectExpressionForDebuggerTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTestWoMethodCalls, this, testDataFilePath);
        }

        public void testAllFilesPresentInDisallowMethodCalls() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls"), Pattern.compile("^(.+)\\.kt$"), null, true);
        }

        @TestMetadata("binaryExpression.kt")
        public void testBinaryExpression() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/binaryExpression.kt");
        }

        @TestMetadata("call.kt")
        public void testCall() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/call.kt");
        }

        @TestMetadata("expressionInPropertyInitializer.kt")
        public void testExpressionInPropertyInitializer() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/expressionInPropertyInitializer.kt");
        }

        @TestMetadata("extensionFun.kt")
        public void testExtensionFun() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/extensionFun.kt");
        }

        @TestMetadata("funArgument.kt")
        public void testFunArgument() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/funArgument.kt");
        }

        @TestMetadata("functionLiteral.kt")
        public void testFunctionLiteral() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/functionLiteral.kt");
        }

        @TestMetadata("getConvention.kt")
        public void testGetConvention() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/getConvention.kt");
        }

        @TestMetadata("infixCall.kt")
        public void testInfixCall() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/infixCall.kt");
        }

        @TestMetadata("infixCallArgument.kt")
        public void testInfixCallArgument() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/infixCallArgument.kt");
        }

        @TestMetadata("isExpression.kt")
        public void testIsExpression() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/isExpression.kt");
        }

        @TestMetadata("propertyCall.kt")
        public void testPropertyCall() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/propertyCall.kt");
        }

        @TestMetadata("qualifiedExpressionProperty.kt")
        public void testQualifiedExpressionProperty() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/qualifiedExpressionProperty.kt");
        }

        @TestMetadata("qualifiedExpressionReceiver.kt")
        public void testQualifiedExpressionReceiver() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/qualifiedExpressionReceiver.kt");
        }

        @TestMetadata("qualifiedExpressionSelector.kt")
        public void testQualifiedExpressionSelector() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/qualifiedExpressionSelector.kt");
        }

        @TestMetadata("super.kt")
        public void testSuper() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/super.kt");
        }

        @TestMetadata("superSelector.kt")
        public void testSuperSelector() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/superSelector.kt");
        }

        @TestMetadata("this.kt")
        public void testThis() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/this.kt");
        }

        @TestMetadata("thisSelector.kt")
        public void testThisSelector() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/thisSelector.kt");
        }

        @TestMetadata("thisWithLabel.kt")
        public void testThisWithLabel() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/thisWithLabel.kt");
        }

        @TestMetadata("unaryExpression.kt")
        public void testUnaryExpression() throws Exception {
            runTest("idea/jvm-debugger/jvm-debugger-test/testData/selectExpression/disallowMethodCalls/unaryExpression.kt");
        }
    }
}
