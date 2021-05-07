/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.asJava

import com.intellij.psi.*
import org.jetbrains.kotlin.asJava.builder.LightMemberOrigin
import org.jetbrains.kotlin.asJava.classes.lazyPub
import org.jetbrains.kotlin.asJava.elements.FirLightIdentifier
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase
import org.jetbrains.kotlin.idea.frontend.api.symbols.KtPropertySymbol
import org.jetbrains.kotlin.idea.frontend.api.symbols.markers.KtSimpleConstantValue
import org.jetbrains.kotlin.psi.KtDeclaration

internal class FirLightFieldForPropertySymbol(
    private val propertySymbol: KtPropertySymbol,
    nameGenerator: FieldNameGenerator,
    containingClass: FirLightClassBase,
    lightMemberOrigin: LightMemberOrigin?,
    isTopLevel: Boolean,
    forceStatic: Boolean,
    takePropertyVisibility: Boolean
) : FirLightField(containingClass, lightMemberOrigin) {

    private val _name: String = nameGenerator.generateUniqueFieldName(propertySymbol.name.asString())

    override val kotlinOrigin: KtDeclaration? = propertySymbol.psi as? KtDeclaration

    private val _returnedType: PsiType by lazyPub {
        propertySymbol.type.asPsiType(
            propertySymbol,
            this@FirLightFieldForPropertySymbol,
            FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE
        )
    }

    private val _isDeprecated: Boolean by lazyPub {
        propertySymbol.hasDeprecatedAnnotation(AnnotationUseSiteTarget.FIELD)
    }

    override fun isDeprecated(): Boolean = _isDeprecated

    private val _identifier: PsiIdentifier by lazyPub {
        FirLightIdentifier(this, propertySymbol)
    }

    override fun getNameIdentifier(): PsiIdentifier = _identifier

    override fun getType(): PsiType = _returnedType

    override fun getName(): String = _name

    private val _modifierList: PsiModifierList by lazyPub {

        val suppressFinal = !propertySymbol.isVal

        val modifiersFromSymbol = propertySymbol.computeModalityForMethod(
            isTopLevel = isTopLevel,
            suppressFinal = suppressFinal
        )

        val basicModifiers = modifiersFromSymbol.add(
            what = PsiModifier.STATIC,
            `if` = forceStatic
        )

        val visibility =
            if (takePropertyVisibility) propertySymbol.computeVisibility(isTopLevel = false) else PsiModifier.PRIVATE

        val modifiersWithVisibility = basicModifiers + visibility

        val modifiers = modifiersWithVisibility.add(
            what = PsiModifier.FINAL,
            `if` = !suppressFinal
        ).add(
            what = PsiModifier.TRANSIENT,
            `if` = propertySymbol.hasAnnotation("kotlin/jvm/Transient", null)
        ).add(
            what = PsiModifier.VOLATILE,
            `if` = propertySymbol.hasAnnotation("kotlin/jvm/Volatile", null)
        )

        val nullability = if (visibility != PsiModifier.PRIVATE)
            propertySymbol.type.getTypeNullability(propertySymbol, FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE)
        else NullabilityType.Unknown

        val annotations = propertySymbol.computeAnnotations(
            parent = this,
            nullability = nullability,
            annotationUseSiteTarget = AnnotationUseSiteTarget.FIELD,
        )

        FirLightClassModifierList(this, modifiers, annotations)
    }

    override fun getModifierList(): PsiModifierList = _modifierList

    private val _initializer by lazyPub {
        (propertySymbol.initializer as? KtSimpleConstantValue<*>)?.createPsiLiteral(this)
    }

    override fun getInitializer(): PsiExpression? = _initializer

    override fun equals(other: Any?): Boolean =
        this === other ||
                (other is FirLightFieldForPropertySymbol &&
                        kotlinOrigin == other.kotlinOrigin &&
                        propertySymbol == other.propertySymbol)

    override fun hashCode(): Int = kotlinOrigin.hashCode()
}