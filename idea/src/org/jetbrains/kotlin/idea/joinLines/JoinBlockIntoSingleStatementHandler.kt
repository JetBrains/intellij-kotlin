/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.joinLines

import com.intellij.codeInsight.editorActions.JoinRawLinesHandlerDelegate
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiFile
import org.jetbrains.kotlin.idea.inspections.UseExpressionBodyInspection
import org.jetbrains.kotlin.idea.intentions.MergeIfsIntention
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.startOffset

class JoinBlockIntoSingleStatementHandler : JoinRawLinesHandlerDelegate {
    override fun tryJoinRawLines(document: Document, file: PsiFile, start: Int, end: Int): Int {
        if (file !is KtFile) return -1

        if (start == 0) return -1
        val c = document.charsSequence[start]
        val index = if (c == '\n') start - 1 else start

        val brace = file.findElementAt(index)!!
        if (brace.node!!.elementType != KtTokens.LBRACE) return -1

        val block = brace.parent as? KtBlockExpression ?: return -1
        val statement = block.statements.singleOrNull() ?: return -1

        val parent = block.parent
        val useExpressionBodyInspection = UseExpressionBodyInspection(convertEmptyToUnit = false)
        val oneLineReturnFunction = (parent as? KtDeclarationWithBody)?.takeIf { useExpressionBodyInspection.isActiveFor(it) }
        if (parent !is KtContainerNode && parent !is KtWhenEntry && oneLineReturnFunction == null) return -1

        if (block.node.getChildren(KtTokens.COMMENTS).isNotEmpty()) return -1 // otherwise we will loose comments

        // handle nested if's
        val pparent = parent.parent
        if (pparent is KtIfExpression) {
            if (block == pparent.then && statement is KtIfExpression && statement.`else` == null) {
                // if outer if has else-branch and inner does not have it, do not remove braces otherwise else-branch will belong to different if!
                if (pparent.`else` != null) return -1

                return MergeIfsIntention.applyTo(pparent)
            }

            if (block == pparent.`else`) {
                val ifParent = pparent.parent
                if (!(ifParent is KtBlockExpression ||
                            ifParent is KtDeclaration ||
                            KtPsiUtil.isAssignment(ifParent))
                ) {
                    return -1
                }
            }
        }

        return if (oneLineReturnFunction != null) {
            useExpressionBodyInspection.simplify(oneLineReturnFunction, false)
            oneLineReturnFunction.bodyExpression!!.startOffset
        } else {
            val newStatement = block.replace(statement)
            newStatement.textRange!!.startOffset
        }
    }

    override fun tryJoinLines(document: Document, file: PsiFile, start: Int, end: Int) = -1
}
