package com.ekaterinael.buildlogic


import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
abstract class AndroidLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")

            extensions.configure<LibraryExtension>("android") {
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

                testOptions.animationsDisabled = true
            }

            dependencies {
                add("implementation", findLib("androidx.core.ktx"))
                add("implementation", findLib("androidx.appcompat"))
            }
        }
    }
}