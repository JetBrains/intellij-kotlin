/*
 * Copyright 2010-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.idea.refactoring.rename

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.idea.KotlinBundle
import org.jetbrains.kotlin.idea.caches.resolve.analyze
import org.jetbrains.kotlin.idea.references.mainReference
import org.jetbrains.kotlin.idea.references.resolveToDescriptors
import org.jetbrains.kotlin.idea.util.application.executeWriteCommand
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.DescriptorToSourceUtils
import org.jetbrains.kotlin.resolve.lazy.BodyResolveMode
import org.jetbrains.kotlin.resolve.source.getPsi

class RenameKotlinImplicitLambdaParameter : KotlinVariableInplaceRenameHandler() {
    override fun isAvailable(element: PsiElement?, editor: Editor, file: PsiFile): Boolean {
        val nameExpression = findElementToRename(file, editor)
        return nameExpression != null && isAutoCreatedItUsage(nameExpression)
    }

    override fun invoke(project: Project, editor: Editor?, file: PsiFile?, dataContext: DataContext) {
        if (editor == null || file == null) return
        val itExpression = findElementToRename(file, editor) ?: return
        val explicitItParameter = project.executeWriteCommand(
            KotlinBundle.message("text.convert._it_.to.explicit.lambda.parameter"),
            groupId = null,
            command = { convertImplicitItToExplicit(itExpression, editor) },
        ) ?: return

        doRename(explicitItParameter, editor, dataContext)
    }

    private fun findElementToRename(
        file: PsiFile,
        editor: Editor,
    ) = file.findElementForRename<KtNameReferenceExpression>(editor.caretModel.offset)

    override fun invoke(project: Project, elements: Array<out PsiElement>, dataContext: DataContext) {
        // Do nothing: this method is called not from editor
    }

    companion object {
        fun isAutoCreatedItUsage(expression: KtNameReferenceExpression) = resolveToAutoCreatedItDescriptor(expression) != null
        fun convertImplicitItToExplicit(itExpression: KtNameReferenceExpression, editor: Editor): KtParameter? {
            val target = itExpression.mainReference.resolveToDescriptors(itExpression.analyze()).single()
            val containingDescriptor = target.containingDeclaration ?: return null
            val functionLiteral = DescriptorToSourceUtils.descriptorToDeclaration(containingDescriptor) as? KtFunctionLiteral ?: return null
            val newExpr = KtPsiFactory(itExpression).createExpression("{ it -> }") as KtLambdaExpression
            functionLiteral.addRangeAfter(
                newExpr.functionLiteral.valueParameterList,
                newExpr.functionLiteral.arrow ?: return null,
                functionLiteral.lBrace,
            )

            PsiDocumentManager.getInstance(itExpression.project).doPostponedOperationsAndUnblockDocument(editor.document)

            return functionLiteral.valueParameters.singleOrNull()
        }

        fun getLambdaByImplicitItReference(expression: KtNameReferenceExpression) =
            resolveToAutoCreatedItDescriptor(expression)?.containingDeclaration?.source?.getPsi() as? KtFunctionLiteral

        private fun resolveToAutoCreatedItDescriptor(expression: KtNameReferenceExpression): ValueParameterDescriptor? {
            if (expression.getReferencedName() != "it") return null
            val context = expression.analyze(BodyResolveMode.PARTIAL)
            val target = expression.mainReference.resolveToDescriptors(context).singleOrNull() as? ValueParameterDescriptor ?: return null
            return if (context[BindingContext.AUTO_CREATED_IT, target] == true) target else null
        }
    }
}
