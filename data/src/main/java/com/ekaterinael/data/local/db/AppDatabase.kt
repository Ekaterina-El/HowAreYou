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

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.SQLiteConnection
import com.ekaterinael.data.BuildConfig
import com.ekaterinael.data.local.dao.MoodLogDao
import com.ekaterinael.data.local.entity.MoodLogEntity
import com.ekaterinael.data.local.typeConverter.DateTypeConverter

/** Application database that provides access to the available Room DAOs. */
@Database(entities = [MoodLogEntity::class], version = 1, exportSchema = true)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
  /** Provides access to mood log database operations. */
  abstract val moodLogDao: MoodLogDao

  companion object {
    private const val APP_DATABASE_NAME = "mood_log_db"
    private const val DESCRIPTION_INDEX = 2
    private const val MOOD_INDEX = 3

    @Volatile private var INSTANCE: AppDatabase? = null

    private val databaseCallback =
      object : Callback() {
        override fun onCreate(connection: SQLiteConnection) {
          super.onCreate(connection)
          if (BuildConfig.DEBUG) mockLogs(connection)
        }
      }

    /**
     * Returns the application database instance.
     *
     * @param context the context used to initialize the database.
     * @return the shared [AppDatabase] instance.
     */
    fun getInstance(context: Context): AppDatabase {
      return INSTANCE
        ?: synchronized(this) {
          INSTANCE
            ?: Room.databaseBuilder(
                context = context,
                name = APP_DATABASE_NAME,
                klass = AppDatabase::class.java,
              )
              .addCallback(databaseCallback)
              .build()
              .also { INSTANCE = it }
        }
    }

    private fun mockLogs(connection: SQLiteConnection) {
      MockData.mockLogs.forEach { log ->
        connection.prepare("INSERT INTO mood_log(date, description, mood) VALUES (?, ?, ?)").use {
          statement ->
          val date = log.date?.time
          if (date != null) statement.bindLong(1, date) else statement.bindNull(1)
          statement.bindText(DESCRIPTION_INDEX, log.description)
          statement.bindLong(MOOD_INDEX, log.mood.toLong())
          statement.step()
        }
      }
    }
  }
}
