plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.ekaterinael.mood.add_edit_mood_log"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // MVI
    api(libs.mvikotlin)
    implementation(libs.mvikotlin.extensions.coroutines)
    api(libs.decompose)

    // Dagger 2
    api(libs.dagger)
    ksp(libs.dagger.compiler)

    // Project
    implementation(project(":ui"))
    implementation(project(":core"))
    api(project(":feature:mood:core"))
    api(project(":feature:mood:domain"))
}