package com.ekaterinael.mood.data.repository

import com.ekaterinael.core.di.AppScope
import com.ekaterinael.mood.data.dataSource.LocalMoodDataSource
import com.ekaterinael.mood.domain.model.MoodLog
import com.ekaterinael.mood.domain.repository.MoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@AppScope
class MoodRepositoryImpl @Inject constructor(
    private val localDataSource: LocalMoodDataSource
): MoodRepository {
    override suspend fun addNewLog(moodLog: MoodLog) = localDataSource.add(moodLog)
    override suspend fun editLog(moodLog: MoodLog) = localDataSource.update(moodLog)
    override suspend fun removeLog(id: Long) = localDataSource.removeById(id = id)

    override suspend fun getLogById(id: Long): MoodLog? = localDataSource.getById(id = id)
    override fun getLogs(): Flow<List<MoodLog>> = localDataSource.getLogs()
}