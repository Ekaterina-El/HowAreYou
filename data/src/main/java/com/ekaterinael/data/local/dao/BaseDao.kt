package com.ekaterinael.data.local.dao

import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {
    @Insert
    fun add(entity: T)

    @Update
    fun update(entity: T)
}