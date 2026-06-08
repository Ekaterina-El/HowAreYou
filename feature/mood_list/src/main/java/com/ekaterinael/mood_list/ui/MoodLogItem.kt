package com.ekaterinael.mood_list.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekaterinael.core.ui.Container
import com.ekaterinael.core.ui.helper.rememberFormatedDate
import com.ekaterinael.core.ui.theme.HowAreYouTheme
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.mood_list.MoodListItemUI
import java.util.Calendar

@Composable
fun MoodLogItem(moodLog: MoodListItemUI, onSelect: () -> Unit = {}) {
    Container(onSelect = onSelect) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(moodLog.moodImageResId),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = rememberFormatedDate(moodLog.date),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = stringResource(moodLog.moodTitleId).lowercase(),
                    style = MaterialTheme.typography.titleMedium,
                )

                Spacer(Modifier.height(5.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = moodLog.description,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
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
                .padding(10.dp),
        ) {
            MoodListItemUI(
                id = 1,
                date = Calendar.getInstance().time,
                description = "Поездка в аквопарк в Екатеринбурге выдалась в хороший солнечный день",
                moodTitleId = Mood.GREAT.titleResId,
                moodImageResId = Mood.GREAT.imageResId
            )
        }
    }
}