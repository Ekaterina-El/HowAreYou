package com.ekaterinael.domain.model

import java.util.Date

/**
 * A Data Transfer Object representing a mood log entry.
 *
 * @property id the record's unique identifier
 * @property date the record's creation date
 * @property title a brief title for the record (e.g., "A Good Day")
 * @property description a detailed description: what happened, thoughts, events
 * @property mood the user's current mood, represented as [Mood]
 */
data class MoodLogDTO(
    private val id: Long,
    private val date: Date,
    private val title: String,
    private val description: String,
    private val mood: Mood
)