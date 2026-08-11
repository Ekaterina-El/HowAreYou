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
package com.ekaterinael.mood.core.model

import androidx.compose.ui.graphics.Color
import com.ekaterinael.mood.core.R
import com.ekaterinael.mood.core.ext.toUI
import com.ekaterinael.mood.domain.model.Mood
import com.ekaterinael.ui.theme.AwfulMood
import com.ekaterinael.ui.theme.BadMood
import com.ekaterinael.ui.theme.GoodMood
import com.ekaterinael.ui.theme.GreatMood
import com.ekaterinael.ui.theme.SoSoMood
import com.ekaterinael.ui.theme.UnknownMood

/**
 * Represents a mood state prepared for display in the user interface.
 *
 * @property mood the corresponding domain-layer mood value.
 * @property color the color associated with the mood.
 * @property imageResId the resource identifier of the mood image.
 * @property titleResId the resource identifier of the localized mood title.
 */
sealed class MoodUI(val mood: Mood, val color: Color, val imageResId: Int, val titleResId: Int) {
  data object Great :
    MoodUI(
      mood = Mood.GREAT,
      color = GreatMood,
      imageResId = R.drawable.mood_great,
      titleResId = R.string.great_mood,
    )

  data object Good :
    MoodUI(
      mood = Mood.GOOD,
      color = GoodMood,
      imageResId = R.drawable.mood_good,
      titleResId = R.string.good_mood,
    )

  data object SoSo :
    MoodUI(
      mood = Mood.SO_SO,
      color = SoSoMood,
      imageResId = R.drawable.mood_so_so,
      titleResId = R.string.so_so_mood,
    )

  data object Bad :
    MoodUI(
      mood = Mood.BAD,
      color = BadMood,
      imageResId = R.drawable.mood_bad,
      titleResId = R.string.bad_mood,
    )

  data object Awful :
    MoodUI(
      mood = Mood.AWFUL,
      color = AwfulMood,
      imageResId = R.drawable.mood_awful,
      titleResId = R.string.awful_mood,
    )

  data object Unknown :
    MoodUI(
      mood = Mood.UNKNOWN,
      color = UnknownMood,
      imageResId = R.drawable.mood_awful,
      titleResId = R.string.unknown_mood,
    )

  companion object {
    /**
     * All available mood states converted to their UI representations.
     *
     * The list is initialized lazily on first access.
     */
    val all by lazy { Mood.all.map { it.toUI() } }
  }
}
