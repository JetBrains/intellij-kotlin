// GENERATED build.gradle.kts
// GENERATED BY kotlin.gradle.gradle-tooling.iml
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
    jpsLikeJarDependency(intellijPluginDep("gradle", forIde = true), JpsDepScope.COMPILE)
    jpsLikeJarDependency("org.jetbrains.intellij.deps:gradle-api:6.7", JpsDepScope.COMPILE) // Exported transitive dependency
    jpsLikeJarDependency("org.slf4j:slf4j-log4j12:1.7.25", JpsDepScope.COMPILE) // Exported transitive dependency
    jpsLikeJarDependency("org.codehaus.groovy:groovy-ant:2.4.17", JpsDepScope.PROVIDED) // Exported transitive dependency
    jpsLikeJarDependency("org.codehaus.groovy:groovy:2.4.17", JpsDepScope.PROVIDED) // Exported transitive dependency
    jpsLikeModuleDependency(":kotlin-ide.kotlin.resources-descriptors", JpsDepScope.TEST)
}

configurations.all {
    exclude(module = "tests-common") // Avoid classes with same FQN clashing
}

sourceSets {
    "main" {
        java.srcDir("src")
        resources.srcDir("resources")
    }
    "test" {
        
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile> {
    kotlinOptions.freeCompilerArgs = listOf("-version", "-Xstrict-java-nullability-assertions", "-Xjvm-default=enable", "-Xskip-prerelease-check")
    kotlinOptions.jdkHome = rootProject.extra["JDK_11"] as String
    kotlinOptions.useOldBackend = true // KT-45697
}

testsJar()