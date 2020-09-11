/*
 * Copyright 2000-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea

import com.intellij.openapi.application.ApplicationInfo
import com.intellij.openapi.ui.Messages
import com.intellij.util.PlatformUtils
import com.intellij.util.text.nullize
import org.intellij.lang.annotations.RegExp

object KotlinPluginCompatibilityVerifier {
    @JvmStatic
    fun checkCompatibility() {
        val kotlinVersion = KotlinPluginVersion.getCurrent() ?: return
        val platformVersion = PlatformVersion.getCurrent() ?: return

        if (kotlinVersion.platformVersion.platform != platformVersion.platform) {
            Messages.showWarningDialog(
                KotlinBundle.message("plugin.verifier.compatibility.issue.message", kotlinVersion, platformVersion),
                KotlinBundle.message("plugin.verifier.compatibility.issue.title")
            )
        }
    }
}

data class KotlinPluginVersion(
    val kotlinVersion: String, // 1.2.3
    val milestone: String?, // M1
    val status: String, // release, eap, rc
    val buildNumber: String?, // 53
    val platformVersion: PlatformVersion,
    val patchNumber: String // usually '1'
) {
    companion object {
        private const val KOTLIN_VERSION_REGEX_STRING =
            "^([\\d.]+)" +                // Version number, like 1.3.50
                    "(?:-(M\\d+))?" +     // (Optional) M-release, like M2
                    "-([A-Za-z]+)" +      // status, like 'eap/dev/release'
                    "(?:-(\\d+))?" +      // (Optional) buildNumber (absent for 'release')
                    "-([A-Za-z0-9.]+)" +  // Platform version, like Studio4.0.1
                    "-(\\d+)$"            // Tooling update, like '-1'

        private val KOTLIN_VERSION_REGEX = KOTLIN_VERSION_REGEX_STRING.toRegex()

        fun parse(version: String): KotlinPluginVersion? {
            val matchResult = KOTLIN_VERSION_REGEX.matchEntire(version) ?: return null
            val (kotlinVersion, milestone, status, buildNumber, platformString, patchNumber) = matchResult.destructured
            val platformVersion = PlatformVersion.parse(platformString) ?: return null
            return KotlinPluginVersion(
                kotlinVersion,
                milestone.nullize(),
                status,
                buildNumber.nullize(),
                platformVersion,
                patchNumber
            )
        }

        fun getCurrent(): KotlinPluginVersion? = parse(KotlinPluginUtil.getPluginVersion())
    }

    override fun toString() = "$kotlinVersion for $platformVersion"
}
