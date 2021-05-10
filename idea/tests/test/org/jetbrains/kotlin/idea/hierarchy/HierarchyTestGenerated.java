/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.hierarchy;

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
public class HierarchyTestGenerated extends AbstractHierarchyTest {
    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/hierarchy/class/type")
    public static class Type extends AbstractHierarchyTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTypeClassHierarchyTest, this, testDataFilePath);
        }

        @TestMetadata("CaretAtAnnotation")
        public void testCaretAtAnnotation() throws Exception {
            runTest("testData/hierarchy/class/type/CaretAtAnnotation/");
        }

        @TestMetadata("CaretAtConstructor")
        public void testCaretAtConstructor() throws Exception {
            runTest("testData/hierarchy/class/type/CaretAtConstructor/");
        }

        @TestMetadata("CaretAtFabricMethod")
        public void testCaretAtFabricMethod() throws Exception {
            runTest("testData/hierarchy/class/type/CaretAtFabricMethod/");
        }

        @TestMetadata("CaretAtImport")
        public void testCaretAtImport() throws Exception {
            runTest("testData/hierarchy/class/type/CaretAtImport/");
        }

        @TestMetadata("CaretAtJavaType")
        public void testCaretAtJavaType() throws Exception {
            runTest("testData/hierarchy/class/type/CaretAtJavaType/");
        }

        @TestMetadata("CaretAtModifierList")
        public void testCaretAtModifierList() throws Exception {
            runTest("testData/hierarchy/class/type/CaretAtModifierList/");
        }

        @TestMetadata("CaretAtReceiverExtFun")
        public void testCaretAtReceiverExtFun() throws Exception {
            runTest("testData/hierarchy/class/type/CaretAtReceiverExtFun/");
        }

        @TestMetadata("CaretAtSuperCall")
        public void testCaretAtSuperCall() throws Exception {
            runTest("testData/hierarchy/class/type/CaretAtSuperCall/");
        }

        @TestMetadata("CaretAtSuperTypeCallEntry")
        public void testCaretAtSuperTypeCallEntry() throws Exception {
            runTest("testData/hierarchy/class/type/CaretAtSuperTypeCallEntry/");
        }

        @TestMetadata("CaretAtSupertypesList")
        public void testCaretAtSupertypesList() throws Exception {
            runTest("testData/hierarchy/class/type/CaretAtSupertypesList/");
        }

        @TestMetadata("CaretAtTypeReference")
        public void testCaretAtTypeReference() throws Exception {
            runTest("testData/hierarchy/class/type/CaretAtTypeReference/");
        }

        @TestMetadata("CaretInClassBody")
        public void testCaretInClassBody() throws Exception {
            runTest("testData/hierarchy/class/type/CaretInClassBody/");
        }

        @TestMetadata("ClassFromClass")
        public void testClassFromClass() throws Exception {
            runTest("testData/hierarchy/class/type/ClassFromClass/");
        }

        @TestMetadata("ClassFromObject")
        public void testClassFromObject() throws Exception {
            runTest("testData/hierarchy/class/type/ClassFromObject/");
        }

        @TestMetadata("ClassInClassObject")
        public void testClassInClassObject() throws Exception {
            runTest("testData/hierarchy/class/type/ClassInClassObject/");
        }

        @TestMetadata("ClassInPackage")
        public void testClassInPackage() throws Exception {
            runTest("testData/hierarchy/class/type/ClassInPackage/");
        }

        @TestMetadata("InnerClass")
        public void testInnerClass() throws Exception {
            runTest("testData/hierarchy/class/type/InnerClass/");
        }

        @TestMetadata("JKJHierarchy")
        public void testJKJHierarchy() throws Exception {
            runTest("testData/hierarchy/class/type/JKJHierarchy/");
        }

        @TestMetadata("JavaFromKotlin")
        public void testJavaFromKotlin() throws Exception {
            runTest("testData/hierarchy/class/type/JavaFromKotlin/");
        }

        @TestMetadata("JavaFromKotlinByRef")
        public void testJavaFromKotlinByRef() throws Exception {
            runTest("testData/hierarchy/class/type/JavaFromKotlinByRef/");
        }

        @TestMetadata("JavaFromKotlinForKotlinClass")
        public void testJavaFromKotlinForKotlinClass() throws Exception {
            runTest("testData/hierarchy/class/type/JavaFromKotlinForKotlinClass/");
        }

        @TestMetadata("JavaStyleClassLiteralInvalidCode")
        public void testJavaStyleClassLiteralInvalidCode() throws Exception {
            runTest("testData/hierarchy/class/type/JavaStyleClassLiteralInvalidCode/");
        }

        @TestMetadata("JetInt")
        public void testJetInt() throws Exception {
            runTest("testData/hierarchy/class/type/JetInt/");
        }

        @TestMetadata("KJKHierarchy")
        public void testKJKHierarchy() throws Exception {
            runTest("testData/hierarchy/class/type/KJKHierarchy/");
        }

        @TestMetadata("KotlinFromJava")
        public void testKotlinFromJava() throws Exception {
            runTest("testData/hierarchy/class/type/KotlinFromJava/");
        }

        @TestMetadata("KotlinFromJavaByRef")
        public void testKotlinFromJavaByRef() throws Exception {
            runTest("testData/hierarchy/class/type/KotlinFromJavaByRef/");
        }

        @TestMetadata("KotlinFromKotlinByRef")
        public void testKotlinFromKotlinByRef() throws Exception {
            runTest("testData/hierarchy/class/type/KotlinFromKotlinByRef/");
        }

        @TestMetadata("Object")
        public void testObject() throws Exception {
            runTest("testData/hierarchy/class/type/Object/");
        }

        @TestMetadata("TwoChildren")
        public void testTwoChildren() throws Exception {
            runTest("testData/hierarchy/class/type/TwoChildren/");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/hierarchy/class/super")
    public static class Super extends AbstractHierarchyTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doSuperClassHierarchyTest, this, testDataFilePath);
        }

        @TestMetadata("JetList")
        public void testJetList() throws Exception {
            runTest("testData/hierarchy/class/super/JetList/");
        }

        @TestMetadata("MultiTypeAlias")
        public void testMultiTypeAlias() throws Exception {
            runTest("testData/hierarchy/class/super/MultiTypeAlias/");
        }

        @TestMetadata("TwoTraits")
        public void testTwoTraits() throws Exception {
            runTest("testData/hierarchy/class/super/TwoTraits/");
        }

        @TestMetadata("TypeAlias")
        public void testTypeAlias() throws Exception {
            runTest("testData/hierarchy/class/super/TypeAlias/");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/hierarchy/class/sub")
    public static class Sub extends AbstractHierarchyTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doSubClassHierarchyTest, this, testDataFilePath);
        }

        @TestMetadata("AllFromClass")
        public void testAllFromClass() throws Exception {
            runTest("testData/hierarchy/class/sub/AllFromClass/");
        }

        @TestMetadata("AllFromTrait")
        public void testAllFromTrait() throws Exception {
            runTest("testData/hierarchy/class/sub/AllFromTrait/");
        }

        @TestMetadata("ClassFromClass")
        public void testClassFromClass() throws Exception {
            runTest("testData/hierarchy/class/sub/ClassFromClass/");
        }

        @TestMetadata("ClassFromTrait")
        public void testClassFromTrait() throws Exception {
            runTest("testData/hierarchy/class/sub/ClassFromTrait/");
        }

        @TestMetadata("ConstructorCallCaretAfter")
        public void testConstructorCallCaretAfter() throws Exception {
            runTest("testData/hierarchy/class/sub/ConstructorCallCaretAfter/");
        }

        @TestMetadata("ConstructorCallCaretBefore")
        public void testConstructorCallCaretBefore() throws Exception {
            runTest("testData/hierarchy/class/sub/ConstructorCallCaretBefore/");
        }

        @TestMetadata("MultiTypeAlias")
        public void testMultiTypeAlias() throws Exception {
            runTest("testData/hierarchy/class/sub/MultiTypeAlias/");
        }

        @TestMetadata("ObjectFromClass")
        public void testObjectFromClass() throws Exception {
            runTest("testData/hierarchy/class/sub/ObjectFromClass/");
        }

        @TestMetadata("ObjectFromTrait")
        public void testObjectFromTrait() throws Exception {
            runTest("testData/hierarchy/class/sub/ObjectFromTrait/");
        }

        @TestMetadata("SecondaryConstructorCallCaretAfter")
        public void testSecondaryConstructorCallCaretAfter() throws Exception {
            runTest("testData/hierarchy/class/sub/SecondaryConstructorCallCaretAfter/");
        }

        @TestMetadata("SecondaryConstructorCallCaretBefore")
        public void testSecondaryConstructorCallCaretBefore() throws Exception {
            runTest("testData/hierarchy/class/sub/SecondaryConstructorCallCaretBefore/");
        }

        @TestMetadata("TraitFromClass")
        public void testTraitFromClass() throws Exception {
            runTest("testData/hierarchy/class/sub/TraitFromClass/");
        }

        @TestMetadata("TraitFromTrait")
        public void testTraitFromTrait() throws Exception {
            runTest("testData/hierarchy/class/sub/TraitFromTrait/");
        }

        @TestMetadata("TypeAlias")
        public void testTypeAlias() throws Exception {
            runTest("testData/hierarchy/class/sub/TypeAlias/");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/hierarchy/calls/callers")
    public static class Callers extends AbstractHierarchyTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doCallerHierarchyTest, this, testDataFilePath);
        }

        @TestMetadata("callInsideAnonymousFun")
        public void testCallInsideAnonymousFun() throws Exception {
            runTest("testData/hierarchy/calls/callers/callInsideAnonymousFun/");
        }

        @TestMetadata("callInsideLambda")
        public void testCallInsideLambda() throws Exception {
            runTest("testData/hierarchy/calls/callers/callInsideLambda/");
        }

        @TestMetadata("insideJavadoc")
        public void testInsideJavadoc() throws Exception {
            runTest("testData/hierarchy/calls/callers/insideJavadoc/");
        }

        @TestMetadata("insideKDoc")
        public void testInsideKDoc() throws Exception {
            runTest("testData/hierarchy/calls/callers/insideKDoc/");
        }

        @TestMetadata("kotlinClass")
        public void testKotlinClass() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinClass/");
        }

        @TestMetadata("kotlinFunction")
        public void testKotlinFunction() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinFunction/");
        }

        @TestMetadata("kotlinFunctionNonCallUsages")
        public void testKotlinFunctionNonCallUsages() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinFunctionNonCallUsages/");
        }

        @TestMetadata("kotlinInterface")
        public void testKotlinInterface() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinInterface/");
        }

        @TestMetadata("kotlinLocalClass")
        public void testKotlinLocalClass() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinLocalClass/");
        }

        @TestMetadata("kotlinLocalFunction")
        public void testKotlinLocalFunction() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinLocalFunction/");
        }

        @TestMetadata("kotlinLocalFunctionWithNonLocalCallers")
        public void testKotlinLocalFunctionWithNonLocalCallers() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinLocalFunctionWithNonLocalCallers/");
        }

        @TestMetadata("kotlinNestedClass")
        public void testKotlinNestedClass() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinNestedClass/");
        }

        @TestMetadata("kotlinNestedInnerClass")
        public void testKotlinNestedInnerClass() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinNestedInnerClass/");
        }

        @TestMetadata("kotlinPackageFunction")
        public void testKotlinPackageFunction() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinPackageFunction/");
        }

        @TestMetadata("kotlinPackageProperty")
        public void testKotlinPackageProperty() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinPackageProperty/");
        }

        @TestMetadata("kotlinPrimaryConstructorImplicitCalls")
        public void testKotlinPrimaryConstructorImplicitCalls() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinPrimaryConstructorImplicitCalls/");
        }

        @TestMetadata("kotlinPrivateClass")
        public void testKotlinPrivateClass() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinPrivateClass/");
        }

        @TestMetadata("kotlinPrivateFunction")
        public void testKotlinPrivateFunction() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinPrivateFunction/");
        }

        @TestMetadata("kotlinPrivateProperty")
        public void testKotlinPrivateProperty() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinPrivateProperty/");
        }

        @TestMetadata("kotlinProperty")
        public void testKotlinProperty() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinProperty/");
        }

        @TestMetadata("kotlinSecondaryConstructor")
        public void testKotlinSecondaryConstructor() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinSecondaryConstructor/");
        }

        @TestMetadata("kotlinSecondaryConstructorImplicitCalls")
        public void testKotlinSecondaryConstructorImplicitCalls() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinSecondaryConstructorImplicitCalls/");
        }

        @TestMetadata("kotlinUnresolvedFunction")
        public void testKotlinUnresolvedFunction() throws Exception {
            runTest("testData/hierarchy/calls/callers/kotlinUnresolvedFunction/");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/hierarchy/calls/callersJava")
    public static class CallersJava extends AbstractHierarchyTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doCallerJavaHierarchyTest, this, testDataFilePath);
        }

        @TestMetadata("javaMethod")
        public void testJavaMethod() throws Exception {
            runTest("testData/hierarchy/calls/callersJava/javaMethod/");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/hierarchy/calls/callees")
    public static class Callees extends AbstractHierarchyTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doCalleeHierarchyTest, this, testDataFilePath);
        }

        @TestMetadata("kotlinAnonymousObject")
        public void testKotlinAnonymousObject() throws Exception {
            runTest("testData/hierarchy/calls/callees/kotlinAnonymousObject/");
        }

        @TestMetadata("kotlinClass")
        public void testKotlinClass() throws Exception {
            runTest("testData/hierarchy/calls/callees/kotlinClass/");
        }

        @TestMetadata("kotlinClassObject")
        public void testKotlinClassObject() throws Exception {
            runTest("testData/hierarchy/calls/callees/kotlinClassObject/");
        }

        @TestMetadata("kotlinEnumClass")
        public void testKotlinEnumClass() throws Exception {
            runTest("testData/hierarchy/calls/callees/kotlinEnumClass/");
        }

        @TestMetadata("kotlinFunction")
        public void testKotlinFunction() throws Exception {
            runTest("testData/hierarchy/calls/callees/kotlinFunction/");
        }

        @TestMetadata("kotlinInterface")
        public void testKotlinInterface() throws Exception {
            runTest("testData/hierarchy/calls/callees/kotlinInterface/");
        }

        @TestMetadata("kotlinLocalClass")
        public void testKotlinLocalClass() throws Exception {
            runTest("testData/hierarchy/calls/callees/kotlinLocalClass/");
        }

        @TestMetadata("kotlinLocalFunction")
        public void testKotlinLocalFunction() throws Exception {
            runTest("testData/hierarchy/calls/callees/kotlinLocalFunction/");
        }

        @TestMetadata("kotlinNestedClass")
        public void testKotlinNestedClass() throws Exception {
            runTest("testData/hierarchy/calls/callees/kotlinNestedClass/");
        }

        @TestMetadata("kotlinObject")
        public void testKotlinObject() throws Exception {
            runTest("testData/hierarchy/calls/callees/kotlinObject/");
        }

        @TestMetadata("kotlinPackageFunction")
        public void testKotlinPackageFunction() throws Exception {
            runTest("testData/hierarchy/calls/callees/kotlinPackageFunction/");
        }

        @TestMetadata("kotlinPackageProperty")
        public void testKotlinPackageProperty() throws Exception {
            runTest("testData/hierarchy/calls/callees/kotlinPackageProperty/");
        }

        @TestMetadata("kotlinProperty")
        public void testKotlinProperty() throws Exception {
            runTest("testData/hierarchy/calls/callees/kotlinProperty/");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/hierarchy/overrides")
    public static class Overrides extends AbstractHierarchyTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doOverrideHierarchyTest, this, testDataFilePath);
        }

        @TestMetadata("kotlinBuiltInMemberFunction")
        public void testKotlinBuiltInMemberFunction() throws Exception {
            runTest("testData/hierarchy/overrides/kotlinBuiltInMemberFunction/");
        }

        @TestMetadata("kotlinFunctionInClass")
        public void testKotlinFunctionInClass() throws Exception {
            runTest("testData/hierarchy/overrides/kotlinFunctionInClass/");
        }

        @TestMetadata("kotlinFunctionInTrait")
        public void testKotlinFunctionInTrait() throws Exception {
            runTest("testData/hierarchy/overrides/kotlinFunctionInTrait/");
        }

        @TestMetadata("kotlinPropertyInClass")
        public void testKotlinPropertyInClass() throws Exception {
            runTest("testData/hierarchy/overrides/kotlinPropertyInClass/");
        }

        @TestMetadata("kotlinPropertyInTrait")
        public void testKotlinPropertyInTrait() throws Exception {
            runTest("testData/hierarchy/overrides/kotlinPropertyInTrait/");
        }

        @TestMetadata("kotlinTopLevelFunction")
        public void testKotlinTopLevelFunction() throws Exception {
            runTest("testData/hierarchy/overrides/kotlinTopLevelFunction/");
        }

        @TestMetadata("kotlinVarParameter")
        public void testKotlinVarParameter() throws Exception {
            runTest("testData/hierarchy/overrides/kotlinVarParameter/");
        }
    }
}
