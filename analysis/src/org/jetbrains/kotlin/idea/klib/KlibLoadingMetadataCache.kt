/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.klib

import com.intellij.openapi.application.ApplicationManager

// BUNCH: 192
class KlibLoadingMetadataCache : KlibLoadingMetadataCacheCompat() {

    companion object {
        @JvmStatic
        fun getInstance(): KlibLoadingMetadataCache =
            ApplicationManager.getApplication().getService(KlibLoadingMetadataCache::class.java)
    }

}
