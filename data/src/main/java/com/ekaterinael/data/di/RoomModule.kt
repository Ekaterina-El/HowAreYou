package com.ekaterinael.data.di

import android.content.Context
import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.local.dao.MoodLogDao
import com.ekaterinael.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module
interface RoomModule {
    companion object {
        @[Provides AppScope]
        fun providesMoodLogDao(db: AppDatabase): MoodLogDao = db.moodLogDto

        @[Provides AppScope]
        fun providesAppDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context = context)
    }
}