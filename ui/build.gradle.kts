plugins {
    alias(libs.plugins.android.library.convention)
    alias(libs.plugins.jetpack.compose.convention)
}

android {
    namespace = "com.ekaterinael.ui"
}

dependencies {
    implementation(libs.androidx.compose.material.icons.extended)

    // Project
    implementation(project(":core"))
}