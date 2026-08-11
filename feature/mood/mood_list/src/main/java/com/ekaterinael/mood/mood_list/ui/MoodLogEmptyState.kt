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
package com.ekaterinael.mood.mood_list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekaterinael.mood.mood_list.R
import com.ekaterinael.ui.R as UiR
import com.ekaterinael.ui.theme.HowAreYouTheme

@Composable
fun MoodLogEmptyState(modifier: Modifier = Modifier) {
  Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
    Icon(
      modifier = Modifier.size(100.dp),
      painter = painterResource(UiR.drawable.empty),
      tint = MaterialTheme.colorScheme.onSurface,
      contentDescription = null,
    )

    Text(
      modifier = Modifier.padding(top = 12.dp),
      text = stringResource(R.string.mood_log_empty_month_description),
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
  }
}

@Preview
@Composable
private fun PreviewMoodLogEmptyState() {
  HowAreYouTheme { MoodLogEmptyState(modifier = Modifier.fillMaxWidth()) }
}
