/*
 * Copyright 2026 Ekaterina Elshina
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import com.ekaterinael.mood.add_edit_mood_log.presentation.DefaultAddEditMoodLogComponent
import com.ekaterinael.mood.domain.model.Mood
import com.ekaterinael.mood.domain.model.MoodLog
import com.ekaterinael.mood.mood_list.DefaultMoodLogComponent
import com.ekaterinael.mood.mood_statistic.DefaultMoodStatisticComponent
import com.ekaterinael.ui.navgiation.bottom.BottomTab
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

/**
 * Default implementation of [RootComponent] responsible for managing the application's root
 * navigation and creating child components.
 *
 * @param moodLogComponentFactory factory for creating the mood log component.
 * @param moodStatisticComponentFactory factory for creating the mood statistics component.
 * @param addEditMoodLogComponentFactory factory for creating the mood log editor component.
 * @param componentContext Decompose context used for lifecycle and navigation management.
 */
class DefaultRootComponent
@AssistedInject
constructor(
  private val moodLogComponentFactory: DefaultMoodLogComponent.Factory,
  private val moodStatisticComponentFactory: DefaultMoodStatisticComponent.Factory,
  private val addEditMoodLogComponentFactory: DefaultAddEditMoodLogComponent.Factory,
  @Assisted("componentContext") private val componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

  private val navigation = StackNavigation<Config>()

  override val childStack: Value<ChildStack<Config, RootComponent.Child>> =
    childStack(
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

  private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child {
    return when (config) {
      Config.MoodLogList -> createMoodLogChild(componentContext)
      Config.MoodLogStatistic -> createMoodLogStatisticChild(componentContext)
      is Config.AddMoodLog -> createAddMoodLogChild(componentContext, config)
      is Config.EditMoodLog -> createEditMoodLogChild(componentContext, config)
    }
  }

  private fun createMoodLogStatisticChild(
    componentContext: ComponentContext
  ): RootComponent.Child.MoodStatistic {
    val component = moodStatisticComponentFactory.create(componentContext = componentContext)
    return RootComponent.Child.MoodStatistic(component)
  }

  private fun createMoodLogChild(componentContext: ComponentContext): RootComponent.Child.MoodLog {
    val component =
      moodLogComponentFactory.create(
        componentContext = componentContext,
        onOpenLogToEdit = { moodLogId -> navigation.push(Config.EditMoodLog(moodId = moodLogId)) },
        goToCreateNewLog = { selectedMood ->
          navigation.push(Config.AddMoodLog(mood = selectedMood))
        },
      )
    return RootComponent.Child.MoodLog(component)
  }

  private fun createAddMoodLogChild(
    componentContext: ComponentContext,
    config: Config.AddMoodLog,
  ): RootComponent.Child.AddEditMoodLog {
    return createAddEditMoodLogChild(
      componentContext = componentContext,
      moodLog = MoodLog(mood = config.mood),
    )
  }

  private fun createEditMoodLogChild(
    componentContext: ComponentContext,
    config: Config.EditMoodLog,
  ): RootComponent.Child.AddEditMoodLog {
    return createAddEditMoodLogChild(
      componentContext = componentContext,
      moodLog = MoodLog(id = config.moodId),
    )
  }

  private fun createAddEditMoodLogChild(
    componentContext: ComponentContext,
    moodLog: MoodLog,
  ): RootComponent.Child.AddEditMoodLog {
    val component =
      addEditMoodLogComponentFactory.create(
        componentContext = componentContext,
        moodLog = moodLog,
        onGoBackCallback = { navigation.pop() },
      )

    return RootComponent.Child.AddEditMoodLog(component)
  }

  /** Defines the navigation configurations available in the root component. */
  sealed interface Config : Parcelable {
    /** Displays the list of mood log entries. */
    @Parcelize data object MoodLogList : Config

    /** Displays mood statistics. */
    @Parcelize data object MoodLogStatistic : Config

    /**
     * Opens the screen for creating a mood log entry.
     *
     * @property mood the initially selected mood.
     */
    @Parcelize data class AddMoodLog(val mood: Mood) : Config

    /**
     * Opens the screen for editing an existing mood log entry.
     *
     * @property moodId the identifier of the mood log entry to edit.
     */
    @Parcelize data class EditMoodLog(val moodId: Long) : Config
  }

  /**
   * Factory for creating [DefaultRootComponent] instances with an assisted [ComponentContext]
   * parameter.
   */
  @AssistedFactory
  interface Factory {
    /**
     * Creates a new [DefaultRootComponent].
     *
     * @param componentContext Decompose context used by the root component.
     * @return a new root component instance.
     */
    fun create(
      @Assisted("componentContext") componentContext: ComponentContext
    ): DefaultRootComponent
  }
}
