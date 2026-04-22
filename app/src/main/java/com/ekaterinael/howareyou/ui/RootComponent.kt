package com.ekaterinael.howareyou.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogComponent
import com.ekaterinael.mode_statistic.MoodStatisticComponent
import com.ekaterinael.mood_list.MoodLogComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>


    sealed interface Child {
        class MoodLog(val component: MoodLogComponent): Child
        class AddEditMoodLog(val component: AddEditMoodLogComponent): Child
        class MoodStatistic(val component: MoodStatisticComponent): Child
    }
}