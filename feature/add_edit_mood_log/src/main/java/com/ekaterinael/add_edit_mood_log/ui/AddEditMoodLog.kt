package com.ekaterinael.add_edit_mood_log.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogComponent
import com.ekaterinael.core.ui.theme.lightBlue
import com.ekaterinael.core.ui.theme.lightOrange
import com.ekaterinael.core.ui.theme.lightPink
import com.ekaterinael.core.ui.triangleGradient
import com.ekaterinael.domain.model.Mood

@Composable
fun AddEditMoodLog(component: AddEditMoodLogComponent) {
    val model by component.model.collectAsState()

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
        AddEditMoodForm(
            modifier = Modifier.padding(paddingValues),
            moods = Mood.all,
            selectedMood = model.mood,
            description = model.description,
            onChangeDescription = component::onChangeDescription,
            onSelectMood = component::onChangeMood,
            onSave = component::onClickSave,
            onGoBack = component::onGoBack,
        )
    }
}