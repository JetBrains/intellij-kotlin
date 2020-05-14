/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.findUsages;

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
@TestRoot("idea")
@SuppressWarnings("all")
@TestMetadata("testData/findUsages/kotlin/conventions/components")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class FindUsagesWithDisableComponentSearchTestGenerated extends AbstractFindUsagesWithDisableComponentSearchTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("callableReferences.0.kt")
    public void testCallableReferences() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/callableReferences.0.kt");
    }

    @TestMetadata("companionObjectAccess.0.kt")
    public void testCompanionObjectAccess() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/companionObjectAccess.0.kt");
    }

    @TestMetadata("componentFunForGenericType1.0.kt")
    public void testComponentFunForGenericType1() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/componentFunForGenericType1.0.kt");
    }

    @TestMetadata("componentFunForGenericType2.0.kt")
    public void testComponentFunForGenericType2() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/componentFunForGenericType2.0.kt");
    }

    @TestMetadata("dataClass.0.kt")
    public void testDataClass() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/dataClass.0.kt");
    }

    @TestMetadata("dataClassComponentByRef.0.kt")
    public void testDataClassComponentByRef() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/dataClassComponentByRef.0.kt");
    }

    @TestMetadata("dataClassFromStdlib.0.kt")
    public void testDataClassFromStdlib() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/dataClassFromStdlib.0.kt");
    }

    @TestMetadata("dataClassInsideDataClass.0.kt")
    public void testDataClassInsideDataClass() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/dataClassInsideDataClass.0.kt");
    }

    @TestMetadata("extensionComponentFun.0.kt")
    public void testExtensionComponentFun() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/extensionComponentFun.0.kt");
    }

    @TestMetadata("for.0.kt")
    public void testFor() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/for.0.kt");
    }

    @TestMetadata("isAndAs.0.kt")
    public void testIsAndAs() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/isAndAs.0.kt");
    }

    @TestMetadata("lambdas.0.kt")
    public void testLambdas() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/lambdas.0.kt");
    }

    @TestMetadata("mayTypeAffectAncestors.0.kt")
    public void testMayTypeAffectAncestors() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/mayTypeAffectAncestors.0.kt");
    }

    @TestMetadata("memberComponentFun.0.kt")
    public void testMemberComponentFun() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/memberComponentFun.0.kt");
    }

    @TestMetadata("operators.0.kt")
    public void testOperators() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/operators.0.kt");
    }

    @TestMetadata("recursiveDataClass1.0.kt")
    public void testRecursiveDataClass1() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/recursiveDataClass1.0.kt");
    }

    @TestMetadata("recursiveDataClass2.0.kt")
    public void testRecursiveDataClass2() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/recursiveDataClass2.0.kt");
    }

    @TestMetadata("SAM.0.kt")
    public void testSAM() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/SAM.0.kt");
    }

    @TestMetadata("when.0.kt")
    public void testWhen() throws Exception {
        runTest("testData/findUsages/kotlin/conventions/components/when.0.kt");
    }
}
