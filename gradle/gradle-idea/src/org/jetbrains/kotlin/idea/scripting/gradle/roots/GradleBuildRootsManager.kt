/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.scripting.gradle.roots

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.externalSystem.util.ExternalSystemApiUtil
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.progress.util.BackgroundTaskUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import com.intellij.ui.EditorNotifications
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.kotlin.idea.core.script.ScriptConfigurationManager
import org.jetbrains.kotlin.idea.core.script.configuration.CompositeScriptConfigurationManager
import org.jetbrains.kotlin.idea.core.script.configuration.ScriptingSupport
import org.jetbrains.kotlin.idea.core.script.configuration.ScriptingSupport.Companion.EPN
import org.jetbrains.kotlin.idea.core.script.ucache.ScriptClassRootsBuilder
import org.jetbrains.kotlin.idea.core.util.EDT
import org.jetbrains.kotlin.idea.scripting.gradle.*
import org.jetbrains.kotlin.idea.scripting.gradle.importing.KotlinDslGradleBuildSync
import org.jetbrains.kotlin.idea.util.application.runReadAction
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstance
import org.jetbrains.plugins.gradle.config.GradleSettingsListenerAdapter
import org.jetbrains.plugins.gradle.settings.*
import org.jetbrains.plugins.gradle.util.GradleConstants
import java.io.File
import java.nio.file.Paths
import java.util.concurrent.atomic.AtomicBoolean

/**
 * [GradleBuildRoot] is a linked gradle build (don't confuse with gradle project and included build).
 * Each [GradleBuildRoot] may have it's own Gradle version, Java home and other settings.
 *
 * Typically, IntelliJ project have no more than one [GradleBuildRoot].
 *
 * This manager allows to find related Gradle build by the Gradle Kotlin script file path.
 * Each imported build have info about all of it's Kotlin Build Scripts.
 * It is populated by calling [update], stored in FS and will be loaded from FS on next project opening
 *
 * [CompositeScriptConfigurationManager] may ask about known scripts by calling [collectConfigurations].
 *
 * It also used to show related notification and floating actions depending on root kind, state and script state itself.
 *
 * Roots may be:
 * - [GradleBuildRoot] - Linked project, that may be itself:
 *   - [Legacy] - Gradle build with old Gradle version (<6.0)
 *   - [New] - not yet imported
 *   - [Imported] - imported
 */
class GradleBuildRootsManager(val project: Project) : GradleBuildRootsLocator(), ScriptingSupport {
    private val manager: CompositeScriptConfigurationManager
        get() = ScriptConfigurationManager.getInstance(project) as CompositeScriptConfigurationManager

    private val updater
        get() = manager.updater

    ////////////
    /// ScriptingSupport.Provider implementation:

    override fun isApplicable(file: VirtualFile): Boolean {
        val scriptUnderRoot = findScriptBuildRoot(file) ?: return false
        if (scriptUnderRoot.root is Legacy) return false
        if (roots.isStandaloneScript(file.path)) return false
        return true
    }

    override fun isConfigurationLoadingInProgress(file: KtFile): Boolean {
        return when (val root = findScriptBuildRoot(file.originalFile.virtualFile)?.root) {
            is GradleBuildRoot -> root.importing
            else -> false
        }
    }

    @Suppress("MemberVisibilityCanBePrivate") // used in GradleImportHelper.kt.193
    fun checkUpToDate(file: VirtualFile) {
        if (isConfigurationOutOfDate(file)) {
            showNotificationForProjectImport(project)
        } else {
            hideNotificationForProjectImport(project)
        }
    }

    @Suppress("MemberVisibilityCanBePrivate") // used in GradleImportHelper.kt.201
    fun isConfigurationOutOfDate(file: VirtualFile): Boolean {
        val script = getScriptInfo(file) ?: return false
        return !script.model.inputs.isUpToDate(project, file)
    }

    override fun collectConfigurations(builder: ScriptClassRootsBuilder) {
        roots.list.forEach { root ->
            if (root is Imported) {
                root.collectConfigurations(builder)
            }
        }
    }

    //////////////////

    override fun getScriptInfo(localPath: String): GradleScriptInfo? =
        manager.getLightScriptInfo(localPath) as? GradleScriptInfo

    fun fileChanged(filePath: String, ts: Long = System.currentTimeMillis()) {
        findAffectedFileRoot(filePath)?.fileChanged(filePath, ts)
        scheduleLastModifiedFilesSave()
    }

