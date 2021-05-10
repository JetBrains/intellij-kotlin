/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.maven.configuration;

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
public class MavenConfigureProjectByChangingFileTestGenerated extends AbstractMavenConfigureProjectByChangingFileTest {
    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/configurator/jvm")
    public static class Jvm extends AbstractMavenConfigureProjectByChangingFileTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTestWithMaven, this, testDataFilePath);
        }

        @TestMetadata("fixExisting")
        public void testFixExisting() throws Exception {
            runTest("testData/configurator/jvm/fixExisting/");
        }

        @TestMetadata("jreLib")
        public void testJreLib() throws Exception {
            runTest("testData/configurator/jvm/jreLib/");
        }

        @TestMetadata("libraryMissed")
        public void testLibraryMissed() throws Exception {
            runTest("testData/configurator/jvm/libraryMissed/");
        }

        @TestMetadata("pluginMissed")
        public void testPluginMissed() throws Exception {
            runTest("testData/configurator/jvm/pluginMissed/");
        }

        @TestMetadata("simpleProject")
        public void testSimpleProject() throws Exception {
            runTest("testData/configurator/jvm/simpleProject/");
        }

        @TestMetadata("simpleProjectEAP")
        public void testSimpleProjectEAP() throws Exception {
            runTest("testData/configurator/jvm/simpleProjectEAP/");
        }

        @TestMetadata("simpleProjectRc")
        public void testSimpleProjectRc() throws Exception {
            runTest("testData/configurator/jvm/simpleProjectRc/");
        }

        @TestMetadata("simpleProjectSnapshot")
        public void testSimpleProjectSnapshot() throws Exception {
            runTest("testData/configurator/jvm/simpleProjectSnapshot/");
        }

        @TestMetadata("withJava9ModuleInfo")
        public void testWithJava9ModuleInfo() throws Exception {
            runTest("testData/configurator/jvm/withJava9ModuleInfo/");
        }
    }

    @RunWith(JUnit3RunnerWithInners.class)
    @TestMetadata("testData/configurator/js")
    public static class Js extends AbstractMavenConfigureProjectByChangingFileTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTestWithJSMaven, this, testDataFilePath);
        }

        @TestMetadata("libraryMissed")
        public void testLibraryMissed() throws Exception {
            runTest("testData/configurator/js/libraryMissed/");
        }

        @TestMetadata("pluginMissed")
        public void testPluginMissed() throws Exception {
            runTest("testData/configurator/js/pluginMissed/");
        }

        @TestMetadata("simpleProject")
        public void testSimpleProject() throws Exception {
            runTest("testData/configurator/js/simpleProject/");
        }

        @TestMetadata("simpleProjectSnapshot")
        public void testSimpleProjectSnapshot() throws Exception {
            runTest("testData/configurator/js/simpleProjectSnapshot/");
        }
    }
}
