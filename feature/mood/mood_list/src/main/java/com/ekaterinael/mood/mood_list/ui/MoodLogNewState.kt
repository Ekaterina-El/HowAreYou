package com.ekaterinael.mood.mood_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekaterinael.mood.core.MoodSelector
import com.ekaterinael.mood.core.MoodUI
import com.ekaterinael.mood.core.R
import com.ekaterinael.ui.theme.HowAreYouTheme
import com.ekaterinael.ui.viewGroups.Container

@Composable
fun MoodLogNewState(
    modifier: Modifier = Modifier,
    moods: List<MoodUI>,
    onSelectMood: (MoodUI) -> Unit
) {
    Container(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxWidth()
            .padding(16.dp)
            .then(modifier)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.how_are_you),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(14.dp))
        MoodSelector(moods = moods, onSelectMood = onSelectMood)
    }
}

@Preview
@Composable
private fun MoodLogNewStatePreview() {
    HowAreYouTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp)
        ) {
            MoodLogNewState(moods = MoodUI.all) {}
        }
    }
}