/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.maven;

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
@TestRoot("maven")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
@TestMetadata("testData/maven-inspections")
public class KotlinMavenInspectionTestGenerated extends AbstractKotlinMavenInspectionTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @TestMetadata("bothCompileAndTestCompileInTheSameExecution.xml")
    public void testBothCompileAndTestCompileInTheSameExecution() throws Exception {
        runTest("testData/maven-inspections/bothCompileAndTestCompileInTheSameExecution.xml");
    }

    @TestMetadata("dependencyWithNoExecution.xml")
    public void testDependencyWithNoExecution() throws Exception {
        runTest("testData/maven-inspections/dependencyWithNoExecution.xml");
    }

    @TestMetadata("deprecatedJre.xml")
    public void testDeprecatedJre() throws Exception {
        runTest("testData/maven-inspections/deprecatedJre.xml");
    }

    @TestMetadata("deprecatedJreWithDependencyManagement.xml")
    public void testDeprecatedJreWithDependencyManagement() throws Exception {
        runTest("testData/maven-inspections/deprecatedJreWithDependencyManagement.xml");
    }

    @TestMetadata("deprecatedKotlinxCoroutines.xml")
    public void testDeprecatedKotlinxCoroutines() throws Exception {
        runTest("testData/maven-inspections/deprecatedKotlinxCoroutines.xml");
    }

    @TestMetadata("deprecatedKotlinxCoroutinesNoError.xml")
    public void testDeprecatedKotlinxCoroutinesNoError() throws Exception {
        runTest("testData/maven-inspections/deprecatedKotlinxCoroutinesNoError.xml");
    }

    @TestMetadata("ideAndMavenVersions.xml")
    public void testIdeAndMavenVersions() throws Exception {
        runTest("testData/maven-inspections/ideAndMavenVersions.xml");
    }

    @TestMetadata("ideAndMavenVersionsSuppression.xml")
    public void testIdeAndMavenVersionsSuppression() throws Exception {
        runTest("testData/maven-inspections/ideAndMavenVersionsSuppression.xml");
    }

    @TestMetadata("kotlinTestWithJunit.xml")
    public void testKotlinTestWithJunit() throws Exception {
        runTest("testData/maven-inspections/kotlinTestWithJunit.xml");
    }

    @TestMetadata("missingDependencies.xml")
    public void testMissingDependencies() throws Exception {
        runTest("testData/maven-inspections/missingDependencies.xml");
    }

    @TestMetadata("noExecutions.xml")
    public void testNoExecutions() throws Exception {
        runTest("testData/maven-inspections/noExecutions.xml");
    }

    @TestMetadata("oldVersionWithJre.xml")
    public void testOldVersionWithJre() throws Exception {
        runTest("testData/maven-inspections/oldVersionWithJre.xml");
    }

    @TestMetadata("sameVersionPluginLibrary.xml")
    public void testSameVersionPluginLibrary() throws Exception {
        runTest("testData/maven-inspections/sameVersionPluginLibrary.xml");
    }

    @TestMetadata("sameVersionPluginLibrarySuppression.xml")
    public void testSameVersionPluginLibrarySuppression() throws Exception {
        runTest("testData/maven-inspections/sameVersionPluginLibrarySuppression.xml");
    }

    @TestMetadata("wrongJsExecution.xml")
    public void testWrongJsExecution() throws Exception {
        runTest("testData/maven-inspections/wrongJsExecution.xml");
    }

    @TestMetadata("wrongPhaseExecution.xml")
    public void testWrongPhaseExecution() throws Exception {
        runTest("testData/maven-inspections/wrongPhaseExecution.xml");
    }
}
