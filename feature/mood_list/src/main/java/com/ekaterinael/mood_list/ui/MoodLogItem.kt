package com.ekaterinael.mood_list.ui

import android.icu.util.Calendar
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
import com.ekaterinael.core.dateUserString
import com.ekaterinael.core.ui.Container
import com.ekaterinael.core.ui.helper.getLocale
import com.ekaterinael.core.ui.theme.HowAreYouTheme
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.domain.model.MoodLogDTO

@Composable
fun MoodLogItem(moodLog: MoodLogDTO, onSelect: () -> Unit = {}) {
    Container(onSelect) {
        Row(modifier = Modifier.fillMaxWidth()) {
            val mood = moodLog.mood

            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(mood.imageResId),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = moodLog.dateUserString(getLocale()).uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = stringResource(mood.titleResId).lowercase(),
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
            MoodLogItem(
                moodLog = MoodLogDTO(
                    id = 1,
                    date = Calendar.getInstance().time,
                    title = "",
                    description = "Поездка в аквопарк в Екатеринбурге выдалась в хороший солнечный день",
                    mood = Mood.GREAT
                ),
            )
        }
    }
}