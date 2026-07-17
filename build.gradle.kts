import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessExtensionPredeclare
import com.diffplug.spotless.LineEnding

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.spotless) apply false
}

dependencyAnalysis {
    issues {
        all {
            onAny {
                severity("warn")
            }
        }
    }

    structure {
        ignoreKtx(true)
    }
}

val ktfmtVersion = libs.versions.ktfmt.get()

subprojects {
    apply(plugin = "com.autonomousapps.dependency-analysis")
    apply(plugin = "com.diffplug.spotless")

    val spotlessFormatters: SpotlessExtension.() -> Unit = {
        lineEndings = LineEnding.PLATFORM_NATIVE

        format("misc") {
            target(
                "*.md",
                ".gitignore",
                "**/*.xml",
                "**/*.json"
            )
            trimTrailingWhitespace()
            endWithNewline()
        }

        kotlin {
            target("**/src/**/*.kt")
            targetExclude("spotless/copyright-header.txt")
            ktfmt(ktfmtVersion).googleStyle()
            licenseHeaderFile(rootProject.file("spotless/copyright-header.txt"),  "(package|@file:)")
            trimTrailingWhitespace()
            endWithNewline()
        }

        kotlinGradle {
            target("*.kts")
            targetExclude("spotless/copyright-header.txt")
            ktfmt(ktfmtVersion).googleStyle()
            trimTrailingWhitespace()
            endWithNewline()
            licenseHeaderFile(
                rootProject.file("spotless/copyright-header.txt"),
                "(import|plugins|buildscript|dependencies|pluginManagement|dependencyResolutionManagement)",
            )
        }
    }

    configure<SpotlessExtension> { spotlessFormatters() }

    if (project.rootProject == project) {
        configure<SpotlessExtensionPredeclare> { spotlessFormatters() }
    }
}
