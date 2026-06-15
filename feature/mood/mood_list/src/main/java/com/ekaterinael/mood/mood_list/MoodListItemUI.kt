package com.ekaterinael.mood.mood_list

import com.ekaterinael.mood.core.MoodUI
import java.util.Date

data class MoodListItemUI(
    val id: Long? = null,
    val date: Date?,
    val description: String,
    val mood: MoodUI
)