plugins {
    alias(libs.plugins.android.library.convention)
    alias(libs.plugins.android.dagger.convention)
}

android {
    namespace = "com.ekaterinael.mood.data"
}

dependencies {
    // Coroutines
    implementation(libs.kotlinx.coroutine.android)

    // Project
    implementation(project(":core"))
    implementation(project(":data"))
    api(project(":feature:mood:domain"))
}