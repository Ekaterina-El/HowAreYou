package com.ekaterinael.mood.data.dataSource

import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.local.dao.MoodLogDao
import com.ekaterinael.data.local.entity.MoodLogEntity
import com.ekaterinael.data.mapper.Mapper
import com.ekaterinael.mood.domain.model.MoodLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AppScope
class LocalMoodDataSource @Inject constructor(
    private val mapper: Mapper<MoodLog, MoodLogEntity>,
    private val dao: MoodLogDao
): MoodDataSource {
    override suspend fun add(log: MoodLog) = dao.add(mapper.fromDTO(log))
    override suspend fun update(log: MoodLog) = dao.update(mapper.fromDTO(log))
    override suspend fun removeById(id: Long) = dao.removeById(id)
    override suspend fun getById(id: Long): MoodLog? = dao.getById(id = id)?.let { mapper.toDTO(it) }
    override fun getLogs(): Flow<List<MoodLog>> = dao.getLog().map(mapper::toDTO)
}