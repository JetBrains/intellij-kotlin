/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.highlighter;

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
@TestMetadata("testData/diagnosticMessage")
public class DiagnosticMessageTestGenerated extends AbstractDiagnosticMessageTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("abstractBaseClassMemberNotImplemented.kt")
    public void testAbstractBaseClassMemberNotImplemented() throws Exception {
        runTest("testData/diagnosticMessage/abstractBaseClassMemberNotImplemented.kt");
    }

    @TestMetadata("annotationsForResolve.kt")
    public void testAnnotationsForResolve() throws Exception {
        runTest("testData/diagnosticMessage/annotationsForResolve.kt");
    }

    @TestMetadata("assignedButNeverAccessedVariable.kt")
    public void testAssignedButNeverAccessedVariable() throws Exception {
        runTest("testData/diagnosticMessage/assignedButNeverAccessedVariable.kt");
    }

    @TestMetadata("callableReferenceResolutionAmbiguityHtml.kt")
    public void testCallableReferenceResolutionAmbiguityHtml() throws Exception {
        runTest("testData/diagnosticMessage/callableReferenceResolutionAmbiguityHtml.kt");
    }

    @TestMetadata("callableReferenceResolutionAmbiguityTxt.kt")
    public void testCallableReferenceResolutionAmbiguityTxt() throws Exception {
        runTest("testData/diagnosticMessage/callableReferenceResolutionAmbiguityTxt.kt");
    }

    @TestMetadata("cannotInferVisibility.kt")
    public void testCannotInferVisibility() throws Exception {
        runTest("testData/diagnosticMessage/cannotInferVisibility.kt");
    }

    @TestMetadata("cannotOverrideInvisibleMember.kt")
    public void testCannotOverrideInvisibleMember() throws Exception {
        runTest("testData/diagnosticMessage/cannotOverrideInvisibleMember.kt");
    }

    @TestMetadata("complexTypeMismatchWithTypeParameters.kt")
    public void testComplexTypeMismatchWithTypeParameters() throws Exception {
        runTest("testData/diagnosticMessage/complexTypeMismatchWithTypeParameters.kt");
    }

    @TestMetadata("complexTypeMismatchWithTypeParametersAndTypeAlias.kt")
    public void testComplexTypeMismatchWithTypeParametersAndTypeAlias() throws Exception {
        runTest("testData/diagnosticMessage/complexTypeMismatchWithTypeParametersAndTypeAlias.kt");
    }

    @TestMetadata("conflictingOverloadsClass.kt")
    public void testConflictingOverloadsClass() throws Exception {
        runTest("testData/diagnosticMessage/conflictingOverloadsClass.kt");
    }

    @TestMetadata("conflictingOverloadsDefaultPackage.kt")
    public void testConflictingOverloadsDefaultPackage() throws Exception {
        runTest("testData/diagnosticMessage/conflictingOverloadsDefaultPackage.kt");
    }

    @TestMetadata("conflictingSubstitutions.kt")
    public void testConflictingSubstitutions() throws Exception {
        runTest("testData/diagnosticMessage/conflictingSubstitutions.kt");
    }

    @TestMetadata("constructorsRedeclaration.kt")
    public void testConstructorsRedeclaration() throws Exception {
        runTest("testData/diagnosticMessage/constructorsRedeclaration.kt");
    }

    @TestMetadata("constructorsRedeclarationTopLevel.kt")
    public void testConstructorsRedeclarationTopLevel() throws Exception {
        runTest("testData/diagnosticMessage/constructorsRedeclarationTopLevel.kt");
    }

    @TestMetadata("differentNamesForSameParameter.kt")
    public void testDifferentNamesForSameParameter() throws Exception {
        runTest("testData/diagnosticMessage/differentNamesForSameParameter.kt");
    }

    @TestMetadata("expectedNothingDueToProjections.kt")
    public void testExpectedNothingDueToProjections() throws Exception {
        runTest("testData/diagnosticMessage/expectedNothingDueToProjections.kt");
    }

    @TestMetadata("extensionInClassReference.kt")
    public void testExtensionInClassReference() throws Exception {
        runTest("testData/diagnosticMessage/extensionInClassReference.kt");
    }

    @TestMetadata("functionPlaceholder.kt")
    public void testFunctionPlaceholder() throws Exception {
        runTest("testData/diagnosticMessage/functionPlaceholder.kt");
    }

    @TestMetadata("illegalSuspendCall.kt")
    public void testIllegalSuspendCall() throws Exception {
        runTest("testData/diagnosticMessage/illegalSuspendCall.kt");
    }

    @TestMetadata("incompatibleEnums.kt")
    public void testIncompatibleEnums() throws Exception {
        runTest("testData/diagnosticMessage/incompatibleEnums.kt");
    }

    @TestMetadata("invisibleMember.kt")
    public void testInvisibleMember() throws Exception {
        runTest("testData/diagnosticMessage/invisibleMember.kt");
    }

    @TestMetadata("multipleDefaultsFromSupertypes.kt")
    public void testMultipleDefaultsFromSupertypes() throws Exception {
        runTest("testData/diagnosticMessage/multipleDefaultsFromSupertypes.kt");
    }

    @TestMetadata("nameInConstraintIsNotATypeParameter.kt")
    public void testNameInConstraintIsNotATypeParameter() throws Exception {
        runTest("testData/diagnosticMessage/nameInConstraintIsNotATypeParameter.kt");
    }

    @TestMetadata("nestedClassAcessedViaInstanceReference.kt")
    public void testNestedClassAcessedViaInstanceReference() throws Exception {
        runTest("testData/diagnosticMessage/nestedClassAcessedViaInstanceReference.kt");
    }

    @TestMetadata("nestedClassShouldBeQualified.kt")
    public void testNestedClassShouldBeQualified() throws Exception {
        runTest("testData/diagnosticMessage/nestedClassShouldBeQualified.kt");
    }

    @TestMetadata("noneApplicable.kt")
    public void testNoneApplicable() throws Exception {
        runTest("testData/diagnosticMessage/noneApplicable.kt");
    }

    @TestMetadata("noneApplicableCallableReference.kt")
    public void testNoneApplicableCallableReference() throws Exception {
        runTest("testData/diagnosticMessage/noneApplicableCallableReference.kt");
    }

    @TestMetadata("noneApplicableConstructor.kt")
    public void testNoneApplicableConstructor() throws Exception {
        runTest("testData/diagnosticMessage/noneApplicableConstructor.kt");
    }

    @TestMetadata("noneApplicableGeneric.kt")
    public void testNoneApplicableGeneric() throws Exception {
        runTest("testData/diagnosticMessage/noneApplicableGeneric.kt");
    }

    @TestMetadata("noneApplicableHtml.kt")
    public void testNoneApplicableHtml() throws Exception {
        runTest("testData/diagnosticMessage/noneApplicableHtml.kt");
    }

    @TestMetadata("noneApplicableTxt.kt")
    public void testNoneApplicableTxt() throws Exception {
        runTest("testData/diagnosticMessage/noneApplicableTxt.kt");
    }

    @TestMetadata("numberValueTypes.kt")
    public void testNumberValueTypes() throws Exception {
        runTest("testData/diagnosticMessage/numberValueTypes.kt");
    }

    @TestMetadata("overloadResolutionAmbiguityHtml.kt")
    public void testOverloadResolutionAmbiguityHtml() throws Exception {
        runTest("testData/diagnosticMessage/overloadResolutionAmbiguityHtml.kt");
    }

    @TestMetadata("overloadResolutionAmbiguityTxt.kt")
    public void testOverloadResolutionAmbiguityTxt() throws Exception {
        runTest("testData/diagnosticMessage/overloadResolutionAmbiguityTxt.kt");
    }

    @TestMetadata("renderCollectionOfTypes.kt")
    public void testRenderCollectionOfTypes() throws Exception {
        runTest("testData/diagnosticMessage/renderCollectionOfTypes.kt");
    }

    @TestMetadata("returnTypeMismatchOnOverride.kt")
    public void testReturnTypeMismatchOnOverride() throws Exception {
        runTest("testData/diagnosticMessage/returnTypeMismatchOnOverride.kt");
    }

    @TestMetadata("typeInferenceCannotCaptureTypes.kt")
    public void testTypeInferenceCannotCaptureTypes() throws Exception {
        runTest("testData/diagnosticMessage/typeInferenceCannotCaptureTypes.kt");
    }

    @TestMetadata("typeInferenceExpectedTypeMismatch.kt")
    public void testTypeInferenceExpectedTypeMismatch() throws Exception {
        runTest("testData/diagnosticMessage/typeInferenceExpectedTypeMismatch.kt");
    }

    @TestMetadata("typeMismatchDueToProjections.kt")
    public void testTypeMismatchDueToProjections() throws Exception {
        runTest("testData/diagnosticMessage/typeMismatchDueToProjections.kt");
    }

    @TestMetadata("typeMismatchDueToProjectionsIn.kt")
    public void testTypeMismatchDueToProjectionsIn() throws Exception {
        runTest("testData/diagnosticMessage/typeMismatchDueToProjectionsIn.kt");
    }

    @TestMetadata("typeMismatchDueToProjectionsTxt.kt")
    public void testTypeMismatchDueToProjectionsTxt() throws Exception {
        runTest("testData/diagnosticMessage/typeMismatchDueToProjectionsTxt.kt");
    }

    @TestMetadata("typeMismatchWithFunctionalType.kt")
    public void testTypeMismatchWithFunctionalType() throws Exception {
        runTest("testData/diagnosticMessage/typeMismatchWithFunctionalType.kt");
    }

    @TestMetadata("typeMismatchWithNothing.kt")
    public void testTypeMismatchWithNothing() throws Exception {
        runTest("testData/diagnosticMessage/typeMismatchWithNothing.kt");
    }

    @TestMetadata("typeMismatchWithTypeParameters.kt")
    public void testTypeMismatchWithTypeParameters() throws Exception {
        runTest("testData/diagnosticMessage/typeMismatchWithTypeParameters.kt");
    }

    @TestMetadata("typeParameterAsReified.kt")
    public void testTypeParameterAsReified() throws Exception {
        runTest("testData/diagnosticMessage/typeParameterAsReified.kt");
    }

    @TestMetadata("typeVarianceConflictInTypeAliasExpansion.kt")
    public void testTypeVarianceConflictInTypeAliasExpansion() throws Exception {
        runTest("testData/diagnosticMessage/typeVarianceConflictInTypeAliasExpansion.kt");
    }

    @TestMetadata("unsupportedFeature.kt")
    public void testUnsupportedFeature() throws Exception {
        runTest("testData/diagnosticMessage/unsupportedFeature.kt");
    }

    @TestMetadata("unusedParameter.kt")
    public void testUnusedParameter() throws Exception {
        runTest("testData/diagnosticMessage/unusedParameter.kt");
    }

    @TestMetadata("unusedValue.kt")
    public void testUnusedValue() throws Exception {
        runTest("testData/diagnosticMessage/unusedValue.kt");
    }

    @TestMetadata("unusedVariable.kt")
    public void testUnusedVariable() throws Exception {
        runTest("testData/diagnosticMessage/unusedVariable.kt");
    }

    @TestMetadata("upperBoundViolated.kt")
    public void testUpperBoundViolated() throws Exception {
        runTest("testData/diagnosticMessage/upperBoundViolated.kt");
    }

    @TestMetadata("upperBoundViolatedInTypeAliasConstructorCall.kt")
    public void testUpperBoundViolatedInTypeAliasConstructorCall() throws Exception {
        runTest("testData/diagnosticMessage/upperBoundViolatedInTypeAliasConstructorCall.kt");
    }

    @TestMetadata("urlRender.kt")
    public void testUrlRender() throws Exception {
        runTest("testData/diagnosticMessage/urlRender.kt");
    }
}
