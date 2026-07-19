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
package com.ekaterinael.mood.mood_statistic

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.ekaterinael.mood.mood_statistic.MoodStatisticStore.State
import com.ekaterinael.mood.mood_statistic.di.MoodStatisticScope
import com.ekaterinael.mood.mood_statistic.mapper.MoodStatisticUIMapper
import javax.inject.Inject

/** Creates and configures [MoodStatisticStore] instances. */
@MoodStatisticScope
class MoodStatisticStoreFactory
@Inject
constructor(private val storeFactory: StoreFactory, private val mapper: MoodStatisticUIMapper) {
  /**
   * Creates a new [MoodStatisticStore] instance.
   *
   * @return the configured mood statistics store.
   */
  fun create(): MoodStatisticStore =
    object :
      MoodStatisticStore,
      Store<Nothing, State, Nothing> by storeFactory.create(
        name = MoodStatisticStore::class.simpleName,
        initialState = State(data = mapper.map("[In development]")),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl,
      ) {}

  /** Represents internal actions handled by the store executor. */
  sealed interface Action

  /** Represents state update messages handled by the reducer. */
  sealed interface Message

  /** Executes internal mood statistics store actions. */
  private class ExecutorImpl : CoroutineExecutor<Nothing, Action, State, Message, Nothing>()

  /** Reduces incoming messages into a new store state. */
  private object ReducerImpl : Reducer<State, Message> {

    /**
     * Returns the current state because no state changes are currently implemented.
     *
     * @param msg the message to process.
     * @return the unchanged state.
     */
    override fun State.reduce(msg: Message) = this
  }
}
