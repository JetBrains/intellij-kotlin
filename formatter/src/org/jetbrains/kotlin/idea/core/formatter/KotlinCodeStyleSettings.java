/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.core.formatter;

import com.intellij.application.options.CodeStyle;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.idea.util.FormatterUtilKt;
import org.jetbrains.kotlin.idea.util.ReflectionUtil;

public class KotlinCodeStyleSettings extends CustomCodeStyleSettings {
    @ReflectionUtil.SkipInEquals
    @NotNull
    public KotlinPackageEntryTable PACKAGES_TO_USE_IMPORT_ON_DEMAND = new KotlinPackageEntryTable();

    @ReflectionUtil.SkipInEquals
    @NotNull
    public KotlinPackageEntryTable IMPORTS_LAYOUT = new KotlinPackageEntryTable();

    public boolean SPACE_AROUND_RANGE = false;
    public boolean SPACE_BEFORE_TYPE_COLON = false;
    public boolean SPACE_AFTER_TYPE_COLON = true;
    public boolean SPACE_BEFORE_EXTEND_COLON = true;
    public boolean SPACE_AFTER_EXTEND_COLON = true;
    public boolean INSERT_WHITESPACES_IN_SIMPLE_ONE_LINE_METHOD = true;
    public boolean ALIGN_IN_COLUMNS_CASE_BRANCH = false;
    public boolean SPACE_AROUND_FUNCTION_TYPE_ARROW = true;
    public boolean SPACE_AROUND_WHEN_ARROW = true;
    public boolean SPACE_BEFORE_LAMBDA_ARROW = true;
    public boolean SPACE_BEFORE_WHEN_PARENTHESES = true;
    public boolean LBRACE_ON_NEXT_LINE = false;
    public int NAME_COUNT_TO_USE_STAR_IMPORT = ApplicationManager.getApplication().isUnitTestMode() ? Integer.MAX_VALUE : 5;
    public int NAME_COUNT_TO_USE_STAR_IMPORT_FOR_MEMBERS = ApplicationManager.getApplication().isUnitTestMode() ? Integer.MAX_VALUE : 3;
    public boolean IMPORT_NESTED_CLASSES = false;
    public boolean CONTINUATION_INDENT_IN_PARAMETER_LISTS = true;
    public boolean CONTINUATION_INDENT_IN_ARGUMENT_LISTS = true;
    public boolean CONTINUATION_INDENT_FOR_EXPRESSION_BODIES = true;
    public boolean CONTINUATION_INDENT_FOR_CHAINED_CALLS = true;
    public boolean CONTINUATION_INDENT_IN_SUPERTYPE_LISTS = true;
    public boolean CONTINUATION_INDENT_IN_IF_CONDITIONS = true;
    public boolean CONTINUATION_INDENT_IN_ELVIS = true;
    public int BLANK_LINES_AROUND_BLOCK_WHEN_BRANCHES = 0;
    public int WRAP_EXPRESSION_BODY_FUNCTIONS = 0;
    public int WRAP_ELVIS_EXPRESSIONS = 1;
    public boolean IF_RPAREN_ON_NEW_LINE = false;
    public boolean ALLOW_TRAILING_COMMA = false;
    public boolean ALLOW_TRAILING_COMMA_ON_CALL_SITE = false;
    public int BLANK_LINES_BEFORE_DECLARATION_WITH_COMMENT_OR_ANNOTATION_ON_SEPARATE_LINE = 1;

    @ReflectionUtil.SkipInEquals
    public String CODE_STYLE_DEFAULTS = null;

    private final boolean isTempForDeserialize;

    public KotlinCodeStyleSettings(CodeStyleSettings container) {
        this(container, false);
    }

