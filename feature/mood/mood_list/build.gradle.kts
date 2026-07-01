plugins {
    alias(libs.plugins.android.library.convention)
    alias(libs.plugins.android.dagger.convention)
    alias(libs.plugins.decompose.convention)
    alias(libs.plugins.jetpack.compose.convention)
    alias(libs.plugins.mvi.convention)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.ekaterinael.mood.mood_list"
}

dependencies {
    // Project
    implementation(project(":core"))
    implementation(project(":feature:mood:core"))
    implementation(project(":feature:mood:domain"))
    implementation(project(":ui"))
}