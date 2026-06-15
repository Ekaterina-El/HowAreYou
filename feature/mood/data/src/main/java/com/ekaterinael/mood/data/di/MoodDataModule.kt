package com.ekaterinael.mood.data.di

import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.local.entity.MoodLogEntity
import com.ekaterinael.data.mapper.Mapper
import com.ekaterinael.mood.data.mapper.MoodLogMapper
import com.ekaterinael.mood.data.repository.MoodRepositoryImpl
import com.ekaterinael.mood.domain.model.MoodLog
import com.ekaterinael.mood.domain.repository.MoodRepository
import com.ekaterinael.mood.domain.usecase.GetLogsUseCase
import com.ekaterinael.mood.domain.usecase.GetMoodLogByIdUseCase
import com.ekaterinael.mood.domain.usecase.SaveMoodLogUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface MoodDataModule {
    @[Binds AppScope]
    fun bindMoodRepository(impl: MoodRepositoryImpl): MoodRepository

    @[Binds AppScope]
    fun bindMoodLogMapper(impl: MoodLogMapper): Mapper<MoodLog, MoodLogEntity>

    companion object {
        @[Provides AppScope]
        fun provideGetLogsUseCase(repository: MoodRepository) = GetLogsUseCase(repository)

        @[Provides AppScope]
        fun provideSaveMoodLogUseCase(repository: MoodRepository) = SaveMoodLogUseCase(repository)

        @[Provides AppScope]
        fun provideGetMoodLogByIdUseCase(repository: MoodRepository) = GetMoodLogByIdUseCase(repository)
    }
}