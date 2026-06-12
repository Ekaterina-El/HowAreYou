package com.ekaterinael.howareyou

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ekaterinael.mood.add_edit_mood_log.presentation.AddEditMoodLogComponent
import com.ekaterinael.mood.mood_list.MoodLogComponent
import com.ekaterinael.mood.mood_statistic.MoodStatisticComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>


    sealed interface Child {
        class MoodLog(val component: MoodLogComponent): Child
        class AddEditMoodLog(val component: AddEditMoodLogComponent): Child
        class MoodStatistic(val component: MoodStatisticComponent): Child
    }
}