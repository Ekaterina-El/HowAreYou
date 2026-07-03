package com.ekaterinael.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.ekaterinael.data.BuildConfig
import com.ekaterinael.data.local.dao.MoodLogDao
import com.ekaterinael.data.local.entity.MoodLogEntity
import com.ekaterinael.data.local.typeConverter.DateTypeConverter

@Database(
    entities = [MoodLogEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val moodLogDao: MoodLogDao

    companion object {
        private const val APP_DATABASE_NAME = "mood_log_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            INSTANCE?.run { return this }

            synchronized(this) {
                INSTANCE?.run { return this }
                val instance = Room.databaseBuilder(
                    context = context,
                    name = APP_DATABASE_NAME,
                    klass = AppDatabase::class.java
                )
                    .addCallback(databaseCallback)
                    .build()

                INSTANCE = instance
                return instance
            }
        }

        private val databaseCallback = object : Callback() {
            override fun onCreate(connection: SQLiteConnection) {
                super.onCreate(connection)
                if (BuildConfig.DEBUG) mockLogs(connection)
            }
        }

        private fun mockLogs(connection: SQLiteConnection) {
            MockData.mockLogs.forEach {
                connection.execSQL(
                    "INSERT INTO mood_log(date, description, mood) " +
                            "VALUES(" +
                            "${it.date?.time}, " +
                            "'${it.description}', " +
                            "${it.mood}" +
                            ");"
                )
            }
        }
    }
}