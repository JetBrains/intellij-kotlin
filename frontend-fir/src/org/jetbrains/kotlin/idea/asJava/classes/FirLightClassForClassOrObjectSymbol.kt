/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.asJava

import com.intellij.openapi.util.Comparing
import com.intellij.psi.*
import com.intellij.psi.impl.InheritanceImplUtil
import com.intellij.psi.impl.PsiClassImplUtil
import com.intellij.psi.search.SearchScope
import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.StubElement
import org.jetbrains.annotations.NonNls
import org.jetbrains.kotlin.asJava.classes.KotlinSuperTypeListBuilder
import org.jetbrains.kotlin.asJava.classes.getOutermostClassOrObject
import org.jetbrains.kotlin.asJava.classes.lazyPub
import org.jetbrains.kotlin.asJava.elements.KtLightField
import org.jetbrains.kotlin.asJava.elements.KtLightIdentifier
import org.jetbrains.kotlin.fir.symbols.StandardClassIds
import org.jetbrains.kotlin.idea.asJava.classes.getOrCreateFirLightClass
import org.jetbrains.kotlin.idea.frontend.api.symbols.*
import org.jetbrains.kotlin.idea.frontend.api.symbols.markers.KtSymbolKind
import org.jetbrains.kotlin.idea.frontend.api.types.KtClassType
import org.jetbrains.kotlin.idea.frontend.api.types.KtType
import org.jetbrains.kotlin.idea.util.ifFalse
import org.jetbrains.kotlin.load.java.structure.LightClassOriginKind
import org.jetbrains.kotlin.psi.KtBlockExpression
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassBody
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.debugText.getDebugText
import org.jetbrains.kotlin.psi.stubs.KotlinClassOrObjectStub