    private KotlinCodeStyleSettings(CodeStyleSettings container, boolean isTempForDeserialize) {
        super("JetCodeStyleSettings", container);

        this.isTempForDeserialize = isTempForDeserialize;

        // defaults in IDE but not in tests
        if (!ApplicationManager.getApplication().isUnitTestMode()) {
            PACKAGES_TO_USE_IMPORT_ON_DEMAND.addEntry(new KotlinPackageEntry("java.util", false));
            PACKAGES_TO_USE_IMPORT_ON_DEMAND.addEntry(new KotlinPackageEntry("kotlinx.android.synthetic", true));
            PACKAGES_TO_USE_IMPORT_ON_DEMAND.addEntry(new KotlinPackageEntry("io.ktor", true));
        }

        // Many of test data actually depend on this order of imports,
        // that is why we put it here even for test mode
        IMPORTS_LAYOUT.addEntry(KotlinPackageEntry.ALL_OTHER_IMPORTS_ENTRY);
        IMPORTS_LAYOUT.addEntry(new KotlinPackageEntry("java", true));
        IMPORTS_LAYOUT.addEntry(new KotlinPackageEntry("javax", true));
        IMPORTS_LAYOUT.addEntry(new KotlinPackageEntry("kotlin", true));
        IMPORTS_LAYOUT.addEntry(KotlinPackageEntry.ALL_OTHER_ALIAS_IMPORTS_ENTRY);
    }

    public static KotlinCodeStyleSettings getInstance(Project project) {
        return CodeStyle.getSettings(project).getCustomSettings(KotlinCodeStyleSettings.class);
    }

    @Override
    public Object clone() {
        KotlinCodeStyleSettings clone = (KotlinCodeStyleSettings)super.clone();

        clone.PACKAGES_TO_USE_IMPORT_ON_DEMAND = new KotlinPackageEntryTable();
        clone.PACKAGES_TO_USE_IMPORT_ON_DEMAND.copyFrom(this.PACKAGES_TO_USE_IMPORT_ON_DEMAND);

        clone.IMPORTS_LAYOUT = new KotlinPackageEntryTable();
        clone.IMPORTS_LAYOUT.copyFrom(this.IMPORTS_LAYOUT);

        return clone;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof KotlinCodeStyleSettings)) return false;

        KotlinCodeStyleSettings that = (KotlinCodeStyleSettings)obj;

        if (!Comparing.equal(PACKAGES_TO_USE_IMPORT_ON_DEMAND, that.PACKAGES_TO_USE_IMPORT_ON_DEMAND)) return false;
        if (!Comparing.equal(IMPORTS_LAYOUT, that.IMPORTS_LAYOUT)) return false;
        if (!ReflectionUtil.comparePublicNonFinalFieldsWithSkip(this, that)) return false;
        return true;
    }

    @Override
    public void writeExternal(Element parentElement, @NotNull CustomCodeStyleSettings parentSettings) throws WriteExternalException {
        if (CODE_STYLE_DEFAULTS != null) {
            KotlinCodeStyleSettings defaultKotlinCodeStyle = (KotlinCodeStyleSettings) parentSettings.clone();

            FormatterUtilKt.applyKotlinCodeStyle(CODE_STYLE_DEFAULTS, defaultKotlinCodeStyle, false);

            parentSettings = defaultKotlinCodeStyle;
        }

        super.writeExternal(parentElement, parentSettings);
    }

    @Override
    public void readExternal(Element parentElement) throws InvalidDataException {
        if (isTempForDeserialize) {
            super.readExternal(parentElement);
            return;
        }

        KotlinCodeStyleSettings tempSettings = readExternalToTemp(parentElement);
        String customDefaults = tempSettings.CODE_STYLE_DEFAULTS;

        FormatterUtilKt.applyKotlinCodeStyle(customDefaults, this, true);

        // Actual read
        super.readExternal(parentElement);
    }

    private static KotlinCodeStyleSettings readExternalToTemp(Element parentElement) {
        // Read to temp
        KotlinCodeStyleSettings tempSettings = new KotlinCodeStyleSettings(null, true);
        tempSettings.readExternal(parentElement);

        return tempSettings;
    }
}
