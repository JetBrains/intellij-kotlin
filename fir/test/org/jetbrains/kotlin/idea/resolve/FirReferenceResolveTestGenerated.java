/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.resolve;

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
@TestRoot("fir")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
@TestMetadata("../idea/tests/testData/resolve/references")
public class FirReferenceResolveTestGenerated extends AbstractFirReferenceResolveTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("AnnotationForClass.kt")
    public void testAnnotationForClass() throws Exception {
        runTest("../idea/tests/testData/resolve/references/AnnotationForClass.kt");
    }

    @TestMetadata("AnnotationInsideFunction.kt")
    public void testAnnotationInsideFunction() throws Exception {
        runTest("../idea/tests/testData/resolve/references/AnnotationInsideFunction.kt");
    }

    @TestMetadata("AnnotationOnFile.kt")
    public void testAnnotationOnFile() throws Exception {
        runTest("../idea/tests/testData/resolve/references/AnnotationOnFile.kt");
    }

    @TestMetadata("AnnotationOnFileWithImport.kt")
    public void testAnnotationOnFileWithImport() throws Exception {
        runTest("../idea/tests/testData/resolve/references/AnnotationOnFileWithImport.kt");
    }

    @TestMetadata("AnnotationParameter.kt")
    public void testAnnotationParameter() throws Exception {
        runTest("../idea/tests/testData/resolve/references/AnnotationParameter.kt");
    }

    @TestMetadata("AnnotationTypeParameter.kt")
    public void testAnnotationTypeParameter() throws Exception {
        runTest("../idea/tests/testData/resolve/references/AnnotationTypeParameter.kt");
    }

    @TestMetadata("ClassInTypeConstraint.kt")
    public void testClassInTypeConstraint() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ClassInTypeConstraint.kt");
    }

    @TestMetadata("ClassNameBeforeDot.kt")
    public void testClassNameBeforeDot() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ClassNameBeforeDot.kt");
    }

    @TestMetadata("ClassObjectClassLiteralReference.kt")
    public void testClassObjectClassLiteralReference() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ClassObjectClassLiteralReference.kt");
    }

    @TestMetadata("ClassObjectClassLiteralReferenceWithField.kt")
    public void testClassObjectClassLiteralReferenceWithField() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ClassObjectClassLiteralReferenceWithField.kt");
    }

    @TestMetadata("ClassQualifierForNestedClassConstructorCall.kt")
    public void testClassQualifierForNestedClassConstructorCall() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ClassQualifierForNestedClassConstructorCall.kt");
    }

    @TestMetadata("ClassReferenceInImport.kt")
    public void testClassReferenceInImport() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ClassReferenceInImport.kt");
    }

    @TestMetadata("CollectionLiteralLeft.kt")
    public void testCollectionLiteralLeft() throws Exception {
        runTest("../idea/tests/testData/resolve/references/CollectionLiteralLeft.kt");
    }

    @TestMetadata("CollectionLiteralRight.kt")
    public void testCollectionLiteralRight() throws Exception {
        runTest("../idea/tests/testData/resolve/references/CollectionLiteralRight.kt");
    }

    @TestMetadata("CoroutineSuspensionPoint.kt")
    public void testCoroutineSuspensionPoint() throws Exception {
        runTest("../idea/tests/testData/resolve/references/CoroutineSuspensionPoint.kt");
    }

    @TestMetadata("CtrlClickResolve.kt")
    public void testCtrlClickResolve() throws Exception {
        runTest("../idea/tests/testData/resolve/references/CtrlClickResolve.kt");
    }

    @TestMetadata("DataClassCopy.kt")
    public void testDataClassCopy() throws Exception {
        runTest("../idea/tests/testData/resolve/references/DataClassCopy.kt");
    }

    @TestMetadata("DefaultObjectAsExtensionReceiverForFunction.kt")
    public void testDefaultObjectAsExtensionReceiverForFunction() throws Exception {
        runTest("../idea/tests/testData/resolve/references/DefaultObjectAsExtensionReceiverForFunction.kt");
    }

    @TestMetadata("DefaultObjectAsExtensionReceiverForProperty.kt")
    public void testDefaultObjectAsExtensionReceiverForProperty() throws Exception {
        runTest("../idea/tests/testData/resolve/references/DefaultObjectAsExtensionReceiverForProperty.kt");
    }

    @TestMetadata("DefaultObjectAsReceiverForExtensionFunctionOnSuperType.kt")
    public void testDefaultObjectAsReceiverForExtensionFunctionOnSuperType() throws Exception {
        runTest("../idea/tests/testData/resolve/references/DefaultObjectAsReceiverForExtensionFunctionOnSuperType.kt");
    }

    @TestMetadata("DefaultObjectAsReceiverForMemberPropertyInSuperType.kt")
    public void testDefaultObjectAsReceiverForMemberPropertyInSuperType() throws Exception {
        runTest("../idea/tests/testData/resolve/references/DefaultObjectAsReceiverForMemberPropertyInSuperType.kt");
    }

    @TestMetadata("DefaultObjectInShortReferenceFormCall.kt")
    public void testDefaultObjectInShortReferenceFormCall() throws Exception {
        runTest("../idea/tests/testData/resolve/references/DefaultObjectInShortReferenceFormCall.kt");
    }

    @TestMetadata("EnumValues.kt")
    public void testEnumValues() throws Exception {
        runTest("../idea/tests/testData/resolve/references/EnumValues.kt");
    }

    @TestMetadata("ExternalCompanionObject.kt")
    public void testExternalCompanionObject() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ExternalCompanionObject.kt");
    }

    @TestMetadata("FakeJavaLang1.kt")
    public void testFakeJavaLang1() throws Exception {
        runTest("../idea/tests/testData/resolve/references/FakeJavaLang1.kt");
    }

    @TestMetadata("FakeJavaLang2.kt")
    public void testFakeJavaLang2() throws Exception {
        runTest("../idea/tests/testData/resolve/references/FakeJavaLang2.kt");
    }

    @TestMetadata("FakeJavaLang3.kt")
    public void testFakeJavaLang3() throws Exception {
        runTest("../idea/tests/testData/resolve/references/FakeJavaLang3.kt");
    }

    @TestMetadata("FakeJavaLang4.kt")
    public void testFakeJavaLang4() throws Exception {
        runTest("../idea/tests/testData/resolve/references/FakeJavaLang4.kt");
    }

    @TestMetadata("fileRefInRawStringLiteral.kt")
    public void testFileRefInRawStringLiteral() throws Exception {
        runTest("../idea/tests/testData/resolve/references/fileRefInRawStringLiteral.kt");
    }

    @TestMetadata("fileRefInStringLiteral.kt")
    public void testFileRefInStringLiteral() throws Exception {
        runTest("../idea/tests/testData/resolve/references/fileRefInStringLiteral.kt");
    }

    @TestMetadata("GenericFunctionParameter.kt")
    public void testGenericFunctionParameter() throws Exception {
        runTest("../idea/tests/testData/resolve/references/GenericFunctionParameter.kt");
    }

    @TestMetadata("GenericTypeInFunctionParameter.kt")
    public void testGenericTypeInFunctionParameter() throws Exception {
        runTest("../idea/tests/testData/resolve/references/GenericTypeInFunctionParameter.kt");
    }

    @TestMetadata("ImportFromRootScope.kt")
    public void testImportFromRootScope() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ImportFromRootScope.kt");
    }

    @TestMetadata("InClassParameter.kt")
    public void testInClassParameter() throws Exception {
        runTest("../idea/tests/testData/resolve/references/InClassParameter.kt");
    }

    @TestMetadata("InClassParameterField.kt")
    public void testInClassParameterField() throws Exception {
        runTest("../idea/tests/testData/resolve/references/InClassParameterField.kt");
    }

    @TestMetadata("InEnumEntry.kt")
    public void testInEnumEntry() throws Exception {
        runTest("../idea/tests/testData/resolve/references/InEnumEntry.kt");
    }

    @TestMetadata("InFunctionParameterType.kt")
    public void testInFunctionParameterType() throws Exception {
        runTest("../idea/tests/testData/resolve/references/InFunctionParameterType.kt");
    }

    @TestMetadata("InMethodParameter.kt")
    public void testInMethodParameter() throws Exception {
        runTest("../idea/tests/testData/resolve/references/InMethodParameter.kt");
    }

    @TestMetadata("InMethodVarargParameter.kt")
    public void testInMethodVarargParameter() throws Exception {
        runTest("../idea/tests/testData/resolve/references/InMethodVarargParameter.kt");
    }

    @TestMetadata("InObjectClassObject.kt")
    public void testInObjectClassObject() throws Exception {
        runTest("../idea/tests/testData/resolve/references/InObjectClassObject.kt");
    }

    @TestMetadata("InSecondClassObject.kt")
    public void testInSecondClassObject() throws Exception {
        runTest("../idea/tests/testData/resolve/references/InSecondClassObject.kt");
    }

    @TestMetadata("InVaragReferenceInFunctionBody.kt")
    public void testInVaragReferenceInFunctionBody() throws Exception {
        runTest("../idea/tests/testData/resolve/references/InVaragReferenceInFunctionBody.kt");
    }

    @TestMetadata("InVaragReferenceInNamedParameter.kt")
    public void testInVaragReferenceInNamedParameter() throws Exception {
        runTest("../idea/tests/testData/resolve/references/InVaragReferenceInNamedParameter.kt");
    }

    @TestMetadata("JavaAnnotationParameter.kt")
    public void testJavaAnnotationParameter() throws Exception {
        runTest("../idea/tests/testData/resolve/references/JavaAnnotationParameter.kt");
    }

    @TestMetadata("JavaConstructorNotNullParameter.kt")
    public void testJavaConstructorNotNullParameter() throws Exception {
        runTest("../idea/tests/testData/resolve/references/JavaConstructorNotNullParameter.kt");
    }

    @TestMetadata("JavaEnumEntry.kt")
    public void testJavaEnumEntry() throws Exception {
        runTest("../idea/tests/testData/resolve/references/JavaEnumEntry.kt");
    }

    @TestMetadata("JavaEnumValueOf.kt")
    public void testJavaEnumValueOf() throws Exception {
        runTest("../idea/tests/testData/resolve/references/JavaEnumValueOf.kt");
    }

    @TestMetadata("JavaParameter.kt")
    public void testJavaParameter() throws Exception {
        runTest("../idea/tests/testData/resolve/references/JavaParameter.kt");
    }

    @TestMetadata("JavaReference.kt")
    public void testJavaReference() throws Exception {
        runTest("../idea/tests/testData/resolve/references/JavaReference.kt");
    }

    @TestMetadata("KotlinPropertyAssignment.kt")
    public void testKotlinPropertyAssignment() throws Exception {
        runTest("../idea/tests/testData/resolve/references/KotlinPropertyAssignment.kt");
    }

    @TestMetadata("KotlinPropertyWithGetterAndSetterAssignment.kt")
    public void testKotlinPropertyWithGetterAndSetterAssignment() throws Exception {
        runTest("../idea/tests/testData/resolve/references/KotlinPropertyWithGetterAndSetterAssignment.kt");
    }

    @TestMetadata("MultiDeclarationExtension.kt")
    public void testMultiDeclarationExtension() throws Exception {
        runTest("../idea/tests/testData/resolve/references/MultiDeclarationExtension.kt");
    }

    @TestMetadata("MultiDeclarationMember.kt")
    public void testMultiDeclarationMember() throws Exception {
        runTest("../idea/tests/testData/resolve/references/MultiDeclarationMember.kt");
    }

    @TestMetadata("NamedClassObject.kt")
    public void testNamedClassObject() throws Exception {
        runTest("../idea/tests/testData/resolve/references/NamedClassObject.kt");
    }

    @TestMetadata("PackageReference.kt")
    public void testPackageReference() throws Exception {
        runTest("../idea/tests/testData/resolve/references/PackageReference.kt");
    }

    @TestMetadata("PackageReferenceInImport.kt")
    public void testPackageReferenceInImport() throws Exception {
        runTest("../idea/tests/testData/resolve/references/PackageReferenceInImport.kt");
    }

    @TestMetadata("parameterByName.kt")
    public void testParameterByName() throws Exception {
        runTest("../idea/tests/testData/resolve/references/parameterByName.kt");
    }

    @TestMetadata("PropertyPlaceInClassObjectInObject.kt")
    public void testPropertyPlaceInClassObjectInObject() throws Exception {
        runTest("../idea/tests/testData/resolve/references/PropertyPlaceInClassObjectInObject.kt");
    }

    @TestMetadata("ReferenceInClassWhereConstraint.kt")
    public void testReferenceInClassWhereConstraint() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ReferenceInClassWhereConstraint.kt");
    }

    @TestMetadata("ReferenceInFunWhereConstraint.kt")
    public void testReferenceInFunWhereConstraint() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ReferenceInFunWhereConstraint.kt");
    }

    @TestMetadata("ReferenceToSam.kt")
    public void testReferenceToSam() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ReferenceToSam.kt");
    }

    @TestMetadata("ResolveClass.kt")
    public void testResolveClass() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ResolveClass.kt");
    }

    @TestMetadata("ResolvePackageInProperty.kt")
    public void testResolvePackageInProperty() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ResolvePackageInProperty.kt");
    }

    @TestMetadata("ResolvePackageInTheEndInProperty.kt")
    public void testResolvePackageInTheEndInProperty() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ResolvePackageInTheEndInProperty.kt");
    }

    @TestMetadata("ResolvePackageInTheMiddleInProperty.kt")
    public void testResolvePackageInTheMiddleInProperty() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ResolvePackageInTheMiddleInProperty.kt");
    }

    @TestMetadata("ResolvePackageInTheTypeNameInProperty.kt")
    public void testResolvePackageInTheTypeNameInProperty() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ResolvePackageInTheTypeNameInProperty.kt");
    }

    @TestMetadata("SamAdapter.kt")
    public void testSamAdapter() throws Exception {
        runTest("../idea/tests/testData/resolve/references/SamAdapter.kt");
    }

    @TestMetadata("SamConstructor.kt")
    public void testSamConstructor() throws Exception {
        runTest("../idea/tests/testData/resolve/references/SamConstructor.kt");
    }

    @TestMetadata("SamConstructorTypeArguments.kt")
    public void testSamConstructorTypeArguments() throws Exception {
        runTest("../idea/tests/testData/resolve/references/SamConstructorTypeArguments.kt");
    }

    @TestMetadata("SeveralOverrides.kt")
    public void testSeveralOverrides() throws Exception {
        runTest("../idea/tests/testData/resolve/references/SeveralOverrides.kt");
    }

    @TestMetadata("SyntheticProperty.kt")
    public void testSyntheticProperty() throws Exception {
        runTest("../idea/tests/testData/resolve/references/SyntheticProperty.kt");
    }

    @TestMetadata("TopLevelClassVsLocalClassConstructor.kt")
    public void testTopLevelClassVsLocalClassConstructor() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TopLevelClassVsLocalClassConstructor.kt");
    }

    @TestMetadata("TopLevelClassVsLocalClassConstructor2.kt")
    public void testTopLevelClassVsLocalClassConstructor2() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TopLevelClassVsLocalClassConstructor2.kt");
    }

    @TestMetadata("TopLevelClassVsLocalClassQualifier.kt")
    public void testTopLevelClassVsLocalClassQualifier() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TopLevelClassVsLocalClassQualifier.kt");
    }

    @TestMetadata("TopLevelCompanionObjectVsLocalClassConstructor.kt")
    public void testTopLevelCompanionObjectVsLocalClassConstructor() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TopLevelCompanionObjectVsLocalClassConstructor.kt");
    }

    @TestMetadata("TopLevelCompanionObjectVsLocalClassConstructor2.kt")
    public void testTopLevelCompanionObjectVsLocalClassConstructor2() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TopLevelCompanionObjectVsLocalClassConstructor2.kt");
    }

    @TestMetadata("TopLevelCompanionObjectVsLocalClassQualifier.kt")
    public void testTopLevelCompanionObjectVsLocalClassQualifier() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TopLevelCompanionObjectVsLocalClassQualifier.kt");
    }

    @TestMetadata("TopLevelObjectVsLocalClassConstructor.kt")
    public void testTopLevelObjectVsLocalClassConstructor() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TopLevelObjectVsLocalClassConstructor.kt");
    }

    @TestMetadata("TopLevelObjectVsLocalClassConstructor2.kt")
    public void testTopLevelObjectVsLocalClassConstructor2() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TopLevelObjectVsLocalClassConstructor2.kt");
    }

    @TestMetadata("TopLevelObjectVsLocalClassConstructor3.kt")
    public void testTopLevelObjectVsLocalClassConstructor3() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TopLevelObjectVsLocalClassConstructor3.kt");
    }

    @TestMetadata("TopLevelObjectVsLocalClassQualifier.kt")
    public void testTopLevelObjectVsLocalClassQualifier() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TopLevelObjectVsLocalClassQualifier.kt");
    }

    @TestMetadata("TypeAlias.kt")
    public void testTypeAlias() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TypeAlias.kt");
    }

    @TestMetadata("TypeAliasAsSupertypeConstructor.kt")
    public void testTypeAliasAsSupertypeConstructor() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TypeAliasAsSupertypeConstructor.kt");
    }

    @TestMetadata("TypeAliasRHS.kt")
    public void testTypeAliasRHS() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TypeAliasRHS.kt");
    }

    @TestMetadata("TypeArgumentBeforeDot.kt")
    public void testTypeArgumentBeforeDot() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TypeArgumentBeforeDot.kt");
    }

    @TestMetadata("TypeArgumentBeforeDot2.kt")
    public void testTypeArgumentBeforeDot2() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TypeArgumentBeforeDot2.kt");
    }

    @TestMetadata("TypeArgumentUnresolvedClass.kt")
    public void testTypeArgumentUnresolvedClass() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TypeArgumentUnresolvedClass.kt");
    }

    @TestMetadata("TypeArgumentUnresolvedConstructor.kt")
    public void testTypeArgumentUnresolvedConstructor() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TypeArgumentUnresolvedConstructor.kt");
    }

    @TestMetadata("TypeArgumentWrongNumber.kt")
    public void testTypeArgumentWrongNumber() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TypeArgumentWrongNumber.kt");
    }

    @TestMetadata("TypeParameterInAnonymousObject.kt")
    public void testTypeParameterInAnonymousObject() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TypeParameterInAnonymousObject.kt");
    }

    @TestMetadata("TypeParameterInFunctionLiteral.kt")
    public void testTypeParameterInFunctionLiteral() throws Exception {
        runTest("../idea/tests/testData/resolve/references/TypeParameterInFunctionLiteral.kt");
    }

    @TestMetadata("ValueParameter.kt")
    public void testValueParameter() throws Exception {
        runTest("../idea/tests/testData/resolve/references/ValueParameter.kt");
    }

    @TestMetadata("WrongNumberOfTypeArguments.kt")
    public void testWrongNumberOfTypeArguments() throws Exception {
        runTest("../idea/tests/testData/resolve/references/WrongNumberOfTypeArguments.kt");
    }

    @TestMetadata("WrongNumberOfTypeArguments2.kt")
    public void testWrongNumberOfTypeArguments2() throws Exception {
        runTest("../idea/tests/testData/resolve/references/WrongNumberOfTypeArguments2.kt");
    }

    @TestMetadata("WrongNumberOfTypeArguments3.kt")
    public void testWrongNumberOfTypeArguments3() throws Exception {
        runTest("../idea/tests/testData/resolve/references/WrongNumberOfTypeArguments3.kt");
    }

    @TestMetadata("WrongNumberOfTypeArgumentsInSupertype.kt")
    public void testWrongNumberOfTypeArgumentsInSupertype() throws Exception {
        runTest("../idea/tests/testData/resolve/references/WrongNumberOfTypeArgumentsInSupertype.kt");
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("../idea/tests/testData/resolve/references/arrayAccess")
    public static class ArrayAccess extends AbstractFirReferenceResolveTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("get.kt")
        public void testGet() throws Exception {
            runTest("../idea/tests/testData/resolve/references/arrayAccess/get.kt");
        }

        @TestMetadata("set.kt")
        public void testSet() throws Exception {
            runTest("../idea/tests/testData/resolve/references/arrayAccess/set.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("../idea/tests/testData/resolve/references/constructorDelegatingReference")
    public static class ConstructorDelegatingReference extends AbstractFirReferenceResolveTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("toPrimary.kt")
        public void testToPrimary() throws Exception {
            runTest("../idea/tests/testData/resolve/references/constructorDelegatingReference/toPrimary.kt");
        }

        @TestMetadata("toSecondary.kt")
        public void testToSecondary() throws Exception {
            runTest("../idea/tests/testData/resolve/references/constructorDelegatingReference/toSecondary.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("../idea/tests/testData/resolve/references/delegatedPropertyAccessors")
    public static class DelegatedPropertyAccessors extends AbstractFirReferenceResolveTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("unresolved.kt")
        public void testUnresolved() throws Exception {
            runTest("../idea/tests/testData/resolve/references/delegatedPropertyAccessors/unresolved.kt");
        }

        @RunWith(JUnit3RunnerWithInners.class)
        @TestMetadata("../idea/tests/testData/resolve/references/delegatedPropertyAccessors/inSource")
        public static class InSource extends AbstractFirReferenceResolveTest {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
            }

            @TestMetadata("getExtension.kt")
            public void testGetExtension() throws Exception {
                runTest("../idea/tests/testData/resolve/references/delegatedPropertyAccessors/inSource/getExtension.kt");
            }

            @TestMetadata("getMember.kt")
            public void testGetMember() throws Exception {
                runTest("../idea/tests/testData/resolve/references/delegatedPropertyAccessors/inSource/getMember.kt");
            }

            @TestMetadata("getMultipleDeclarations.kt")
            public void testGetMultipleDeclarations() throws Exception {
                runTest("../idea/tests/testData/resolve/references/delegatedPropertyAccessors/inSource/getMultipleDeclarations.kt");
            }

            @TestMetadata("getOneFakeOverride.kt")
            public void testGetOneFakeOverride() throws Exception {
                runTest("../idea/tests/testData/resolve/references/delegatedPropertyAccessors/inSource/getOneFakeOverride.kt");
            }
        }

        @RunWith(JUnit3RunnerWithInners.class)
        @TestMetadata("../idea/tests/testData/resolve/references/delegatedPropertyAccessors/inStandardLibrary")
        public static class InStandardLibrary extends AbstractFirReferenceResolveTest {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
            }

            @TestMetadata("lazy.kt")
            public void testLazy() throws Exception {
                runTest("../idea/tests/testData/resolve/references/delegatedPropertyAccessors/inStandardLibrary/lazy.kt");
            }

            @TestMetadata("notNull.kt")
            public void testNotNull() throws Exception {
                runTest("../idea/tests/testData/resolve/references/delegatedPropertyAccessors/inStandardLibrary/notNull.kt");
            }
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("../idea/tests/testData/resolve/references/forLoopIn")
    public static class ForLoopIn extends AbstractFirReferenceResolveTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("unresolvedIterator.kt")
        public void testUnresolvedIterator() throws Exception {
            runTest("../idea/tests/testData/resolve/references/forLoopIn/unresolvedIterator.kt");
        }

        @RunWith(JUnit3RunnerWithInners.class)
        @TestMetadata("../idea/tests/testData/resolve/references/forLoopIn/inBuiltIns")
        public static class InBuiltIns extends AbstractFirReferenceResolveTest {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
            }

            @TestMetadata("extension.kt")
            public void testExtension() throws Exception {
                runTest("../idea/tests/testData/resolve/references/forLoopIn/inBuiltIns/extension.kt");
            }

            @TestMetadata("member.kt")
            public void testMember() throws Exception {
                runTest("../idea/tests/testData/resolve/references/forLoopIn/inBuiltIns/member.kt");
            }
        }

        @RunWith(JUnit3RunnerWithInners.class)
        @TestMetadata("../idea/tests/testData/resolve/references/forLoopIn/inLibrary")
        public static class InLibrary extends AbstractFirReferenceResolveTest {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
            }

            @TestMetadata("extension.kt")
            public void testExtension() throws Exception {
                runTest("../idea/tests/testData/resolve/references/forLoopIn/inLibrary/extension.kt");
            }
        }

        @RunWith(JUnit3RunnerWithInners.class)
        @TestMetadata("../idea/tests/testData/resolve/references/forLoopIn/inSource")
        public static class InSource extends AbstractFirReferenceResolveTest {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
            }

            @TestMetadata("allExtensions.kt")
            public void testAllExtensions() throws Exception {
                runTest("../idea/tests/testData/resolve/references/forLoopIn/inSource/allExtensions.kt");
            }

            @TestMetadata("allMembers.kt")
            public void testAllMembers() throws Exception {
                runTest("../idea/tests/testData/resolve/references/forLoopIn/inSource/allMembers.kt");
            }

            @TestMetadata("nextMissing.kt")
            public void testNextMissing() throws Exception {
                runTest("../idea/tests/testData/resolve/references/forLoopIn/inSource/nextMissing.kt");
            }
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("../idea/tests/testData/resolve/references/invoke")
    public static class Invoke extends AbstractFirReferenceResolveTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("lambdaAndParens.kt")
        public void testLambdaAndParens() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/lambdaAndParens.kt");
        }

        @TestMetadata("lambdaAndParensIncorrectVararg.kt")
        public void testLambdaAndParensIncorrectVararg() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/lambdaAndParensIncorrectVararg.kt");
        }

        @TestMetadata("lambdaNoPar.kt")
        public void testLambdaNoPar() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/lambdaNoPar.kt");
        }

        @TestMetadata("lambdaNoParIncorrectVararg.kt")
        public void testLambdaNoParIncorrectVararg() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/lambdaNoParIncorrectVararg.kt");
        }

        @TestMetadata("lambdaNoParLabel.kt")
        public void testLambdaNoParLabel() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/lambdaNoParLabel.kt");
        }

        @TestMetadata("lambdaNoParLabelIncorrectVararg.kt")
        public void testLambdaNoParLabelIncorrectVararg() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/lambdaNoParLabelIncorrectVararg.kt");
        }

        @TestMetadata("lambdaNoParRCurly.kt")
        public void testLambdaNoParRCurly() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/lambdaNoParRCurly.kt");
        }

        @TestMetadata("lambdaNoParRCurlyIncorrectVararg.kt")
        public void testLambdaNoParRCurlyIncorrectVararg() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/lambdaNoParRCurlyIncorrectVararg.kt");
        }

        @TestMetadata("noParams.kt")
        public void testNoParams() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/noParams.kt");
        }

        @TestMetadata("noParamsRPar.kt")
        public void testNoParamsRPar() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/noParamsRPar.kt");
        }

        @TestMetadata("nonemptyLambdaRPar.kt")
        public void testNonemptyLambdaRPar() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/nonemptyLambdaRPar.kt");
        }

        @TestMetadata("nonemptyLambdaRParIncorrectVararg.kt")
        public void testNonemptyLambdaRParIncorrectVararg() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/nonemptyLambdaRParIncorrectVararg.kt");
        }

        @TestMetadata("oneParam.kt")
        public void testOneParam() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/oneParam.kt");
        }

        @TestMetadata("oneParamRPar.kt")
        public void testOneParamRPar() throws Exception {
            runTest("../idea/tests/testData/resolve/references/invoke/oneParamRPar.kt");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("../idea/tests/testData/resolve/references/nestedTypes")
    public static class NestedTypes extends AbstractFirReferenceResolveTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        @TestMetadata("ResolveCompanionInCompanionType.kt")
        public void testResolveCompanionInCompanionType() throws Exception {
            runTest("../idea/tests/testData/resolve/references/nestedTypes/ResolveCompanionInCompanionType.kt");
        }

        @TestMetadata("ResolveEndOfPackageInType.kt")
        public void testResolveEndOfPackageInType() throws Exception {
            runTest("../idea/tests/testData/resolve/references/nestedTypes/ResolveEndOfPackageInType.kt");
        }

        @TestMetadata("ResolveMiddleOfPackageInType.kt")
        public void testResolveMiddleOfPackageInType() throws Exception {
            runTest("../idea/tests/testData/resolve/references/nestedTypes/ResolveMiddleOfPackageInType.kt");
        }

        @TestMetadata("ResolveStartOfPackageInType.kt")
        public void testResolveStartOfPackageInType() throws Exception {
            runTest("../idea/tests/testData/resolve/references/nestedTypes/ResolveStartOfPackageInType.kt");
        }

        @TestMetadata("ResolveTypeInTheEndOfType.kt")
        public void testResolveTypeInTheEndOfType() throws Exception {
            runTest("../idea/tests/testData/resolve/references/nestedTypes/ResolveTypeInTheEndOfType.kt");
        }

        @TestMetadata("ResolveTypeInTheMiddleOfCompanionType.kt")
        public void testResolveTypeInTheMiddleOfCompanionType() throws Exception {
            runTest("../idea/tests/testData/resolve/references/nestedTypes/ResolveTypeInTheMiddleOfCompanionType.kt");
        }

        @TestMetadata("ResolveTypeInTheMiddleOfFunctionalType.kt")
        public void testResolveTypeInTheMiddleOfFunctionalType() throws Exception {
            runTest("../idea/tests/testData/resolve/references/nestedTypes/ResolveTypeInTheMiddleOfFunctionalType.kt");
        }

        @TestMetadata("ResolveTypeInTheMiddleOfNullableType.kt")
        public void testResolveTypeInTheMiddleOfNullableType() throws Exception {
            runTest("../idea/tests/testData/resolve/references/nestedTypes/ResolveTypeInTheMiddleOfNullableType.kt");
        }

        @TestMetadata("ResolveTypeInTheMiddleOfType.kt")
        public void testResolveTypeInTheMiddleOfType() throws Exception {
            runTest("../idea/tests/testData/resolve/references/nestedTypes/ResolveTypeInTheMiddleOfType.kt");
        }

        @TestMetadata("ResolveTypeInTheStartOfCompanionType.kt")
        public void testResolveTypeInTheStartOfCompanionType() throws Exception {
            runTest("../idea/tests/testData/resolve/references/nestedTypes/ResolveTypeInTheStartOfCompanionType.kt");
        }

        @TestMetadata("ResolveTypeInTheStartOfType.kt")
        public void testResolveTypeInTheStartOfType() throws Exception {
            runTest("../idea/tests/testData/resolve/references/nestedTypes/ResolveTypeInTheStartOfType.kt");
        }
    }
}
