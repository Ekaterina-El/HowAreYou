package com.ekaterinael.mood.mood_statistic.mapper

import com.ekaterinael.mood.mood_statistic.MoodStatisticUI
import com.ekaterinael.mood.mood_statistic.di.MoodStatisticScope
import javax.inject.Inject

@MoodStatisticScope
class MoodStatisticUIMapperImpl @Inject constructor() : MoodStatisticUIMapper {
    override fun map(data: String) = MoodStatisticUI(data = data)
}