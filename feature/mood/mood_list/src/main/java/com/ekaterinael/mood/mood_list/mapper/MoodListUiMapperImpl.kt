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
package com.ekaterinael.mood.mood_list.mapper

import com.ekaterinael.mood.core.toUI
import com.ekaterinael.mood.domain.model.MoodLog
import com.ekaterinael.mood.mood_list.MoodListItemUI
import com.ekaterinael.mood.mood_list.di.MoodListScope
import javax.inject.Inject

/**
 * Default implementation of [MoodListUiMapper].
 *
 * Converts domain-layer mood log entries into models used by the mood list UI.
 */
@MoodListScope
class MoodListUiMapperImpl @Inject constructor() : MoodListUiMapper {
  override fun map(logs: List<MoodLog>): List<MoodListItemUI> =
    logs.map {
      MoodListItemUI(
        id = it.id,
        date = it.date,
        description = it.description,
        mood = it.mood.toUI(),
      )
    }
}
