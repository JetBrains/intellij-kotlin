package org.jetbrains.kotlin.idea.artifacts

import com.intellij.jarRepository.JarRepositoryManager
import com.intellij.openapi.application.PathManager
import com.intellij.util.io.Decompressor
import org.eclipse.aether.repository.RemoteRepository
import org.jdom.input.SAXBuilder
import org.jetbrains.idea.maven.aether.ArtifactKind
import org.jetbrains.idea.maven.aether.ArtifactRepositoryManager
import org.jetbrains.idea.maven.aether.ProgressConsumer
import org.jetbrains.kotlin.test.KotlinRoot
import java.io.File
import java.security.MessageDigest

/**
 * This is used via reflection in [KotlinArtifacts]
 */
@Suppress("unused")
private class TestKotlinArtifacts : KotlinArtifacts(run {
    val jar = findLibrary(RepoLocation.MAVEN_REPOSITORY, "kotlinc_kotlin_dist.xml", "org.jetbrains.kotlin", "kotlin-dist-for-ide")
    lazyUnpackJar(jar, File(PathManager.getHomePath(), "out").resolve("kotlinc-dist"), "kotlinc")
})

private fun lazyUnpackJar(jar: File, holderDir: File, dirName: String): File {
    val hashFile = holderDir.resolve("md5")
    val hash = jar.md5()
    val dirWhereToExtract = holderDir.resolve(dirName)
    if (hashFile.exists() && hashFile.readText() == hash) {
        return dirWhereToExtract
    }
    dirWhereToExtract.deleteRecursively()
    dirWhereToExtract.mkdirs()
    hashFile.writeText(hash)
    Decompressor.Zip(jar).extract(dirWhereToExtract)
    return dirWhereToExtract
}

private fun File.md5(): String {
    return MessageDigest.getInstance("MD5").digest(readBytes()).joinToString("") { "%02x".format(it) }
}

private fun findLibrary(
        repoLocation: RepoLocation,
        library: String,
        groupId: String,
        artifactId: String,
        kind: LibraryFileKind = LibraryFileKind.CLASSES
): File {
    val librariesDir = File(KotlinRoot.REPO, ".idea/libraries")
    if (!librariesDir.exists()) {
        throw IllegalStateException("Can't find $librariesDir")
    }

    val libraryFile = File(librariesDir, library)
    if (!libraryFile.exists()) {
        throw IllegalStateException("Can't find library $library")
    }

    val document = libraryFile.inputStream().use { stream -> SAXBuilder().build(stream) }
    val urlScheme = "jar://"
    val pathInRepository = groupId.replace('.', '/') + '/' + artifactId
    val pathPrefix = "$urlScheme$repoLocation/$pathInRepository/"

    val root = document.rootElement
                       .getChild("library")
                       ?.getChild(kind.name)
                       ?.getChildren("root")
                       ?.singleOrNull { (it.getAttributeValue("url") ?: "").startsWith(pathPrefix) }
               ?: throw IllegalStateException("Root '$pathInRepository' not found in library $library")

    val url = root.getAttributeValue("url") ?: ""
    val path = url.drop(urlScheme.length).dropLast(2) // last '!/'

    val result = File(substitutePathVariables(path))
    if (!result.exists()) {
        if (kind == LibraryFileKind.SOURCES) {
            val version = result.nameWithoutExtension.drop(artifactId.length + 1).dropLast(kind.classifierSuffix.length)
            return resolveArtifact(groupId, artifactId, version, kind)
        }

        throw IllegalStateException("File $result doesn't exist")
    }
    return result
}

object AdditionalKotlinArtifacts {
    val jetbrainsAnnotations: File by lazy {
        findLibrary(RepoLocation.MAVEN_REPOSITORY, "jetbrains_annotations.xml", "org.jetbrains", "annotations")
    }

    val kotlinStdlibCommon: File by lazy {
        findLibrary(RepoLocation.MAVEN_REPOSITORY, "kotlinc_kotlin_stdlib_common.xml", "org.jetbrains.kotlin", "kotlin-stdlib-common")
    }

    val kotlinStdlibCommonSources: File by lazy {
        findLibrary(RepoLocation.MAVEN_REPOSITORY, "kotlinc_kotlin_stdlib_common.xml", "org.jetbrains.kotlin", "kotlin-stdlib-common", LibraryFileKind.SOURCES)
    }

