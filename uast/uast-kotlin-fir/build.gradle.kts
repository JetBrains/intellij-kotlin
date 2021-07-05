// GENERATED build.gradle.kts
// GENERATED BY kotlin.uast.uast-kotlin-fir.iml
// USE `./gradlew generateIdePluginGradleFiles` TO REGENERATE THIS FILE

plugins {
    kotlin("jvm")
    `java-library` // Add `compileOnlyApi` configuration
    id("jps-compatible")
}

repositories {
    maven { setUrl("https://cache-redirector.jetbrains.com/maven-central") }
    maven { setUrl("https://cache-redirector.jetbrains.com/intellij-dependencies") }
    maven { setUrl("https://cache-redirector.jetbrains.com/jcenter") }
    maven { setUrl("https://cache-redirector.jetbrains.com/dl.google.com/dl/android/maven2") }
    maven { setUrl("https://cache-redirector.jetbrains.com/repo.jenkins-ci.org/releases") }
    maven { setUrl("https://cache-redirector.jetbrains.com/www.myget.org/F/rd-snapshots/maven") }
    maven { setUrl("https://cache-redirector.jetbrains.com/download.jetbrains.com/teamcity-repository") }
    maven { setUrl("https://cache-redirector.jetbrains.com/maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-ide-plugin-dependencies") }
    maven { setUrl("https://cache-redirector.jetbrains.com/maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-ide") }
    maven { setUrl("https://cache-redirector.jetbrains.com/maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-ide-plugin-dependencies") }
}

disableDependencyVerification()

dependencies {
    implementation(toolsJarApi())
    jpsLikeJarDependency(kotlinStdlib(), JpsDepScope.COMPILE)
    jpsLikeJarDependency(project(":kotlin-stdlib-jdk7"), JpsDepScope.COMPILE)
    jpsLikeJarDependency(project(":prepare:ide-plugin-dependencies:kotlin-compiler-for-ide"), JpsDepScope.COMPILE)
    jpsLikeJarDependency(project(":prepare:ide-plugin-dependencies:kotlin-compiler-tests-for-ide"), JpsDepScope.TEST)
    jpsLikeJarDependency("org.jetbrains.intellij.deps:asm-all:9.1", JpsDepScope.COMPILE, { isTransitive = false })
    jpsLikeModuleDependency(":kotlin-ide.kotlin.resources-fir", JpsDepScope.COMPILE)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.uast.uast-kotlin-base", JpsDepScope.COMPILE)
    jpsLikeJarDependency(project(":prepare:ide-plugin-dependencies:high-level-api-for-ide"), JpsDepScope.COMPILE)
    jpsLikeJarDependency(project(":prepare:ide-plugin-dependencies:high-level-api-fir-for-ide"), JpsDepScope.COMPILE)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.fir.frontend-independent", JpsDepScope.COMPILE)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.fir", JpsDepScope.RUNTIME)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.tests-common", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.test-framework", JpsDepScope.TEST)
    jpsLikeJarDependency(intellijPluginDep("java", forIde = true), JpsDepScope.COMPILE) // 'intellij.platform.uast' dependency
    jpsLikeJarDependency(intellijCoreDep(), JpsDepScope.COMPILE) // 'intellij.platform.uast' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.COMPILE, { includeJars("intellij-core-analysis-deprecated") }) // 'intellij.platform.uast' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.COMPILE, { includeJars("platform-api") }) // 'intellij.platform.uast' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.COMPILE, { includeJars("resources_en") }) // 'intellij.platform.uast' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.COMPILE, { includeJars("util") }) // 'intellij.platform.uast' dependency
    jpsLikeJarDependency("org.jetbrains:annotations:20.1.0", JpsDepScope.COMPILE) // 'intellij.platform.uast' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("testFramework") }) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency("junit:junit:4.12", JpsDepScope.TEST) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency("org.jetbrains.intellij.deps:log4j:1.2.17.2", JpsDepScope.TEST) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("idea_rt") }) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("platform-impl") }) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("resources_en") }) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("intellij-core-analysis-deprecated") }) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("platform-api") }) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency(intellijCoreDep(), JpsDepScope.TEST) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("util") }) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("forms_rt") }) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency("org.apache.velocity:velocity:1.7", JpsDepScope.TEST) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("jps-model") }) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency("org.jetbrains.intellij.deps:jdom:2.0.6", JpsDepScope.TEST) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("resources") }) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("testFramework.core") }) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency("org.jetbrains:jetCheck:0.2.2", JpsDepScope.TEST, { isTransitive = false }) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency("org.jetbrains:annotations:20.1.0", JpsDepScope.TEST) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency("com.jgoodies:forms:1.1-preview", JpsDepScope.TEST) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.32", JpsDepScope.TEST) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency("org.codehaus.groovy:groovy:2.5.11", JpsDepScope.TEST) // 'intellij.platform.testFramework' dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.COMPILE, { includeJars("platform-impl") }) // 'intellij.java.psi.impl' dependency
    jpsLikeJarDependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.32", JpsDepScope.COMPILE) // 'intellij.java.psi.impl' dependency
    jpsLikeJarDependency(project(":prepare:ide-plugin-dependencies:kotlin-compiler-cli-for-ide"), JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.uast.uast-kotlin-idea-fir", JpsDepScope.RUNTIME)
    jpsLikeJarDependency(project(":prepare:ide-plugin-dependencies:low-level-api-fir-for-ide"), JpsDepScope.COMPILE)
}

configurations.all {
    exclude(module = "tests-common") // Avoid classes with same FQN clashing
}

sourceSets {
    "main" {
        java.srcDir("src")
    }
    "test" {
        java.srcDir("test")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile> {
    kotlinOptions.freeCompilerArgs = listOf("-version", "-Xstrict-java-nullability-assertions", "-Xjvm-default=enable", "-Xskip-prerelease-check")
    kotlinOptions.useOldBackend = true // KT-45697
}

testsJar()