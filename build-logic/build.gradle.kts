import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.ekaterinael.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidLibraryConvention") {
            id = libs.plugins.android.library.convention.get().pluginId
            implementationClass = "com.ekaterinael.buildlogic.AndroidLibraryConventionPlugin"
        }

        register("androidDaggerConvention") {
            id = libs.plugins.android.dagger.convention.get().pluginId
            implementationClass = "com.ekaterinael.buildlogic.AndroidDaggerConventionPlugin"
        }

        register("decompose") {
            id = libs.plugins.decompose.convention.get().pluginId
            implementationClass = "com.ekaterinael.buildlogic.AndroidDecomposeConventionPlugin"
        }

        register("jetpack-compose") {
            id = libs.plugins.jetpack.compose.convention.get().pluginId
            implementationClass = "com.ekaterinael.buildlogic.AndroidJetpackComposeConventionPlugin"
        }

        register("mvi") {
            id = libs.plugins.mvi.convention.get().pluginId
            implementationClass = "com.ekaterinael.buildlogic.AndroidMVIConventionPlugin"
        }
    }
}