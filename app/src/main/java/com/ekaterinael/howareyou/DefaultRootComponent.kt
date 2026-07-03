package com.ekaterinael.howareyou

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.ekaterinael.howareyou.navigation.BottomTab
import com.ekaterinael.mood.add_edit_mood_log.presentation.DefaultAddEditMoodLogComponent
import com.ekaterinael.mood.domain.model.Mood
import com.ekaterinael.mood.domain.model.MoodLog
import com.ekaterinael.mood.mood_list.DefaultMoodLogComponent
import com.ekaterinael.mood.mood_statistic.DefaultMoodStatisticComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

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
    override val bottomTabs: List<BottomTab> by lazy { BottomTab.default }

    override fun onBottomTabSelected(tab: BottomTab) {
        when (tab) {
            BottomTab.MoodLog -> {
                navigation.bringToFront(Config.MoodLogList)
            }

            BottomTab.Statistic -> {
                navigation.bringToFront(Config.MoodLogStatistic)
            }
        }
    }

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            Config.MoodLogList -> createMoodLogChild(componentContext)
            Config.MoodLogStatistic -> createMoodLogStatisticChild(componentContext)

            is Config.AddMoodLog -> createAddMoodLogChild(componentContext, config)
            is Config.EditMoodLog -> createEditMoodLogChild(componentContext, config)
        }
    }

    private fun createMoodLogStatisticChild(componentContext: ComponentContext): RootComponent.Child.MoodStatistic {
        val component = moodStatisticComponentFactory.create(componentContext = componentContext)
        return RootComponent.Child.MoodStatistic(component)
    }

    private fun createMoodLogChild(componentContext: ComponentContext): RootComponent.Child.MoodLog {
        val component = moodLogComponentFactory.create(
            componentContext = componentContext,
            onOpenLogToEdit = { moodLogId ->
                navigation.push(Config.EditMoodLog(moodId = moodLogId))
            },
            goToCreateNewLog = { selectedMood ->
                navigation.push(
                    Config.AddMoodLog(mood = selectedMood)
                )
            }
        )
        return RootComponent.Child.MoodLog(component)
    }

    private fun createAddMoodLogChild(
        componentContext: ComponentContext,
        config: Config.AddMoodLog
    ): RootComponent.Child.AddEditMoodLog {
        return createAddEditMoodLogChild(
            componentContext = componentContext,
            moodLog = MoodLog(mood = config.mood)
        )
    }

    private fun createEditMoodLogChild(
        componentContext: ComponentContext,
        config: Config.EditMoodLog
    ): RootComponent.Child.AddEditMoodLog {
        return createAddEditMoodLogChild(
            componentContext = componentContext,
            moodLog = MoodLog(id = config.moodId)
        )
    }

    private fun createAddEditMoodLogChild(
        componentContext: ComponentContext,
        moodLog: MoodLog
    ): RootComponent.Child.AddEditMoodLog {
        val component = addEditMoodLogComponentFactory.create(
            componentContext = componentContext,
            moodLog = moodLog,
            onGoBackCallback = { navigation.pop() }
        )

        return RootComponent.Child.AddEditMoodLog(component)
    }


    sealed interface Config : Parcelable {
        @Parcelize
        data object MoodLogList : Config

        @Parcelize
        data object MoodLogStatistic : Config

        @Parcelize
        data class AddMoodLog(val mood: Mood) : Config

        @Parcelize
        data class EditMoodLog(val moodId: Long) : Config
        /*
               @Parcelize
               data class AddEditMoodLog(val moodLog: MoodLog): Config*/
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}