    fun markImportingInProgress(workingDir: String, inProgress: Boolean = true) {
        actualizeBuildRoot(workingDir)?.importing = inProgress
        updateNotifications { it.startsWith(workingDir) }
        hideNotificationForProjectImport(project)
    }

    fun update(build: KotlinDslGradleBuildSync) {
        // fast path for linked gradle builds without .gradle.kts support
        if (build.models.isEmpty()) {
            val root = getBuildRootByWorkingDir(build.workingDir) ?: return
            if (root is Imported && root.data.models.isEmpty()) return
        }

        val root = actualizeBuildRoot(build.workingDir) ?: return
        root.importing = false

        if (root is Legacy) return

        val templateClasspath = GradleScriptDefinitionsContributor.getDefinitionsTemplateClasspath(project)
        val newData = GradleBuildRootData(build.projectRoots, templateClasspath, build.models)
        val mergedData = if (build.failed && root is Imported) merge(root.data, newData) else newData

        val newSupport = tryCreateImportedRoot(build.workingDir) { mergedData } ?: return
        GradleBuildRootDataSerializer.write(newSupport.dir ?: return, mergedData)

        add(newSupport)

        hideNotificationForProjectImport(project)
    }

    private fun merge(old: GradleBuildRootData, new: GradleBuildRootData): GradleBuildRootData {
        val roots = old.projectRoots.toMutableSet()
        roots.addAll(new.projectRoots)

        val models = old.models.associateByTo(mutableMapOf()) { it.file }
        new.models.associateByTo(models) { it.file }

        return GradleBuildRootData(roots, new.templateClasspath, models.values)
    }

    private val lastModifiedFilesSaveScheduled = AtomicBoolean()

    fun scheduleLastModifiedFilesSave() {
        if (lastModifiedFilesSaveScheduled.compareAndSet(false, true)) {
            BackgroundTaskUtil.executeOnPooledThread(project) {
                if (lastModifiedFilesSaveScheduled.compareAndSet(true, false)) {
                    roots.list.forEach {
                        it.saveLastModifiedFiles()
                    }
                }
            }
        }
    }

    fun updateStandaloneScripts(update: StandaloneScriptsUpdater.() -> Unit) {
        val changes = StandaloneScriptsUpdater.collectChanges(delegate = roots, update)

        updateNotifications { it in changes.new || it in changes.removed }

        runReadAction {
            changes.new.forEach {
                val virtualFile = LocalFileSystem.getInstance().findFileByPath(it)
                if (virtualFile != null) {
                    val ktFile = PsiManager.getInstance(project).findFile(virtualFile) as? KtFile
                    if (ktFile != null) {
                        ScriptConfigurationManager.getInstance(project).getConfiguration(ktFile)
                    }
                }
            }
        }
    }

    init {
        getGradleProjectSettings(project).forEach {
            // don't call this.add, as we are inside scripting manager initialization
            roots.add(loadLinkedRoot(it))
        }

        // subscribe to linked gradle project modification
        val listener = object : GradleSettingsListenerAdapter() {
            override fun onProjectsLinked(settings: MutableCollection<GradleProjectSettings>) {
                settings.forEach {
                    add(loadLinkedRoot(it))
                }
            }

            override fun onProjectsUnlinked(linkedProjectPaths: MutableSet<String>) {
                linkedProjectPaths.forEach {
                    remove(it)
                }
            }

            override fun onGradleHomeChange(oldPath: String?, newPath: String?, linkedProjectPath: String) {
                reloadBuildRoot(linkedProjectPath)
            }

            override fun onGradleDistributionTypeChange(currentValue: DistributionType?, linkedProjectPath: String) {
                reloadBuildRoot(linkedProjectPath)
            }
        }

        project.messageBus.connect(project).subscribe(GradleSettingsListener.TOPIC, listener)
    }

    private fun getGradleProjectSettings(workingDir: String): GradleProjectSettings? {
        return (ExternalSystemApiUtil.getSettings(project, GradleConstants.SYSTEM_ID) as GradleSettings)
            .getLinkedProjectSettings(workingDir)
    }

