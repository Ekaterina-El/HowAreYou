package com.ekaterinael.mood_list.di

import com.ekaterinael.mood_list.mapper.MoodListUiMapper
import com.ekaterinael.mood_list.mapper.MoodListUiMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface MoodListModule {
    @[Binds MoodListScope]
    fun bindsMoodListUiMapper(impl: MoodListUiMapperImpl): MoodListUiMapper
}