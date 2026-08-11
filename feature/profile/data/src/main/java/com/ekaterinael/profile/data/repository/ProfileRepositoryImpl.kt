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
package com.ekaterinael.profile.data.repository

import com.ekaterinael.core.di.AppScope
import com.ekaterinael.profile.data.dataSource.LocalProfileDataSource
import com.ekaterinael.profile.domain.model.Profile
import com.ekaterinael.profile.domain.repository.ProfileRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Default implementation of [ProfileRepository] that delegates to the local data source.
 *
 * @param localDataSource the local source used to access profile data.
 */
@AppScope
class ProfileRepositoryImpl
@Inject
constructor(private val localDataSource: LocalProfileDataSource) : ProfileRepository {
  override fun getProfile(): Flow<Profile?> = localDataSource.getProfile()
}
