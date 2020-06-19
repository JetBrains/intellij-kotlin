package org.jetbrains.kotlin.idea.artifacts

import org.jdom.input.SAXBuilder
import org.jetbrains.annotations.TestOnly
import org.jetbrains.kotlin.idea.artifacts.KotlinArtifactNames.KOTLIN_MAIN_KTS_NAME
import org.jetbrains.kotlin.idea.artifacts.KotlinArtifactNames.KOTLIN_STDLIB_JS_NAME
import java.io.File

@get:TestOnly
val KOTLIN_PLUGIN_ROOT_DIRECTORY: File by lazy {
    var currentDir = File(".").absoluteFile
    while (!File(currentDir, "kotlin.kotlin-ide.iml").exists()) {
        currentDir = currentDir.parentFile ?: error("Can't find repository root")
    }

    File(currentDir, "kotlin").takeIf { it.exists() } ?: error("Can't find Kotlin plugin root directory")
}

private const val PROJECT_DIR = "\$PROJECT_DIR\$"
private const val MAVEN_REPOSITORY = "\$MAVEN_REPOSITORY\$"

private fun substitutePathVariables(path: String): String {
    if (path.startsWith("$PROJECT_DIR/")) {
        val projectDir = KOTLIN_PLUGIN_ROOT_DIRECTORY.parentFile
        return projectDir.absolutePath + path.drop(PROJECT_DIR.length)
    } else if (path.startsWith("$MAVEN_REPOSITORY/")) {
        val userHomeDir = System.getProperty("user.home", null) ?: error("Unable to get the user home directory")
        val repoDir = File(userHomeDir, ".m2/repository")
        return repoDir.absolutePath + path.drop(MAVEN_REPOSITORY.length)
    }

    return path
}

private enum class LibraryFileKind {
    CLASSES, SOURCES
}

private fun findLibrary(
    repoLocation: String,
    library: String,
    group: String,
    artifact: String,
    kind: LibraryFileKind = LibraryFileKind.CLASSES
): File {
    val librariesDir = File(KOTLIN_PLUGIN_ROOT_DIRECTORY, "../.idea/libraries")
    if (!librariesDir.exists()) {
        throw IllegalStateException("Can't find $librariesDir")
    }

    val libraryFile = File(librariesDir, library)
    if (!libraryFile.exists()) {
        throw IllegalStateException("Can't find library $library")
    }

    val document = libraryFile.inputStream().use { stream -> SAXBuilder().build(stream) }
    val urlScheme = "jar://"
    val pathInRepository = group.replace('.', '/') + '/' + artifact
    val pathPrefix = "$urlScheme$repoLocation/$pathInRepository/"

    val root = document.rootElement
        .getChild("library")
        ?.getChild(kind.name)
        ?.getChildren("root")
        ?.singleOrNull { (it.getAttributeValue("url") ?: "").startsWith(pathPrefix) }
        ?: error("Root '$pathInRepository' not found in library $library")

    val url = root.getAttributeValue("url") ?: ""
    val path = url.drop(urlScheme.length).dropLast(2) // last '!/'

    val result = File(substitutePathVariables(path))
    if (!result.exists()) {
        throw IllegalStateException("File $result doesn't exist")
    }
    return result
}

@TestOnly
object TestKotlinArtifacts : KotlinArtifacts() {
    private const val artifactName = "KotlinPlugin"
    private const val repoPath = "$PROJECT_DIR/dependencies/repo"

    private val artifactDirectory: File by lazy {
        val result = File(KOTLIN_PLUGIN_ROOT_DIRECTORY, "../out/artifacts/$artifactName")
        if (!result.exists()) {
            throw IllegalStateException("Artifact '$artifactName' doesn't exist")
        }
        return@lazy result
    }

    override val kotlincDirectory: File by lazy { findFile(artifactDirectory, "kotlinc") }
    override val kotlincLibDirectory: File by lazy { findFile(artifactDirectory, "lib") }

    override val jetbrainsAnnotations by lazy { findLibrary(MAVEN_REPOSITORY, "jetbrains_annotations.xml", "org.jetbrains", "annotations") }
    override val kotlinStdlib by lazy { findLibrary(repoPath, "kotlin_stdlib_jdk8.xml", "org.jetbrains.kotlin", "kotlin-stdlib") }
    override val kotlinStdlibSources by lazy { findLibrary(repoPath, "kotlin_stdlib_jdk8.xml", "org.jetbrains.kotlin", "kotlin-stdlib", LibraryFileKind.SOURCES) }
    override val kotlinReflect by lazy { findLibrary(repoPath, "kotlin_reflect.xml", "org.jetbrains.kotlin", "kotlin-reflect") }
    override val kotlinStdlibJs by lazy { findLibrary(MAVEN_REPOSITORY, "kotlin_stdlib_js.xml", "org.jetbrains.kotlin", KOTLIN_STDLIB_JS_NAME) }
    override val kotlinStdlibJsSources by lazy { findLibrary(MAVEN_REPOSITORY, "kotlin_stdlib_js.xml", "org.jetbrains.kotlin", KOTLIN_STDLIB_JS_NAME, LibraryFileKind.SOURCES) }
    override val kotlinTest by lazy { findLibrary(MAVEN_REPOSITORY, "kotlin_test.xml", "org.jetbrains.kotlin", "kotlin-test") }
    override val kotlinMainKts by lazy { findLibrary(MAVEN_REPOSITORY, "kotlin_main_kts.xml", "org.jetbrains.kotlin", KOTLIN_MAIN_KTS_NAME) }
    override val kotlinScriptRuntime by lazy { findLibrary(MAVEN_REPOSITORY, "kotlinc_kotlin_script_runtime.xml", "org.jetbrains.kotlin", "kotlin-script-runtime") }
}