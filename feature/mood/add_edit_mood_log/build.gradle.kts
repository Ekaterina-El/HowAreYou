plugins {
    alias(libs.plugins.android.library.convention)
    alias(libs.plugins.android.dagger.convention)
    alias(libs.plugins.decompose.convention)
    alias(libs.plugins.jetpack.compose.convention)
    alias(libs.plugins.mvi.convention)
}

android {
    namespace = "com.ekaterinael.mood.add_edit_mood_log"
}

dependencies {
    // Project
    implementation(project(":ui"))
    implementation(project(":core"))
    api(project(":feature:mood:core"))
    api(project(":feature:mood:domain"))
}