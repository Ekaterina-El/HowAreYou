package com.ekaterinael.data.mapper

import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.local.entity.MoodLogEntity
import com.ekaterinael.domain.model.Mood.Companion.toMood
import com.ekaterinael.domain.model.MoodLogDTO
import javax.inject.Inject

@AppScope
class MoodLogMapper @Inject constructor(): Mapper<MoodLogDTO, MoodLogEntity> {
    override fun fromDTO(input: MoodLogDTO) = MoodLogEntity(
        id = input.id,
        date = input.date,
        title = input.title,
        description = input.description,
        mood = input.mood.scope
    )

    override fun toDTO(input: MoodLogEntity) = MoodLogDTO(
        id = input.id,
        date = input.date,
        title = input.title,
        description = input.description,
        mood = input.mood.toMood()
    )

    override fun toDTO(list: List<MoodLogEntity>) = list.map(::toDTO)
}