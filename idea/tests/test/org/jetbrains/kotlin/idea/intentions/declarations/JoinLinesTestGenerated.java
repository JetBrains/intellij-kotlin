/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.intentions.declarations;

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
@TestRoot("idea/tests")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
@TestMetadata("testData/joinLines")
public abstract class JoinLinesTestGenerated extends AbstractJoinLinesTest {
    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/joinLines/addSemicolon")
    public static class AddSemicolon extends AbstractJoinLinesTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("ClassDeclarations.kt")
        public void testClassDeclarations() throws Exception {
            runTest("testData/joinLines/addSemicolon/ClassDeclarations.kt");
        }

        @TestMetadata("FunctionCalls.kt")
        public void testFunctionCalls() throws Exception {
            runTest("testData/joinLines/addSemicolon/FunctionCalls.kt");
        }

        @TestMetadata("FunctionCallsAndAComment.kt")
        public void testFunctionCallsAndAComment() throws Exception {
            runTest("testData/joinLines/addSemicolon/FunctionCallsAndAComment.kt");
        }

        @TestMetadata("FunctionCallsAndAMultilineComment.kt")
        public void testFunctionCallsAndAMultilineComment() throws Exception {
            runTest("testData/joinLines/addSemicolon/FunctionCallsAndAMultilineComment.kt");
        }

        @TestMetadata("FunctionCallsAndSeveralComments.kt")
        public void testFunctionCallsAndSeveralComments() throws Exception {
            runTest("testData/joinLines/addSemicolon/FunctionCallsAndSeveralComments.kt");
        }

        @TestMetadata("FunctionCallsWithMutlineSeparator.kt")
        public void testFunctionCallsWithMutlineSeparator() throws Exception {
            runTest("testData/joinLines/addSemicolon/FunctionCallsWithMutlineSeparator.kt");
        }

        @TestMetadata("FunctionDeclarations.kt")
        public void testFunctionDeclarations() throws Exception {
            runTest("testData/joinLines/addSemicolon/FunctionDeclarations.kt");
        }

        @TestMetadata("InsideBraces.kt")
        public void testInsideBraces() throws Exception {
            runTest("testData/joinLines/addSemicolon/InsideBraces.kt");
        }

        @TestMetadata("InsideIfBlock.kt")
        public void testInsideIfBlock() throws Exception {
            runTest("testData/joinLines/addSemicolon/InsideIfBlock.kt");
        }

        @TestMetadata("LocalAssingments.kt")
        public void testLocalAssingments() throws Exception {
            runTest("testData/joinLines/addSemicolon/LocalAssingments.kt");
        }

        @TestMetadata("LocalAssingmentsWithSemicolon.kt")
        public void testLocalAssingmentsWithSemicolon() throws Exception {
            runTest("testData/joinLines/addSemicolon/LocalAssingmentsWithSemicolon.kt");
        }

        @TestMetadata("LocalClasses.kt")
        public void testLocalClasses() throws Exception {
            runTest("testData/joinLines/addSemicolon/LocalClasses.kt");
        }

        @TestMetadata("LocalFunctions.kt")
        public void testLocalFunctions() throws Exception {
            runTest("testData/joinLines/addSemicolon/LocalFunctions.kt");
        }

        @TestMetadata("MemberFunctions.kt")
        public void testMemberFunctions() throws Exception {
            runTest("testData/joinLines/addSemicolon/MemberFunctions.kt");
        }

        @TestMetadata("WhileAndACall.kt")
        public void testWhileAndACall() throws Exception {
            runTest("testData/joinLines/addSemicolon/WhileAndACall.kt");
        }

        @TestMetadata("WhileBlockAndACall.kt")
        public void testWhileBlockAndACall() throws Exception {
            runTest("testData/joinLines/addSemicolon/WhileBlockAndACall.kt");
        }

        @TestMetadata("WhileBlockAndACall2.kt")
        public void testWhileBlockAndACall2() throws Exception {
            runTest("testData/joinLines/addSemicolon/WhileBlockAndACall2.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/joinLines/declarationAndAssignment")
    public static class DeclarationAndAssignment extends AbstractJoinLinesTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("blankLineBetween.kt")
        public void testBlankLineBetween() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/blankLineBetween.kt");
        }

