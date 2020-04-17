/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.filters

import com.intellij.execution.filters.*
import com.intellij.execution.filters.impl.HyperlinkInfoFactoryImpl
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import org.jetbrains.kotlin.idea.debugger.*
import org.jetbrains.kotlin.idea.util.application.runReadAction
import org.jetbrains.kotlin.resolve.jvm.JvmClassName
import java.util.*
import java.util.regex.Pattern

class KotlinExceptionFilter(private val searchScope: GlobalSearchScope) : Filter {
    private val exceptionFilter = ExceptionFilter(searchScope)

    override fun applyFilter(line: String, entireLength: Int): Filter.Result? {
        return runReadAction {
            val result = exceptionFilter.applyFilter(line, entireLength)
            if (result == null) parseNativeStackTraceLine(line, entireLength) else patchResult(result, line)
        }
    }

    private fun patchResult(result: Filter.Result, line: String): Filter.Result {
        val newHyperlinkInfo = createHyperlinkInfo(line, result) ?: return result

        return Filter.Result(result.resultItems.map {
            Filter.ResultItem(it.getHighlightStartOffset(), it.getHighlightEndOffset(), newHyperlinkInfo, it.getHighlightAttributes())
        })
    }

    private fun createHyperlinkInfo(line: String, defaultResult: Filter.Result): HyperlinkInfo? {
        val project = searchScope.project ?: return null

        val stackTraceElement = parseStackTraceLine(line) ?: return null

        // All true classes should be handled correctly in the default ExceptionFilter. Special cases:
        // - static facades;
        // - package facades / package parts (generated by pre-M13 compiled);
        // - local classes (and closures) in top-level function and property declarations.
        // - bad line numbers for inline functions
        // - already applied smap for inline functions

        val fileName = stackTraceElement.fileName

        if (!DebuggerUtils.isKotlinSourceFile(fileName)) return null

        // fullyQualifiedName is of format "package.Class$Inner"
        val fullyQualifiedName = stackTraceElement.className
        val lineNumber = stackTraceElement.lineNumber - 1

        val internalName = fullyQualifiedName.replace('.', '/')
        val jvmClassName = JvmClassName.byInternalName(internalName)

        val file = DebuggerUtils.findSourceFileForClassIncludeLibrarySources(project, searchScope, jvmClassName, fileName)

        if (file == null) {
            // File can't be found by class name and file name: this can happen when smap info is already applied.
            // Default filter favours looking for file from class name and that can lead to wrong navigation to inline fun call file and
            // line from inline function definition.
            val defaultLinkFileNames =
                defaultResult.resultItems.mapNotNullTo(HashSet()) { (it as? FileHyperlinkInfo)?.descriptor?.file?.name }
            if (!defaultLinkFileNames.contains(fileName)) {
                val filesByName = FilenameIndex.getFilesByName(project, fileName, searchScope).mapNotNullTo(HashSet()) {
                    if (!it.isValid) return@mapNotNullTo null
                    it.virtualFile
                }

                if (filesByName.isNotEmpty()) {
                    return if (filesByName.size > 1) {
                        HyperlinkInfoFactoryImpl.getInstance().createMultipleFilesHyperlinkInfo(filesByName.toList(), lineNumber, project)
                    } else {
                        OpenFileHyperlinkInfo(project, filesByName.first(), lineNumber)
                    }
                }
            }

            return null
        }

        val virtualFile = file.virtualFile ?: return null

        val hyperlinkInfoForInline = createHyperlinks(jvmClassName, virtualFile, lineNumber + 1, project)
        if (hyperlinkInfoForInline != null) {
            return hyperlinkInfoForInline
        }

        return OpenFileHyperlinkInfo(project, virtualFile, lineNumber)
    }

