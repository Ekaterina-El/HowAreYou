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
package com.ekaterinael.mood.data.mapper

import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.local.entity.MoodLogEntity
import com.ekaterinael.data.mapper.Mapper
import com.ekaterinael.mood.domain.model.Mood.Companion.toMood
import com.ekaterinael.mood.domain.model.MoodLog
import javax.inject.Inject

/** Maps mood log objects between the domain and database representations. */
@AppScope
class MoodLogMapper @Inject constructor() : Mapper<MoodLog, MoodLogEntity> {
  override fun fromDTO(input: MoodLog) =
    MoodLogEntity(
      id = input.id,
      date = input.date,
      description = input.description,
      mood = input.mood.scope,
    )

  override fun toDTO(input: MoodLogEntity) =
    MoodLog(
      id = input.id,
      date = input.date,
      description = input.description,
      mood = input.mood.toMood(),
    )

  override fun toDTO(list: List<MoodLogEntity>) = list.map(::toDTO)
}
