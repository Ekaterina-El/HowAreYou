package com.ekaterinael.mood.mood_list.mapper

import com.ekaterinael.mood.core.toUI
import com.ekaterinael.mood.domain.model.MoodLog
import com.ekaterinael.mood.mood_list.MoodListItemUI
import com.ekaterinael.mood.mood_list.di.MoodListScope
import javax.inject.Inject

@MoodListScope
class MoodListUiMapperImpl @Inject constructor() : MoodListUiMapper {
    override fun map(logs: List<MoodLog>): List<MoodListItemUI> = logs.map {
        MoodListItemUI(
            id = it.id,
            date = it.date,
            description = it.description,
            mood = it.mood.toUI()
        )
    }
}