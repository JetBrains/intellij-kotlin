/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.facet

import com.intellij.facet.FacetManager
import com.intellij.openapi.externalSystem.service.project.IdeModifiableModelsProvider
import com.intellij.openapi.module.Module
import com.intellij.openapi.projectRoots.JavaSdk
import com.intellij.openapi.projectRoots.JavaSdkVersion
import com.intellij.openapi.roots.ExternalProjectSystemRegistry
import com.intellij.openapi.roots.ModuleRootManager
import com.intellij.openapi.roots.ModuleRootModel
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.util.Key
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.util.text.StringUtil
import org.jetbrains.kotlin.cli.common.arguments.*
import org.jetbrains.kotlin.compilerRunner.ArgumentUtils
import org.jetbrains.kotlin.config.*
import org.jetbrains.kotlin.idea.compiler.configuration.Kotlin2JvmCompilerArgumentsHolder
import org.jetbrains.kotlin.idea.compiler.configuration.KotlinCommonCompilerArgumentsHolder
import org.jetbrains.kotlin.idea.compiler.configuration.KotlinCompilerSettings
import org.jetbrains.kotlin.idea.configuration.externalCompilerVersion
import org.jetbrains.kotlin.idea.core.isAndroidModule
import org.jetbrains.kotlin.idea.framework.KotlinSdkType
import org.jetbrains.kotlin.idea.platform.tooling
import org.jetbrains.kotlin.idea.defaultSubstitutors
import org.jetbrains.kotlin.idea.util.application.runWriteAction
import org.jetbrains.kotlin.idea.util.getProjectJdkTableSafe
import org.jetbrains.kotlin.platform.*
import org.jetbrains.kotlin.platform.impl.JvmIdePlatformKind
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms
import org.jetbrains.kotlin.psi.NotNullableUserDataProperty
import kotlin.reflect.KProperty1

var Module.hasExternalSdkConfiguration: Boolean
        by NotNullableUserDataProperty(Key.create<Boolean>("HAS_EXTERNAL_SDK_CONFIGURATION"), false)

private fun getDefaultTargetPlatform(module: Module, rootModel: ModuleRootModel?): TargetPlatform {
    val platformKind = IdePlatformKind.ALL_KINDS.firstOrNull {
        getRuntimeLibraryVersions(module, rootModel, it).isNotEmpty()
    } ?: DefaultIdeTargetPlatformKindProvider.defaultPlatform.idePlatformKind
    if (platformKind == JvmIdePlatformKind) {
        var jvmTarget = Kotlin2JvmCompilerArgumentsHolder.getInstance(module.project).settings.jvmTarget?.let { JvmTarget.fromString(it) }
        if (jvmTarget == null) {
            val sdk = ((rootModel ?: ModuleRootManager.getInstance(module))).sdk
            val sdkVersion = (sdk?.sdkType as? JavaSdk)?.getVersion(sdk)
            if (sdkVersion == null || sdkVersion >= JavaSdkVersion.JDK_1_8) {
                jvmTarget = JvmTarget.JVM_1_8
            }
        }
        return if (jvmTarget != null) JvmPlatforms.jvmPlatformByTargetVersion(jvmTarget) else JvmPlatforms.defaultJvmPlatform
    }
    return platformKind.defaultPlatform
}

fun KotlinFacetSettings.initializeIfNeeded(
    module: Module,
    rootModel: ModuleRootModel?,
    platform: TargetPlatform? = null, // if null, detect by module dependencies
    compilerVersion: String? = null
) {
    val project = module.project

    val shouldInferLanguageLevel = languageLevel == null
    val shouldInferAPILevel = apiLevel == null

    if (compilerSettings == null) {
        compilerSettings = KotlinCompilerSettings.getInstance(project).settings
    }

    val commonArguments = KotlinCommonCompilerArgumentsHolder.getInstance(module.project).settings

    if (compilerArguments == null) {
        val targetPlatform = platform ?: getDefaultTargetPlatform(module, rootModel)
        compilerArguments = targetPlatform.createArguments {
            targetPlatform.idePlatformKind.tooling.compilerArgumentsForProject(module.project)?.let { mergeBeans(it, this) }
            mergeBeans(commonArguments, this)
        }
        this.targetPlatform = targetPlatform
    }

    if (shouldInferLanguageLevel) {
        languageLevel = (if (useProjectSettings) LanguageVersion.fromVersionString(commonArguments.languageVersion) else null)
            ?: getDefaultLanguageLevel(module, compilerVersion, coerceRuntimeLibraryVersionToReleased = compilerVersion == null)
    }

    if (shouldInferAPILevel) {
        apiLevel = if (useProjectSettings) {
            LanguageVersion.fromVersionString(commonArguments.apiVersion) ?: languageLevel
        } else {
            languageLevel?.coerceAtMost(
                getLibraryLanguageLevel(
                    module,
                    rootModel,
                    this.targetPlatform?.idePlatformKind,
                    coerceRuntimeLibraryVersionToReleased = compilerVersion == null
                )
            )
        }
    }
}

