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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekaterinael.mood.core.model.MoodUI
import com.ekaterinael.mood.mood_list.MoodListItemUI
import com.ekaterinael.ui.theme.HowAreYouTheme
import java.util.Calendar

@Composable
fun MoodLogsList(
  logs: List<MoodListItemUI>,
  moods: List<MoodUI>,
  showAddNewLogWidget: Boolean,
  onClickAddNewLog: (MoodUI) -> Unit = {},
  onSelectLog: (logId: Long?) -> Unit = {},
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
    contentPadding = PaddingValues(bottom = 16.dp),
    verticalArrangement = Arrangement.spacedBy(15.dp),
  ) {
    val isEmpty = logs.isEmpty()
    item {
      AnimatedVisibility(
        visible = showAddNewLogWidget,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
      ) {
        MoodLogNewState(moods = moods, onSelectMood = onClickAddNewLog)
      }
    }

    if (isEmpty) {
      item { MoodLogEmptyState(modifier = Modifier.fillMaxWidth()) }
    }

    items(logs, key = { it.id ?: it.date ?: it.description }) { moodLog ->
      MoodLogItem(
        modifier = Modifier.fillMaxWidth().animateItem(),
        moodLog = moodLog,
        onSelect = { onSelectLog(moodLog.id) },
      )
    }

    if (!isEmpty) item { MoodLogListEnd(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) }
  }
}

@Preview
@Composable
private fun MoodLogItemPreview() {
  HowAreYouTheme(darkTheme = true) {
    Box(
      modifier =
        Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(10.dp)
    ) {
      val mockText = "Поездка в аквопарк в Екатеринбурге выдалась в хороший солнечный день"
      MoodLogsList(
        moods = MoodUI.all,
        showAddNewLogWidget = true,
        logs =
          listOf(
            MoodListItemUI(
              id = 1,
              date = Calendar.getInstance().time,
              description = mockText,
              mood = MoodUI.Great,
            ),
            MoodListItemUI(
              id = 1,
              date = Calendar.getInstance().time,
              description = mockText,
              mood = MoodUI.Good,
            ),
            MoodListItemUI(
              id = 1,
              date = Calendar.getInstance().time,
              description = mockText,
              mood = MoodUI.Awful,
            ),
            MoodListItemUI(
              id = 1,
              date = Calendar.getInstance().time,
              description = mockText,
              mood = MoodUI.SoSo,
            ),
          ),
      )
    }
  }
}

@Preview
@Composable
private fun MoodLogsListEmptyPreview() {
  HowAreYouTheme(darkTheme = true) {
    Box(
      modifier =
        Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(10.dp)
    ) {
      MoodLogsList(moods = MoodUI.all, showAddNewLogWidget = true, logs = emptyList())
    }
  }
}
