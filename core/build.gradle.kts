plugins {
    alias(libs.plugins.android.library.convention)
    alias(libs.plugins.android.dagger.convention)
    alias(libs.plugins.decompose.convention)
}

android {
    namespace = "com.ekaterinael.core"
}