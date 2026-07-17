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
package com.ekaterinael.data.di

import android.content.Context
import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.local.dao.MoodLogDao
import com.ekaterinael.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module
interface RoomModule {
  companion object {
    @[Provides AppScope]
    fun providesMoodLogDao(db: AppDatabase): MoodLogDao = db.moodLogDao

    @[Provides AppScope]
    fun providesAppDatabase(context: Context): AppDatabase =
      AppDatabase.getInstance(context = context)
  }
}