        @TestMetadata("commentBetween.kt")
        public void testCommentBetween() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/commentBetween.kt");
        }

        @TestMetadata("longInit.kt")
        public void testLongInit() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/longInit.kt");
        }

        @TestMetadata("longInit2.kt")
        public void testLongInit2() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/longInit2.kt");
        }

        @TestMetadata("propertyWithAnnotation.kt")
        public void testPropertyWithAnnotation() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/propertyWithAnnotation.kt");
        }

        @TestMetadata("simpleInit.kt")
        public void testSimpleInit() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/simpleInit.kt");
        }

        @TestMetadata("simpleInit2.kt")
        public void testSimpleInit2() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/simpleInit2.kt");
        }

        @TestMetadata("simpleInitWithBackticks.kt")
        public void testSimpleInitWithBackticks() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/simpleInitWithBackticks.kt");
        }

        @TestMetadata("simpleInitWithBackticks2.kt")
        public void testSimpleInitWithBackticks2() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/simpleInitWithBackticks2.kt");
        }

        @TestMetadata("simpleInitWithBackticks3.kt")
        public void testSimpleInitWithBackticks3() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/simpleInitWithBackticks3.kt");
        }

        @TestMetadata("simpleInitWithComments.kt")
        public void testSimpleInitWithComments() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/simpleInitWithComments.kt");
        }

        @TestMetadata("simpleInitWithComments2.kt")
        public void testSimpleInitWithComments2() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/simpleInitWithComments2.kt");
        }

        @TestMetadata("simpleInitWithSemicolons.kt")
        public void testSimpleInitWithSemicolons() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/simpleInitWithSemicolons.kt");
        }

        @TestMetadata("simpleInitWithSemicolons2.kt")
        public void testSimpleInitWithSemicolons2() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/simpleInitWithSemicolons2.kt");
        }

        @TestMetadata("simpleInitWithSemicolons3.kt")
        public void testSimpleInitWithSemicolons3() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/simpleInitWithSemicolons3.kt");
        }

        @TestMetadata("simpleInitWithType.kt")
        public void testSimpleInitWithType() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/simpleInitWithType.kt");
        }

        @TestMetadata("simpleInitWithType2.kt")
        public void testSimpleInitWithType2() throws Exception {
            runTest("testData/joinLines/declarationAndAssignment/simpleInitWithType2.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/joinLines/initializerAndIfToElvis")
    public static class InitializerAndIfToElvis extends AbstractJoinLinesTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("simple.kt")
        public void testSimple() throws Exception {
            runTest("testData/joinLines/initializerAndIfToElvis/simple.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/joinLines/nestedIfs")
    public static class NestedIfs extends AbstractJoinLinesTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("BlockBody.kt")
        public void testBlockBody() throws Exception {
            runTest("testData/joinLines/nestedIfs/BlockBody.kt");
        }

        @TestMetadata("ComplexCondition1.kt")
        public void testComplexCondition1() throws Exception {
            runTest("testData/joinLines/nestedIfs/ComplexCondition1.kt");
        }

        @TestMetadata("ComplexCondition2.kt")
        public void testComplexCondition2() throws Exception {
            runTest("testData/joinLines/nestedIfs/ComplexCondition2.kt");
        }

        @TestMetadata("InnerWithElse.kt")
        public void testInnerWithElse() throws Exception {
            runTest("testData/joinLines/nestedIfs/InnerWithElse.kt");
        }

        @TestMetadata("OuterWithElse.kt")
        public void testOuterWithElse() throws Exception {
            runTest("testData/joinLines/nestedIfs/OuterWithElse.kt");
        }

        @TestMetadata("Simple.kt")
        public void testSimple() throws Exception {
            runTest("testData/joinLines/nestedIfs/Simple.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/joinLines/removeBraces")
    public static class RemoveBraces extends AbstractJoinLinesTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("CommentAfterStatement.kt")
        public void testCommentAfterStatement() throws Exception {
            runTest("testData/joinLines/removeBraces/CommentAfterStatement.kt");
        }

        @TestMetadata("CommentAfterStatement2.kt")
        public void testCommentAfterStatement2() throws Exception {
            runTest("testData/joinLines/removeBraces/CommentAfterStatement2.kt");
        }

        @TestMetadata("CommentBeforeStatement.kt")
        public void testCommentBeforeStatement() throws Exception {
            runTest("testData/joinLines/removeBraces/CommentBeforeStatement.kt");
        }

        @TestMetadata("CommentBeforeStatement2.kt")
        public void testCommentBeforeStatement2() throws Exception {
            runTest("testData/joinLines/removeBraces/CommentBeforeStatement2.kt");
        }

        @TestMetadata("DoWhile.kt")
        public void testDoWhile() throws Exception {
            runTest("testData/joinLines/removeBraces/DoWhile.kt");
        }

        @TestMetadata("Else.kt")
        public void testElse() throws Exception {
            runTest("testData/joinLines/removeBraces/Else.kt");
        }

        @TestMetadata("For.kt")
        public void testFor() throws Exception {
            runTest("testData/joinLines/removeBraces/For.kt");
        }

        @TestMetadata("FunctionBody.kt")
        public void testFunctionBody() throws Exception {
            runTest("testData/joinLines/removeBraces/FunctionBody.kt");
        }

        @TestMetadata("FunctionWithOneLineReturn.kt")
        public void testFunctionWithOneLineReturn() throws Exception {
            runTest("testData/joinLines/removeBraces/FunctionWithOneLineReturn.kt");
        }

        @TestMetadata("If.kt")
        public void testIf() throws Exception {
            runTest("testData/joinLines/removeBraces/If.kt");
        }

        @TestMetadata("IfWithElse.kt")
        public void testIfWithElse() throws Exception {
            runTest("testData/joinLines/removeBraces/IfWithElse.kt");
        }

        @TestMetadata("IfWithElseBlockInAssignment.kt")
        public void testIfWithElseBlockInAssignment() throws Exception {
            runTest("testData/joinLines/removeBraces/IfWithElseBlockInAssignment.kt");
        }

        @TestMetadata("IfWithPostfixAfterElseBlock.kt")
        public void testIfWithPostfixAfterElseBlock() throws Exception {
            runTest("testData/joinLines/removeBraces/IfWithPostfixAfterElseBlock.kt");
        }

        @TestMetadata("IfWithQualifiedAfterElseBlock.kt")
        public void testIfWithQualifiedAfterElseBlock() throws Exception {
            runTest("testData/joinLines/removeBraces/IfWithQualifiedAfterElseBlock.kt");
        }

        @TestMetadata("LambdaBody.kt")
        public void testLambdaBody() throws Exception {
            runTest("testData/joinLines/removeBraces/LambdaBody.kt");
        }

        @TestMetadata("NotSingleLineStatement.kt")
        public void testNotSingleLineStatement() throws Exception {
            runTest("testData/joinLines/removeBraces/NotSingleLineStatement.kt");
        }

        @TestMetadata("TwoStatements.kt")
        public void testTwoStatements() throws Exception {
            runTest("testData/joinLines/removeBraces/TwoStatements.kt");
        }

        @TestMetadata("WhenEntry.kt")
        public void testWhenEntry() throws Exception {
            runTest("testData/joinLines/removeBraces/WhenEntry.kt");
        }

        @TestMetadata("While.kt")
        public void testWhile() throws Exception {
            runTest("testData/joinLines/removeBraces/While.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/joinLines/removeTrailingComma")
    public static class RemoveTrailingComma extends AbstractJoinLinesTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("destructuringDeclarations.kt")
        public void testDestructuringDeclarations() throws Exception {
            runTest("testData/joinLines/removeTrailingComma/destructuringDeclarations.kt");
        }

        @TestMetadata("destructuringDeclarationsInLambda.kt")
        public void testDestructuringDeclarationsInLambda() throws Exception {
            runTest("testData/joinLines/removeTrailingComma/destructuringDeclarationsInLambda.kt");
        }

        @TestMetadata("functionArguments.kt")
        public void testFunctionArguments() throws Exception {
            runTest("testData/joinLines/removeTrailingComma/functionArguments.kt");
        }

        @TestMetadata("indices.kt")
        public void testIndices() throws Exception {
            runTest("testData/joinLines/removeTrailingComma/indices.kt");
        }

        @TestMetadata("inner.kt")
        public void testInner() throws Exception {
            runTest("testData/joinLines/removeTrailingComma/inner.kt");
        }

        @TestMetadata("lambda.kt")
        public void testLambda() throws Exception {
            runTest("testData/joinLines/removeTrailingComma/lambda.kt");
        }

        @TestMetadata("literal.kt")
        public void testLiteral() throws Exception {
            runTest("testData/joinLines/removeTrailingComma/literal.kt");
        }

        @TestMetadata("simple.kt")
        public void testSimple() throws Exception {
            runTest("testData/joinLines/removeTrailingComma/simple.kt");
        }

        @TestMetadata("typeArguments.kt")
        public void testTypeArguments() throws Exception {
            runTest("testData/joinLines/removeTrailingComma/typeArguments.kt");
        }

        @TestMetadata("typeParameters.kt")
        public void testTypeParameters() throws Exception {
            runTest("testData/joinLines/removeTrailingComma/typeParameters.kt");
        }

        @TestMetadata("whenEntry.kt")
        public void testWhenEntry() throws Exception {
            runTest("testData/joinLines/removeTrailingComma/whenEntry.kt");
        }

        @TestMetadata("whenEntry2.kt")
        public void testWhenEntry2() throws Exception {
            runTest("testData/joinLines/removeTrailingComma/whenEntry2.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/joinLines/stringTemplate")
    public static class StringTemplate extends AbstractJoinLinesTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("firstLineVariable.kt")
        public void testFirstLineVariable() throws Exception {
            runTest("testData/joinLines/stringTemplate/firstLineVariable.kt");
        }

        @TestMetadata("lineWithMultiStringLiteral.kt")
        public void testLineWithMultiStringLiteral() throws Exception {
            runTest("testData/joinLines/stringTemplate/lineWithMultiStringLiteral.kt");
        }

        @TestMetadata("lineWithMultiStringLiteralAndVariable.kt")
        public void testLineWithMultiStringLiteralAndVariable() throws Exception {
            runTest("testData/joinLines/stringTemplate/lineWithMultiStringLiteralAndVariable.kt");
        }

        @TestMetadata("secondLineVariable.kt")
        public void testSecondLineVariable() throws Exception {
            runTest("testData/joinLines/stringTemplate/secondLineVariable.kt");
        }

        @TestMetadata("simple.kt")
        public void testSimple() throws Exception {
            runTest("testData/joinLines/stringTemplate/simple.kt");
        }

        @TestMetadata("threeLines.kt")
        public void testThreeLines() throws Exception {
            runTest("testData/joinLines/stringTemplate/threeLines.kt");
        }

        @TestMetadata("threeLines2.kt")
        public void testThreeLines2() throws Exception {
            runTest("testData/joinLines/stringTemplate/threeLines2.kt");
        }
    }
}
