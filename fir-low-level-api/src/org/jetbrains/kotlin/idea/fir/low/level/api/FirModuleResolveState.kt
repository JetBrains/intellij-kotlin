/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.low.level.api

import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirDeclaration
import org.jetbrains.kotlin.fir.declarations.FirFile
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase
import org.jetbrains.kotlin.idea.caches.project.IdeaModuleInfo
import org.jetbrains.kotlin.idea.caches.project.getModuleInfo
import org.jetbrains.kotlin.idea.fir.low.level.api.diagnostics.DiagnosticsCollector
import org.jetbrains.kotlin.idea.fir.low.level.api.element.builder.FirElementBuilder
import org.jetbrains.kotlin.idea.fir.low.level.api.element.builder.FirTowerDataContextCollector
import org.jetbrains.kotlin.idea.fir.low.level.api.element.builder.PsiToFirCache
import org.jetbrains.kotlin.idea.fir.low.level.api.file.builder.FirFileBuilder
import org.jetbrains.kotlin.idea.fir.low.level.api.file.builder.ModuleFileCache
import org.jetbrains.kotlin.idea.fir.low.level.api.lazy.resolve.FirLazyDeclarationResolver
import org.jetbrains.kotlin.idea.fir.low.level.api.providers.FirIdeProvider
import org.jetbrains.kotlin.idea.fir.low.level.api.sessions.*
import org.jetbrains.kotlin.idea.fir.low.level.api.sessions.FirIdeCurrentModuleSourcesSession
import org.jetbrains.kotlin.idea.fir.low.level.api.sessions.FirIdeDependentModulesSourcesSession
import org.jetbrains.kotlin.idea.fir.low.level.api.sessions.FirIdeLibrariesSession
import org.jetbrains.kotlin.idea.fir.low.level.api.sessions.FirIdeSessionProvider
import org.jetbrains.kotlin.idea.fir.low.level.api.sessions.FirIdeSourcesSession
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile

abstract class FirModuleResolveState {
    abstract val moduleInfo: IdeaModuleInfo
    abstract val currentModuleSourcesSession: FirSession
    abstract val dependentModulesSourcesSession: FirSession
    abstract val librariesSession: FirSession

    abstract fun getSessionFor(moduleInfo: IdeaModuleInfo): FirSession

    abstract fun getOrBuildFirFor(element: KtElement, toPhase: FirResolvePhase): FirElement

    abstract fun getDiagnostics(element: KtElement): List<Diagnostic>

    // todo temporary, used only in completion
    abstract fun recordPsiToFirMappingsForCompletionFrom(fir: FirDeclaration, firFile: FirFile, ktFile: KtFile)

    // todo temporary, used only in completion
    abstract fun getCachedMappingForCompletion(element: KtElement): FirElement?

    abstract fun <D : FirDeclaration> resolvedFirToPhase(declaration: D, toPhase: FirResolvePhase): D

    // todo temporary, used only in completion
    internal abstract fun lazyResolveDeclarationForCompletion(
        firFunction: FirDeclaration,
        containerFirFile: FirFile,
        firIdeProvider: FirIdeProvider,
        toPhase: FirResolvePhase,
        towerDataContextCollector: FirTowerDataContextCollector
    )

}


internal class FirModuleResolveStateImpl(
    override val moduleInfo: IdeaModuleInfo,
    override val currentModuleSourcesSession: FirIdeCurrentModuleSourcesSession,
    override val dependentModulesSourcesSession: FirIdeDependentModulesSourcesSession,
    override val librariesSession: FirIdeLibrariesSession,
    private val sessionProvider: FirIdeSessionProvider,
    val firFileBuilder: FirFileBuilder,
    val firLazyDeclarationResolver: FirLazyDeclarationResolver,
) : FirModuleResolveState() {
    val psiToFirCache = PsiToFirCache(currentModuleSourcesSession.cache)
    val elementBuilder = FirElementBuilder(firFileBuilder, firLazyDeclarationResolver)
    private val diagnosticsCollector =
        DiagnosticsCollector(firFileBuilder, elementBuilder, psiToFirCache, currentModuleSourcesSession.cache)

    override fun getSessionFor(moduleInfo: IdeaModuleInfo): FirSession =
        sessionProvider.getSession(moduleInfo)

    override fun getOrBuildFirFor(element: KtElement, toPhase: FirResolvePhase): FirElement =
        elementBuilder.getOrBuildFirFor(element, currentModuleSourcesSession.cache, psiToFirCache, toPhase)

    override fun getDiagnostics(element: KtElement): List<Diagnostic> =
        diagnosticsCollector.getDiagnosticsFor(element)

    override fun recordPsiToFirMappingsForCompletionFrom(fir: FirDeclaration, firFile: FirFile, ktFile: KtFile) {
        psiToFirCache.recordElementsForCompletionFrom(fir, firFile, ktFile)
    }

    override fun <D : FirDeclaration> resolvedFirToPhase(declaration: D, toPhase: FirResolvePhase): D {
        val fileCache = when (val session = declaration.session) {
            is FirIdeSourcesSession -> session.cache
            else -> return declaration
        }
        firLazyDeclarationResolver.lazyResolveDeclaration(declaration, fileCache, toPhase, checkPCE = true)
        return declaration
    }

    override fun getCachedMappingForCompletion(element: KtElement): FirElement? =
        psiToFirCache.getCachedMapping(element)

    override fun lazyResolveDeclarationForCompletion(
        firFunction: FirDeclaration,
        containerFirFile: FirFile,
        firIdeProvider: FirIdeProvider,
        toPhase: FirResolvePhase,
        towerDataContextCollector: FirTowerDataContextCollector
    ) {
        firLazyDeclarationResolver.runLazyResolveWithoutLock(
            firFunction,
            currentModuleSourcesSession.cache,
            containerFirFile,
            firIdeProvider,
            toPhase,
            towerDataContextCollector,
            checkPCE = false
        )
    }
}

internal fun KtElement.firResolveState(): FirModuleResolveState =
    FirIdeResolveStateService.getInstance(project).getResolveState(getModuleInfo())

