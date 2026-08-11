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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekaterinael.mood.add_edit_mood_log.R
import com.ekaterinael.mood.core.R as CoreR
import com.ekaterinael.mood.core.model.MoodUI
import com.ekaterinael.mood.core.ui.MoodSelector
import com.ekaterinael.ui.buttons.AppButton
import com.ekaterinael.ui.navgiation.GoBackButton
import com.ekaterinael.ui.theme.HowAreYouTheme

@Composable
internal fun AddEditMoodForm(
  modifier: Modifier = Modifier,
  moods: List<MoodUI>,
  selectedMood: MoodUI?,
  description: String,
  onChangeDescription: (String) -> Unit = {},
  onGoBack: () -> Unit = {},
  onSelectMood: (MoodUI) -> Unit = {},
  onSave: () -> Unit = {},
) {
  Column(modifier = modifier.padding(horizontal = 16.dp)) {
    GoBackButton(onGoBack)

    Spacer(Modifier.weight(1.5f))

    Text(
      modifier = Modifier.align(Alignment.CenterHorizontally),
      text = stringResource(CoreR.string.how_are_you),
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.displaySmall,
      color = MaterialTheme.colorScheme.primary,
    )

    Spacer(Modifier.height(30.dp))

    MoodSelector(moods = moods, selectedMood = selectedMood, onSelectMood = onSelectMood)

    Spacer(Modifier.height(30.dp))

    OutlinedTextField(
      modifier =
        Modifier.fillMaxWidth()
          .weight(3f)
          .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp)),
      shape = RoundedCornerShape(10.dp),
      colors =
        OutlinedTextFieldDefaults.colors(
          unfocusedContainerColor = MaterialTheme.colorScheme.background,
          focusedContainerColor = MaterialTheme.colorScheme.background,
          unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
          focusedTextColor = MaterialTheme.colorScheme.onBackground,
          unfocusedBorderColor = Color.Transparent,
          focusedBorderColor = Color.Transparent,
        ),
      value = description,
      onValueChange = onChangeDescription,
      placeholder = {
        Text(
          text = stringResource(R.string.describe_your_day),
          style = MaterialTheme.typography.titleMedium,
        )
      },
    )

    Spacer(Modifier.weight(1f))

    AppButton(
      text = stringResource(CoreR.string.save),
      onClick = onSave,
      containerColor = MaterialTheme.colorScheme.background,
      textColor = MaterialTheme.colorScheme.onBackground,
    )
  }
}

@Preview
@Composable
private fun AddEditMoodFormPreview() {
  HowAreYouTheme { AddEditMoodForm(moods = MoodUI.all, selectedMood = null, description = "") }
}
