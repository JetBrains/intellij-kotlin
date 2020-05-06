/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.formatter;

import com.intellij.application.options.CodeStyle;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.testFramework.EditorTestUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.idea.test.KotlinLightCodeInsightTestCase;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.SettingsConfigurator;

import java.io.File;

@SuppressWarnings("deprecation")
public abstract class AbstractTypingIndentationTestBase extends KotlinLightCodeInsightTestCase {
    public void doNewlineTest(String afterFilePath) throws Exception {
        doNewlineTest(afterFilePath, false);
    }

    public void doNewlineTestWithInvert(String afterInvFilePath) throws Exception {
        doNewlineTest(afterInvFilePath, true);
    }

    public void doNewlineTest(String afterFilePath, boolean inverted) throws Exception {
        String testFileName = afterFilePath.substring(0, afterFilePath.indexOf("."));
        String testFileExtension = afterFilePath.substring(afterFilePath.lastIndexOf("."));

        String originFilePath = testFileName + testFileExtension;
        String originalFileText = FileUtil.loadFile(new File(originFilePath), true);

        try {
            SettingsConfigurator configurator = FormatSettingsUtil.createConfigurator(originalFileText, CodeStyle.getSettings(getProject_()));
            if (!inverted) {
                configurator.configureSettings();
            }
            else {
                configurator.configureInvertedSettings();
            }

            doNewlineTest(originFilePath, afterFilePath);
        }
        finally {
            CodeStyle.getSettings(getProject_()).clearCodeStyleSettings();
        }
    }

    private void doNewlineTest(String beforeFilePath, String afterFilePath) {
        configureByFile(beforeFilePath);
        type('\n');

        CaretModel caretModel = getEditor().getCaretModel();
        int offset = caretModel.getOffset();
        String actualTextWithCaret = new StringBuilder(getEditor().getDocument().getText()).insert(offset, EditorTestUtil.CARET_TAG).toString();

        KotlinTestUtils.assertEqualsToFile(new File(afterFilePath), actualTextWithCaret);
    }
}
