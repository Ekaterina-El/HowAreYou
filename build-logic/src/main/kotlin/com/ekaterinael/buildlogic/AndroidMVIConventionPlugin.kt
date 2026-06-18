package com.ekaterinael.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
abstract class AndroidMVIConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("api", findLib("mvikotlin"))
                add("implementation", findLib("mvikotlin.extensions.coroutines"))
            }
        }
    }
}