/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.caches.resolve;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("kotlin/idea/testData/compiler/asJava/lightClasses")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class IdeCompiledLightClassTestGenerated extends AbstractIdeCompiledLightClassTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInLightClasses() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("kotlin/idea/testData/compiler/asJava/lightClasses"), Pattern.compile("^([^.]+)\\.(kt|kts)$"), null, true, "local", "compilationErrors", "ideRegression");
    }

    @TestMetadata("AnnotatedParameterInEnumConstructor.kt")
    public void testAnnotatedParameterInEnumConstructor() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/AnnotatedParameterInEnumConstructor.kt");
    }

    @TestMetadata("AnnotatedParameterInInnerClassConstructor.kt")
    public void testAnnotatedParameterInInnerClassConstructor() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/AnnotatedParameterInInnerClassConstructor.kt");
    }

    @TestMetadata("AnnotationClass.kt")
    public void testAnnotationClass() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/AnnotationClass.kt");
    }

    @TestMetadata("DataClassWithCustomImplementedMembers.kt")
    public void testDataClassWithCustomImplementedMembers() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/DataClassWithCustomImplementedMembers.kt");
    }

    @TestMetadata("DelegatedNested.kt")
    public void testDelegatedNested() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/DelegatedNested.kt");
    }

    @TestMetadata("Delegation.kt")
    public void testDelegation() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/Delegation.kt");
    }

    @TestMetadata("DeprecatedEnumEntry.kt")
    public void testDeprecatedEnumEntry() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/DeprecatedEnumEntry.kt");
    }

    @TestMetadata("DeprecatedNotHiddenInClass.kt")
    public void testDeprecatedNotHiddenInClass() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/DeprecatedNotHiddenInClass.kt");
    }

    @TestMetadata("DollarsInName.kt")
    public void testDollarsInName() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/DollarsInName.kt");
    }

    @TestMetadata("DollarsInNameNoPackage.kt")
    public void testDollarsInNameNoPackage() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/DollarsInNameNoPackage.kt");
    }

    @TestMetadata("ExtendingInterfaceWithDefaultImpls.kt")
    public void testExtendingInterfaceWithDefaultImpls() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/ExtendingInterfaceWithDefaultImpls.kt");
    }

    @TestMetadata("HiddenDeprecated.kt")
    public void testHiddenDeprecated() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/HiddenDeprecated.kt");
    }

    @TestMetadata("HiddenDeprecatedInClass.kt")
    public void testHiddenDeprecatedInClass() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/HiddenDeprecatedInClass.kt");
    }

    @TestMetadata("InheritingInterfaceDefaultImpls.kt")
    public void testInheritingInterfaceDefaultImpls() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/InheritingInterfaceDefaultImpls.kt");
    }

    @TestMetadata("InlineReified.kt")
    public void testInlineReified() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/InlineReified.kt");
    }

    @TestMetadata("JvmNameOnMember.kt")
    public void testJvmNameOnMember() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/JvmNameOnMember.kt");
    }

    @TestMetadata("JvmStatic.kt")
    public void testJvmStatic() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/JvmStatic.kt");
    }

    @TestMetadata("NestedObjects.kt")
    public void testNestedObjects() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/NestedObjects.kt");
    }

    @TestMetadata("NonDataClassWithComponentFunctions.kt")
    public void testNonDataClassWithComponentFunctions() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/NonDataClassWithComponentFunctions.kt");
    }

    @TestMetadata("PublishedApi.kt")
    public void testPublishedApi() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/PublishedApi.kt");
    }

    @TestMetadata("SpecialAnnotationsOnAnnotationClass.kt")
    public void testSpecialAnnotationsOnAnnotationClass() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/SpecialAnnotationsOnAnnotationClass.kt");
    }

    @TestMetadata("StubOrderForOverloads.kt")
    public void testStubOrderForOverloads() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/StubOrderForOverloads.kt");
    }

    @TestMetadata("TypePararametersInClass.kt")
    public void testTypePararametersInClass() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/TypePararametersInClass.kt");
    }

    @TestMetadata("VarArgs.kt")
    public void testVarArgs() throws Exception {
        runTest("kotlin/idea/testData/compiler/asJava/lightClasses/VarArgs.kt");
    }

    @TestMetadata("kotlin/idea/testData/compiler/asJava/lightClasses/delegation")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Delegation extends AbstractIdeCompiledLightClassTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInDelegation() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("kotlin/idea/testData/compiler/asJava/lightClasses/delegation"), Pattern.compile("^([^.]+)\\.(kt|kts)$"), null, true);
        }

        @TestMetadata("Function.kt")
        public void testFunction() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/delegation/Function.kt");
        }

        @TestMetadata("Property.kt")
        public void testProperty() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/delegation/Property.kt");
        }
    }

    @TestMetadata("kotlin/idea/testData/compiler/asJava/lightClasses/facades")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Facades extends AbstractIdeCompiledLightClassTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInFacades() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("kotlin/idea/testData/compiler/asJava/lightClasses/facades"), Pattern.compile("^([^.]+)\\.(kt|kts)$"), null, true);
        }

        @TestMetadata("AllPrivate.kt")
        public void testAllPrivate() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/facades/AllPrivate.kt");
        }

        @TestMetadata("MultiFile.kt")
        public void testMultiFile() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/facades/MultiFile.kt");
        }

        @TestMetadata("SingleFile.kt")
        public void testSingleFile() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/facades/SingleFile.kt");
        }

        @TestMetadata("SingleJvmClassName.kt")
        public void testSingleJvmClassName() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/facades/SingleJvmClassName.kt");
        }
    }

    @TestMetadata("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class NullabilityAnnotations extends AbstractIdeCompiledLightClassTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInNullabilityAnnotations() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations"), Pattern.compile("^([^.]+)\\.(kt|kts)$"), null, true);
        }

        @TestMetadata("Class.kt")
        public void testClass() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/Class.kt");
        }

        @TestMetadata("ClassObjectField.kt")
        public void testClassObjectField() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/ClassObjectField.kt");
        }

        @TestMetadata("ClassWithConstructor.kt")
        public void testClassWithConstructor() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/ClassWithConstructor.kt");
        }

        @TestMetadata("ClassWithConstructorAndProperties.kt")
        public void testClassWithConstructorAndProperties() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/ClassWithConstructorAndProperties.kt");
        }

        @TestMetadata("FileFacade.kt")
        public void testFileFacade() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/FileFacade.kt");
        }

        @TestMetadata("Generic.kt")
        public void testGeneric() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/Generic.kt");
        }

        @TestMetadata("IntOverridesAny.kt")
        public void testIntOverridesAny() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/IntOverridesAny.kt");
        }

        @TestMetadata("JvmOverloads.kt")
        public void testJvmOverloads() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/JvmOverloads.kt");
        }

        @TestMetadata("NullableUnitReturn.kt")
        public void testNullableUnitReturn() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/NullableUnitReturn.kt");
        }

        @TestMetadata("OverrideAnyWithUnit.kt")
        public void testOverrideAnyWithUnit() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/OverrideAnyWithUnit.kt");
        }

        @TestMetadata("PlatformTypes.kt")
        public void testPlatformTypes() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/PlatformTypes.kt");
        }

        @TestMetadata("Primitives.kt")
        public void testPrimitives() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/Primitives.kt");
        }

        @TestMetadata("PrivateInClass.kt")
        public void testPrivateInClass() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/PrivateInClass.kt");
        }

        @TestMetadata("Synthetic.kt")
        public void testSynthetic() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/Synthetic.kt");
        }

        @TestMetadata("Trait.kt")
        public void testTrait() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/Trait.kt");
        }

        @TestMetadata("UnitAsGenericArgument.kt")
        public void testUnitAsGenericArgument() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/UnitAsGenericArgument.kt");
        }

        @TestMetadata("UnitParameter.kt")
        public void testUnitParameter() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/UnitParameter.kt");
        }

        @TestMetadata("VoidReturn.kt")
        public void testVoidReturn() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/nullabilityAnnotations/VoidReturn.kt");
        }
    }

    @TestMetadata("kotlin/idea/testData/compiler/asJava/lightClasses/object")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Object extends AbstractIdeCompiledLightClassTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInObject() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("kotlin/idea/testData/compiler/asJava/lightClasses/object"), Pattern.compile("^([^.]+)\\.(kt|kts)$"), null, true);
        }

        @TestMetadata("SimpleObject.kt")
        public void testSimpleObject() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/object/SimpleObject.kt");
        }
    }

    @TestMetadata("kotlin/idea/testData/compiler/asJava/lightClasses/publicField")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class PublicField extends AbstractIdeCompiledLightClassTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInPublicField() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("kotlin/idea/testData/compiler/asJava/lightClasses/publicField"), Pattern.compile("^([^.]+)\\.(kt|kts)$"), null, true);
        }

        @TestMetadata("CompanionObject.kt")
        public void testCompanionObject() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/publicField/CompanionObject.kt");
        }

        @TestMetadata("Simple.kt")
        public void testSimple() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/publicField/Simple.kt");
        }
    }

    @TestMetadata("kotlin/idea/testData/compiler/asJava/lightClasses/script")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Script extends AbstractIdeCompiledLightClassTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInScript() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("kotlin/idea/testData/compiler/asJava/lightClasses/script"), Pattern.compile("^([^.]+)\\.(kt|kts)$"), null, true);
        }

        @TestMetadata("HelloWorld.kts")
        public void testHelloWorld() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/script/HelloWorld.kts");
        }

        @TestMetadata("InnerClasses.kts")
        public void testInnerClasses() throws Exception {
            runTest("kotlin/idea/testData/compiler/asJava/lightClasses/script/InnerClasses.kts");
        }
    }
}
