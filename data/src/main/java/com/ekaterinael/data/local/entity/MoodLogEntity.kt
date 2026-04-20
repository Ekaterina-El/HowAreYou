package com.ekaterinael.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ekaterinael.domain.model.Mood
import java.util.Date

/**
 * An entity representing a mood log entry.
 *
 * @property id the record's unique identifier
 * @property date the record's creation date
 * @property title a brief title for the record (e.g., "A Good Day")
 * @property description a detailed description: what happened, thoughts, events
 * @property mood the user's current mood, represented as [Mood]
 */
@Entity(tableName = "mood_log")
data class MoodLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val date: Date,
    val title: String,
    val description: String,
    val mood: Int
)