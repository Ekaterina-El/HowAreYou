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
package com.ekaterinael.mood.add_edit_mood_log.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.ekaterinael.core.ext.componentScope
import com.ekaterinael.mood.core.model.MoodUI
import com.ekaterinael.mood.domain.model.MoodLog
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Default implementation of [AddEditMoodLogComponent] responsible for managing the mood log editor
 * state and handling user interactions.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class DefaultAddEditMoodLogComponent
@AssistedInject
constructor(
  addEditMoodLogStoreFactory: AddEditMoodLogStoreFactory,
  @Assisted("componentContext") private val componentContext: ComponentContext,
  @Assisted("moodLog") moodLog: MoodLog,
  @Assisted("onGoBackCallback") private val onGoBackCallback: () -> Unit,
) : AddEditMoodLogComponent, ComponentContext by componentContext {

  private val store: AddEditMoodLogStore =
    instanceKeeper.getStore { addEditMoodLogStoreFactory.create(moodLog) }
  override val state: StateFlow<AddEditMoodLogStore.State> = store.stateFlow

  init {
    componentScope().launch {
      store.labels.collect {
        when (it) {
          AddEditMoodLogStore.Label.AfterSave,
          AddEditMoodLogStore.Label.OnGoBack -> onGoBackCallback()
        }
      }
    }
  }

  override fun onChangeDescription(value: String) {
    store.accept(AddEditMoodLogStore.Intent.OnChangeDescription(description = value))
  }

  override fun onChangeMood(value: MoodUI) {
    store.accept(AddEditMoodLogStore.Intent.OnChangeMood(mood = value))
  }

  override fun onGoBack() = store.accept(AddEditMoodLogStore.Intent.OnGoBack)

  override fun onClickSave() = store.accept(AddEditMoodLogStore.Intent.OnSave)

  /** Factory for creating [DefaultAddEditMoodLogComponent] instances with assisted dependencies. */
  @AssistedFactory
  interface Factory {

    /**
     * Creates a new [DefaultAddEditMoodLogComponent].
     *
     * @param componentContext Decompose context used for lifecycle management.
     * @param moodLog the mood log entry to create or edit.
     * @param onGoBackCallback callback invoked when navigation back is requested.
     * @return a new [DefaultAddEditMoodLogComponent] instance.
     */
    fun create(
      @Assisted("componentContext") componentContext: ComponentContext,
      @Assisted("moodLog") moodLog: MoodLog,
      @Assisted("onGoBackCallback") onGoBackCallback: () -> Unit,
    ): DefaultAddEditMoodLogComponent
  }
}
