package com.ekaterinael.data.repository

import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.local.dao.MoodLogDao
import com.ekaterinael.data.local.entity.MoodLogEntity
import com.ekaterinael.data.mapper.Mapper
import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.domain.repository.MoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AppScope
class DBMoodRepository @Inject constructor(
    private val mapper: Mapper<MoodLogDTO, MoodLogEntity>,
    private val dao: MoodLogDao
): MoodRepository {
    override suspend fun addNewLog(log: MoodLogDTO) = dao.add(mapper.fromDTO(log))
    override suspend fun editLog(log: MoodLogDTO) = dao.update(mapper.fromDTO(log))
    override suspend fun removeLog(id: Long) = dao.removeById(id = id)

    override suspend fun getLogById(id: Long): MoodLogDTO? = dao.getById(id = id)?.let {
        mapper.toDTO(it)
    }

    override suspend fun getLogs(): Flow<List<MoodLogDTO>> = dao.getLog().map(mapper::toDTO)
}