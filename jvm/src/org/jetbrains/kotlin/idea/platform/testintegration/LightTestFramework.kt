package org.jetbrains.kotlin.idea.platform.testintegration

import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.openapi.util.NlsSafe
import com.intellij.testIntegration.TestFramework
import org.jetbrains.kotlin.psi.KtNamedDeclaration

interface LightTestFramework {
    val name: @NlsSafe String

    fun qualifiedName(namedDeclaration: KtNamedDeclaration): String?
    fun detectFramework(namedDeclaration: KtNamedDeclaration): LightTestFrameworkResult

    companion object {
        val EXTENSION_NAME: ExtensionPointName<LightTestFramework> = ExtensionPointName.create("org.jetbrains.kotlin.lightTestFramework")
    }
}

sealed class LightTestFrameworkResult

data class ResolvedLightTestFrameworkResult(val testFramework: TestFramework): LightTestFrameworkResult()
object UnsureLightTestFrameworkResult : LightTestFrameworkResult()
object NoLightTestFrameworkResult : LightTestFrameworkResult()