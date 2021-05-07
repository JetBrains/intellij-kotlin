/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle

@RequiresOptIn(level = RequiresOptIn.Level.ERROR, message = "APIs can change with every release")
annotation class ExperimentalKotlinMPPGradleModelExtensionsApi

@ExperimentalKotlinMPPGradleModelExtensionsApi
fun KotlinMPPGradleModel.getCompilations(sourceSet: KotlinSourceSet): Set<KotlinCompilation> {
    return targets.flatMap { target -> target.compilations }
        .filter { compilation -> compilationDependsOnSourceSet(compilation, sourceSet) }
        .toSet()
}

@ExperimentalKotlinMPPGradleModelExtensionsApi
fun KotlinMPPGradleModel.compilationDependsOnSourceSet(
    compilation: KotlinCompilation, sourceSet: KotlinSourceSet
): Boolean {
    return compilation.declaredSourceSets.any { sourceSetInCompilation ->
        sourceSetInCompilation == sourceSet || sourceSetInCompilation.isDependsOn(this, sourceSet)
    }
}

@ExperimentalKotlinMPPGradleModelExtensionsApi
fun KotlinMPPGradleModel.resolveDeclaredDependsOnSourceSets(sourceSet: KotlinSourceSet): Set<KotlinSourceSet> {
    return resolveAllDependsOnSourceSets(sourceSet).mapNotNull { name -> sourceSetsByName[name] }.toSet()
}

@ExperimentalKotlinMPPGradleModelExtensionsApi
fun KotlinMPPGradleModel.resolveAllDependsOnSourceSets(sourceSet: KotlinSourceSet): Set<KotlinSourceSet> {
    return mutableSetOf<KotlinSourceSet>().apply {
        val declaredDependencySourceSets = resolveDeclaredDependsOnSourceSets(sourceSet)
        addAll(declaredDependencySourceSets)
        for (declaredDependencySourceSet in declaredDependencySourceSets) {
            addAll(resolveAllDependsOnSourceSets(declaredDependencySourceSet))
        }
    }
}

@ExperimentalKotlinMPPGradleModelExtensionsApi
fun KotlinMPPGradleModel.isDependsOn(from: KotlinSourceSet, to: KotlinSourceSet): Boolean {
    return to in resolveAllDependsOnSourceSets(from)
}

@ExperimentalKotlinMPPGradleModelExtensionsApi
fun KotlinSourceSet.isDependsOn(model: KotlinMPPGradleModel, sourceSet: KotlinSourceSet): Boolean {
    return model.isDependsOn(from = this, to = sourceSet)
}
