package com.ekaterinael.mood.mood_list.ui

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
import com.ekaterinael.mood.core.MoodUI
import com.ekaterinael.mood.mood_list.MoodListItemUI
import com.ekaterinael.ui.theme.HowAreYouTheme
import java.util.Calendar

@Composable
fun MoodLogsList(
    logs: List<MoodListItemUI>,
    moods: List<MoodUI>,
    onClickAddNewLog: (MoodUI) -> Unit = {},
    onSelectLog: (MoodListItemUI) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            MoodLogNewState(
                modifier = Modifier.padding(10.dp),
                moods = moods,
                onSelectMood = onClickAddNewLog
            )
        }

        items(logs, key = { it.id ?: it.date ?: it.description }) { moodLog ->
            MoodLogItem(
                modifier = Modifier.fillMaxWidth(),
                moodLog = moodLog,
                onSelect = {
                    onSelectLog(moodLog)
                }
            )
        }
    }
}


@Preview
@Composable
private fun MoodLogItemPreview() {
    HowAreYouTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp)
        ) {
            MoodLogsList(
                moods = MoodUI.all,
                logs = listOf(
                    MoodListItemUI(
                        id = 1,
                        date = Calendar.getInstance().time,
                        description = "Поездка в аквопарк в Екатеринбурге выдалась в хороший солнечный день",
                        mood = MoodUI.Great
                    ),

                    MoodListItemUI(
                        id = 1,
                        date = Calendar.getInstance().time,
                        description = "Поездка в аквопарк в Екатеринбурге выдалась в хороший солнечный день",
                        mood = MoodUI.Good
                    ),

                    MoodListItemUI(
                        id = 1,
                        date = Calendar.getInstance().time,
                        description = "Поездка в аквопарк в Екатеринбурге выдалась в хороший солнечный день",
                        mood = MoodUI.Awful
                    ),

                    MoodListItemUI(
                        id = 1,
                        date = Calendar.getInstance().time,
                        description = "Поездка в аквопарк в Екатеринбурге выдалась в хороший солнечный день",
                        mood = MoodUI.SoSo
                    )
                ),
            )
        }
    }
}