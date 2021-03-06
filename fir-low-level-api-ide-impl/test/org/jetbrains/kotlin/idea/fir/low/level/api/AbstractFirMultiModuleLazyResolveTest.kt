/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.low.level.api

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.psi.PsiManager
import org.jetbrains.kotlin.executeOnPooledThreadInReadAction
import org.jetbrains.kotlin.fir.declarations.FirFile
import org.jetbrains.kotlin.fir.render
import org.jetbrains.kotlin.idea.fir.low.level.api.api.getOrBuildFirOfType
import org.jetbrains.kotlin.idea.fir.low.level.api.api.getResolveState
import org.jetbrains.kotlin.idea.jsonUtils.getString
import org.jetbrains.kotlin.idea.stubs.AbstractMultiModuleTest
import org.jetbrains.kotlin.idea.test.IDEA_TEST_DATA_DIR
import org.jetbrains.kotlin.idea.util.sourceRoots
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.test.KotlinRoot
import org.jetbrains.kotlin.idea.test.KotlinTestUtils
import java.io.File
import java.nio.file.Paths

abstract class AbstractFirMultiModuleLazyResolveTest : AbstractMultiModuleTest() {
    override fun getTestDataDirectory(): File =
        KotlinRoot.DIR.resolve("fir-low-level-api").resolve("testdata").resolve("multiModuleLazyResolve")

    fun doTest(path: String) {
        val testStructure = MultiModuleTestProjectStructure.fromTestProjectStructure(TestProjectStructureReader.read(Paths.get(path)))
        val modulesByNames = testStructure.modules.associate { moduleData ->
            moduleData.name to module(moduleData.name)
        }
        testStructure.modules.forEach { moduleData ->
            val module = modulesByNames.getValue(moduleData.name)
            moduleData.dependsOnModules.forEach { dependencyName ->
                module.addDependency(modulesByNames.getValue(dependencyName))
            }
        }
        val moduleToResolve = modulesByNames.getValue(testStructure.fileToResolve.moduleName)
        val fileToAnalysePath = moduleToResolve.sourceRoots.first().url + "/" + testStructure.fileToResolve.relativeFilePath

        val virtualFileToAnalyse = VirtualFileManager.getInstance().findFileByUrl(fileToAnalysePath)
            ?: error("File ${testStructure.fileToResolve.filePath} not found")
        val ktFileToAnalyse = PsiManager.getInstance(project).findFile(virtualFileToAnalyse) as KtFile
        val resolveState = ktFileToAnalyse.getResolveState()

        val fails = testStructure.fails

        try {
            val fir = executeOnPooledThreadInReadAction { ktFileToAnalyse.getOrBuildFirOfType<FirFile>(resolveState) }
                ?: throw AssertionError("Can't build FirFile from ${ktFileToAnalyse.virtualFilePath}")
            checkFirFile(fir, path)
        } catch (e: Throwable) {
            if (!fails) throw e
            return
        }
        if (fails) {
            throw AssertionError("Looks like test is passing, please remove `\"fails\": true` from structure.json")
        }
    }

    protected open fun checkFirFile(firFile: FirFile, path: String) {
        KotlinTestUtils.assertEqualsToFile(File("$path/expected.txt"), firFile.render())
    }
}

private data class FileToResolve(val moduleName: String, val relativeFilePath: String) {
    val filePath get() = "$moduleName/$relativeFilePath"

    companion object {
        fun parse(json: JsonElement): FileToResolve {
            require(json is JsonObject)
            return FileToResolve(
                moduleName = json.getString("module"),
                relativeFilePath = json.getString("file")
            )
        }
    }
}

private data class MultiModuleTestProjectStructure(
    val modules: List<TestProjectModule>,
    val fileToResolve: FileToResolve,
    val fails: Boolean
) {
    companion object {
        fun fromTestProjectStructure(testProjectStructure: TestProjectStructure): MultiModuleTestProjectStructure {
            val json = testProjectStructure.json

            val fails = if (json.has(FAILS_FIELD)) json.get(FAILS_FIELD).asBoolean else false
            val fileToResolve = FileToResolve.parse(json.getAsJsonObject("fileToResolve"))

            return MultiModuleTestProjectStructure(
                testProjectStructure.modules,
                fileToResolve,
                fails
            )
        }

        private const val FAILS_FIELD = "fails"
    }
}
