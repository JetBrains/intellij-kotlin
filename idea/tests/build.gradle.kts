// GENERATED build.gradle.kts
// GENERATED BY kotlin.idea.tests.iml
// USE `./gradlew generateIdeaGradleFiles` TO REGENERATE THIS FILE

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

repositories {
    maven { setUrl("https://cache-redirector.jetbrains.com/maven-central") }
    maven { setUrl("https://cache-redirector.jetbrains.com/jetbrains.bintray.com/intellij-third-party-dependencies") }
    maven { setUrl("https://cache-redirector.jetbrains.com/jetbrains.bintray.com/dekaf") }
    maven { setUrl("https://cache-redirector.jetbrains.com/jcenter.bintray.com") }
    maven { setUrl("https://cache-redirector.jetbrains.com/dl.google.com/dl/android/maven2") }
    maven { setUrl("https://cache-redirector.jetbrains.com/repo.spring.io/milestone") }
    maven { setUrl("https://cache-redirector.jetbrains.com/repo.jenkins-ci.org/releases") }
    maven { setUrl("https://cache-redirector.jetbrains.com/jetbrains.bintray.com/test-discovery") }
    maven { setUrl("https://cache-redirector.jetbrains.com/jetbrains.bintray.com/markdown") }
    maven { setUrl("https://cache-redirector.jetbrains.com/www.myget.org/F/intellij-go-snapshots/maven") }
    maven { setUrl("https://cache-redirector.jetbrains.com/dl.bintray.com/groovy/maven") }
    maven { setUrl("https://cache-redirector.jetbrains.com/www.myget.org/F/rd-snapshots/maven") }
    maven { setUrl("https://cache-redirector.jetbrains.com/www.myget.org/F/rd-model-snapshots/maven") }
    maven { setUrl("https://cache-redirector.jetbrains.com/jetbrains.bintray.com/jediterm") }
    maven { setUrl("https://cache-redirector.jetbrains.com/jetbrains.bintray.com/intellij-terraform") }
    maven { setUrl("https://cache-redirector.jetbrains.com/download.jetbrains.com/teamcity-repository") }
    maven { setUrl("https://cache-redirector.jetbrains.com/kotlin.bintray.com/kotlin-plugin/") }
    maven { setUrl("https://cache-redirector.jetbrains.com/repo.maven.apache.org/maven2/") }
    maven { setUrl("https://cache-redirector.jetbrains.com/clojars.org/repo") }
    maven { setUrl("https://cache-redirector.jetbrains.com/jetbrains.bintray.com/space") }
    maven { setUrl("https://cache-redirector.jetbrains.com/kotlin.bintray.com/kotlin-ide-plugin-dependencies") }
    maven { setUrl("https://cache-redirector.jetbrains.com/maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-ide") }
    maven { setUrl("https://cache-redirector.jetbrains.com/kotlin.bintray.com/kotlin-ide-plugin-dependencies") }
    maven { setUrl("https://repo.labs.intellij.net/artifactory/mobile-ide-tmp") }
}

