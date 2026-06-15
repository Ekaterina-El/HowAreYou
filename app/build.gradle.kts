plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.ekaterinael.howareyou"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.ekaterinael.howareyou"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // DI
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    // Decompose
    implementation(libs.decompose)
    implementation(libs.decompose.extensions.compose.jetpack)

    // MVI
    implementation(libs.mvikotlin)
    implementation(libs.mvikotlin.main)

    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":ui"))
    implementation(project(":feature:mood:add_edit_mood_log"))
    implementation(project(":feature:mood:mood_list"))
    implementation(project(":feature:mood:mood_statistic"))
    implementation(project(":feature:mood:data"))
    implementation(project(":feature:mood:core"))
    implementation(project(":feature:mood:domain"))

    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}