    private fun createHyperlinks(jvmName: JvmClassName, file: VirtualFile, line: Int, project: Project): InlineFunctionHyperLinkInfo? {
        if (!isInlineFunctionLineNumber(file, line, project)) return null

        val debugInfo = readBytecodeInfo(project, jvmName, file) ?: return null
        val smapData = debugInfo.smapData ?: return null

        val inlineInfos = arrayListOf<InlineFunctionHyperLinkInfo.InlineInfo>()

        val (inlineFunctionBodyFile, inlineFunctionBodyLine) =
            mapStacktraceLineToSource(smapData, line, project, SourceLineKind.EXECUTED_LINE, searchScope) ?: return null

        inlineInfos.add(
            InlineFunctionHyperLinkInfo.InlineInfo.InlineFunctionBodyInfo(
                inlineFunctionBodyFile.virtualFile,
                inlineFunctionBodyLine
            )
        )

        val inlineFunCallInfo = mapStacktraceLineToSource(smapData, line, project, SourceLineKind.CALL_LINE, searchScope)
        if (inlineFunCallInfo != null) {
            val (callSiteFile, callSiteLine) = inlineFunCallInfo
            inlineInfos.add(InlineFunctionHyperLinkInfo.InlineInfo.CallSiteInfo(callSiteFile.virtualFile, callSiteLine))
        }

        return InlineFunctionHyperLinkInfo(project, inlineInfos)
    }


    // Extracts file names from Kotlin Native stacktrace strings like
    // "\tat 1   main.kexe\t\t 0x000000010d7cdb4c kfun:package.function(kotlin.Int) + 108 (/Users/user.name/repo_name/file_name.kt:10:27)\n"
    // The form is encoded in Kotlin Native code and covered by tests witch are linked here.
    private fun parseNativeStackTraceLine(rawLine: String, entireLength: Int): Filter.Result? {
        val atPrefix = "at "
        val ktExtension = ".kt:"
        val project = searchScope.project ?: return null
        val line = rawLine.trim()

        if (!line.startsWith(atPrefix) && !line.endsWith(')')) {
            return null
        }

        val fileNameBegin = line.lastIndexOf('(') + 1
        if (fileNameBegin < 1) { // no parentheses => no file name provided
            return null
        }

        val fileNameEnd = line.indexOf(ktExtension, fileNameBegin) + ktExtension.length - 1
        if (fileNameEnd < ktExtension.length - 1) { // no kt file extension => not interested
            return null
        }

        val virtualFile = findFile(line.substring(fileNameBegin, fileNameEnd)) ?: return null
        val (lineNumber, columnNumber) = parsLineColumn(line.substring(fileNameEnd + 1, line.lastIndex))

        val offset = entireLength - rawLine.length + rawLine.indexOf(atPrefix)
        val highlightEndOffset = offset + (if (lineNumber > 0) line.lastIndex else fileNameEnd)

        // OpenFileHyperlinkInfo accepts zero-based line number
        val hyperLinkInfo = OpenFileHyperlinkInfo(project, virtualFile, lineNumber - 1, columnNumber)
        return Filter.Result(offset + fileNameBegin, highlightEndOffset, hyperLinkInfo)
    }

    companion object {
        // Matches strings like "\tat test.TestPackage$foo$f$1.invoke(a.kt:3)\n"
        //                   or "\tBreakpoint reached at test.TestPackage$foo$f$1.invoke(a.kt:3)\n"
        private val STACK_TRACE_ELEMENT_PATTERN = Pattern.compile("^[\\w|\\s]*at\\s+(.+)\\.(.+)\\((.+):(\\d+)\\)\\s*$")

        private val LINE_COLUMN_PATTERN = Pattern.compile("(\\d+):(\\d+)")

        private fun parseStackTraceLine(line: String): StackTraceElement? {
            val matcher = STACK_TRACE_ELEMENT_PATTERN.matcher(line)
            if (matcher.matches()) {
                val declaringClass = matcher.group(1)
                val methodName = matcher.group(2)
                val fileName = matcher.group(3)
                val lineNumber = matcher.group(4)
                //noinspection ConstantConditions
                return StackTraceElement(declaringClass, methodName, fileName, Integer.parseInt(lineNumber))
            }
            return null
        }

        private fun findFile(fileName: String): VirtualFile? {
            if (!DebuggerUtils.isKotlinSourceFile(fileName))
                return null

            val vfsFileName = FileUtil.toSystemIndependentName(fileName)
            return LocalFileSystem.getInstance().findFileByPath(vfsFileName)
        }

        private fun parsLineColumn(locationLine: String): Pair<Int, Int> {
            val matcher = LINE_COLUMN_PATTERN.matcher(locationLine)
            if (matcher.matches()) {
                val line = Integer.parseInt(matcher.group(1))
                val column = Integer.parseInt(matcher.group(2))
                return Pair(line, column)
            }
            return Pair(0, 0)
        }
    }
}
