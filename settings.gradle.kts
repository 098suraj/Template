
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        mavenCentral()
        jcenter()
    }
}
rootProject.name = "template"
include (":app")