val mavenLibraryIdToPlatform: Map<String, IdePlatformKind<*>> by lazy {
    IdePlatformKind.ALL_KINDS
        .flatMap { platform -> platform.tooling.mavenLibraryIds.map { it to platform } }
        .sortedByDescending { it.first.length }
        .toMap()
}

fun Module.getOrCreateFacet(
    modelsProvider: IdeModifiableModelsProvider,
    useProjectSettings: Boolean,
    externalSystemId: String? = null,
    commitModel: Boolean = false
): KotlinFacet {
    return getOrCreateConfiguredFacet(modelsProvider, useProjectSettings, externalSystemId, commitModel)
}

fun Module.getOrCreateConfiguredFacet(
    modelsProvider: IdeModifiableModelsProvider,
    useProjectSettings: Boolean,
    externalSystemId: String? = null,
    commitModel: Boolean = false,
    configure: KotlinFacet.() -> Unit = {}
): KotlinFacet {
    val facetModel = modelsProvider.getModifiableFacetModel(this)

    val facet = facetModel.findFacet(KotlinFacetType.TYPE_ID, KotlinFacetType.INSTANCE.defaultFacetName)
        ?: with(KotlinFacetType.INSTANCE) { createFacet(this@getOrCreateConfiguredFacet, defaultFacetName, createDefaultConfiguration(), null) }
            .apply {
                val externalSource = externalSystemId?.let { ExternalProjectSystemRegistry.getInstance().getSourceById(it) }
                facetModel.addFacet(this, externalSource)
            }
    facet.configuration.settings.useProjectSettings = useProjectSettings
    facet.configure()
    if (commitModel) {
        runWriteAction {
            facetModel.commit()
        }
    }
    return facet
}

fun Module.hasKotlinFacet() = FacetManager.getInstance(this).getFacetByType(KotlinFacetType.TYPE_ID) != null

fun Module.removeKotlinFacet(
    modelsProvider: IdeModifiableModelsProvider,
    commitModel: Boolean = false
) {
    val facetModel = modelsProvider.getModifiableFacetModel(this)
    val facet = facetModel.findFacet(KotlinFacetType.TYPE_ID, KotlinFacetType.INSTANCE.defaultFacetName) ?: return
    facetModel.removeFacet(facet)
    if (commitModel) {
        runWriteAction {
            facetModel.commit()
        }
    }
}

//method used for non-mpp modules
fun KotlinFacet.configureFacet(
    compilerVersion: String?,
    platform: TargetPlatform?,
    modelsProvider: IdeModifiableModelsProvider
) {
    configureFacet(compilerVersion, platform, modelsProvider, false, emptyList(), emptyList())
}

fun KotlinFacet.configureFacet(
    compilerVersion: String?,
    platform: TargetPlatform?, // if null, detect by module dependencies
    modelsProvider: IdeModifiableModelsProvider,
    hmppEnabled: Boolean,
    pureKotlinSourceFolders: List<String>,
    dependsOnList: List<String>
) {
    val module = module
    with(configuration.settings) {
        compilerArguments = null
        targetPlatform = null
        compilerSettings = null
        isHmppEnabled = hmppEnabled
        dependsOnModuleNames = dependsOnList
        initializeIfNeeded(
            module,
            modelsProvider.getModifiableRootModel(module),
            platform,
            compilerVersion
        )
        val apiLevel = apiLevel
        val languageLevel = languageLevel
        if (languageLevel != null && apiLevel != null && apiLevel > languageLevel) {
            this.apiLevel = languageLevel
        }
        this.pureKotlinSourceFolders = pureKotlinSourceFolders
    }

    module.externalCompilerVersion = compilerVersion
}

private fun Module.externalSystemRunTasks(): List<ExternalSystemRunTask> {
    val settingsProvider = KotlinFacetSettingsProvider.getInstance(project) ?: return emptyList()
    return settingsProvider.getInitializedSettings(this).externalSystemRunTasks
}

fun Module.externalSystemTestRunTasks() = externalSystemRunTasks().filterIsInstance<ExternalSystemTestRunTask>()
fun Module.externalSystemNativeMainRunTasks() = externalSystemRunTasks().filterIsInstance<ExternalSystemNativeMainRunTask>()

