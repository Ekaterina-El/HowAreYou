package com.ekaterinael.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
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
@Parcelize
data class MoodLogDTO(
    val id: Long = UNKNOWN_ID,
    val date: Date,
    val title: String = "",
    val description: String = "",
    val mood: Mood = Mood.UNKNOWN
): Parcelable {

    companion object {
        const val UNKNOWN_ID = -1L
    }

}