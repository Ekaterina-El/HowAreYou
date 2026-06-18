package com.ekaterinael.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
abstract class AndroidJetpackComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<CommonExtension>("android") {
                buildFeatures.compose = true
            }

            dependencies {
                add("implementation", findLib("androidx.activity.compose"))
                add("implementation", findLib("androidx.compose.ui"))
                add("implementation", findLib("androidx.compose.ui.graphics"))
                add("implementation", findLib("androidx.compose.ui.tooling.preview"))
                add("implementation", findLib("androidx.compose.material3"))
                add("implementation", platform(findLib("androidx.compose.bom")))
            }
        }
    }
}