plugins {
    alias(libs.plugins.android.library.convention)
    alias(libs.plugins.jetpack.compose.convention)
    alias(libs.plugins.mvi.convention)
}

android {
    namespace = "com.ekaterinael.mood.core"
}

dependencies {
    implementation(project(":ui"))
    implementation(project(":feature:mood:domain"))
}