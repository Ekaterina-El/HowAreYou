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

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ekaterinael.mood.add_edit_mood_log.presentation.AddEditMoodLogComponent
import com.ekaterinael.mood.mood_list.MoodLogComponent
import com.ekaterinael.mood.mood_statistic.MoodStatisticComponent
import com.ekaterinael.ui.navgiation.bottom.BottomTab

/**
 * Represents the root component of the application.
 *
 * Defines the main navigation state and exposes child components associated with the application's
 * top-level screens.
 */
interface RootComponent {
  /** The current stack of child components. */
  val childStack: Value<ChildStack<*, Child>>

  /** The list of tabs displayed in the bottom navigation bar. */
  val bottomTabs: List<BottomTab>

  /**
   * Selects the specified bottom navigation [tab].
   *
   * @param tab the tab to navigate to.
   */
  fun onBottomTabSelected(tab: BottomTab)

  /**
   * Represents a child component displayed by the root component.
   *
   * @property selectedTab the bottom navigation tab associated with this child.
   * @property showBottomBar whether the bottom navigation bar should be visible.
   */
  sealed class Child(val selectedTab: BottomTab, val showBottomBar: Boolean = true) {
    /**
     * Displays the mood log list.
     *
     * @property component the mood log component.
     */
    class MoodLog(val component: MoodLogComponent) : Child(selectedTab = BottomTab.MoodLog)

    /**
     * Displays the screen for adding or editing a mood log entry.
     *
     * @property component the add or edit mood log component.
     */
    class AddEditMoodLog(val component: AddEditMoodLogComponent) :
      Child(selectedTab = BottomTab.MoodLog, showBottomBar = false)

    /**
     * Displays mood statistics.
     *
     * @property component the mood statistics component.
     */
    class MoodStatistic(val component: MoodStatisticComponent) :
      Child(selectedTab = BottomTab.Statistic)
  }
}
