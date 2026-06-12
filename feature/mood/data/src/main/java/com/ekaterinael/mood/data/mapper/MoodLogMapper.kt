package com.ekaterinael.mood.data.mapper

import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.local.entity.MoodLogEntity
import com.ekaterinael.data.mapper.Mapper
import com.ekaterinael.mood.domain.model.Mood.Companion.toMood
import com.ekaterinael.mood.domain.model.MoodLog
import javax.inject.Inject

@AppScope
class MoodLogMapper @Inject constructor(): Mapper<MoodLog, MoodLogEntity> {
    override fun fromDTO(input: MoodLog) = MoodLogEntity(
        id = input.id,
        date = input.date,
        description = input.description,
        mood = input.mood.scope
    )

    override fun toDTO(input: MoodLogEntity) = MoodLog(
        id = input.id,
        date = input.date,
        description = input.description,
        mood = input.mood.toMood()
    )

    override fun toDTO(list: List<MoodLogEntity>) = list.map(::toDTO)
}