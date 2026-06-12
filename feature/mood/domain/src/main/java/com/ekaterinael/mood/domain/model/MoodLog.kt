package com.ekaterinael.mood.domain.model

import java.util.Date

/**
 * A Data Transfer Object representing a mood log entry.
 *
 * @property id the record's unique identifier
 * @property date the record's creation date
 * @property description a detailed description: what happened, thoughts, events
 * @property mood the user's current mood, represented as [com.ekaterinael.mood.domain.model.Mood]
 */
data class MoodLog(
    val id: Long? = null,
    val date: Date,
    val description: String = "",
    val mood: Mood = Mood.UNKNOWN
)