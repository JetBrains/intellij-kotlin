pluginManagement {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlin-dev")
        }
    }

}
rootProject.name = "generatedProject"


include(":a")
include(":b")
include(":c")
include(":d")