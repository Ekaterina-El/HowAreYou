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
package com.ekaterinael.mood.mood_statistic

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

/**
 * Default implementation of [MoodStatisticComponent] responsible for managing the mood statistics
 * screen state and user interactions.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class DefaultMoodStatisticComponent
@AssistedInject
constructor(
  moodStatisticStoreFactory: MoodStatisticStoreFactory,
  @Assisted("componentContext") private val componentContext: ComponentContext,
) : MoodStatisticComponent, ComponentContext by componentContext {
  private val store: MoodStatisticStore =
    instanceKeeper.getStore { moodStatisticStoreFactory.create() }

  override val model: StateFlow<MoodStatisticStore.State> = store.stateFlow

  /**
   * Factory for creating [DefaultMoodStatisticComponent] instances with an assisted
   * [ComponentContext].
   */
  @AssistedFactory
  interface Factory {
    /**
     * Creates a new [DefaultMoodStatisticComponent].
     *
     * @param componentContext Decompose context used for lifecycle management.
     * @return a new [DefaultMoodStatisticComponent] instance.
     */
    fun create(
      @Assisted("componentContext") componentContext: ComponentContext
    ): DefaultMoodStatisticComponent
  }
}
