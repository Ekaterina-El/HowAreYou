package com.ekaterinael.mood.mood_statistic.di

import com.ekaterinael.mood.mood_statistic.mapper.MoodStatisticUIMapper
import com.ekaterinael.mood.mood_statistic.mapper.MoodStatisticUIMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface MoodStatisticModule {
    @[Binds MoodStatisticScope]
    fun bindMoodStatisticUIMapper(impl: MoodStatisticUIMapperImpl): MoodStatisticUIMapper
}