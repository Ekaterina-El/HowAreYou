import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessExtensionPredeclare
import com.diffplug.spotless.LineEnding
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.kotlin.dsl.libs

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.detekt) apply false
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
val detektVersion = libs.versions.detekt.get()

subprojects {
    apply(plugin = "com.autonomousapps.dependency-analysis")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    configureSpotless()
    configureDetekt()
}

private fun Project.configureDetekt() {
    configure<DetektExtension> {
        toolVersion = detektVersion

        config.setFrom(
            rootProject.files("configure/detekt/config.yml")
        )

        baseline = project.file("detekt-baseline.yml")

        buildUponDefaultConfig = true
        allRules = false
        ignoreFailures = false
        parallel = true
    }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = "17"
        exclude(
            "**/build/**",
            "**/generated/**",
            "**/build/generated/**",
            "**/ksp/**",
        )

        reports {
            html.required.set(true)

            xml.required.set(false)
            sarif.required.set(false)
            txt.required.set(false)
            md.required.set(false)
        }
    }

    tasks.matching { it.name == "check" }.configureEach {
        dependsOn("detekt")
    }
}

tasks.register("detektAll") {
    group = "verification"
    description = "Runs detekt analysis for all project modules"

    dependsOn(
        subprojects.map { project ->
            "${project.path}:detekt"
        }
    )
}

private fun Project.configureSpotless() {

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
            licenseHeaderFile(rootProject.file("spotless/copyright-header.txt"), "(package|@file:)")
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