internal abstract class FirLightClassForClassOrObjectSymbol(
    private val classOrObjectSymbol: KtClassOrObjectSymbol,
    manager: PsiManager
) : FirLightClassBase(manager),
    StubBasedPsiElement<KotlinClassOrObjectStub<out KtClassOrObject>> {

    private val isTopLevel: Boolean = classOrObjectSymbol.symbolKind == KtSymbolKind.TOP_LEVEL

    abstract override fun getModifierList(): PsiModifierList?
    abstract override fun getOwnFields(): List<KtLightField>
    abstract override fun getOwnMethods(): List<PsiMethod>
    override fun isDeprecated(): Boolean = false //TODO()
    override fun getNameIdentifier(): KtLightIdentifier? = null //TODO()
    abstract override fun getExtendsList(): PsiReferenceList?
    abstract override fun getImplementsList(): PsiReferenceList?
    override fun getTypeParameterList(): PsiTypeParameterList? = null //TODO()
    override fun getTypeParameters(): Array<PsiTypeParameter> = emptyArray() //TODO()

    abstract override fun getOwnInnerClasses(): List<PsiClass>

    override fun getTextOffset(): Int = kotlinOrigin?.textOffset ?: 0
    override fun getStartOffsetInParent(): Int = kotlinOrigin?.startOffsetInParent ?: 0
    override fun isWritable() = kotlinOrigin?.isWritable ?: false
    override val kotlinOrigin: KtClassOrObject? = classOrObjectSymbol.psi as? KtClassOrObject

    protected fun createInheritanceList(forExtendsList: Boolean): PsiReferenceList {

        val role = if (forExtendsList) PsiReferenceList.Role.EXTENDS_LIST else PsiReferenceList.Role.IMPLEMENTS_LIST

        val listBuilder = KotlinSuperTypeListBuilder(
            kotlinOrigin = kotlinOrigin?.getSuperTypeList(),
            manager = manager,
            language = language,
            role = role
        )

        fun KtType.needToAddTypeIntoList(): Boolean {
            if (this !is KtClassType) return false

            // Do not add redundant "extends java.lang.Object" anywhere
            if (this.classId == StandardClassIds.Any) return false

            // We don't have Enum among enums supertype in sources neither we do for decompiled class-files and light-classes
            if (isEnum && this.classId == StandardClassIds.Enum) return false

            val isInterfaceType =
                (this.classSymbol as? KtClassOrObjectSymbol)?.classKind == KtClassKind.INTERFACE
            
            return forExtendsList == !isInterfaceType
        }

        //TODO Add support for kotlin.collections.
        classOrObjectSymbol.superTypes
            .filterIsInstance<KtClassType>()
            .filter { it.needToAddTypeIntoList() }
            .mapNotNull { it.mapSupertype(this, kotlinCollectionAsIs = true) }
            .forEach { listBuilder.addReference(it) }

        return listBuilder
    }

    protected fun MutableList<KtLightField>.addCompanionObjectFieldIfNeeded() {
        classOrObjectSymbol.companionObject?.run {
            add(FirLightFieldForObjectSymbol(this, this@FirLightClassForClassOrObjectSymbol, null))
        }
    }

    private val _containingFile: PsiFile? by lazyPub {

        val kotlinOrigin = kotlinOrigin ?: return@lazyPub null

        val containingClass = isTopLevel.ifFalse { getOrCreateFirLightClass(getOutermostClassOrObject(kotlinOrigin)) } ?: this

        FirFakeFileImpl(kotlinOrigin, containingClass)
    }

    override fun getContainingFile(): PsiFile? = _containingFile

    override fun getNavigationElement(): PsiElement = kotlinOrigin ?: this

    override fun isEquivalentTo(another: PsiElement?): Boolean =
        basicIsEquivalentTo(this, another) ||
                another is PsiClass && qualifiedName != null && Comparing.equal(another.qualifiedName, qualifiedName)

    abstract override fun equals(other: Any?): Boolean

    abstract override fun hashCode(): Int

    override fun getName(): String = classOrObjectSymbol.name.asString()

    override fun hasModifierProperty(@NonNls name: String): Boolean = modifierList?.hasModifierProperty(name) ?: false

    abstract override fun isInterface(): Boolean

    abstract override fun isAnnotationType(): Boolean

    abstract override fun isEnum(): Boolean

    override fun hasTypeParameters(): Boolean =
        classOrObjectSymbol is KtClass && classOrObjectSymbol.typeParameters.isNotEmpty()

    override fun isValid(): Boolean = kotlinOrigin?.isValid ?: true

    override fun isInheritor(baseClass: PsiClass, checkDeep: Boolean): Boolean =
        InheritanceImplUtil.isInheritor(this, baseClass, checkDeep)

    override fun toString() =
        "${this::class.java.simpleName}:${kotlinOrigin?.getDebugText()}"

    override fun getUseScope(): SearchScope = kotlinOrigin?.useScope ?: TODO()
    override fun getElementType(): IStubElementType<out StubElement<*>, *>? = kotlinOrigin?.elementType
    override fun getStub(): KotlinClassOrObjectStub<out KtClassOrObject>? = kotlinOrigin?.stub

    override val originKind: LightClassOriginKind
        get() = LightClassOriginKind.SOURCE

    override fun getQualifiedName() = kotlinOrigin?.fqName?.asString()

    override fun getInterfaces(): Array<PsiClass> = PsiClassImplUtil.getInterfaces(this)
    override fun getSuperClass(): PsiClass? = PsiClassImplUtil.getSuperClass(this)
    override fun getSupers(): Array<PsiClass> = PsiClassImplUtil.getSupers(this)
    override fun getSuperTypes(): Array<PsiClassType> = PsiClassImplUtil.getSuperTypes(this)

    override fun getContainingClass(): PsiClass? {

        val containingBody = kotlinOrigin?.parent as? KtClassBody
        val containingClass = containingBody?.parent as? KtClassOrObject
        containingClass?.let { return getOrCreateFirLightClass(it) }

        val containingBlock = kotlinOrigin?.parent as? KtBlockExpression
//        val containingScript = containingBlock?.parent as? KtScript
//        containingScript?.let { return KtLightClassForScript.create(it) }

        return null
    }

    override fun getParent(): PsiElement? = containingClass ?: containingFile

    override fun getScope(): PsiElement? = parent

    override fun isInheritorDeep(baseClass: PsiClass?, classToByPass: PsiClass?): Boolean =
        baseClass?.let { InheritanceImplUtil.isInheritorDeep(this, it, classToByPass) } ?: false

    abstract override fun copy(): FirLightClassForClassOrObjectSymbol
}