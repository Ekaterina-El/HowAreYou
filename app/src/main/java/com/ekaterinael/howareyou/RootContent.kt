package com.ekaterinael.howareyou

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.ekaterinael.howareyou.navigation.BottomNavigationBar
import com.ekaterinael.mood.add_edit_mood_log.presentation.ui.AddEditMoodLog
import com.ekaterinael.mood.mood_list.ui.MoodLogScreen
import com.ekaterinael.mood.mood_statistic.ui.MoodStatistic
import com.ekaterinael.ui.effects.animation.slideFromBottom
import com.ekaterinael.ui.theme.HowAreYouTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RootContent(component: RootComponent) {
    val childStack by component.childStack.subscribeAsState()
    val activeChild = childStack.active.instance

    HowAreYouTheme {
        Scaffold(
            bottomBar = {
                if (activeChild.showBottomBar) {
                    BottomNavigationBar(
                        tabs = component.bottomTabs,
                        selectedTab = activeChild.selectedTab,
                        onTabSelected = component::onBottomTabSelected,
                    )
                }
            }
        ) {
            Children(
                stack = component.childStack,
                animation = stackAnimation { child ->
                    when (child.instance) {
                        is RootComponent.Child.AddEditMoodLog -> fade() + slideFromBottom()
                        else -> fade()
                    }
                }
            ) { child ->
                when (val activeChild = child.instance) {
                    is RootComponent.Child.AddEditMoodLog -> AddEditMoodLog(activeChild.component)
                    is RootComponent.Child.MoodLog -> MoodLogScreen(activeChild.component)
                    is RootComponent.Child.MoodStatistic -> MoodStatistic(activeChild.component)
                }
            }
        }
    }
}