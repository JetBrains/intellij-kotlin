/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.caches.resolve;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.idea.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.idea.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.jetbrains.kotlin.idea.test.TestRoot;
import org.junit.runner.RunWith;
import static org.jetbrains.kotlin.idea.artifacts.AdditionalKotlinArtifacts.compilerTestData;

/*
 * This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}.
 * DO NOT MODIFY MANUALLY.
 */
@SuppressWarnings("all")
@TestRoot("idea/tests")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
@TestMetadata("../../../intellij/out/kotlinc-testdata/testData/compiler/testData/asJava/script/ide")
public class IdeLightClassForScriptTestGenerated extends AbstractIdeLightClassForScriptTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    @Override
    protected void setUp() {
        compilerTestData("compiler/testData/asJava/script/ide");
        super.setUp();
    }

    @TestMetadata("HelloWorld.kts")
    public void testHelloWorld() throws Exception {
        runTest(compilerTestData("compiler/testData/asJava/script/ide/HelloWorld.kts"));
    }

    @TestMetadata("InnerClasses.kts")
    public void testInnerClasses() throws Exception {
        runTest(compilerTestData("compiler/testData/asJava/script/ide/InnerClasses.kts"));
    }
}
