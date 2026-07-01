package com.ekaterinael.mood.mood_list.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekaterinael.mood.core.MoodUI
import com.ekaterinael.mood.mood_list.MoodListItemUI
import com.ekaterinael.ui.helper.rememberFormatedDate
import com.ekaterinael.ui.theme.HowAreYouTheme
import com.ekaterinael.ui.viewGroups.Container
import java.util.Calendar

@Composable
fun MoodLogItem(
    modifier: Modifier = Modifier,
    moodLog: MoodListItemUI,
    onSelect: () -> Unit = {}
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 12.dp, bottom = 5.dp),
            text = rememberFormatedDate(moodLog.date),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        Container(
            modifier = Modifier.background(moodLog.mood.color),
            onSelect = onSelect
        ) {
            Row(modifier = Modifier
                .padding(start = 4.dp)
                .clip(MaterialTheme.shapes.small)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(moodLog.mood.imageResId),
                            contentDescription = null,
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = stringResource(moodLog.mood.titleResId),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }

                    Spacer(Modifier.height(5.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = moodLog.description,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 3,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MoodLogItemPreview() {
    HowAreYouTheme(darkTheme = false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp)
        ) {
            MoodLogItem(
                modifier = Modifier.fillMaxWidth(),
                moodLog = MoodListItemUI(
                    id = 1,
                    date = Calendar.getInstance().time,
                    description = "Поездка в аквопарк в Екатеринбурге выдалась в хороший солнечный день",
                    mood = MoodUI.Great
                )
            )

        }
    }
}