package com.ekaterinael.howareyou

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.ekaterinael.add_edit_mood_log.ui.AddEditMoodLog
import com.ekaterinael.core.ui.theme.HowAreYouTheme
import com.ekaterinael.mode_statistic.ui.MoodStatistic
import com.ekaterinael.mood_list.ui.MoodLogScreen

@Composable
fun RootContent(component: RootComponent) {
    HowAreYouTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Children(stack = component.childStack) {
                when (val child = it.instance) {
                    is RootComponent.Child.AddEditMoodLog -> AddEditMoodLog(child.component)
                    is RootComponent.Child.MoodLog -> MoodLogScreen(child.component)
                    is RootComponent.Child.MoodStatistic -> MoodStatistic(child.component)
                }
            }
        }
    }
}