package com.ekaterinael.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
abstract class AndroidDaggerConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.google.devtools.ksp")

            dependencies {
                add("implementation", findLib("dagger"))
                add("ksp", findLib("dagger.compiler"))
            }
        }
    }
}