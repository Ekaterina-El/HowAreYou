package com.ekaterinael.mood.domain.repository

import com.ekaterinael.mood.domain.model.MoodLog
import kotlinx.coroutines.flow.Flow

/** Repository that manages mood logs data */
interface MoodRepository {
    /**
     * Adds a new mood log entry.
     * @param moodLog the mood log to be inserted
     */
    suspend fun addNewLog(moodLog: MoodLog)

    /**
     * Update an existing log entry.
     * @param moodLog the mood log to be inserted
     */
    suspend fun editLog(moodLog: MoodLog)

    /** Delete an existing log entry by ID */
    suspend fun removeLog(id: Long)

    /** Returns a reactive stream of mood logs */
    fun getLogs(): Flow<List<MoodLog>>

    /** Get a mood logs by ID */
    suspend fun getLogById(id: Long): MoodLog?
}