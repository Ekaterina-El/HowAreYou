package com.ekaterinael.add_edit_mood_log.ui

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
import com.ekaterinael.core.ui.buttons.AppButton
import com.ekaterinael.core.ui.mood.MoodSelector
import com.ekaterinael.core.ui.navgiation.GoBackButton
import com.ekaterinael.core.ui.theme.HowAreYouTheme
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.resources.R

@Composable
internal fun AddEditMoodForm(
    modifier: Modifier = Modifier,
    moods: List<Mood>,
    selectedMood: Mood?,
    description: String,
    onChangeDescription: (String) -> Unit = {},
    onGoBack: () -> Unit = {},
    onSelectMood: (Mood) -> Unit = {},
    onSave: () -> Unit = {}
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        GoBackButton(onGoBack)

        Spacer(Modifier.weight(1.5f))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.how_are_you),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        Spacer(Modifier.height(30.dp))

        MoodSelector(
            moods = moods,
            selectedMood = selectedMood,
            onSelectMood = onSelectMood
        )

        Spacer(Modifier.height(30.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(10.dp),
                ),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            ),
            value = description,
            onValueChange = onChangeDescription,
            placeholder = {
                Text(
                    text = stringResource(R.string.describe_your_day),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        )

        Spacer(Modifier.weight(1f))

        AppButton(
            text = stringResource(R.string.save),
            onClick = onSave,
            containerColor = MaterialTheme.colorScheme.background,
            textColor = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
private fun AddEditMoodFormPreview() {
    HowAreYouTheme {
        AddEditMoodForm(moods = Mood.all, selectedMood = null, description = "")
    }
}