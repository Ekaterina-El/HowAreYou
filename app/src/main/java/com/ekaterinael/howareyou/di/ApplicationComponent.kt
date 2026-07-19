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
package com.ekaterinael.howareyou.di

import android.content.Context
import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.di.RoomModule
import com.ekaterinael.howareyou.MainActivity
import com.ekaterinael.mood.data.di.MoodDataModule
import com.ekaterinael.mood.mood_list.di.MoodListModule
import com.ekaterinael.mood.mood_statistic.di.MoodStatisticModule
import dagger.BindsInstance
import dagger.Component

/**
 * Application-level dependency injection component.
 *
 * Provides dependencies shared across the application and injects them into supported Android
 * components.
 */
@AppScope
@Component(
  modules =
    [
      RoomModule::class,
      PresentationModule::class,
      MoodListModule::class,
      MoodStatisticModule::class,
      MoodDataModule::class,
    ]
)
interface ApplicationComponent {
  /**
   * Injects application dependencies into the specified [activity].
   *
   * @param activity the activity receiving injected dependencies.
   */
  fun inject(activity: MainActivity)

  /** Builder for creating an [ApplicationComponent] instance. */
  @Component.Builder
  interface Builder {
    /**
     * Binds the application [context] to the dependency graph.
     *
     * @param context the application context.
     * @return this builder instance.
     */
    fun context(@BindsInstance context: Context): Builder

    /**
     * Creates the configured [ApplicationComponent].
     *
     * @return the application-level dependency injection component.
     */
    fun build(): ApplicationComponent
  }
}
