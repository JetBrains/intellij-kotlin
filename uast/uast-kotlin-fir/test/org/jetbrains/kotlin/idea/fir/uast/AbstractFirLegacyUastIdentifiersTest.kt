/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.uast

import org.jetbrains.uast.UFile
import org.jetbrains.uast.test.common.kotlin.LegacyUastIdentifiersTestBase

abstract class AbstractFirLegacyUastIdentifiersTest : AbstractFirUastIdentifiersTest(), LegacyUastIdentifiersTestBase {
    override fun isExpectedToFail(filePath: String): Boolean {
        // TODO: Implement parent conversion in FIR UAST
        return true
    }

    override fun check(filePath: String, file: UFile) {
        super<LegacyUastIdentifiersTestBase>.check(filePath, file)
    }
}
