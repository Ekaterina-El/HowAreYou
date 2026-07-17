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
package com.ekaterinael.mood.domain.model

/** A list describing possible user mood */
enum class Mood(val scope: Int) {
  /** Excellent condition: high energy, positive mood */
  GREAT(scope = 5),

  /** In good condition: everything is gerally fine, and my mood is stable */
  GOOD(scope = 4),

  /** Neutral state: without any strong emotions */
  SO_SO(scope = 3),

  /** Feeling under the weather: low mood, possible accompanied by fatigue or stress */
  BAD(scope = 2),

  /** Very poot condition: severe discomfort, low spirits */
  AWFUL(scope = 1),
  UNKNOWN(scope = -1);

  companion object {
    val all = entries.filterNot { it == UNKNOWN }

    fun Int.toMood() = entries.firstOrNull { it.scope == this } ?: UNKNOWN
  }
}
