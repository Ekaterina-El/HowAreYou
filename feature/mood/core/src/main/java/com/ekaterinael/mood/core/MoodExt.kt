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
package com.ekaterinael.mood.core

import com.ekaterinael.mood.domain.model.Mood

fun Mood.toUI(): MoodUI {
  return when (this) {
    Mood.GREAT -> MoodUI.Great
    Mood.GOOD -> MoodUI.Good
    Mood.SO_SO -> MoodUI.SoSo
    Mood.BAD -> MoodUI.Bag
    Mood.AWFUL -> MoodUI.Awful
    Mood.UNKNOWN -> MoodUI.Unknown
  }
}
