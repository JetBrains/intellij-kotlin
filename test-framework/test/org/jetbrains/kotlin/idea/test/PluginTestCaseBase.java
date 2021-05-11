/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.idea.test;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.testFramework.IdeaTestUtil;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.idea.util.IjPlatformUtil;
import org.jetbrains.kotlin.idea.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestJdkKind;

import java.io.File;

public class PluginTestCaseBase {
    private PluginTestCaseBase() {
    }

    @NotNull
    private static Sdk getSdk(String sdkHome, String name) {
        ProjectJdkTable table = IjPlatformUtil.getProjectJdkTableSafe();
        Sdk existing = table.findJdk(name);
        if (existing != null) {
            return existing;
        }
        return JavaSdk.getInstance().createJdk(name, sdkHome, true);
    }

    @NotNull
    public static Sdk fullJdk() {
        String javaHome = System.getProperty("java.home");
        assert new File(javaHome).isDirectory();
        return getSdk(javaHome, "Full JDK");
    }

    @NotNull
    public static Sdk addJdk(@NotNull Disposable disposable, @NotNull Function0<Sdk> getJdk) {
        Sdk jdk = getJdk.invoke();
        Sdk[] allJdks = IjPlatformUtil.getProjectJdkTableSafe().getAllJdks();
        for (Sdk existingJdk : allJdks) {
            if (existingJdk == jdk) {
                return existingJdk;
            }
        }
        ApplicationManager.getApplication().runWriteAction(() -> IjPlatformUtil.getProjectJdkTableSafe().addJdk(jdk, disposable));
        return jdk;
    }

    @NotNull
    public static Sdk jdk(@NotNull TestJdkKind kind) {
        switch (kind) {
            case MOCK_JDK:
                return IdeaTestUtil.getMockJdk18();
            case FULL_JDK_9:
                String jre9 = KotlinTestUtils.getAtLeastJdk9Home().getPath();
                return getSdk(jre9, "Full JDK 9");
            case FULL_JDK:
                return fullJdk();
            default:
                throw new UnsupportedOperationException(kind.toString());
        }
    }

    @NotNull
    public static LanguageLevel getLanguageLevel(@NotNull TestJdkKind kind) {
        switch (kind) {
            case MOCK_JDK:
                return LanguageLevel.JDK_1_8;
            case FULL_JDK_9:
                return LanguageLevel.JDK_1_9;
            case FULL_JDK:
                return LanguageLevel.JDK_1_8;
            default:
                throw new UnsupportedOperationException(kind.toString());
        }
    }
}
