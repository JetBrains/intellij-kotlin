/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.decompiler.textBuilder;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("testData/decompiler/decompiledTextJvm")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class JvmDecompiledTextTestGenerated extends AbstractJvmDecompiledTextTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInDecompiledTextJvm() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("testData/decompiler/decompiledTextJvm"), Pattern.compile("^([^\\.]+)$"), null, true);
    }

    @TestMetadata("EnumWithQuotes")
    public void testEnumWithQuotes() throws Exception {
        runTest("testData/decompiler/decompiledTextJvm/EnumWithQuotes");
    }

    @TestMetadata("Modifiers")
    public void testModifiers() throws Exception {
        runTest("testData/decompiler/decompiledTextJvm/Modifiers");
    }

    @TestMetadata("MultifileClass")
    public void testMultifileClass() throws Exception {
        runTest("testData/decompiler/decompiledTextJvm/MultifileClass");
    }

    @TestMetadata("PackageWithQuotes")
    public void testPackageWithQuotes() throws Exception {
        runTest("testData/decompiler/decompiledTextJvm/PackageWithQuotes");
    }

    @TestMetadata("TestKt")
    public void testTestKt() throws Exception {
        runTest("testData/decompiler/decompiledTextJvm/TestKt");
    }

    @TestMetadata("TypeAliases")
    public void testTypeAliases() throws Exception {
        runTest("testData/decompiler/decompiledTextJvm/TypeAliases");
    }

    @TestMetadata("testData/decompiler/decompiledTextJvm/EnumWithQuotes")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class EnumWithQuotes extends AbstractJvmDecompiledTextTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInEnumWithQuotes() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("testData/decompiler/decompiledTextJvm/EnumWithQuotes"), Pattern.compile("^([^\\.]+)$"), null, true);
        }
    }

    @TestMetadata("testData/decompiler/decompiledTextJvm/Modifiers")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Modifiers extends AbstractJvmDecompiledTextTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInModifiers() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("testData/decompiler/decompiledTextJvm/Modifiers"), Pattern.compile("^([^\\.]+)$"), null, true);
        }
    }

    @TestMetadata("testData/decompiler/decompiledTextJvm/MultifileClass")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class MultifileClass extends AbstractJvmDecompiledTextTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInMultifileClass() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("testData/decompiler/decompiledTextJvm/MultifileClass"), Pattern.compile("^([^\\.]+)$"), null, true);
        }
    }

    @TestMetadata("testData/decompiler/decompiledTextJvm/PackageWithQuotes")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class PackageWithQuotes extends AbstractJvmDecompiledTextTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInPackageWithQuotes() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("testData/decompiler/decompiledTextJvm/PackageWithQuotes"), Pattern.compile("^([^\\.]+)$"), null, true);
        }
    }

    @TestMetadata("testData/decompiler/decompiledTextJvm/TestKt")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class TestKt extends AbstractJvmDecompiledTextTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInTestKt() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("testData/decompiler/decompiledTextJvm/TestKt"), Pattern.compile("^([^\\.]+)$"), null, true);
        }
    }

    @TestMetadata("testData/decompiler/decompiledTextJvm/TypeAliases")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class TypeAliases extends AbstractJvmDecompiledTextTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInTypeAliases() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("testData/decompiler/decompiledTextJvm/TypeAliases"), Pattern.compile("^([^\\.]+)$"), null, true);
        }
    }
}