fun KotlinFacet.noVersionAutoAdvance() {
    configuration.settings.compilerArguments?.let {
        it.autoAdvanceLanguageVersion = false
        it.autoAdvanceApiVersion = false
    }
}

// "Primary" fields are written to argument beans directly and thus not presented in the "additional arguments" string
// Update these lists when facet/project settings UI changes
val commonUIExposedFields = listOf(
    CommonCompilerArguments::languageVersion.name,
    CommonCompilerArguments::apiVersion.name,
    CommonCompilerArguments::suppressWarnings.name
)
private val commonUIHiddenFields = listOf(
    CommonCompilerArguments::pluginClasspaths.name,
    CommonCompilerArguments::pluginOptions.name,
    CommonCompilerArguments::multiPlatform.name
)
private val commonPrimaryFields = commonUIExposedFields + commonUIHiddenFields

private val jvmSpecificUIExposedFields = listOf(
    K2JVMCompilerArguments::jvmTarget.name,
    K2JVMCompilerArguments::destination.name,
    K2JVMCompilerArguments::classpath.name
)
private val jvmSpecificUIHiddenFields = listOf(
    K2JVMCompilerArguments::friendPaths.name
)
val jvmUIExposedFields = commonUIExposedFields + jvmSpecificUIExposedFields
private val jvmPrimaryFields = commonPrimaryFields + jvmSpecificUIExposedFields + jvmSpecificUIHiddenFields

private val jsSpecificUIExposedFields = listOf(
    K2JSCompilerArguments::sourceMap.name,
    K2JSCompilerArguments::sourceMapPrefix.name,
    K2JSCompilerArguments::sourceMapEmbedSources.name,
    K2JSCompilerArguments::outputPrefix.name,
    K2JSCompilerArguments::outputPostfix.name,
    K2JSCompilerArguments::moduleKind.name
)
val jsUIExposedFields = commonUIExposedFields + jsSpecificUIExposedFields
private val jsPrimaryFields = commonPrimaryFields + jsSpecificUIExposedFields

private val metadataSpecificUIExposedFields = listOf(
    K2MetadataCompilerArguments::destination.name,
    K2MetadataCompilerArguments::classpath.name
)
val metadataUIExposedFields = commonUIExposedFields + metadataSpecificUIExposedFields
private val metadataPrimaryFields = commonPrimaryFields + metadataSpecificUIExposedFields

private val CommonCompilerArguments.primaryFields: List<String>
    get() = when (this) {
        is K2JVMCompilerArguments -> jvmPrimaryFields
        is K2JSCompilerArguments -> jsPrimaryFields
        is K2MetadataCompilerArguments -> metadataPrimaryFields
        else -> commonPrimaryFields
    }

private val CommonCompilerArguments.ignoredFields: List<String>
    get() = when (this) {
        is K2JVMCompilerArguments -> listOf(K2JVMCompilerArguments::noJdk.name, K2JVMCompilerArguments::jdkHome.name)
        else -> emptyList()
    }

private fun Module.configureSdkIfPossible(compilerArguments: CommonCompilerArguments, modelsProvider: IdeModifiableModelsProvider) {
    // SDK for Android module is already configured by Android plugin at this point
    if (isAndroidModule(modelsProvider) || hasNonOverriddenExternalSdkConfiguration(compilerArguments)) return

    val projectSdk = ProjectRootManager.getInstance(project).projectSdk
    KotlinSdkType.setUpIfNeeded()
    val allSdks = getProjectJdkTableSafe().allJdks
    val sdk = if (compilerArguments is K2JVMCompilerArguments) {
        val jdkHome = compilerArguments.jdkHome
        when {
            jdkHome != null -> allSdks.firstOrNull { it.sdkType is JavaSdk && FileUtil.comparePaths(it.homePath, jdkHome) == 0 }
            projectSdk != null && projectSdk.sdkType is JavaSdk -> projectSdk
            else -> allSdks.firstOrNull { it.sdkType is JavaSdk }
        }
    } else {
        allSdks.firstOrNull { it.sdkType is KotlinSdkType }
            ?: modelsProvider
                .modifiableModuleModel
                .modules
                .asSequence()
                .mapNotNull { modelsProvider.getModifiableRootModel(it).sdk }
                .firstOrNull { it.sdkType is KotlinSdkType }
    }

    val rootModel = modelsProvider.getModifiableRootModel(this)
    if (sdk == null || sdk == projectSdk) {
        rootModel.inheritSdk()
    } else {
        rootModel.sdk = sdk
    }
}

private fun Module.hasNonOverriddenExternalSdkConfiguration(compilerArguments: CommonCompilerArguments): Boolean =
    hasExternalSdkConfiguration && (compilerArguments !is K2JVMCompilerArguments || compilerArguments.jdkHome == null)

