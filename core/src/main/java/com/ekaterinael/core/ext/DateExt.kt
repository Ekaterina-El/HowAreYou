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
package com.ekaterinael.core.ext

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Formats this [Date] as a full, user-readable date string using the specified [locale].
 *
 * @param locale the locale used for date formatting.
 * @return the formatted date string.
 */
fun Date.toFullUserString(locale: Locale): String {
  val formatter = SimpleDateFormat("EEEE, d MMMM H:mm", locale)
  return formatter.format(this)
}

/**
 * Formats this [Date] as a short, user-readable date string using the specified [locale].
 *
 * @param locale the locale used for date formatting.
 * @return the formatted date string.
 */
fun Date.toShortUserString(locale: Locale): String {
  val formatter = SimpleDateFormat("LLLL yyyy", locale)
  return formatter.format(this).capitalizeFirstLetter()
}

/**
 * Returns whether this [Date] falls in the same calendar month and year as [other].
 *
 * @param other the date to compare against.
 * @return `true` if both dates share the same month and year.
 */
fun Date.isSameMonthAs(other: Date): Boolean {
  val calendar = Calendar.getInstance().apply { time = this@isSameMonthAs }
  val otherCalendar = Calendar.getInstance().apply { time = other }
  return calendar.get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR) &&
    calendar.get(Calendar.MONTH) == otherCalendar.get(Calendar.MONTH)
}

/**
 * Returns a new [Date] shifted by the specified number of months.
 *
 * @param months the number of months to add; negative values shift backwards.
 * @return the shifted date.
 */
fun Date.plusMonths(months: Int): Date {
  val calendar = Calendar.getInstance().apply { time = this@plusMonths }
  calendar.add(Calendar.MONTH, months)
  return calendar.time
}
