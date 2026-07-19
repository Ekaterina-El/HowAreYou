/*
 * Copyright 2026 Ekaterina Elshina
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ekaterinael.data.local.dao

import androidx.room.Insert
import androidx.room.Update

/**
 * Defines common database operations for Room entities.
 *
 * @param T the type of entity managed by this DAO.
 */
interface BaseDao<T> {
  /**
   * Inserts the specified [entity] into the database.
   *
   * @param entity the entity to insert.
   */
  @Insert suspend fun add(entity: T)

  /**
   * Updates the specified [entity] in the database.
   *
   * @param entity the entity to update.
   */
  @Update suspend fun update(entity: T)
}
