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

package org.jetbrains.kotlin.idea.codeInsight.surroundWith.statement;

import com.intellij.codeInsight.CodeInsightBundle;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.psi.KtTryExpression;

public class KotlinTryCatchFinallySurrounder extends KotlinTrySurrounderBase {

    @Override
    protected String getCodeTemplate() {
        return "try { \n} catch(e: Exception) {\n} finally {\n}";
    }

    @NotNull
    @Override
    protected TextRange getTextRangeForCaret(@NotNull KtTryExpression expression) {
        return getCatchTypeParameterTextRange(expression);
    }

    @Override
    public String getTemplateDescription() {
        return CodeInsightBundle.message("surround.with.try.catch.finally.template");
    }
}
