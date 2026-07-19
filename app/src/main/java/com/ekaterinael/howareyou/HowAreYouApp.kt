/*
 * Copyright 2026 Ekaterina Elshina
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ekaterinael.howareyou

import android.app.Application
import com.ekaterinael.howareyou.di.ApplicationComponent
import com.ekaterinael.howareyou.di.DaggerApplicationComponent

/**
 * Application entry point responsible for initializing and exposing the application-level
 * dependency injection component.
 */
class HowAreYouApp : Application() {
  /** The application-wide dependency injection component. */
  lateinit var applicationComponent: ApplicationComponent

  override fun onCreate() {
    super.onCreate()
    applicationComponent = DaggerApplicationComponent.builder().context(applicationContext).build()
  }
}
