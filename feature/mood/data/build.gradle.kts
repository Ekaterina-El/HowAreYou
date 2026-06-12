plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.ekaterinael.mood.data"
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

}

dependencies {
    // Dagger 2
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutine.android)

    // Project
    implementation(project(":core"))
    implementation(project(":data"))
    api(project(":feature:mood:domain"))
}