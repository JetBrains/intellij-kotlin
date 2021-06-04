package org.jetbrains.uast.test.env.kotlin

import com.intellij.openapi.util.text.StringUtil
import com.intellij.rt.execution.junit.FileComparisonFailure
import junit.framework.TestCase
import java.io.File

private fun String.trimTrailingWhitespacesAndAddNewlineAtEOF(): String =
        this.split('\n').map(String::trimEnd).joinToString(separator = "\n").let {
            result -> if (result.endsWith("\n")) result else result + "\n"
        }


fun assertEqualsToFile(description: String, expected: File, actual: String) {
    if (!expected.exists()) {
        expected.writeText(actual)
        TestCase.fail("File didn't exist. New file was created (${expected.canonicalPath}).")
    }

    val expectedText = StringUtil.convertLineSeparators(expected.readText().trim()).trimTrailingWhitespacesAndAddNewlineAtEOF()
    val actualText = StringUtil.convertLineSeparators(actual.trim()).trimTrailingWhitespacesAndAddNewlineAtEOF()
    if (expectedText != actualText) {
        throw FileComparisonFailure(description, expectedText, actualText, expected.absolutePath)
    }
}
