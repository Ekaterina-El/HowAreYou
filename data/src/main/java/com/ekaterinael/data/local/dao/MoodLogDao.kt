package com.ekaterinael.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.ekaterinael.data.local.entity.MoodLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodLogDao: BaseDao<MoodLogEntity> {
    // TODO: add filters
    @Query("SELECT * FROM mood_log")
    fun getLog(): Flow<List<MoodLogEntity>>

    @Query("SELECT * FROM mood_log WHERE id = :id")
    fun getById(id: Long): MoodLogEntity?

    @Query("DELETE FROM mood_log WHERE id = :id")
    fun removeById(id: Long)
}