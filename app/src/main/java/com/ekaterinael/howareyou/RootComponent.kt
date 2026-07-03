package com.ekaterinael.howareyou

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ekaterinael.mood.add_edit_mood_log.presentation.AddEditMoodLogComponent
import com.ekaterinael.mood.mood_list.MoodLogComponent
import com.ekaterinael.mood.mood_statistic.MoodStatisticComponent
import com.ekaterinael.ui.navgiation.bottom.BottomTab

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    val bottomTabs: List<BottomTab>

    fun onBottomTabSelected(tab: BottomTab)



    sealed class Child(val selectedTab: BottomTab, val showBottomBar: Boolean = true) {
        class MoodLog(val component: MoodLogComponent): Child(selectedTab = BottomTab.MoodLog)
        class AddEditMoodLog(val component: AddEditMoodLogComponent): Child(
            selectedTab = BottomTab.MoodLog,
            showBottomBar = false
        )
        class MoodStatistic(val component: MoodStatisticComponent): Child(selectedTab = BottomTab.Statistic)
    }
}