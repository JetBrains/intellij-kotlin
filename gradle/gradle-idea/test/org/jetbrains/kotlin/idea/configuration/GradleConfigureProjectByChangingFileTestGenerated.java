/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.configuration;

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
@TestRoot("gradle/gradle-idea")
@TestDataPath("$CONTENT_ROOT")
public class GradleConfigureProjectByChangingFileTestGenerated extends AbstractGradleConfigureProjectByChangingFileTest {
    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("../../idea/testData/configuration/gradle")
    public static class Gradle extends AbstractGradleConfigureProjectByChangingFileTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTestGradle, this, testDataFilePath);
        }

        @TestMetadata("default")
        public void testDefault() throws Exception {
            runTest("../../idea/testData/configuration/gradle/default/");
        }

        @TestMetadata("eapVersion")
        public void testEapVersion() throws Exception {
            runTest("../../idea/testData/configuration/gradle/eapVersion/");
        }

        @TestMetadata("jreLib")
        public void testJreLib() throws Exception {
            runTest("../../idea/testData/configuration/gradle/jreLib/");
        }

        @TestMetadata("js")
        public void testJs() throws Exception {
            runTest("../../idea/testData/configuration/gradle/js/");
        }

        @TestMetadata("m04Version")
        public void testM04Version() throws Exception {
            runTest("../../idea/testData/configuration/gradle/m04Version/");
        }

        @TestMetadata("missedLibrary")
        public void testMissedLibrary() throws Exception {
            runTest("../../idea/testData/configuration/gradle/missedLibrary/");
        }

        @TestMetadata("plugin_present")
        public void testPlugin_present() throws Exception {
            runTest("../../idea/testData/configuration/gradle/plugin_present/");
        }

        @TestMetadata("rcVersion")
        public void testRcVersion() throws Exception {
            runTest("../../idea/testData/configuration/gradle/rcVersion/");
        }

        @TestMetadata("withJava9ModuleInfo")
        public void testWithJava9ModuleInfo() throws Exception {
            runTest("../../idea/testData/configuration/gradle/withJava9ModuleInfo/");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("../../idea/testData/configuration/gsk")
    public static class Gsk extends AbstractGradleConfigureProjectByChangingFileTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTestGradle, this, testDataFilePath);
        }

        @TestMetadata("eap11Version")
        public void testEap11Version() throws Exception {
            runTest("../../idea/testData/configuration/gsk/eap11Version/");
        }

        @TestMetadata("eapVersion")
        public void testEapVersion() throws Exception {
            runTest("../../idea/testData/configuration/gsk/eapVersion/");
        }

        @TestMetadata("helloWorld")
        public void testHelloWorld() throws Exception {
            runTest("../../idea/testData/configuration/gsk/helloWorld/");
        }

        @TestMetadata("missedLibrary")
        public void testMissedLibrary() throws Exception {
            runTest("../../idea/testData/configuration/gsk/missedLibrary/");
        }

        @TestMetadata("pluginPresent")
        public void testPluginPresent() throws Exception {
            runTest("../../idea/testData/configuration/gsk/pluginPresent/");
        }
    }
}