    val kotlinStdlibMinimalForTest: File by lazy {
        findLibrary(RepoLocation.MAVEN_REPOSITORY, "kotlinc_kotlin_stdlib_minimal_for_test_for_ide.xml", "org.jetbrains.kotlin", "kotlin-stdlib-minimal-for-test-for-ide")
    }

    val jsr305: File by lazy {
        findLibrary(RepoLocation.MAVEN_REPOSITORY, "jsr305.xml", "com.google.code.findbugs", "jsr305")
    }

    val parcelizeRuntime: File by lazy {
        KotlinArtifacts.instance.kotlincDirectory.resolve("lib/parcelize-runtime.jar").also { check(it.exists()) }
    }

    val androidExtensionsRuntime by lazy {
        KotlinArtifacts.instance.kotlincDirectory.resolve("lib/android-extensions-runtime.jar").also { check(it.exists()) }
    }

    @JvmStatic
    val compilerTestDataDir = run {
        val testDataJar = findLibrary(
            RepoLocation.MAVEN_REPOSITORY,
            "kotlinc_kotlin_compiler_testdata.xml",
            "org.jetbrains.kotlin",
            "kotlin-compiler-testdata-for-ide"
        )
        lazyUnpackJar(
            testDataJar,
            KotlinRoot.DIR.resolve("out").resolve("kotlinc-testdata"),
            "testData"
        )
    }

    @JvmStatic
    fun compilerTestData(compilerTestDataPath: String): String {
        return compilerTestDataDir.resolve(compilerTestDataPath).canonicalPath
    }
}

private enum class RepoLocation {
    PROJECT_DIR {
        override fun toString(): String {
            return "\$PROJECT_DIR\$"
        }
    },
    MAVEN_REPOSITORY {
        override fun toString(): String {
            return "\$MAVEN_REPOSITORY\$"
        }
    }
}

private enum class LibraryFileKind(val classifierSuffix: String, val artifactKind: ArtifactKind) {
    CLASSES("", ArtifactKind.ARTIFACT), SOURCES("-sources", ArtifactKind.SOURCES);
}

private val remoteMavenRepositories: List<RemoteRepository> by lazy {
    val jarRepositoriesFile = File(KotlinRoot.REPO, ".idea/jarRepositories.xml")
    val document = jarRepositoriesFile.inputStream().use { stream -> SAXBuilder().build(stream) }

    val repositories = mutableListOf<RemoteRepository>()

    for (remoteRepo in document.rootElement.getChild("component")?.getChildren("remote-repository").orEmpty()) {
        val options = remoteRepo.getChildren("option") ?: continue

        fun getOptionValue(key: String): String? {
            val option = options.find { it.getAttributeValue("name") == key } ?: return null
            return option.getAttributeValue("value")?.takeIf { it.isNotEmpty() }
        }

        val id = getOptionValue("id") ?: continue
        val url = getOptionValue("url") ?: continue
        repositories += ArtifactRepositoryManager.createRemoteRepository(id, url)
    }

    return@lazy repositories
}

private fun substitutePathVariables(path: String): String {
    if (path.startsWith("${RepoLocation.PROJECT_DIR}/")) {
        val projectDir = KotlinRoot.REPO
        return projectDir.resolve(path.drop(RepoLocation.PROJECT_DIR.toString().length)).absolutePath
    }
    else if (path.startsWith("${RepoLocation.MAVEN_REPOSITORY}/")) {
        val m2 = System.getenv("M2_HOME")?.let { File(it) }
                 ?: File(System.getProperty("user.home", null) ?: error("Unable to get the user home directory"), ".m2")
        val repoDir = m2.resolve("repository")
        return repoDir.absolutePath + path.drop(RepoLocation.MAVEN_REPOSITORY.toString().length)
    }

    return path
}

private fun resolveArtifact(groupId: String, artifactId: String, version: String, kind: LibraryFileKind): File {
    val repositoryManager = ArtifactRepositoryManager(
            JarRepositoryManager.getLocalRepositoryPath(),
            remoteMavenRepositories,
            ProgressConsumer.DEAF,
            false
    )

    val artifacts = repositoryManager.resolveDependencyAsArtifact(
            groupId, artifactId, version,
            setOf(kind.artifactKind), false, emptyList()
    )

    assert(artifacts.size == 1) { "Single artifact expected for library \"$groupId:$artifactId:$version\", got $artifacts" }
    return artifacts.single().file
}
