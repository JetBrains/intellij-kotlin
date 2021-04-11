/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.perf.synthetic;

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
@TestRoot("performance-tests")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
@TestMetadata("../idea/tests/testData/addImport")
public class PerformanceAddImportTestGenerated extends AbstractPerformanceAddImportTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doPerfTest, this, testDataFilePath);
    }

    @TestMetadata("CannotImportClass1.kt")
    public void testCannotImportClass1() throws Exception {
        runTest("../idea/tests/testData/addImport/CannotImportClass1.kt");
    }

    @TestMetadata("CannotImportClass2.kt")
    public void testCannotImportClass2() throws Exception {
        runTest("../idea/tests/testData/addImport/CannotImportClass2.kt");
    }

    @TestMetadata("ClassAlreadyImported1.kt")
    public void testClassAlreadyImported1() throws Exception {
        runTest("../idea/tests/testData/addImport/ClassAlreadyImported1.kt");
    }

    @TestMetadata("ClassAlreadyImported2.kt")
    public void testClassAlreadyImported2() throws Exception {
        runTest("../idea/tests/testData/addImport/ClassAlreadyImported2.kt");
    }

    @TestMetadata("Comments.kt")
    public void testComments() throws Exception {
        runTest("../idea/tests/testData/addImport/Comments.kt");
    }

    @TestMetadata("CommentsNoPackageDirective.kt")
    public void testCommentsNoPackageDirective() throws Exception {
        runTest("../idea/tests/testData/addImport/CommentsNoPackageDirective.kt");
    }

    @TestMetadata("ConflictingLocalRef.kt")
    public void testConflictingLocalRef() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingLocalRef.kt");
    }

    @TestMetadata("ConflictingNameAppearsAndHasUsage1.kt")
    public void testConflictingNameAppearsAndHasUsage1() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameAppearsAndHasUsage1.kt");
    }

    @TestMetadata("ConflictingNameAppearsAndHasUsage2.kt")
    public void testConflictingNameAppearsAndHasUsage2() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameAppearsAndHasUsage2.kt");
    }

    @TestMetadata("ConflictingNameAppearsAndHasUsage3.kt")
    public void testConflictingNameAppearsAndHasUsage3() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameAppearsAndHasUsage3.kt");
    }

    @TestMetadata("ConflictingNameAppearsAndHasUsage4.kt")
    public void testConflictingNameAppearsAndHasUsage4() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameAppearsAndHasUsage4.kt");
    }

    @TestMetadata("ConflictingNameAppearsAndHasUsage5.kt")
    public void testConflictingNameAppearsAndHasUsage5() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameAppearsAndHasUsage5.kt");
    }

    @TestMetadata("ConflictingNameAppearsAndHasUsage6.kt")
    public void testConflictingNameAppearsAndHasUsage6() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameAppearsAndHasUsage6.kt");
    }

    @TestMetadata("ConflictingNameAppearsAndHasUsage7.kt")
    public void testConflictingNameAppearsAndHasUsage7() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameAppearsAndHasUsage7.kt");
    }

    @TestMetadata("ConflictingNameAppearsButUsageIsQualified.kt")
    public void testConflictingNameAppearsButUsageIsQualified() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameAppearsButUsageIsQualified.kt");
    }

    @TestMetadata("ConflictingNameAppearsFalseUsage.kt")
    public void testConflictingNameAppearsFalseUsage() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameAppearsFalseUsage.kt");
    }

    @TestMetadata("ConflictingNameAppearsFalseUsage2.kt")
    public void testConflictingNameAppearsFalseUsage2() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameAppearsFalseUsage2.kt");
    }

    @TestMetadata("ConflictingNameAppearsNoUsage.kt")
    public void testConflictingNameAppearsNoUsage() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameAppearsNoUsage.kt");
    }

    @TestMetadata("ConflictingNameHasExplicitImportAlready.kt")
    public void testConflictingNameHasExplicitImportAlready() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameHasExplicitImportAlready.kt");
    }

    @TestMetadata("ConflictingNameNoAllUnderImport.kt")
    public void testConflictingNameNoAllUnderImport() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameNoAllUnderImport.kt");
    }

    @TestMetadata("ConflictingNameNoAllUnderImport2.kt")
    public void testConflictingNameNoAllUnderImport2() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameNoAllUnderImport2.kt");
    }

    @TestMetadata("ConflictingNameNoAllUnderImport3.kt")
    public void testConflictingNameNoAllUnderImport3() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictingNameNoAllUnderImport3.kt");
    }

    @TestMetadata("ConflictsExtensions.kt")
    public void testConflictsExtensions() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictsExtensions.kt");
    }

    @TestMetadata("ConflictsExtensions1.kt")
    public void testConflictsExtensions1() throws Exception {
        runTest("../idea/tests/testData/addImport/ConflictsExtensions1.kt");
    }

    @TestMetadata("DoNotDropConflictingOnStar.kt")
    public void testDoNotDropConflictingOnStar() throws Exception {
        runTest("../idea/tests/testData/addImport/DoNotDropConflictingOnStar.kt");
    }

    @TestMetadata("DropExplicitImports.kt")
    public void testDropExplicitImports() throws Exception {
        runTest("../idea/tests/testData/addImport/DropExplicitImports.kt");
    }

    @TestMetadata("DropExplicitImports2.kt")
    public void testDropExplicitImports2() throws Exception {
        runTest("../idea/tests/testData/addImport/DropExplicitImports2.kt");
    }

    @TestMetadata("FunctionAlreadyImported1.kt")
    public void testFunctionAlreadyImported1() throws Exception {
        runTest("../idea/tests/testData/addImport/FunctionAlreadyImported1.kt");
    }

    @TestMetadata("FunctionAlreadyImported2.kt")
    public void testFunctionAlreadyImported2() throws Exception {
        runTest("../idea/tests/testData/addImport/FunctionAlreadyImported2.kt");
    }

    @TestMetadata("ImportClassSimple.kt")
    public void testImportClassSimple() throws Exception {
        runTest("../idea/tests/testData/addImport/ImportClassSimple.kt");
    }

    @TestMetadata("ImportClassWhenFunctionImported.kt")
    public void testImportClassWhenFunctionImported() throws Exception {
        runTest("../idea/tests/testData/addImport/ImportClassWhenFunctionImported.kt");
    }

    @TestMetadata("ImportEnumMember1.kt")
    public void testImportEnumMember1() throws Exception {
        runTest("../idea/tests/testData/addImport/ImportEnumMember1.kt");
    }

    @TestMetadata("ImportEnumMember2.kt")
    public void testImportEnumMember2() throws Exception {
        runTest("../idea/tests/testData/addImport/ImportEnumMember2.kt");
    }

    @TestMetadata("ImportFromObject.kt")
    public void testImportFromObject() throws Exception {
        runTest("../idea/tests/testData/addImport/ImportFromObject.kt");
    }

    @TestMetadata("ImportFromRoot.kt")
    public void testImportFromRoot() throws Exception {
        runTest("../idea/tests/testData/addImport/ImportFromRoot.kt");
    }

    @TestMetadata("ImportFunctionBug.kt")
    public void testImportFunctionBug() throws Exception {
        runTest("../idea/tests/testData/addImport/ImportFunctionBug.kt");
    }

    @TestMetadata("ImportNestedClass.kt")
    public void testImportNestedClass() throws Exception {
        runTest("../idea/tests/testData/addImport/ImportNestedClass.kt");
    }

    @TestMetadata("ImportSecondFunction.kt")
    public void testImportSecondFunction() throws Exception {
        runTest("../idea/tests/testData/addImport/ImportSecondFunction.kt");
    }

    @TestMetadata("KeywordNames.kt")
    public void testKeywordNames() throws Exception {
        runTest("../idea/tests/testData/addImport/KeywordNames.kt");
    }

    @TestMetadata("NameCountForStarNotReached.kt")
    public void testNameCountForStarNotReached() throws Exception {
        runTest("../idea/tests/testData/addImport/NameCountForStarNotReached.kt");
    }

    @TestMetadata("NameCountForStarReached.kt")
    public void testNameCountForStarReached() throws Exception {
        runTest("../idea/tests/testData/addImport/NameCountForStarReached.kt");
    }

    @TestMetadata("NoConflictingNameForInaccessibleClass1.kt")
    public void testNoConflictingNameForInaccessibleClass1() throws Exception {
        runTest("../idea/tests/testData/addImport/NoConflictingNameForInaccessibleClass1.kt");
    }

    @TestMetadata("NoConflictingNameForInaccessibleClass2.kt")
    public void testNoConflictingNameForInaccessibleClass2() throws Exception {
        runTest("../idea/tests/testData/addImport/NoConflictingNameForInaccessibleClass2.kt");
    }

    @TestMetadata("NoNeedToImportStandardClass.kt")
    public void testNoNeedToImportStandardClass() throws Exception {
        runTest("../idea/tests/testData/addImport/NoNeedToImportStandardClass.kt");
    }

    @TestMetadata("PackageDoesNotConflictWithClass.kt")
    public void testPackageDoesNotConflictWithClass() throws Exception {
        runTest("../idea/tests/testData/addImport/PackageDoesNotConflictWithClass.kt");
    }

    @TestMetadata("PropertyAlreadyImported1.kt")
    public void testPropertyAlreadyImported1() throws Exception {
        runTest("../idea/tests/testData/addImport/PropertyAlreadyImported1.kt");
    }

    @TestMetadata("PropertyAlreadyImported2.kt")
    public void testPropertyAlreadyImported2() throws Exception {
        runTest("../idea/tests/testData/addImport/PropertyAlreadyImported2.kt");
    }

    @TestMetadata("StdlibImportsLast.kt")
    public void testStdlibImportsLast() throws Exception {
        runTest("../idea/tests/testData/addImport/StdlibImportsLast.kt");
    }
}
