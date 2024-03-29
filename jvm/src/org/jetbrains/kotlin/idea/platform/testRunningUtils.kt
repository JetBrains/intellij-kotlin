/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.platform

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptorWithResolutionScopes
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.idea.highlighter.KotlinTestRunLineMarkerContributor.Companion.getTestStateIcon
import org.jetbrains.kotlin.idea.util.string.joinWithEscape
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.platform.IdePlatformKind
import org.jetbrains.kotlin.platform.TargetPlatform
import org.jetbrains.kotlin.platform.has
import org.jetbrains.kotlin.platform.impl.CommonIdePlatformKind
import org.jetbrains.kotlin.platform.impl.JsIdePlatformKind
import org.jetbrains.kotlin.platform.impl.JvmIdePlatformKind
import org.jetbrains.kotlin.platform.impl.NativeIdePlatformKind
import org.jetbrains.kotlin.platform.js.JsPlatform
import org.jetbrains.kotlin.platform.jvm.JvmPlatform
import org.jetbrains.kotlin.platform.konan.NativePlatform
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtNamedDeclaration
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.psiUtil.containingClassOrObject
import org.jetbrains.kotlin.psi.psiUtil.parentsWithSelf
import javax.swing.Icon

private val TEST_FQ_NAME = FqName("kotlin.test.Test")
private val IGNORE_FQ_NAME = FqName("kotlin.test.Ignore")

fun getGenericTestIcon(declaration: KtNamedDeclaration, descriptor: DeclarationDescriptor, initialLocations: () -> List<String>?): Icon? {
    if (!descriptor.isKotlinTestDeclaration()) return null

    val locations = initialLocations()?.toMutableList() ?: return null

    val clazz = when (declaration) {
        is KtClassOrObject -> declaration
        is KtNamedFunction -> declaration.containingClassOrObject ?: return null
        else -> return null
    }

    locations += clazz.parentsWithSelf.filterIsInstance<KtNamedDeclaration>()
        .mapNotNull { it.name }
        .toList().asReversed()

    val testName = (declaration as? KtNamedFunction)?.name
    if (testName != null) {
        locations += "$testName"
    }

    val prefix = if (testName != null) "test://" else "suite://"
    val url = prefix + locations.joinWithEscape('.')

    return getTestStateIcon(listOf("java:$url", url), declaration.project, strict = false)
}

private tailrec fun DeclarationDescriptor.isIgnored(): Boolean {
    if (annotations.any { it.fqName == IGNORE_FQ_NAME }) {
        return true
    }

    val containingClass = containingDeclaration as? ClassDescriptor ?: return false
    return containingClass.isIgnored()
}

fun DeclarationDescriptor.isKotlinTestDeclaration(): Boolean {
    if (isIgnored()) {
        return false
    }

    if (annotations.any { it.fqName == TEST_FQ_NAME }) {
        return true
    }

    val classDescriptor = this as? ClassDescriptorWithResolutionScopes ?: return false
    return classDescriptor.declaredCallableMembers.any { it.isKotlinTestDeclaration() }
}

internal fun IdePlatformKind.isCompatibleWith(platform: TargetPlatform): Boolean {
    return when (this) {
        is JvmIdePlatformKind -> platform.has(JvmPlatform::class)
        is NativeIdePlatformKind -> platform.has(NativePlatform::class)
        is JsIdePlatformKind -> platform.has(JsPlatform::class)
        is CommonIdePlatformKind -> true
        else -> false
    }
}