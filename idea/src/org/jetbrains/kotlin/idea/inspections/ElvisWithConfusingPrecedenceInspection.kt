// Copyright 2000-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.kotlin.idea.inspections

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.util.siblings
import org.jetbrains.kotlin.idea.KotlinBundle
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*

class ElvisWithConfusingPrecedenceInspection : AbstractKotlinInspection() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean) = binaryExpressionVisitor(fun(binaryExpression) {
        if (!binaryExpression.isElvis() || binaryExpression.left == null || binaryExpression.right == null) return
        val rightOperation = binaryExpression.right.operation()
        val parentOperation = binaryExpression.parent.operation()?.takeIf { it.left == binaryExpression }
        if (rightOperation == null && parentOperation == null) return
        holder.registerProblem(
            binaryExpression.operationReference,
            KotlinBundle.message("elvis.with.confusing.precedence"),
            AddParenthesesFix(),
            AddParenthesesFix(addRightHandSide = true)
        )
    })

    private class AddParenthesesFix(private val addRightHandSide: Boolean = false) : LocalQuickFix {
        override fun getName() = KotlinBundle.message(
            if (addRightHandSide) "add.parentheses.to.elvis.right.had.side" else "add.parentheses.to.elvis.expression"
        )

        override fun getFamilyName() = name

        override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
            val binaryExpression = descriptor.psiElement.parent as? KtBinaryExpression ?: return
            val rightOperation = binaryExpression.right.operation()
            val parentOperation = binaryExpression.parent.operation()
            val psiFactory = KtPsiFactory(project)
            when {
                rightOperation != null ->
                    if (addRightHandSide) {
                        rightOperation.expression.replace(psiFactory.createExpressionByPattern("($0)", rightOperation.expression))
                    } else {
                        val newExpression = psiFactory.buildExpression {
                            appendFixedText("(")
                            appendExpression(binaryExpression.left)
                            appendFixedText(binaryExpression.left.nextWhiteSpaceAndComment())
                            appendFixedText(binaryExpression.operationReference.text)
                            appendFixedText(binaryExpression.operationReference.nextWhiteSpaceAndComment())
                            appendExpression(rightOperation.left)
                            appendFixedText(")")
                            appendFixedText(rightOperation.left.nextWhiteSpaceAndComment())
                            appendFixedText(rightOperation.operation.text)
                            appendFixedText(rightOperation.operation.nextWhiteSpaceAndComment())
                            when (val right = rightOperation.right) {
                                is KtTypeReference -> appendTypeReference(right)
                                is KtExpression -> appendExpression(right)
                            }
                        }
                        binaryExpression.replace(newExpression)
                    }
                parentOperation != null ->
                    if (!addRightHandSide) {
                        binaryExpression.replace(psiFactory.createExpressionByPattern("($0)", binaryExpression))
                    } else {
                        val newExpression = psiFactory.buildExpression {
                            appendExpression(binaryExpression.left)
                            appendFixedText(binaryExpression.left.nextWhiteSpaceAndComment())
                            appendFixedText(binaryExpression.operationReference.text)
                            appendFixedText(binaryExpression.operationReference.nextWhiteSpaceAndComment())
                            appendFixedText("(")
                            appendExpression(binaryExpression.right)
                            appendFixedText(binaryExpression.nextWhiteSpaceAndComment())
                            appendFixedText(parentOperation.operation.text)
                            appendFixedText(parentOperation.operation.nextWhiteSpaceAndComment())
                            when (val right = parentOperation.right) {
                                is KtTypeReference -> appendTypeReference(right)
                                is KtExpression -> appendExpression(right)
                            }
                            appendFixedText(")")
                        }
                        parentOperation.expression.replace(newExpression)
                    }
            }
        }

        private fun PsiElement?.nextWhiteSpaceAndComment(): String {
            return this?.siblings(forward = true, withSelf = false)
                ?.takeWhile { it is PsiWhiteSpace || it is PsiComment }
                ?.joinToString("") { it.text }
                .orEmpty()
        }
    }

    companion object {
        private fun KtBinaryExpression.isElvis(): Boolean = operationToken == KtTokens.ELVIS

        private data class Operation(
            val expression: KtExpression,
            val left: KtExpression,
            val operation: KtSimpleNameExpression,
            val right: KtElement
        )

        private fun PsiElement?.operation(): Operation? {
            return when (this) {
                is KtBinaryExpression -> {
                    if (isElvis() || operationToken in KtTokens.ALL_ASSIGNMENTS) return null
                    val left = left ?: return null
                    val right = right ?: return null
                    Operation(this, left, operationReference, right)
                }
                is KtBinaryExpressionWithTypeRHS -> {
                    val right = right ?: return null
                    Operation(this, left, operationReference, right)
                }
                is KtIsExpression -> {
                    val right = typeReference ?: return null
                    Operation(this, leftHandSide, operationReference, right)
                }
                else -> return null
            }
        }
    }
}
