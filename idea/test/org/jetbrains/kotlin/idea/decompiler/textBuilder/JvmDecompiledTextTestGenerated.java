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

/*
 * This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}.
 * DO NOT MODIFY MANUALLY.
 */
@SuppressWarnings("all")
@TestMetadata("idea/testData/decompiler/decompiledTextJvm")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class JvmDecompiledTextTestGenerated extends AbstractJvmDecompiledTextTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresent() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("idea/testData/decompiler/decompiledTextJvm"), Pattern.compile("^([^\\.]+)$"), null, true);
    }

    @TestMetadata("EnumWithQuotes")
    public void testEnumWithQuotes() throws Exception {
        runTest("idea/testData/decompiler/decompiledTextJvm/EnumWithQuotes/");
    }

    @TestMetadata("Modifiers")
    public void testModifiers() throws Exception {
        runTest("idea/testData/decompiler/decompiledTextJvm/Modifiers/");
    }

    @TestMetadata("MultifileClass")
    public void testMultifileClass() throws Exception {
        runTest("idea/testData/decompiler/decompiledTextJvm/MultifileClass/");
    }

    @TestMetadata("PackageWithQuotes")
    public void testPackageWithQuotes() throws Exception {
        runTest("idea/testData/decompiler/decompiledTextJvm/PackageWithQuotes/");
    }

    @TestMetadata("TestKt")
    public void testTestKt() throws Exception {
        runTest("idea/testData/decompiler/decompiledTextJvm/TestKt/");
    }

    @TestMetadata("TypeAliases")
    public void testTypeAliases() throws Exception {
        runTest("idea/testData/decompiler/decompiledTextJvm/TypeAliases/");
    }
}
