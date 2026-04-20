package com.ekaterinael.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ekaterinael.data.local.dao.MoodLogDao
import com.ekaterinael.data.local.entity.MoodLogEntity
import com.ekaterinael.data.local.typeConverter.DateTypeConverter

@Database(
    entities = [MoodLogEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val moodLogDto: MoodLogDao

    companion object {
        private const val APP_DATABASE_NAME = "mood_log_db"

        private val INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            INSTANCE?.run { return this }

            synchronized(this) {
                INSTANCE?.run { return this }
                return Room.databaseBuilder(
                    context = context,
                    name = APP_DATABASE_NAME,
                    klass = AppDatabase::class.java
                ).build()
            }
        }
    }
}