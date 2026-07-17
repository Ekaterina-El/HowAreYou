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

interface RootComponent {
  val childStack: Value<ChildStack<*, Child>>

  val bottomTabs: List<BottomTab>

  fun onBottomTabSelected(tab: BottomTab)

  sealed class Child(val selectedTab: BottomTab, val showBottomBar: Boolean = true) {
    class MoodLog(val component: MoodLogComponent) : Child(selectedTab = BottomTab.MoodLog)

    class AddEditMoodLog(val component: AddEditMoodLogComponent) :
      Child(selectedTab = BottomTab.MoodLog, showBottomBar = false)

    class MoodStatistic(val component: MoodStatisticComponent) :
      Child(selectedTab = BottomTab.Statistic)
  }
}
