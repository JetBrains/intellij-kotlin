/*
 * Copyright 2000-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.codeInsight.gradle

import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.Notification
import com.intellij.notification.Notifications
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.externalSystem.model.DataNode
import com.intellij.openapi.externalSystem.model.ProjectKeys
import com.intellij.openapi.externalSystem.model.task.TaskData
import com.intellij.openapi.externalSystem.util.ExternalSystemApiUtil
import com.intellij.openapi.externalSystem.util.ExternalSystemUtil
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.project.guessProjectDir
import com.intellij.openapi.roots.DependencyScope
import com.intellij.openapi.roots.ExternalLibraryDescriptor
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.util.text.StringUtil
import com.intellij.testFramework.runInEdtAndWait
import com.intellij.util.containers.ContainerUtil
import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.config.LanguageFeature
import org.jetbrains.kotlin.idea.KotlinBundle
import org.jetbrains.kotlin.idea.KotlinVersionVerbose
import org.jetbrains.kotlin.idea.configuration.*
import org.jetbrains.kotlin.idea.configuration.notifications.LAST_BUNDLED_KOTLIN_COMPILER_VERSION_PROPERTY_NAME
import org.jetbrains.kotlin.idea.configuration.notifications.checkExternalKotlinCompilerVersion
import org.jetbrains.kotlin.idea.util.application.executeWriteCommand
import org.jetbrains.kotlin.idea.util.application.runReadAction
import org.jetbrains.plugins.gradle.execution.test.runner.GradleTestTasksProvider
import org.jetbrains.plugins.gradle.service.project.GradleProjectResolverUtil
import org.jetbrains.plugins.gradle.tooling.annotation.TargetVersions
import org.jetbrains.plugins.gradle.util.GradleConstants
import org.junit.Ignore
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class GradleConfiguratorTest : KotlinGradleImportingTestCase() {
    @Test
    fun testProjectWithModule() {
        val propertyKey = LAST_BUNDLED_KOTLIN_COMPILER_VERSION_PROPERTY_NAME
        val propertiesComponent = PropertiesComponent.getInstance()

        val kotlinVersionVerbose = KotlinVersionVerbose.parse(KotlinCompilerVersion.VERSION)
        val notificationText = KotlinBundle.message(
            "kotlin.external.compiler.updates.notification.content.0",
            kotlinVersionVerbose?.plainVersion.toString(),
        )

        val counter = AtomicInteger(0)
        val myDisposable = Disposer.newDisposable()
        try {
            val connection = myProject.messageBus.connect(myDisposable)
            connection.subscribe(Notifications.TOPIC, object : Notifications {
                override fun notify(notification: Notification) {
                    counter.incrementAndGet()
                    assertEquals(notificationText, notification.content)
                }
            })

            propertiesComponent.unsetValue(propertyKey)
            assertFalse(propertiesComponent.isValueSet(propertyKey))

            connection.deliverImmediately()
            assertEquals(0, counter.get())

            importProjectFromTestData()

            runInEdtAndWait {
                runWriteAction {
                    // Create not configured build.gradle for project
                    myProject.guessProjectDir()!!.createChildData(null, "build.gradle")

                    val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                    val moduleGroup = module.toModuleGroup()
                    // We have a Kotlin runtime in build.gradle but not in the classpath, so it doesn't make sense
                    // to suggest configuring it
                    assertEquals(ConfigureKotlinStatus.BROKEN, findGradleModuleConfigurator().getStatus(moduleGroup))
                    // Don't offer the JS configurator if the JVM configuration exists but is broken
                    assertEquals(ConfigureKotlinStatus.BROKEN, findJsGradleModuleConfigurator().getStatus(moduleGroup))
                }
            }

            assertEquals(
                KotlinVersionVerbose(
                    plainVersion = KotlinVersion(major = 1, minor = 3, patch = 70),
                    milestone = null,
                    buildNumber = null
                ),
                myProject.findAnyExternalKotlinCompilerVersion(),
            )

            if (kotlinVersionVerbose == null) {
                System.err.println("bundled version could not be parsed, notification part of test ignored")
                assertFalse(propertiesComponent.isValueSet(propertyKey))
                return
            }

            val isReleaseVersion = kotlinVersionVerbose.milestone == KotlinVersionVerbose.KotlinVersionMilestone.release

            val expectedCountAfter = if (isReleaseVersion) 1 else 0
            connection.deliverImmediately() // the first notification from import action
            assertEquals(expectedCountAfter, counter.get())

            checkExternalKotlinCompilerVersion(myProject)
            connection.deliverImmediately()

            checkExternalKotlinCompilerVersion(myProject)
            checkExternalKotlinCompilerVersion(myProject)
            connection.deliverImmediately()
            assertEquals(expectedCountAfter, counter.get())

            if (isReleaseVersion) {
                assertTrue(propertiesComponent.isValueSet(propertyKey))
            } else {
                assertFalse(propertiesComponent.isValueSet(propertyKey))
            }
        } finally {
            propertiesComponent.unsetValue(propertyKey)
            Disposer.dispose(myDisposable)
        }
    }

    @Test
    fun testConfigure10() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                val configurator = findGradleModuleConfigurator()
                val collector = createConfigureKotlinNotificationCollector(myProject)
                configurator.configureWithVersion(myProject, listOf(module), "1.0.6", collector)

                checkFiles(files)
            }
        }
    }

    @Test
    fun testConfigureKotlinWithPluginsBlock() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                val configurator = findGradleModuleConfigurator()
                val collector = createConfigureKotlinNotificationCollector(myProject)
                configurator.configureWithVersion(myProject, listOf(module), "1.0.6", collector)

                checkFiles(files)
            }
        }
    }

    @Test
    fun testConfigureKotlinDevVersion() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            myTestFixture.project.executeWriteCommand("") {
                val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                val configurator = findGradleModuleConfigurator()
                val collector = createConfigureKotlinNotificationCollector(myProject)
                configurator.configureWithVersion(myProject, listOf(module), "1.2.60-dev-286", collector)

                checkFiles(files)
            }
        }
    }

    @Test
    fun testConfigureGradleKtsKotlinDevVersion() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                val configurator = findGradleModuleConfigurator()
                val collector = createConfigureKotlinNotificationCollector(myProject)
                configurator.configureWithVersion(myProject, listOf(module), "1.2.60-dev-286", collector)

                checkFiles(files)
            }
        }
    }

    @Test
    @TargetVersions("4.4+")
    fun testConfigureJvmWithBuildGradle() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                val configurator = findGradleModuleConfigurator()
                val collector = createConfigureKotlinNotificationCollector(myProject)
                configurator.configureWithVersion(myProject, listOf(module), "1.2.40", collector)

                checkFiles(files)
            }
        }
    }

    @Test
    @TargetVersions("4.4+")
    fun testConfigureJvmWithBuildGradleKts() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                val configurator = findGradleModuleConfigurator()
                val collector = createConfigureKotlinNotificationCollector(myProject)
                configurator.configureWithVersion(myProject, listOf(module), "1.2.40", collector)

                checkFiles(files)
            }
        }
    }

    @Test
    @TargetVersions("4.4+")
    fun testConfigureJvmEAPWithBuildGradle() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                val configurator = findGradleModuleConfigurator()
                val collector = createConfigureKotlinNotificationCollector(myProject)
                configurator.configureWithVersion(myProject, listOf(module), "1.2.40-eap-62", collector)

                checkFiles(files)
            }
        }
    }

    @Test
    @TargetVersions("4.4+")
    fun testConfigureJvmEAPWithBuildGradleKts() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                val configurator = findGradleModuleConfigurator()
                val collector = createConfigureKotlinNotificationCollector(myProject)
                configurator.configureWithVersion(myProject, listOf(module), "1.2.40-eap-62", collector)

                checkFiles(files)
            }
        }
    }

    @Test
    @TargetVersions("4.4+")
    fun testConfigureJsWithBuildGradle() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                val configurator = findJsGradleModuleConfigurator()
                val collector = createConfigureKotlinNotificationCollector(myProject)
                configurator.configureWithVersion(myProject, listOf(module), "1.2.40", collector)

                checkFiles(files)
            }
        }
    }

    @Test
    @TargetVersions("4.4+")
    fun testConfigureJsWithBuildGradleKts() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                val configurator = findJsGradleModuleConfigurator()
                val collector = createConfigureKotlinNotificationCollector(myProject)
                configurator.configureWithVersion(myProject, listOf(module), "1.2.40", collector)

                checkFiles(files)
            }
        }
    }

    @Test
    @TargetVersions("4.4+")
    fun testConfigureJsEAPWithBuildGradle() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                val configurator = findJsGradleModuleConfigurator()
                val collector = createConfigureKotlinNotificationCollector(myProject)
                configurator.configureWithVersion(myProject, listOf(module), "1.2.40-eap-62", collector)

                checkFiles(files)
            }
        }
    }

    @Test
    @TargetVersions("4.4+")
    fun testConfigureJsEAPWithBuildGradleKts() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                val configurator = findJsGradleModuleConfigurator()
                val collector = createConfigureKotlinNotificationCollector(myProject)
                configurator.configureWithVersion(myProject, listOf(module), "1.2.40-eap-62", collector)

                checkFiles(files)
            }
        }
    }

    private fun findGradleModuleConfigurator(): KotlinGradleModuleConfigurator {
        return KotlinProjectConfigurator.EP_NAME.findExtensionOrFail(KotlinGradleModuleConfigurator::class.java)
    }

    private fun findJsGradleModuleConfigurator(): KotlinJsGradleModuleConfigurator {
        return KotlinProjectConfigurator.EP_NAME.findExtensionOrFail(KotlinJsGradleModuleConfigurator::class.java)
    }

    @Test
    fun testConfigureGSK() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            myTestFixture.project.executeWriteCommand("") {
                val module = ModuleManager.getInstance(myProject).findModuleByName("project.app")!!
                val configurator = findGradleModuleConfigurator()
                val collector = createConfigureKotlinNotificationCollector(myProject)
                configurator.configureWithVersion(myProject, listOf(module), "1.1.2", collector)

                checkFiles(files)
            }
        }
    }

    @Test
    fun testListNonConfiguredModules() {
        importProjectFromTestData()

        runReadAction {
            val configurator = findGradleModuleConfigurator()

            val (modules, ableToRunConfigurators) = getConfigurationPossibilitiesForConfigureNotification(myProject)
            assertTrue(ableToRunConfigurators.any { it is KotlinGradleModuleConfigurator })
            assertTrue(ableToRunConfigurators.any { it is KotlinJsGradleModuleConfigurator })
            val moduleNames = modules.map { it.baseModule.name }
            assertSameElements(moduleNames, "project.app")

            val moduleNamesFromConfigurator = getCanBeConfiguredModules(myProject, configurator).map { it.name }
            assertSameElements(moduleNamesFromConfigurator, "project.app")

            val moduleNamesWithKotlinFiles = getCanBeConfiguredModulesWithKotlinFiles(myProject, configurator).map { it.name }
            assertSameElements(moduleNamesWithKotlinFiles, "project.app")
        }
    }

    @Test
    fun testListNonConfiguredModulesConfigured() {
        importProjectFromTestData()

        runReadAction {
            assertEmpty(getConfigurationPossibilitiesForConfigureNotification(myProject).first)
        }
    }

    @Test
    fun testListNonConfiguredModulesConfiguredWithImplementation() {
        importProjectFromTestData()

        runReadAction {
            assertEmpty(getConfigurationPossibilitiesForConfigureNotification(myProject).first)
        }
    }

    @Test
    fun testListNonConfiguredModulesConfiguredOnlyTest() {
        importProjectFromTestData()

        runReadAction {
            assertEmpty(getConfigurationPossibilitiesForConfigureNotification(myProject).first)
        }
    }

    @Ignore
    @Test
    fun testTestTasksAreImported() {
        importProjectFromTestData()

        @Suppress("DEPRECATION")
        val testTasks = getTasksToRun(myTestFixture.module)

        assertTrue("There should be at least one test task", testTasks.isNotEmpty())
    }

    @Deprecated("restored from org.jetbrains.plugins.gradle.execution.test.runner.GradleTestRunConfigurationProducer#getTasksToRun")
    fun getTasksToRun(module: Module): List<String> {
        for (provider in GradleTestTasksProvider.EP_NAME.extensions) {
            val tasks = provider.getTasks(module)
            if (!ContainerUtil.isEmpty(tasks)) {
                return tasks
            }
        }
        val externalProjectId = ExternalSystemApiUtil.getExternalProjectId(module)
            ?: return ContainerUtil.emptyList()
        val projectPath = ExternalSystemApiUtil.getExternalProjectPath(module)
            ?: return ContainerUtil.emptyList()
        val externalProjectInfo = ExternalSystemUtil.getExternalProjectInfo(module.project, GradleConstants.SYSTEM_ID, projectPath)
            ?: return ContainerUtil.emptyList()
        val tasks: List<String>
        val gradlePath = GradleProjectResolverUtil.getGradlePath(module)
            ?: return ContainerUtil.emptyList()
        val taskPrefix = if (StringUtil.endsWithChar(gradlePath, ':')) gradlePath else "$gradlePath:"
        val moduleNode = GradleProjectResolverUtil.findModule(externalProjectInfo.externalProjectStructure, projectPath)
            ?: return ContainerUtil.emptyList()
        val taskNode: DataNode<TaskData>?
        val sourceSetId = StringUtil.substringAfter(externalProjectId, moduleNode.data.externalName + ':')
        taskNode = if (sourceSetId == null) {
            ExternalSystemApiUtil.find(
                moduleNode, ProjectKeys.TASK
            ) { node: DataNode<TaskData> ->
                node.data.isTest &&
                        StringUtil.equals(
                            "test",
                            node.data.name
                        ) || StringUtil.equals(taskPrefix + "test", node.data.name)
            }
        } else {
            ExternalSystemApiUtil.find(
                moduleNode, ProjectKeys.TASK
            ) { node: DataNode<TaskData> ->
                node.data.isTest && StringUtil.startsWith(node.data.name, sourceSetId)
            }
        }
        if (taskNode == null) return ContainerUtil.emptyList()
        val taskName = StringUtil.trimStart(taskNode.data.name, taskPrefix)
        tasks = listOf(taskName)
        return ContainerUtil.map(
            tasks
        ) { task: String -> taskPrefix + task }
    }

    @Test
    fun testAddNonKotlinLibraryGSK() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            myTestFixture.project.executeWriteCommand("") {
                KotlinWithGradleConfigurator.addKotlinLibraryToModule(
                    myTestFixture.module,
                    DependencyScope.COMPILE,
                    object : ExternalLibraryDescriptor("org.a.b", "lib", "1.0.0", "1.0.0") {
                        override fun getLibraryClassesRoots() = emptyList<String>()
                    })
            }

            checkFiles(files)
        }
    }

    @Test
    fun testAddTestLibraryGSK() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            myTestFixture.project.executeWriteCommand("") {
                KotlinWithGradleConfigurator.addKotlinLibraryToModule(
                    myTestFixture.module,
                    DependencyScope.TEST,
                    object : ExternalLibraryDescriptor("junit", "junit", "4.12", "4.12") {
                        override fun getLibraryClassesRoots() = emptyList<String>()
                    })

                KotlinWithGradleConfigurator.addKotlinLibraryToModule(
                    myTestFixture.module,
                    DependencyScope.TEST,
                    object : ExternalLibraryDescriptor("org.jetbrains.kotlin", "kotlin-test", "1.1.2", "1.1.2") {
                        override fun getLibraryClassesRoots() = emptyList<String>()
                    })
            }

            checkFiles(files)
        }
    }

    @Test
    fun testAddLibraryGSK() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            myTestFixture.project.executeWriteCommand("") {
                KotlinWithGradleConfigurator.addKotlinLibraryToModule(
                    myTestFixture.module,
                    DependencyScope.COMPILE,
                    object : ExternalLibraryDescriptor("org.jetbrains.kotlin", "kotlin-reflect", "1.0.0", "1.0.0") {
                        override fun getLibraryClassesRoots() = emptyList<String>()
                    })
            }

            checkFiles(files)
        }
    }

    @Test
    fun testAddLanguageVersion() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                KotlinWithGradleConfigurator.changeLanguageVersion(myTestFixture.module, "1.1", null, false)
            }

            checkFiles(files)
        }
    }

    @Test
    fun testAddLanguageVersionGSK() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                KotlinWithGradleConfigurator.changeLanguageVersion(myTestFixture.module, "1.1", null, false)
            }

            checkFiles(files)
        }
    }

    @Test
    fun testChangeLanguageVersion() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                KotlinWithGradleConfigurator.changeLanguageVersion(myTestFixture.module, "1.1", null, false)
            }

            checkFiles(files)
        }
    }

    @Test
    fun testChangeLanguageVersionGSK() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                KotlinWithGradleConfigurator.changeLanguageVersion(myTestFixture.module, "1.1", null, false)
            }

            checkFiles(files)
        }
    }

    @Test
    fun testAddLibrary() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            myTestFixture.project.executeWriteCommand("") {
                KotlinWithGradleConfigurator.addKotlinLibraryToModule(
                    myTestFixture.module,
                    DependencyScope.COMPILE,
                    object : ExternalLibraryDescriptor("org.jetbrains.kotlin", "kotlin-reflect", "1.0.0", "1.0.0") {
                        override fun getLibraryClassesRoots() = emptyList<String>()
                    })
            }

            checkFiles(files)
        }
    }

    @Test
    fun testChangeFeatureSupport() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                KotlinWithGradleConfigurator.changeFeatureConfiguration(
                    myTestFixture.module, LanguageFeature.InlineClasses, LanguageFeature.State.ENABLED, false
                )
            }

            checkFiles(files)
        }
    }

    @Test
    @TargetVersions("4.7+")
    fun testChangeFeatureSupportWithXFlag() = testChangeFeatureSupport()

    @Test
    fun testDisableFeatureSupport() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                KotlinWithGradleConfigurator.changeFeatureConfiguration(
                    myTestFixture.module, LanguageFeature.InlineClasses, LanguageFeature.State.DISABLED, false
                )
            }

            checkFiles(files)
        }
    }

    @Test
    @TargetVersions("4.7+")
    fun testDisableFeatureSupportWithXFlag() = testDisableFeatureSupport()

    @Test
    fun testEnableFeatureSupport() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                KotlinWithGradleConfigurator.changeFeatureConfiguration(
                    myTestFixture.module, LanguageFeature.InlineClasses, LanguageFeature.State.ENABLED, false
                )
            }

            checkFiles(files)
        }
    }

    @Test
    @TargetVersions("4.7+")
    @JvmName("testEnableFeatureSupportWithXFlag")
    fun testEnableFeatureSupportWithXFlag() = testEnableFeatureSupport()

    @Test
    fun testEnableFeatureSupportToExistentArguments() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                KotlinWithGradleConfigurator.changeFeatureConfiguration(
                    myTestFixture.module, LanguageFeature.InlineClasses, LanguageFeature.State.ENABLED, false
                )
            }

            checkFiles(files)
        }
    }

    @Test
    fun testEnableFeatureSupportGSKWithoutFoundKotlinVersion() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                KotlinWithGradleConfigurator.changeFeatureConfiguration(
                    myTestFixture.module, LanguageFeature.InlineClasses, LanguageFeature.State.ENABLED, false
                )
            }

            checkFiles(files)
        }
    }

    @TargetVersions("4.7+")
    @Test
    fun testEnableFeatureSupportToExistentArgumentsWithXFlag() = testEnableFeatureSupportToExistentArguments()

    @Test
    fun testChangeFeatureSupportGSK() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                KotlinWithGradleConfigurator.changeFeatureConfiguration(
                    myTestFixture.module, LanguageFeature.InlineClasses, LanguageFeature.State.DISABLED, false
                )
            }

            checkFiles(files)
        }
    }

    @Test
    @TargetVersions("4.7+")
    fun testChangeFeatureSupportGSKWithXFlag() = testChangeFeatureSupportGSK()

    @Test
    fun testDisableFeatureSupportGSK() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                KotlinWithGradleConfigurator.changeFeatureConfiguration(
                    myTestFixture.module, LanguageFeature.InlineClasses, LanguageFeature.State.DISABLED, false
                )
            }

            checkFiles(files)
        }
    }

    @Test
    @TargetVersions("4.7+")
    fun testDisableFeatureSupportGSKWithXFlag() = testDisableFeatureSupportGSK()

    @Test
    fun testEnableFeatureSupportGSK() {
        val files = importProjectFromTestData()

        runInEdtAndWait {
            runWriteAction {
                KotlinWithGradleConfigurator.changeFeatureConfiguration(
                    myTestFixture.module, LanguageFeature.InlineClasses, LanguageFeature.State.ENABLED, false
                )
            }

            checkFiles(files)
        }
    }

    @Test
    @TargetVersions("4.7+")
    fun testEnableFeatureSupportGSKWithXFlag() = testEnableFeatureSupportGSK()

    @Test
    @TargetVersions("4.7+")
    fun testEnableFeatureSupportGSKWithNotInfixVersionCallAndXFlag() = testEnableFeatureSupportGSK()

    @Test
    @TargetVersions("4.7+")
    fun testEnableFeatureSupportGSKWithSpecifyingPluginThroughIdAndXFlag() = testEnableFeatureSupportGSK()

    override fun testDataDirName(): String {
        return "configurator"
    }
}
