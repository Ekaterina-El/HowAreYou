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
package com.ekaterinael.ui.navgiation.bottom

import com.ekaterinael.ui.R

/**
 * Represents a top-level destination displayed in the bottom navigation bar.
 *
 * @property iconResId the drawable resource used as the tab icon.
 * @property textResId the string resource used as the tab label.
 */
enum class BottomTab(val iconResId: Int, val textResId: Int) {
  /** Opens the mood log screen. */
  MoodLog(iconResId = R.drawable.home, textResId = R.string.journey),

  /** Opens the mood statistics screen. */
  Statistic(iconResId = R.drawable.chart, textResId = R.string.statistic);

  companion object {
    /** The default list of tabs displayed in the bottom navigation bar. */
    val default by lazy { listOf(MoodLog, Statistic) }
  }
}
