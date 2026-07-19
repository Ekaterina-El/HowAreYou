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
package com.ekaterinael.data.local.typeConverter

import androidx.room.TypeConverter
import java.util.Date

/** Converts between [Date] objects and timestamp values supported by Room. */
class DateTypeConverter {
  /**
   * Converts the specified timestamp to a [Date].
   *
   * @param value the timestamp in milliseconds, or `null`.
   * @return the corresponding date, or `null` if [value] is `null`.
   */
  @TypeConverter fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

  /**
   * Converts the specified [date] to a timestamp.
   *
   * @param date the date to convert, or `null`.
   * @return the date timestamp in milliseconds, or `null` if [date] is `null`.
   */
  @TypeConverter fun dateToTimestamp(date: Date?) = date?.time
}
