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
package com.ekaterinael.data.local.db

import android.icu.util.Calendar
import com.ekaterinael.data.local.entity.MoodLogEntity
import com.ekaterinael.data.local.entity.UserProfileEntity
import kotlin.random.Random

object MockData {
  /** Mock user profile seeded on first launch. */
  val mockProfile by lazy {
    UserProfileEntity(
      id = UserProfileEntity.PROFILE_ID,
      firstName = "Ekaterina",
      lastName = "El",
      photoUrl = null,
    )
  }

  private const val MIN_HOURS = 17
  private const val MAX_HOURS = 23
  private val HOURS_RANGE = MIN_HOURS..MAX_HOURS

  private const val MIN_MINUTES = 0
  private const val MAX_MINUTES = 59

  private val MINUTES_RANGE = MIN_MINUTES..MAX_MINUTES

  private const val MIN_MOOD_SCORE = 1
  private const val MAX_MOOD_SCORE = 5

  /** Mock mood log entries generated lazily on first access. */
  val mockLogs by lazy { generateMockLogs() }

  private val descriptions: List<String> by lazy {
    listOf(
      "Woke up in a good mood today, got ready quickly and had time for a calm breakfast. It " +
        "feels like the day started off right and I'll be able to get a lot done without rushing.",
      "The day went by fairly evenly, without any strong feelings or unpleasant events. In the " +
        "evening I managed to rest a bit, tidy up around the house, and feel like everything's " +
        "under control.",
      "There weren't any bright emotions today, but that's more of a plus. After a walk it got " +
        "easier to gather my thoughts, and clarity and a wish to calmly finish things came along.",
      "There were a lot of thoughts about deadlines and tasks, which made it hard to focus on " +
        "any one thing. I tried to sort things by priority, but the tension stuck around anyway.",
      "By evening I felt pretty tired and a bit sad for no particular reason. It seems like a " +
        "lot of small worries have piled up and became especially noticeable today.",
      "Managed to have a productive day and discuss important things without unnecessary " +
        "tension. It was especially nice to calmly explain my ideas and get some support.",
      "An ordinary day with no notable ups or downs. Got the basic things done, put the phone " +
        "down for a bit, and spent the evening at a calm pace without feeling overloaded.",
      "Managed to close out a task today that had been hanging over me and causing irritation. " +
        "Afterward came a feeling of relief, confidence, and a wish to keep moving forward.",
      "The day was a bit restless because I had to keep too many plans in my head at once. I " +
        "want to slow down and pick just the most important thing for tomorrow.",
      "After a walk it got noticeably easier, my mood evened out, and my thoughts stopped " +
        "circling around work tasks. Just changing my surroundings and getting some fresh air " +
        "really helped.",
    )
  }

  private fun generateMockLogs(): List<MoodLogEntity> {
    val currentDate = Calendar.getInstance()

    val logs = mutableListOf<MoodLogEntity>()

    descriptions.forEach {
      currentDate.add(Calendar.DAY_OF_YEAR, -1)

      val hour = HOURS_RANGE.random()
      val minute = MINUTES_RANGE.random()
      currentDate.set(Calendar.HOUR_OF_DAY, hour)
      currentDate.set(Calendar.MINUTE, minute)
      currentDate.set(Calendar.SECOND, 0)
      currentDate.set(Calendar.MILLISECOND, 0)

      logs.add(
        MoodLogEntity(
          id = null,
          date = currentDate.time,
          description = it,
          mood = Random.nextInt(MIN_MOOD_SCORE, MAX_MOOD_SCORE + 1),
        )
      )
    }
    return logs
  }
}
