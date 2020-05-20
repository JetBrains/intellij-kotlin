/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.navigation;

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
@RunWith(JUnit3RunnerWithInners.class)
@TestMetadata("testData/navigation/implementations/multiModule")
public class KotlinGotoImplementationMultiModuleTestGenerated extends AbstractKotlinGotoImplementationMultiModuleTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("actualTypeAliasWithAnonymousSubclass")
    public void testActualTypeAliasWithAnonymousSubclass() throws Exception {
        runTest("testData/navigation/implementations/multiModule/actualTypeAliasWithAnonymousSubclass/");
    }

    @TestMetadata("expectClass")
    public void testExpectClass() throws Exception {
        runTest("testData/navigation/implementations/multiModule/expectClass/");
    }

    @TestMetadata("expectClassFun")
    public void testExpectClassFun() throws Exception {
        runTest("testData/navigation/implementations/multiModule/expectClassFun/");
    }

    @TestMetadata("expectClassProperty")
    public void testExpectClassProperty() throws Exception {
        runTest("testData/navigation/implementations/multiModule/expectClassProperty/");
    }

    @TestMetadata("expectClassSuperclass")
    public void testExpectClassSuperclass() throws Exception {
        runTest("testData/navigation/implementations/multiModule/expectClassSuperclass/");
    }

    @TestMetadata("expectClassSuperclassFun")
    public void testExpectClassSuperclassFun() throws Exception {
        runTest("testData/navigation/implementations/multiModule/expectClassSuperclassFun/");
    }

    @TestMetadata("expectClassSuperclassProperty")
    public void testExpectClassSuperclassProperty() throws Exception {
        runTest("testData/navigation/implementations/multiModule/expectClassSuperclassProperty/");
    }

    @TestMetadata("expectCompanion")
    public void testExpectCompanion() throws Exception {
        runTest("testData/navigation/implementations/multiModule/expectCompanion/");
    }

    @TestMetadata("expectEnumEntry")
    public void testExpectEnumEntry() throws Exception {
        runTest("testData/navigation/implementations/multiModule/expectEnumEntry/");
    }

    @TestMetadata("expectObject")
    public void testExpectObject() throws Exception {
        runTest("testData/navigation/implementations/multiModule/expectObject/");
    }

    @TestMetadata("suspendFunImpl")
    public void testSuspendFunImpl() throws Exception {
        runTest("testData/navigation/implementations/multiModule/suspendFunImpl/");
    }
}
