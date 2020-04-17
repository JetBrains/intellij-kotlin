plugins {
    kotlin("js") version "KOTLIN_VERSION"
}
group = "testGroupId"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-dev")
    }
}
dependencies {
    testImplementation(kotlin("test-js"))
    implementation(kotlin("stdlib-js"))
}
kotlin {
    js {
        browser {}
        binaries.executable()
    }
}