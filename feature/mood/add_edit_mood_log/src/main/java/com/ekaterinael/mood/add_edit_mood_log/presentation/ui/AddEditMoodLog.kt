package com.ekaterinael.mood.add_edit_mood_log.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ekaterinael.mood.add_edit_mood_log.presentation.AddEditMoodLogComponent
import com.ekaterinael.mood.add_edit_mood_log.presentation.AddEditMoodLogStore
import com.ekaterinael.ui.effects.gradient.triangleGradient
import com.ekaterinael.ui.theme.lightBlue
import com.ekaterinael.ui.theme.lightOrange
import com.ekaterinael.ui.theme.lightPink

@Composable
fun AddEditMoodLog(component: AddEditMoodLogComponent) {
    val state by component.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                triangleGradient(
                    colorTop = lightBlue,
                    colorLeft = lightPink,
                    colorRight = lightOrange,
                )
            },
        containerColor = Color.Transparent
    ) { paddingValues ->

        when (val state = state) {
            AddEditMoodLogStore.State.Initial,
            AddEditMoodLogStore.State.Loading,
            AddEditMoodLogStore.State.LoadingException -> {
                // TODO: add view
            }

            is AddEditMoodLogStore.State.Editing -> {
                AddEditMoodForm(
                    modifier = Modifier.padding(paddingValues),
                    moods = state.moods,
                    selectedMood = state.selectedMood,
                    description = state.description,
                    onChangeDescription = component::onChangeDescription,
                    onSelectMood = component::onChangeMood,
                    onSave = component::onClickSave,
                    onGoBack = component::onGoBack,
                )
            }
        }
    }
}