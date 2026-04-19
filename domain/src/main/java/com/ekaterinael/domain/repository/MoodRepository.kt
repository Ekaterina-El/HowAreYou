package com.ekaterinael.domain.repository

import com.ekaterinael.domain.model.MoodLogDTO
import kotlinx.coroutines.flow.Flow

/** Repository that manages mood logs data */
interface MoodRepository {
    /**
     * Adds a new mood log entry.
     * @param log the mood log to be inserted
     */
    suspend fun addNewLog(log: MoodLogDTO)

    /**
     * Update an existing log entry.
     * @param log the mood log to be inserted
     */
    suspend fun editLog(log: MoodLogDTO)

    /** Returns a reactive stream of mood logs */
    suspend fun getLogs(): Flow<List<MoodLogDTO>>

    /** Get a mood logs by ID */
    suspend fun getLogById(id: Long): MoodLogDTO?
}