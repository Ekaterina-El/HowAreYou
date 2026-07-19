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
package com.ekaterinael.mood.mood_statistic

import com.arkivanov.mvikotlin.core.store.Store

/** Defines the MVI store responsible for managing the mood statistics screen state. */
interface MoodStatisticStore : Store<Nothing, MoodStatisticStore.State, Nothing> {
  /**
   * Represents the current state of the mood statistics screen.
   *
   * @property data the mood statistics data prepared for display.
   */
  data class State(val data: MoodStatisticUI)
}