private fun substituteDefaults(args: List<String>, compilerArguments: CommonCompilerArguments): List<String> =
    args + defaultSubstitutors[compilerArguments::class]?.filter { it.isSubstitutable(args) }?.flatMap { it.oldSubstitution }.orEmpty()

fun parseCompilerArgumentsToFacet(
    arguments: List<String>,
    defaultArguments: List<String>,
    kotlinFacet: KotlinFacet,
    modelsProvider: IdeModifiableModelsProvider?
) {
    val compilerArgumentsClass = kotlinFacet.configuration.settings.compilerArguments?.javaClass ?: return
    val currentArgumentsBean = compilerArgumentsClass.newInstance()
    val defaultArgumentsBean = compilerArgumentsClass.newInstance()
    val defaultArgumentWithDefaults = substituteDefaults(defaultArguments, defaultArgumentsBean)
    val currentArgumentWithDefaults = substituteDefaults(arguments, currentArgumentsBean)
    parseCommandLineArguments(defaultArgumentWithDefaults, defaultArgumentsBean)
    parseCommandLineArguments(currentArgumentWithDefaults, currentArgumentsBean)
    applyCompilerArgumentsToFacet(currentArgumentsBean, defaultArgumentsBean, kotlinFacet, modelsProvider)
}

fun applyCompilerArgumentsToFacet(
    arguments: CommonCompilerArguments,
    defaultArguments: CommonCompilerArguments?,
    kotlinFacet: KotlinFacet,
    modelsProvider: IdeModifiableModelsProvider?
) {
    with(kotlinFacet.configuration.settings) {
        val compilerArguments = this.compilerArguments ?: return

        val defaultCompilerArguments = defaultArguments?.let { copyBean(it) } ?: compilerArguments::class.java.newInstance()
        defaultCompilerArguments.convertPathsToSystemIndependent()

        val oldPluginOptions = compilerArguments.pluginOptions

        val emptyArgs = compilerArguments::class.java.newInstance()

        // Ad-hoc work-around for android compilations: middle source sets could be actualized up to
        // Android target, meanwhile compiler arguments are of type K2Metadata
        // TODO(auskov): merge classpath once compiler arguments are removed from KotlinFacetSettings
        if (arguments.javaClass == compilerArguments.javaClass) {
            copyBeanTo(arguments, compilerArguments) { property, value -> value != property.get(emptyArgs) }
        }
        compilerArguments.pluginOptions = joinPluginOptions(oldPluginOptions, arguments.pluginOptions)

        compilerArguments.convertPathsToSystemIndependent()

        // Retain only fields exposed (and not explicitly ignored) in facet configuration editor.
        // The rest is combined into string and stored in CompilerSettings.additionalArguments

        if (modelsProvider != null)
            kotlinFacet.module.configureSdkIfPossible(compilerArguments, modelsProvider)

        val primaryFields = compilerArguments.primaryFields
        val ignoredFields = compilerArguments.ignoredFields

        fun exposeAsAdditionalArgument(property: KProperty1<CommonCompilerArguments, Any?>) =
            property.name !in primaryFields && property.get(compilerArguments) != property.get(defaultCompilerArguments)

        val additionalArgumentsString = with(compilerArguments::class.java.newInstance()) {
            copyFieldsSatisfying(compilerArguments, this) { exposeAsAdditionalArgument(it) && it.name !in ignoredFields }
            ArgumentUtils.convertArgumentsToStringListNoDefaults(this).joinToString(separator = " ") {
                if (StringUtil.containsWhitespaces(it) || it.startsWith('"')) {
                    StringUtil.wrapWithDoubleQuote(StringUtil.escapeQuotes(it))
                } else it
            }
        }
        compilerSettings?.additionalArguments =
            if (additionalArgumentsString.isNotEmpty()) additionalArgumentsString else CompilerSettings.DEFAULT_ADDITIONAL_ARGUMENTS

        with(compilerArguments::class.java.newInstance()) {
            copyFieldsSatisfying(this, compilerArguments) { exposeAsAdditionalArgument(it) || it.name in ignoredFields }
        }

        val languageLevel = languageLevel
        val apiLevel = apiLevel
        if (languageLevel != null && apiLevel != null && apiLevel > languageLevel) {
            this.apiLevel = languageLevel
        }

        updateMergedArguments()
    }
}

private fun joinPluginOptions(old: Array<String>?, new: Array<String>?): Array<String>? {
    if (old == null && new == null) {
        return old
    } else if (new == null) {
        return old
    } else if (old == null) {
        return new
    }

    return (old + new).distinct().toTypedArray()
}
