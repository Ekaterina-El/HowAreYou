package com.ekaterinael.howareyou.ui

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.ekaterinael.add_edit_mood_log.DefaultAddEditMoodLogComponent
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.mode_statistic.DefaultMoodStatisticComponent
import com.ekaterinael.mood_list.DefaultMoodListComponent
import kotlinx.parcelize.Parcelize

class DefaultRootComponent(
    private val componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<Config, RootComponent.Child>> = childStack(
        navigation,
        initialConfiguration = Config.MoodLogList,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            is Config.AddEditMoodLog -> createAddEditMoodLogChild(componentContext, config)
            Config.MoodLogList -> createMoodLogChild(componentContext)
            Config.MoodLogStatistic -> createMoodLogStatisticChild(componentContext)
        }
    }

    private fun createMoodLogStatisticChild(componentContext: ComponentContext): RootComponent.Child.MoodStatistic {
        val component = DefaultMoodStatisticComponent(
            componentContext = componentContext
        )
        return RootComponent.Child.MoodStatistic(component)
    }

    private fun createMoodLogChild(componentContext: ComponentContext): RootComponent.Child.MoodLog {
        val component = DefaultMoodListComponent(
            componentContext = componentContext,
            onMoodLogSelected = { moodLog ->
                navigation.push(Config.AddEditMoodLog(moodLog = moodLog))
            },
            onAddNewLogClicked = { selectedMood ->
                navigation.push(
                    Config.AddEditMoodLog(
                        moodLog = MoodLogDTO(
                            mood = selectedMood ?: Mood.UNKNOWN
                        )
                    )
                )
            }
        )
        return RootComponent.Child.MoodLog(component)
    }

    private fun createAddEditMoodLogChild(
        componentContext: ComponentContext,
        config: Config.AddEditMoodLog
    ): RootComponent.Child.AddEditMoodLog {
        val component = DefaultAddEditMoodLogComponent(
            componentContext = componentContext,
            moodLog = config.moodLog,
            onGoBackCallback = { navigation.pop() }
        )
        return RootComponent.Child.AddEditMoodLog(component)
    }


    sealed interface Config: Parcelable {
        @Parcelize
        data object MoodLogList: Config

        @Parcelize
        data object MoodLogStatistic: Config

        @Parcelize
        data class AddEditMoodLog(val moodLog: MoodLogDTO): Config
    }
}