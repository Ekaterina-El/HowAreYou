package com.ekaterinael.mood_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekaterinael.core.ui.theme.HowAreYouTheme
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.mood_list.MoodListItemUI
import java.util.Calendar

@Composable
fun MoodLogsList(
    logs: List<MoodListItemUI>,
    onClickAddNewLog: (Mood) -> Unit = {},
    onSelectLog: (MoodListItemUI) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            MoodLogNewState(
                modifier = Modifier.padding(10.dp),
                onSelectMood = onClickAddNewLog
            )
        }

        items(logs, key = { it.id ?: it.date }) { moodLog ->
            MoodLogItem(moodLog = moodLog, onSelect = {
                onSelectLog(moodLog)
            })
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
                logs = listOf(
                    MoodListItemUI(
                        id = 1,
                        date = Calendar.getInstance().time,
                        description = "Поездка в аквопарк в Екатеринбурге выдалась в хороший солнечный день",
                        moodTitleId = Mood.GREAT.titleResId,
                        moodImageResId = Mood.GREAT.imageResId
                    ),

                    MoodListItemUI(
                        id = 1,
                        date = Calendar.getInstance().time,
                        description = "Поездка в аквопарк в Екатеринбурге выдалась в хороший солнечный день",
                        moodTitleId = Mood.GOOD.titleResId,
                        moodImageResId = Mood.GOOD.imageResId
                    ),

                    MoodListItemUI(
                        id = 1,
                        date = Calendar.getInstance().time,
                        description = "Поездка в аквопарк в Екатеринбурге выдалась в хороший солнечный день",
                        moodTitleId = Mood.AWFUL.titleResId,
                        moodImageResId = Mood.AWFUL.imageResId
                    ),

                    MoodListItemUI(
                        id = 1,
                        date = Calendar.getInstance().time,
                        description = "Поездка в аквопарк в Екатеринбурге выдалась в хороший солнечный день",
                        moodTitleId = Mood.SO_SO.titleResId,
                        moodImageResId = Mood.SO_SO.imageResId
                    )
                ),
            )
        }
    }
}