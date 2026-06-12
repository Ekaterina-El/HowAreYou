pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HowAreYou"
include(":app")
include(":core")
include(":data")
include(":feature")
include(":ui")
include(":feature:mood")
include(":feature:mood:core")
include(":feature:mood:data")
include(":feature:mood:add_edit_mood_log")
include(":feature:mood:mood_list")
include(":feature:mood:mood_statistic")
include(":feature:mood:domain")
