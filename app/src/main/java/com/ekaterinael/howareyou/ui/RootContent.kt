package com.ekaterinael.howareyou.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.ekaterinael.add_edit_mood_log.ui.AddEditMoodLog
import com.ekaterinael.howareyou.ui.theme.HowAreYouTheme
import com.ekaterinael.mode_statistic.ui.MoodStatistic
import com.ekaterinael.mood_list.ui.MoodLog

@Composable
fun RootContent(component: RootComponent) {
    HowAreYouTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                Children(stack = component.childStack) {
                    when (val child = it.instance) {
                        is RootComponent.Child.AddEditMoodLog -> AddEditMoodLog(child.component)
                        is RootComponent.Child.MoodLog -> MoodLog(child.component)
                        is RootComponent.Child.MoodStatistic -> MoodStatistic(child.component)
                    }
                }
            }
        }
    }
}