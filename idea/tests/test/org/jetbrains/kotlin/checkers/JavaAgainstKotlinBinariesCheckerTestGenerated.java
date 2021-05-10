/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.checkers;

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
@TestMetadata("testData/kotlinAndJavaChecker/javaAgainstKotlin")
public class JavaAgainstKotlinBinariesCheckerTestGenerated extends AbstractJavaAgainstKotlinBinariesCheckerTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("AssignKotlinClassToObjectInJava.kt")
    public void testAssignKotlinClassToObjectInJava() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/AssignKotlinClassToObjectInJava.kt");
    }

    @TestMetadata("AssignMappedKotlinType.kt")
    public void testAssignMappedKotlinType() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/AssignMappedKotlinType.kt");
    }

    @TestMetadata("ClassObjects.kt")
    public void testClassObjects() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/ClassObjects.kt");
    }

    @TestMetadata("EnumAutoGeneratedMethods.kt")
    public void testEnumAutoGeneratedMethods() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/EnumAutoGeneratedMethods.kt");
    }

    @TestMetadata("EnumEntriesInSwitch.kt")
    public void testEnumEntriesInSwitch() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/EnumEntriesInSwitch.kt");
    }

    @TestMetadata("EnumStaticImportInJava.kt")
    public void testEnumStaticImportInJava() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/EnumStaticImportInJava.kt");
    }

    @TestMetadata("ExtendClassWithDefaultImplementationComplex.kt")
    public void testExtendClassWithDefaultImplementationComplex() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/ExtendClassWithDefaultImplementationComplex.kt");
    }

    @TestMetadata("ExtendClassWithDefaultImplementation_1_6.kt")
    public void testExtendClassWithDefaultImplementation_1_6() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/ExtendClassWithDefaultImplementation_1_6.kt");
    }

    @TestMetadata("ExtendClassWithDefaultImplementation_1_8.kt")
    public void testExtendClassWithDefaultImplementation_1_8() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/ExtendClassWithDefaultImplementation_1_8.kt");
    }

    @TestMetadata("ExtendClassWithJvmDefaultImplementation.kt")
    public void testExtendClassWithJvmDefaultImplementation() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/ExtendClassWithJvmDefaultImplementation.kt");
    }

    @TestMetadata("ExtendInterfaceWithDefaultMethodAndCompatibilityAll.kt")
    public void testExtendInterfaceWithDefaultMethodAndCompatibilityAll() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/ExtendInterfaceWithDefaultMethodAndCompatibilityAll.kt");
    }

    @TestMetadata("ExtendInterfaceWithDefaultMethodAndCompatibilityAllCompatibility.kt")
    public void testExtendInterfaceWithDefaultMethodAndCompatibilityAllCompatibility() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/ExtendInterfaceWithDefaultMethodAndCompatibilityAllCompatibility.kt");
    }

    @TestMetadata("ExtendInterfaceWithDefaultMethodAndCompatibilityDisabled.kt")
    public void testExtendInterfaceWithDefaultMethodAndCompatibilityDisabled() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/ExtendInterfaceWithDefaultMethodAndCompatibilityDisabled.kt");
    }

    @TestMetadata("ExtendingMutableInterfaces.kt")
    public void testExtendingMutableInterfaces() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/ExtendingMutableInterfaces.kt");
    }

    @TestMetadata("ExtendingReadOnlyInterfaces.kt")
    public void testExtendingReadOnlyInterfaces() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/ExtendingReadOnlyInterfaces.kt");
    }

    @TestMetadata("FunctionInNestedClassInDataFlowInspection.kt")
    public void testFunctionInNestedClassInDataFlowInspection() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/FunctionInNestedClassInDataFlowInspection.kt");
    }

    @TestMetadata("ImplementedMethodsFromTraits.kt")
    public void testImplementedMethodsFromTraits() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/ImplementedMethodsFromTraits.kt");
    }

    @TestMetadata("InferenceReturnType_1_6.kt")
    public void testInferenceReturnType_1_6() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/InferenceReturnType_1_6.kt");
    }

    @TestMetadata("InferenceReturnType_1_8.kt")
    public void testInferenceReturnType_1_8() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/InferenceReturnType_1_8.kt");
    }

    @TestMetadata("Interface.kt")
    public void testInterface() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/Interface.kt");
    }

    @TestMetadata("InterfaceDefaultImpls.kt")
    public void testInterfaceDefaultImpls() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/InterfaceDefaultImpls.kt");
    }

    @TestMetadata("InterfaceWithJvmStatic.kt")
    public void testInterfaceWithJvmStatic() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/InterfaceWithJvmStatic.kt");
    }

    @TestMetadata("JvmOverloadsFunctions.kt")
    public void testJvmOverloadsFunctions() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/JvmOverloadsFunctions.kt");
    }

    @TestMetadata("KotlinAnnotations.kt")
    public void testKotlinAnnotations() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/KotlinAnnotations.kt");
    }

    @TestMetadata("MyFunctionType.kt")
    public void testMyFunctionType() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/MyFunctionType.kt");
    }

    @TestMetadata("OverridesAmbiguity.kt")
    public void testOverridesAmbiguity() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/OverridesAmbiguity.kt");
    }

    @TestMetadata("ReturnInnerClasses.kt")
    public void testReturnInnerClasses() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/ReturnInnerClasses.kt");
    }

    @TestMetadata("ThrowsOnGenericMethod.kt")
    public void testThrowsOnGenericMethod() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/ThrowsOnGenericMethod.kt");
    }

    @TestMetadata("TopLevelFunctionInDataFlowInspection.kt")
    public void testTopLevelFunctionInDataFlowInspection() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/TopLevelFunctionInDataFlowInspection.kt");
    }

    @TestMetadata("TopLevelFunctionWithNameSimilarToClassInDataFlowInspection.kt")
    public void testTopLevelFunctionWithNameSimilarToClassInDataFlowInspection() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/TopLevelFunctionWithNameSimilarToClassInDataFlowInspection.kt");
    }

    @TestMetadata("TopLevelFunctionWithNameSimilarToPropertyInDataFlowInspection.kt")
    public void testTopLevelFunctionWithNameSimilarToPropertyInDataFlowInspection() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/TopLevelFunctionWithNameSimilarToPropertyInDataFlowInspection.kt");
    }

    @TestMetadata("TopLevelOverloadedFunctionInDataFlowInspection.kt")
    public void testTopLevelOverloadedFunctionInDataFlowInspection() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/TopLevelOverloadedFunctionInDataFlowInspection.kt");
    }

    @TestMetadata("UseKotlinConstInSwitch.kt")
    public void testUseKotlinConstInSwitch() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/UseKotlinConstInSwitch.kt");
    }

    @TestMetadata("UseKotlinSubclassesOfMappedTypes.kt")
    public void testUseKotlinSubclassesOfMappedTypes() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/UseKotlinSubclassesOfMappedTypes.kt");
    }

    @TestMetadata("UsingKotlinPackageDeclarations.kt")
    public void testUsingKotlinPackageDeclarations() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/UsingKotlinPackageDeclarations.kt");
    }

    @TestMetadata("UsingMutableInterfaces.kt")
    public void testUsingMutableInterfaces() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/UsingMutableInterfaces.kt");
    }

    @TestMetadata("UsingReadOnlyInterfaces.kt")
    public void testUsingReadOnlyInterfaces() throws Exception {
        runTest("testData/kotlinAndJavaChecker/javaAgainstKotlin/UsingReadOnlyInterfaces.kt");
    }
}
