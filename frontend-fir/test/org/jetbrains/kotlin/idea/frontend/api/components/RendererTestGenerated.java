/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.frontend.api.components;

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
@TestRoot("frontend-fir")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
@TestMetadata("testData/components/declarationRenderer")
public class RendererTestGenerated extends AbstractRendererTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("annotation.kt")
    public void testAnnotation() throws Exception {
        runTest("testData/components/declarationRenderer/annotation.kt");
    }

    @TestMetadata("complexTypes.kt")
    public void testComplexTypes() throws Exception {
        runTest("testData/components/declarationRenderer/complexTypes.kt");
    }

    @TestMetadata("constructorInObject.kt")
    public void testConstructorInObject() throws Exception {
        runTest("testData/components/declarationRenderer/constructorInObject.kt");
    }

    @TestMetadata("constructorOfAnonymousObject.kt")
    public void testConstructorOfAnonymousObject() throws Exception {
        runTest("testData/components/declarationRenderer/constructorOfAnonymousObject.kt");
    }

    @TestMetadata("delegates.kt")
    public void testDelegates() throws Exception {
        runTest("testData/components/declarationRenderer/delegates.kt");
    }

    @TestMetadata("derivedClass.kt")
    public void testDerivedClass() throws Exception {
        runTest("testData/components/declarationRenderer/derivedClass.kt");
    }

    @TestMetadata("emptyAnonymousObject.kt")
    public void testEmptyAnonymousObject() throws Exception {
        runTest("testData/components/declarationRenderer/emptyAnonymousObject.kt");
    }

    @TestMetadata("enums.kt")
    public void testEnums() throws Exception {
        runTest("testData/components/declarationRenderer/enums.kt");
    }

    @TestMetadata("enums2.kt")
    public void testEnums2() throws Exception {
        runTest("testData/components/declarationRenderer/enums2.kt");
    }

    @TestMetadata("expectActual.kt")
    public void testExpectActual() throws Exception {
        runTest("testData/components/declarationRenderer/expectActual.kt");
    }

    @TestMetadata("F.kt")
    public void testF() throws Exception {
        runTest("testData/components/declarationRenderer/F.kt");
    }

    @TestMetadata("functionTypes.kt")
    public void testFunctionTypes() throws Exception {
        runTest("testData/components/declarationRenderer/functionTypes.kt");
    }

    @TestMetadata("genericFunctions.kt")
    public void testGenericFunctions() throws Exception {
        runTest("testData/components/declarationRenderer/genericFunctions.kt");
    }

    @TestMetadata("genericProperty.kt")
    public void testGenericProperty() throws Exception {
        runTest("testData/components/declarationRenderer/genericProperty.kt");
    }

    @TestMetadata("intersectionType.kt")
    public void testIntersectionType() throws Exception {
        runTest("testData/components/declarationRenderer/intersectionType.kt");
    }

    @TestMetadata("nestedClass.kt")
    public void testNestedClass() throws Exception {
        runTest("testData/components/declarationRenderer/nestedClass.kt");
    }

    @TestMetadata("NestedOfAliasedType.kt")
    public void testNestedOfAliasedType() throws Exception {
        runTest("testData/components/declarationRenderer/NestedOfAliasedType.kt");
    }

    @TestMetadata("NestedSuperType.kt")
    public void testNestedSuperType() throws Exception {
        runTest("testData/components/declarationRenderer/NestedSuperType.kt");
    }

    @TestMetadata("noPrimaryConstructor.kt")
    public void testNoPrimaryConstructor() throws Exception {
        runTest("testData/components/declarationRenderer/noPrimaryConstructor.kt");
    }

    @TestMetadata("simpleClass.kt")
    public void testSimpleClass() throws Exception {
        runTest("testData/components/declarationRenderer/simpleClass.kt");
    }

    @TestMetadata("simpleFun.kt")
    public void testSimpleFun() throws Exception {
        runTest("testData/components/declarationRenderer/simpleFun.kt");
    }

    @TestMetadata("simpleTypeAlias.kt")
    public void testSimpleTypeAlias() throws Exception {
        runTest("testData/components/declarationRenderer/simpleTypeAlias.kt");
    }

    @TestMetadata("typeAliasWithGeneric.kt")
    public void testTypeAliasWithGeneric() throws Exception {
        runTest("testData/components/declarationRenderer/typeAliasWithGeneric.kt");
    }

    @TestMetadata("typeParameterVsNested.kt")
    public void testTypeParameterVsNested() throws Exception {
        runTest("testData/components/declarationRenderer/typeParameterVsNested.kt");
    }

    @TestMetadata("typeParameters.kt")
    public void testTypeParameters() throws Exception {
        runTest("testData/components/declarationRenderer/typeParameters.kt");
    }

    @TestMetadata("where.kt")
    public void testWhere() throws Exception {
        runTest("testData/components/declarationRenderer/where.kt");
    }
}
