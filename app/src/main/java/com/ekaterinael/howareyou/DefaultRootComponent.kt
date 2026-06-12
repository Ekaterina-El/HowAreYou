package com.ekaterinael.howareyou

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.ekaterinael.mood.add_edit_mood_log.presentation.DefaultAddEditMoodLogComponent
import com.ekaterinael.mood.domain.model.Mood
import com.ekaterinael.mood.domain.model.MoodLog
import com.ekaterinael.mood.mood_list.DefaultMoodLogComponent
import com.ekaterinael.mood.mood_statistic.DefaultMoodStatisticComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize
import java.util.Calendar

class DefaultRootComponent @AssistedInject constructor(
    private val moodLogComponentFactory: DefaultMoodLogComponent.Factory,
    private val moodStatisticComponentFactory: DefaultMoodStatisticComponent.Factory,
    private val addEditMoodLogComponentFactory: DefaultAddEditMoodLogComponent.Factory,
    @Assisted("componentContext") private val componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<Config, RootComponent.Child>> = childStack(
        source = navigation,
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
        val component = moodStatisticComponentFactory.create(componentContext = componentContext)
        return RootComponent.Child.MoodStatistic(component)
    }

    private fun createMoodLogChild(componentContext: ComponentContext): RootComponent.Child.MoodLog {
        val component = moodLogComponentFactory.create(
            componentContext = componentContext,
            onOpenLogToEdit = { moodLog ->
                navigation.push(Config.AddEditMoodLog(moodLog = moodLog))
            },
            goToCreateNewLog = { selectedMood ->
                navigation.push(
                    Config.AddEditMoodLog(
                        moodLog = MoodLog(
                            mood = selectedMood ?: Mood.UNKNOWN,
                            date = Calendar.getInstance().time
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
        val component = addEditMoodLogComponentFactory.create(
            componentContext = componentContext,
            moodLog = config.moodLog,
            onGoBackCallback = {
                navigation.pop()
            }
        )
        return RootComponent.Child.AddEditMoodLog(component)
    }


    sealed interface Config: Parcelable {
        @Parcelize
        data object MoodLogList: Config

        @Parcelize
        data object MoodLogStatistic: Config

        @Parcelize
        data class AddEditMoodLog(val moodLog: MoodLog): Config
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}