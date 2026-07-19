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
package com.ekaterinael.mood.mood_list

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.ekaterinael.core.ext.componentScope
import com.ekaterinael.mood.core.MoodUI
import com.ekaterinael.mood.domain.model.Mood
import com.ekaterinael.mood.mood_list.di.MoodListScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Default implementation of [MoodLogComponent] responsible for managing the mood log screen state
 * and user actions.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@MoodListScope
class DefaultMoodLogComponent
@AssistedInject
constructor(
  moodLogStoreFactory: MoodLogStoreFactory,
  @Assisted("componentContext") private val componentContext: ComponentContext,
  @Assisted("onOpenLogToEdit") private val onOpenLogToEdit: (moodId: Long) -> Unit,
  @Assisted("goToCreateNewLog") private val goToCreateNewLog: (selectedMood: Mood) -> Unit,
) : MoodLogComponent, ComponentContext by componentContext {
  private val store = instanceKeeper.getStore { moodLogStoreFactory.create() }

  override val model: StateFlow<MoodLogStore.State> = store.stateFlow

  init {
    componentScope().launch {
      store.labels.collect {
        when (it) {
          is MoodLogStore.Label.OpenLogToEdit -> {
            onOpenLogToEdit(it.logId)
          }
          is MoodLogStore.Label.GoToCreateNewLog -> {
            goToCreateNewLog(it.selectedMood.mood)
          }
        }
      }
    }
  }

  override fun onClickAddNewLog(selectedMood: MoodUI) {
    store.accept(MoodLogStore.Intent.OnClickAddNewLog(selectedMood))
  }

  override fun onClickByLog(logId: Long?) {
    if (logId == null) {
      Log.w(TAG, "Can`t open log - id is null")
      return
    }

    store.accept(MoodLogStore.Intent.OnClickByLog(logId))
  }

  /** Factory for creating [DefaultMoodLogComponent] instances with assisted dependencies. */
  @[AssistedFactory MoodListScope]
  interface Factory {
    /**
     * Creates a new [DefaultMoodLogComponent].
     *
     * @param componentContext Decompose context used for lifecycle management.
     * @param onOpenLogToEdit callback invoked with the identifier of the mood log to edit.
     * @param goToCreateNewLog callback invoked with the initially selected mood when creating a
     *   log.
     * @return a new [DefaultMoodLogComponent] instance.
     */
    fun create(
      @Assisted("componentContext") componentContext: ComponentContext,
      @Assisted("onOpenLogToEdit") onOpenLogToEdit: (moodId: Long) -> Unit,
      @Assisted("goToCreateNewLog") goToCreateNewLog: (selectedMood: Mood) -> Unit,
    ): DefaultMoodLogComponent
  }

  companion object {
    private const val TAG = "DefaultMoodLogComponent"
  }
}
