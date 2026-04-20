package com.ekaterinael.data.di

import android.content.Context
import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.local.dao.MoodLogDao
import com.ekaterinael.data.local.db.AppDatabase
import com.ekaterinael.data.local.entity.MoodLogEntity
import com.ekaterinael.data.mapper.Mapper
import com.ekaterinael.data.mapper.MoodLogMapper
import com.ekaterinael.data.repository.DBMoodRepository
import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.domain.repository.MoodRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface RoomModule {
    @[Binds AppScope]
    fun bindsMoodLogMapper(impl: MoodLogMapper): Mapper<MoodLogDTO, MoodLogEntity>

    @[Binds AppScope]
    fun bindsMoodRepository(impl: DBMoodRepository): MoodRepository

    companion object {
        @[Provides AppScope]
        fun providesMoodLogDao(db: AppDatabase): MoodLogDao = db.moodLogDto

        @[Provides AppScope]
        fun providesAppDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context = context)
    }
}