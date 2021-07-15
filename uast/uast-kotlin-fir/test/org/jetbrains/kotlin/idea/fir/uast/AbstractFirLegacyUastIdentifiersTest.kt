/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.uast

import org.jetbrains.uast.UFile
import org.jetbrains.uast.test.common.kotlin.LegacyUastIdentifiersTestBase
import java.nio.file.Paths
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.absolute

abstract class AbstractFirLegacyUastIdentifiersTest : AbstractFirUastIdentifiersTest(), LegacyUastIdentifiersTestBase {
    @OptIn(ExperimentalPathApi::class)
    private val whitelist : Set<String> = setOf(
        // TODO: need a special handling of members in enum entries. See testEnumValueMembers in FirLegacyUastIdentifiersTestGenerated
        //   Seems related to KT-45115: in FIR, enum entry init is an anonymous object, and thus members in it are regarded as _local_.
        //   whereas FIR UAST conversion took a path to look up non-local FIR declaration for now.
        "uast-kotlin/testData/EnumValueMembers.kt"
    ).mapTo(mutableSetOf()) { Paths.get("..").resolve(it).absolute().normalize().toString() }

    override fun isExpectedToFail(filePath: String): Boolean {
        // TODO: Implement parent conversion in FIR UAST
        return true
    }

    override fun check(filePath: String, file: UFile) {
        if (filePath in whitelist) {
            return
        }
        super<LegacyUastIdentifiersTestBase>.check(filePath, file)
    }
}
