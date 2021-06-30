/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.uast.test.comparasion

import org.jetbrains.uast.test.common.kotlin.LegacyUastIdentifiersTestBase
import java.nio.file.Paths
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.absolute

abstract class AbstractFE1LegacyUastIdentifiersTest : AbstractFE1UastIdentifiersTest(), LegacyUastIdentifiersTestBase {
    // TODO: better not to see exceptions from legacy UAST
    @OptIn(ExperimentalPathApi::class)
    private val whitelist : Set<String> = setOf(
        "uast-kotlin/testData/DestructuringDeclaration.kt",
        "uast-kotlin/testData/LambdaReturn.kt",
        "uast-kotlin/testData/WhenAndDestructing.kt",
    ).mapTo(mutableSetOf()) { Paths.get("..").resolve(it).absolute().normalize().toString() }

    override fun isExpectedToFail(filePath: String): Boolean {
        return filePath in whitelist
    }
}
