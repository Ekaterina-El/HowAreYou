package com.ekaterinael.data.local.dao

import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {
    @Insert
    suspend fun add(entity: T)

    @Update
    suspend fun update(entity: T)
}