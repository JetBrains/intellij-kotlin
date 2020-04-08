/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.facet

import com.intellij.facet.ui.FacetEditorContext
import com.intellij.facet.ui.FacetEditorTab
import com.intellij.facet.ui.FacetValidatorsManager
import com.intellij.openapi.extensions.ExtensionPointName

interface KotlinFacetConfigurationExtension {
    companion object {
        val EP_NAME: ExtensionPointName<KotlinFacetConfigurationExtension> =
            ExtensionPointName.create("org.jetbrains.kotlin.facetConfigurationExtension")
    }

    fun createEditorTabs(editorContext: FacetEditorContext, validatorsManager: FacetValidatorsManager): List<FacetEditorTab>
}
