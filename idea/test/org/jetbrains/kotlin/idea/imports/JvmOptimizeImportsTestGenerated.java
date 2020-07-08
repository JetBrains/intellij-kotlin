/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.imports;

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
public class JvmOptimizeImportsTestGenerated extends AbstractJvmOptimizeImportsTest {
    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/editor/optimizeImports/jvm")
    public static class Jvm extends AbstractJvmOptimizeImportsTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("AlreadyOptimized.kt")
        public void testAlreadyOptimized() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/AlreadyOptimized.kt");
        }

        @TestMetadata("CallableReference.kt")
        public void testCallableReference() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/CallableReference.kt");
        }

        @TestMetadata("CallableReference2.kt")
        public void testCallableReference2() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/CallableReference2.kt");
        }

        @TestMetadata("ClassFromSameFileImportAddedBug.kt")
        public void testClassFromSameFileImportAddedBug() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/ClassFromSameFileImportAddedBug.kt");
        }

        @TestMetadata("DoNotTouchIfNoChanges.kt")
        public void testDoNotTouchIfNoChanges() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/DoNotTouchIfNoChanges.kt");
        }

        @TestMetadata("DuplicatedImports.kt")
        public void testDuplicatedImports() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/DuplicatedImports.kt");
        }

        @TestMetadata("ExplicitImportOfDefault.kt")
        public void testExplicitImportOfDefault() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/ExplicitImportOfDefault.kt");
        }

        @TestMetadata("FromCompanionObject.kt")
        public void testFromCompanionObject() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/FromCompanionObject.kt");
        }

        @TestMetadata("FromCompanionObjectGeneric.kt")
        public void testFromCompanionObjectGeneric() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/FromCompanionObjectGeneric.kt");
        }

        @TestMetadata("JavaStaticField.kt")
        public void testJavaStaticField() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/JavaStaticField.kt");
        }

        @TestMetadata("KDocReference.kt")
        public void testKDocReference() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/KDocReference.kt");
        }

        @TestMetadata("KT10226.kt")
        public void testKT10226() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/KT10226.kt");
        }

        @TestMetadata("Kt1850FullQualified.kt")
        public void testKt1850FullQualified() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/Kt1850FullQualified.kt");
        }

        @TestMetadata("Kt1850InnerClass.kt")
        public void testKt1850InnerClass() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/Kt1850InnerClass.kt");
        }

        @TestMetadata("NestedClassInObject.kt")
        public void testNestedClassInObject() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/NestedClassInObject.kt");
        }

        @TestMetadata("Operators.kt")
        public void testOperators() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/Operators.kt");
        }

        @TestMetadata("PlusAndPlusAssign.kt")
        public void testPlusAndPlusAssign() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/PlusAndPlusAssign.kt");
        }

        @TestMetadata("RemoveImportsIfGeneral.kt")
        public void testRemoveImportsIfGeneral() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/RemoveImportsIfGeneral.kt");
        }

        @TestMetadata("RemoveImportsIfGeneralBefore.kt")
        public void testRemoveImportsIfGeneralBefore() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/RemoveImportsIfGeneralBefore.kt");
        }

        @TestMetadata("SamConstructor.kt")
        public void testSamConstructor() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/SamConstructor.kt");
        }

        @TestMetadata("staticFromJava.kt")
        public void testStaticFromJava() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/staticFromJava.kt");
        }

        @TestMetadata("StaticMethodFromSuper.kt")
        public void testStaticMethodFromSuper() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/StaticMethodFromSuper.kt");
        }

        @TestMetadata("ThisAndSuper.kt")
        public void testThisAndSuper() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/ThisAndSuper.kt");
        }

        @TestMetadata("TrivialAlias.kt")
        public void testTrivialAlias() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/TrivialAlias.kt");
        }

        @TestMetadata("TypeAliasVsUnderlyingClass.kt")
        public void testTypeAliasVsUnderlyingClass() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/TypeAliasVsUnderlyingClass.kt");
        }

        @TestMetadata("UnusedImports.kt")
        public void testUnusedImports() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/UnusedImports.kt");
        }

        @TestMetadata("UnusedImportsInScript.kts")
        public void testUnusedImportsInScript() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/UnusedImportsInScript.kts");
        }

        @TestMetadata("WithAliases.kt")
        public void testWithAliases() throws Exception {
            runTest("testData/editor/optimizeImports/jvm/WithAliases.kt");
        }

        @RunWith(JUnit3RunnerWithInners.class)
        @TestMetadata("testData/editor/optimizeImports/jvm/allUnderImports")
        public static class AllUnderImports extends AbstractJvmOptimizeImportsTest {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
            }

            @TestMetadata("ClassNameConflict.kt")
            public void testClassNameConflict() throws Exception {
                runTest("testData/editor/optimizeImports/jvm/allUnderImports/ClassNameConflict.kt");
            }

            @TestMetadata("ClassNameConflict2.kt")
            public void testClassNameConflict2() throws Exception {
                runTest("testData/editor/optimizeImports/jvm/allUnderImports/ClassNameConflict2.kt");
            }

            @TestMetadata("ClassNameConflictWithCurrentPackage.kt")
            public void testClassNameConflictWithCurrentPackage() throws Exception {
                runTest("testData/editor/optimizeImports/jvm/allUnderImports/ClassNameConflictWithCurrentPackage.kt");
            }

            @TestMetadata("ClassNameConflictWithCurrentPackage2.kt")
            public void testClassNameConflictWithCurrentPackage2() throws Exception {
                runTest("testData/editor/optimizeImports/jvm/allUnderImports/ClassNameConflictWithCurrentPackage2.kt");
            }

            @TestMetadata("ClassNameConflictWithDefault.kt")
            public void testClassNameConflictWithDefault() throws Exception {
                runTest("testData/editor/optimizeImports/jvm/allUnderImports/ClassNameConflictWithDefault.kt");
            }

            @TestMetadata("ClassNameConflictWithinDefaultImports.kt")
            public void testClassNameConflictWithinDefaultImports() throws Exception {
                runTest("testData/editor/optimizeImports/jvm/allUnderImports/ClassNameConflictWithinDefaultImports.kt");
            }

            @TestMetadata("NameCountSetting.kt")
            public void testNameCountSetting() throws Exception {
                runTest("testData/editor/optimizeImports/jvm/allUnderImports/NameCountSetting.kt");
            }

            @TestMetadata("NameCountSettingWithUnresolvedReference.kt")
            public void testNameCountSettingWithUnresolvedReference() throws Exception {
                runTest("testData/editor/optimizeImports/jvm/allUnderImports/NameCountSettingWithUnresolvedReference.kt");
            }

            @TestMetadata("PackagesToUseStarImport.kt")
            public void testPackagesToUseStarImport() throws Exception {
                runTest("testData/editor/optimizeImports/jvm/allUnderImports/PackagesToUseStarImport.kt");
            }

            @TestMetadata("Simple.kt")
            public void testSimple() throws Exception {
                runTest("testData/editor/optimizeImports/jvm/allUnderImports/Simple.kt");
            }
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/editor/optimizeImports/common")
    public static class Common extends AbstractJvmOptimizeImportsTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("ArrayAccessExpression.kt")
        public void testArrayAccessExpression() throws Exception {
            runTest("testData/editor/optimizeImports/common/ArrayAccessExpression.kt");
        }

        @TestMetadata("ClassMemberImported.kt")
        public void testClassMemberImported() throws Exception {
            runTest("testData/editor/optimizeImports/common/ClassMemberImported.kt");
        }

        @TestMetadata("ComponentFunction.kt")
        public void testComponentFunction() throws Exception {
            runTest("testData/editor/optimizeImports/common/ComponentFunction.kt");
        }

        @TestMetadata("ConflictWithAlias.kt")
        public void testConflictWithAlias() throws Exception {
            runTest("testData/editor/optimizeImports/common/ConflictWithAlias.kt");
        }

        @TestMetadata("ConflictWithAlias2.kt")
        public void testConflictWithAlias2() throws Exception {
            runTest("testData/editor/optimizeImports/common/ConflictWithAlias2.kt");
        }

        @TestMetadata("ConflictWithUnresolvedName.kt")
        public void testConflictWithUnresolvedName() throws Exception {
            runTest("testData/editor/optimizeImports/common/ConflictWithUnresolvedName.kt");
        }

        @TestMetadata("ConflictWithUnresolvedName2.kt")
        public void testConflictWithUnresolvedName2() throws Exception {
            runTest("testData/editor/optimizeImports/common/ConflictWithUnresolvedName2.kt");
        }

        @TestMetadata("CurrentPackage.kt")
        public void testCurrentPackage() throws Exception {
            runTest("testData/editor/optimizeImports/common/CurrentPackage.kt");
        }

        @TestMetadata("DefaultImportAndAlias.kt")
        public void testDefaultImportAndAlias() throws Exception {
            runTest("testData/editor/optimizeImports/common/DefaultImportAndAlias.kt");
        }

        @TestMetadata("DefaultImportAndAlias2.kt")
        public void testDefaultImportAndAlias2() throws Exception {
            runTest("testData/editor/optimizeImports/common/DefaultImportAndAlias2.kt");
        }

        @TestMetadata("DefaultObjectReference.kt")
        public void testDefaultObjectReference() throws Exception {
            runTest("testData/editor/optimizeImports/common/DefaultObjectReference.kt");
        }

        @TestMetadata("Enums.kt")
        public void testEnums() throws Exception {
            runTest("testData/editor/optimizeImports/common/Enums.kt");
        }

        @TestMetadata("ExtensionFunctionalTypeValFromCompanionObject.kt")
        public void testExtensionFunctionalTypeValFromCompanionObject() throws Exception {
            runTest("testData/editor/optimizeImports/common/ExtensionFunctionalTypeValFromCompanionObject.kt");
        }

        @TestMetadata("ExtensionFunctionalTypeValFromCompanionObjectCallOnCompanion.kt")
        public void testExtensionFunctionalTypeValFromCompanionObjectCallOnCompanion() throws Exception {
            runTest("testData/editor/optimizeImports/common/ExtensionFunctionalTypeValFromCompanionObjectCallOnCompanion.kt");
        }

        @TestMetadata("ExtensionFunctionalTypeValFromCompanionObjectNonExtCall.kt")
        public void testExtensionFunctionalTypeValFromCompanionObjectNonExtCall() throws Exception {
            runTest("testData/editor/optimizeImports/common/ExtensionFunctionalTypeValFromCompanionObjectNonExtCall.kt");
        }

        @TestMetadata("InvokeFunction.kt")
        public void testInvokeFunction() throws Exception {
            runTest("testData/editor/optimizeImports/common/InvokeFunction.kt");
        }

        @TestMetadata("IteratorFunction.kt")
        public void testIteratorFunction() throws Exception {
            runTest("testData/editor/optimizeImports/common/IteratorFunction.kt");
        }

        @TestMetadata("IteratorFunction2.kt")
        public void testIteratorFunction2() throws Exception {
            runTest("testData/editor/optimizeImports/common/IteratorFunction2.kt");
        }

        @TestMetadata("KT11640.kt")
        public void testKT11640() throws Exception {
            runTest("testData/editor/optimizeImports/common/KT11640.kt");
        }

        @TestMetadata("KT11640_1.kt")
        public void testKT11640_1() throws Exception {
            runTest("testData/editor/optimizeImports/common/KT11640_1.kt");
        }

        @TestMetadata("KT13689.kt")
        public void testKT13689() throws Exception {
            runTest("testData/editor/optimizeImports/common/KT13689.kt");
        }

        @TestMetadata("KT9875.kt")
        public void testKT9875() throws Exception {
            runTest("testData/editor/optimizeImports/common/KT9875.kt");
        }

        @TestMetadata("KeywordNames.kt")
        public void testKeywordNames() throws Exception {
            runTest("testData/editor/optimizeImports/common/KeywordNames.kt");
        }

        @TestMetadata("Kt2488EnumEntry.kt")
        public void testKt2488EnumEntry() throws Exception {
            runTest("testData/editor/optimizeImports/common/Kt2488EnumEntry.kt");
        }

        @TestMetadata("Kt2709.kt")
        public void testKt2709() throws Exception {
            runTest("testData/editor/optimizeImports/common/Kt2709.kt");
        }

        @TestMetadata("Kt32409.kt")
        public void testKt32409() throws Exception {
            runTest("testData/editor/optimizeImports/common/Kt32409.kt");
        }

        @TestMetadata("MemberImports.kt")
        public void testMemberImports() throws Exception {
            runTest("testData/editor/optimizeImports/common/MemberImports.kt");
        }

        @TestMetadata("MembersInScope.kt")
        public void testMembersInScope() throws Exception {
            runTest("testData/editor/optimizeImports/common/MembersInScope.kt");
        }

        @TestMetadata("NestedClassReferenceOutsideClassBody.kt")
        public void testNestedClassReferenceOutsideClassBody() throws Exception {
            runTest("testData/editor/optimizeImports/common/NestedClassReferenceOutsideClassBody.kt");
        }

        @TestMetadata("Overloads.kt")
        public void testOverloads() throws Exception {
            runTest("testData/editor/optimizeImports/common/Overloads.kt");
        }

        @TestMetadata("ProvideDelegate.kt")
        public void testProvideDelegate() throws Exception {
            runTest("testData/editor/optimizeImports/common/ProvideDelegate.kt");
        }

        @TestMetadata("ProvideDelegate2.kt")
        public void testProvideDelegate2() throws Exception {
            runTest("testData/editor/optimizeImports/common/ProvideDelegate2.kt");
        }

        @TestMetadata("TwoConstructors.kt")
        public void testTwoConstructors() throws Exception {
            runTest("testData/editor/optimizeImports/common/TwoConstructors.kt");
        }

        @TestMetadata("UnresolvedImport.kt")
        public void testUnresolvedImport() throws Exception {
            runTest("testData/editor/optimizeImports/common/UnresolvedImport.kt");
        }

        @TestMetadata("UnresolvedImport2.kt")
        public void testUnresolvedImport2() throws Exception {
            runTest("testData/editor/optimizeImports/common/UnresolvedImport2.kt");
        }

        @TestMetadata("WithAlias.kt")
        public void testWithAlias() throws Exception {
            runTest("testData/editor/optimizeImports/common/WithAlias.kt");
        }

        @TestMetadata("WithAlias2.kt")
        public void testWithAlias2() throws Exception {
            runTest("testData/editor/optimizeImports/common/WithAlias2.kt");
        }

        @RunWith(JUnit3RunnerWithInners.class)
        @TestMetadata("testData/editor/optimizeImports/common/kt21515")
        public static class Kt21515 extends AbstractJvmOptimizeImportsTest {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
            }

            @TestMetadata("callableReferenceOnClass.kt")
            public void testCallableReferenceOnClass() throws Exception {
                runTest("testData/editor/optimizeImports/common/kt21515/callableReferenceOnClass.kt");
            }

            @TestMetadata("callableReferenceOnClassWithCompanion.kt")
            public void testCallableReferenceOnClassWithCompanion() throws Exception {
                runTest("testData/editor/optimizeImports/common/kt21515/callableReferenceOnClassWithCompanion.kt");
            }

            @TestMetadata("callableReferenceOnObject.kt")
            public void testCallableReferenceOnObject() throws Exception {
                runTest("testData/editor/optimizeImports/common/kt21515/callableReferenceOnObject.kt");
            }

            @TestMetadata("constructor.kt")
            public void testConstructor() throws Exception {
                runTest("testData/editor/optimizeImports/common/kt21515/constructor.kt");
            }

            @TestMetadata("typeReference.kt")
            public void testTypeReference() throws Exception {
                runTest("testData/editor/optimizeImports/common/kt21515/typeReference.kt");
            }
        }
    }
}
