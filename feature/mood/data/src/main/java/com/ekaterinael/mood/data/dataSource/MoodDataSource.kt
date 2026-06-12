package com.ekaterinael.mood.data.dataSource

import com.ekaterinael.mood.domain.model.MoodLog
import kotlinx.coroutines.flow.Flow

interface MoodDataSource {
    suspend fun add(log: MoodLog)
    suspend fun update(log: MoodLog)
    suspend fun removeById(id: Long)
    suspend fun getById(id: Long): MoodLog?
    fun getLogs(): Flow<List<MoodLog>>
}