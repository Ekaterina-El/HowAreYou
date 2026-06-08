package com.ekaterinael.mode_statistic.di

import com.ekaterinael.mode_statistic.mapper.MoodStatisticUIMapper
import com.ekaterinael.mode_statistic.mapper.MoodStatisticUIMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface MoodStatisticModule {
    @[Binds MoodStatisticScope]
    fun bindMoodStatisticUIMapper(impl: MoodStatisticUIMapperImpl): MoodStatisticUIMapper
}