dependencies {
    implementation(toolsJarApi())
    jpsLikeJarDependency(kotlinStdlib(), JpsDepScope.COMPILE)
    jpsLikeJarDependency(project(":kotlin-stdlib-jdk7"), JpsDepScope.COMPILE)
    jpsLikeJarDependency("io.javaslang:javaslang:2.0.6", JpsDepScope.TEST)
    jpsLikeJarDependency("org.jetbrains.kotlin:kotlin-test:1.4.0", JpsDepScope.TEST)
    jpsLikeJarDependency(project(":prepare:ide-plugin-dependencies:android-extensions-compiler-plugin-for-ide"), JpsDepScope.TEST)
    jpsLikeJarDependency("com.google.code.gson:gson:2.8.6", JpsDepScope.TEST)
    jpsLikeJarDependency("org.jetbrains.intellij.deps:trove4j:1.0.20200330", JpsDepScope.TEST)
    jpsLikeJarDependency("com.google.guava:guava:29.0-jre", JpsDepScope.TEST)
    jpsLikeJarDependency("org.jetbrains.intellij.deps.fastutil:intellij-deps-fastutil:8.4.1-4", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.jvm", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.test-framework", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.tests-common", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.repl", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.line-indent-provider", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.jvm-debugger.util", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.native", JpsDepScope.TEST)
    jpsLikeJarDependency(project(":prepare:ide-plugin-dependencies:kotlin-compiler-for-ide"), JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.core", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.common", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.fir.frontend-independent", JpsDepScope.TEST)
    jpsLikeJarDependency(project(":prepare:ide-plugin-dependencies:sam-with-receiver-compiler-plugin-for-ide"), JpsDepScope.TEST)
    jpsLikeJarDependency(project(":kotlin-reflect"), JpsDepScope.TEST)
    jpsLikeJarDependency(project(":kotlin-script-runtime"), JpsDepScope.TEST)
    jpsLikeJarDependency(project(":kotlin-scripting-common"), JpsDepScope.TEST)
    jpsLikeJarDependency(project(":kotlin-scripting-jvm"), JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.idea", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.gradle.gradle-idea", JpsDepScope.RUNTIME)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.gradle.gradle-native", JpsDepScope.RUNTIME)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.compiler-plugins.sam-with-receiver", JpsDepScope.RUNTIME)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.compiler-plugins.noarg", JpsDepScope.RUNTIME)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.compiler-plugins.kotlinx-serialization", JpsDepScope.RUNTIME)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.compiler-plugins.allopen", JpsDepScope.RUNTIME)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.compiler-plugins.parcelize", JpsDepScope.RUNTIME)
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("platform-api") })
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("resources_en") }) // Exported transitive dependency
    jpsLikeJarDependency(intellijCoreDep(), JpsDepScope.TEST) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("intellij-core-analysis-deprecated") }) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("extensions") }) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("util") }) // Exported transitive dependency
    jpsLikeJarDependency("org.jetbrains:annotations:20.1.0", JpsDepScope.TEST) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("idea") })
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("structuralsearch") }) // Exported transitive dependency
    jpsLikeJarDependency(intellijPluginDep("gradle", forIde = true), JpsDepScope.TEST)
    jpsLikeJarDependency("org.jetbrains.intellij.deps:gradle-api:6.7", JpsDepScope.TEST) // Exported transitive dependency
    jpsLikeJarDependency("org.slf4j:slf4j-log4j12:1.7.25", JpsDepScope.TEST) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("platform-impl") }) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("external-system-rt") }) // Exported transitive dependency
    jpsLikeJarDependency("org.jetbrains.teamcity:serviceMessages:2019.1.4", JpsDepScope.TEST) // Exported transitive dependency
    jpsLikeJarDependency("org.codehaus.groovy:groovy-ant:2.4.17", JpsDepScope.TEST) // Exported transitive dependency
    jpsLikeJarDependency("org.codehaus.groovy:groovy:2.4.17", JpsDepScope.TEST) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("platform-ide-util-io") })
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("platform-concurrency") }) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("jps-model") }) // Exported transitive dependency
    jpsLikeJarDependency("org.jetbrains.intellij.deps:jdom:2.0.6", JpsDepScope.TEST) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("platform-util-ex") }) // Exported transitive dependency
    jpsLikeJarDependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.0", JpsDepScope.TEST) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("spellchecker") })
    jpsLikeModuleDependency(":kotlin-ide.kotlin.uast.uast-kotlin", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.formatter", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.jps-common", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.uast.uast-kotlin-idea", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.jvm-debugger.evaluation", JpsDepScope.TEST)
    jpsLikeModuleDependency(":kotlin-ide.kotlin.j2k.services", JpsDepScope.TEST)
    jpsLikeJarDependency(intellijPluginDep("IntelliLang", forIde = true), JpsDepScope.TEST)
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("platform-serviceContainer") })
    jpsLikeJarDependency(intellijPluginDep("java", forIde = true), JpsDepScope.TEST)
    jpsLikeJarDependency("org.jetbrains.intellij.deps:asm-all:9.0", JpsDepScope.TEST) // Exported transitive dependency
    jpsLikeJarDependency("org.apache.velocity:velocity:1.7", JpsDepScope.TEST) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("javac2") }) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("forms_rt") }) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("resources") }) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("platform-util-ui") }) // Exported transitive dependency
    jpsLikeJarDependency("com.jgoodies:forms:1.1-preview", JpsDepScope.TEST) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("platform-statistics") }) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("platform-statistics-uploader") }) // Exported transitive dependency
    jpsLikeJarDependency(intellijDep(forIde = true), JpsDepScope.TEST, { includeJars("platform-statistics-config") }) // Exported transitive dependency
    jpsLikeJarDependency(intellijPluginDep("copyright", forIde = true), JpsDepScope.TEST)
    jpsLikeJarDependency(intellijPluginDep("junit", forIde = true), JpsDepScope.TEST)
    jpsLikeJarDependency(intellijPluginDep("Groovy", forIde = true), JpsDepScope.TEST)
    jpsLikeJarDependency("org.codehaus.groovy:groovy:2.5.11", JpsDepScope.TEST) // Exported transitive dependency
    jpsLikeJarDependency(intellijPluginDep("properties", forIde = true), JpsDepScope.TEST)
}

configurations.all {
    exclude(module = "tests-common") // Avoid classes with same FQN clashing
}

sourceSets {
    "main" {
        
    }
    "test" {
        java.srcDir("../../live-templates/tests/test")
        java.srcDir("../../completion/tests/test")
        java.srcDir("test")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile> {
    kotlinOptions.freeCompilerArgs = listOf("-version", "-Xstrict-java-nullability-assertions", "-Xjvm-default=enable")
    kotlinOptions.jdkHome = rootProject.extra["JDK_11"] as String
    kotlinOptions.useOldBackend = true // KT-45697
}

testsJar()