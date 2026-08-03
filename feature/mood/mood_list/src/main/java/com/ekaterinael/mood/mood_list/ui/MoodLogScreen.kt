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
package com.ekaterinael.mood.mood_list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ekaterinael.mood.mood_list.MoodLogComponent
import com.ekaterinael.ui.helper.getLocale
import com.ekaterinael.ui.navgiation.TopBarWithSearch

@Composable
fun MoodLogScreen(modifier: Modifier = Modifier, component: MoodLogComponent) {
  val model by component.model.collectAsState()

  Column(modifier = modifier.fillMaxSize().padding(horizontal = 12.dp)) {
    TopBarWithSearch(
      title = model.selectedMonthUserString(getLocale()),
      modifier = Modifier.padding(top = 20.dp, bottom = 14.dp),
      onClickForward = {},
      onClickSearch = {},
    )
    MoodLogsList(
      logs = model.logs,
      moods = model.moods,
      onClickAddNewLog = component::onClickAddNewLog,
      onSelectLog = component::onClickByLog,
    )
  }
}
