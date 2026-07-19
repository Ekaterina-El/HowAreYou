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
package com.ekaterinael.data.mapper

/**
 * Defines bidirectional mapping between domain and database models.
 *
 * @param DTO the domain model type.
 * @param DB the database model type.
 */
interface Mapper<DTO, DB> {
  /**
   * Converts a domain model to its database representation.
   *
   * @param input the domain model to convert.
   * @return the corresponding database model.
   */
  fun fromDTO(input: DTO): DB

  /**
   * Converts a database model to its domain representation.
   *
   * @param input the database model to convert.
   * @return the corresponding domain model.
   */
  fun toDTO(input: DB): DTO

  /**
   * Converts a list of database models to domain models.
   *
   * @param list the database models to convert.
   * @return the corresponding list of domain models.
   */
  fun toDTO(list: List<DB>): List<DTO>
}