    /**
     * Check that root under [workingDir] in sync with it's [GradleProjectSettings].
     * Actually this should be true, but we may miss some change events.
     * For that cases we are rechecking this on each Gradle Project sync (importing/reimporting)
     */
    private fun actualizeBuildRoot(workingDir: String): GradleBuildRoot? {
        val actualSettings = getGradleProjectSettings(workingDir)
        val buildRoot = getBuildRootByWorkingDir(workingDir)

        return when {
            buildRoot != null -> when {
                !buildRoot.checkActual(actualSettings) -> reloadBuildRoot(workingDir)
                else -> buildRoot
            }
            actualSettings != null -> loadLinkedRoot(actualSettings)
            else -> null
        }
    }

    private fun GradleBuildRoot.checkActual(actualSettings: GradleProjectSettings?): Boolean {
        if (actualSettings == null) return false

        val knownAsSupported = this !is Legacy
        val shouldBeSupported = kotlinDslScriptsModelImportSupported(actualSettings.resolveGradleVersion().version)
        return knownAsSupported == shouldBeSupported
    }

    private fun reloadBuildRoot(rootPath: String): GradleBuildRoot? {
        val settings = getGradleProjectSettings(rootPath)
        if (settings == null) {
            remove(rootPath)
            return null
        } else {
            val newRoot = loadLinkedRoot(settings)
            add(newRoot)
            return newRoot
        }
    }

    private fun loadLinkedRoot(settings: GradleProjectSettings) =
        tryLoadFromFsCache(settings) ?: createOtherLinkedRoot(settings)

    private fun tryLoadFromFsCache(settings: GradleProjectSettings) =
        tryCreateImportedRoot(settings.externalProjectPath) {
            GradleBuildRootDataSerializer.read(it)?.let { data ->
                addFromSettings(data, settings)
            }
        }

    private fun addFromSettings(
        data: GradleBuildRootData,
        settings: GradleProjectSettings
    ) = data.copy(projectRoots = data.projectRoots.toSet() + settings.modules)

    private fun createOtherLinkedRoot(settings: GradleProjectSettings): GradleBuildRoot {
        val supported = kotlinDslScriptsModelImportSupported(settings.resolveGradleVersion().version)
        return when {
            supported -> New(settings)
            else -> Legacy(settings)
        }
    }

    private fun tryCreateImportedRoot(
        externalProjectPath: String,
        dataProvider: (buildRoot: VirtualFile) -> GradleBuildRootData?
    ): Imported? {
        val buildRoot = VfsUtil.findFile(Paths.get(externalProjectPath), true) ?: return null
        val data = dataProvider(buildRoot) ?: return null
        val javaHome = ExternalSystemApiUtil
            .getExecutionSettings<GradleExecutionSettings>(project, externalProjectPath, GradleConstants.SYSTEM_ID)
            .javaHome?.let { File(it) }

        return Imported(externalProjectPath, javaHome, data)
    }

    private fun add(newRoot: GradleBuildRoot) {
        val old = roots.add(newRoot)
        if (old is Imported && newRoot !is Imported) removeData(old.pathPrefix)
        if (old is Imported || newRoot is Imported) {
            updater.invalidateAndCommit()
        }

        updateNotifications { it.startsWith(newRoot.pathPrefix) }
    }

    private fun remove(rootPath: String) {
        val removed = roots.remove(rootPath)
        if (removed is Imported) {
            removeData(rootPath)
            updater.invalidateAndCommit()
        }

        updateNotifications { it.startsWith(rootPath) }
    }

    private fun removeData(rootPath: String) {
        val buildRoot = LocalFileSystem.getInstance().findFileByPath(rootPath)
        if (buildRoot != null) {
            GradleBuildRootDataSerializer.remove(buildRoot)
            LastModifiedFiles.remove(buildRoot)
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    private fun updateNotifications(shouldUpdatePath: (String) -> Boolean) {
        if (!project.isOpen) return

        val openedScripts = FileEditorManager.getInstance(project).openFiles.filter {
            shouldUpdatePath(it.path) && maybeAffectedGradleProjectFile(it.path)
        }

        if (openedScripts.isEmpty()) return

        openedScripts.forEach {
            checkUpToDate(it)
        }

        GlobalScope.launch(EDT(project)) {
            if (project.isDisposed) return@launch

            openedScripts.forEach {
                val ktFile = PsiManager.getInstance(project).findFile(it)
                if (ktFile != null) DaemonCodeAnalyzer.getInstance(project).restart(ktFile)
                EditorNotifications.getInstance(project).updateAllNotifications()
            }
        }
    }

    companion object {
        fun getInstance(project: Project): GradleBuildRootsManager =
            EPN.getPoint(project).extensionList.firstIsInstance()
    }
}
