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
package com.ekaterinael.mood.add_edit_mood_log.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ekaterinael.mood.add_edit_mood_log.presentation.AddEditMoodLogComponent
import com.ekaterinael.mood.add_edit_mood_log.presentation.AddEditMoodLogStore
import com.ekaterinael.ui.effects.gradient.triangleGradient
import com.ekaterinael.ui.theme.lightBlue
import com.ekaterinael.ui.theme.lightOrange
import com.ekaterinael.ui.theme.lightPink

@Composable
fun AddEditMoodLog(component: AddEditMoodLogComponent) {
  val state by component.state.collectAsStateWithLifecycle()

  Scaffold(
    modifier =
      Modifier.fillMaxSize().drawBehind {
        triangleGradient(colorTop = lightBlue, colorLeft = lightPink, colorRight = lightOrange)
      },
    containerColor = Color.Transparent,
  ) { paddingValues ->
    when (val state = state) {
      AddEditMoodLogStore.State.Initial,
      AddEditMoodLogStore.State.Loading,
      AddEditMoodLogStore.State.LoadingException -> {
        // TODO: add view
      }
      is AddEditMoodLogStore.State.Editing -> {
        AddEditMoodForm(
          modifier = Modifier.padding(paddingValues),
          moods = state.moods,
          selectedMood = state.selectedMood,
          description = state.description,
          onChangeDescription = component::onChangeDescription,
          onSelectMood = component::onChangeMood,
          onSave = component::onClickSave,
          onGoBack = component::onGoBack,
        )
      }
    }
  }
}
