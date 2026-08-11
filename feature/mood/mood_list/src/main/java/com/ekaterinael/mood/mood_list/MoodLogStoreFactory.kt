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
package com.ekaterinael.mood.mood_list

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.ekaterinael.core.ext.isSameMonthAs
import com.ekaterinael.core.ext.plusMonths
import com.ekaterinael.mood.core.model.MoodUI
import com.ekaterinael.mood.domain.usecase.GetLogsUseCase
import com.ekaterinael.mood.mood_list.MoodLogStore.Intent
import com.ekaterinael.mood.mood_list.MoodLogStore.Label
import com.ekaterinael.mood.mood_list.MoodLogStore.State
import com.ekaterinael.mood.mood_list.di.MoodListScope
import com.ekaterinael.mood.mood_list.mapper.MoodListUiMapper
import java.util.Calendar
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/** Creates and configures [MoodLogStore] instances. */
@MoodListScope
class MoodLogStoreFactory
@Inject
constructor(
  private val storeFactory: StoreFactory,
  private val getLogsUseCase: GetLogsUseCase,
  private val mapper: MoodListUiMapper,
) {
  /**
   * Creates a new [MoodLogStore] instance.
   *
   * @return the configured mood log store.
   */
  fun create(): MoodLogStore {
    val initialMonth = Calendar.getInstance().time
    return object :
      MoodLogStore,
      Store<Intent, State, Label> by storeFactory.create(
        name = MoodLogStore::class.simpleName,
        initialState =
          State(
            moods = MoodUI.all,
            selectedMonth = initialMonth,
            isNextMonthAvailable = isNextMonthAvailable(initialMonth),
          ),
        bootstrapper = BootstrapperImpl(),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl,
      ) {}
  }

  private fun isNextMonthAvailable(month: Date): Boolean = !month.isSameMonthAs(Date())

  private sealed interface Action {
    data object Init : Action
  }

  private sealed interface Message {
    data class MoodLogUpdated(val logs: List<MoodListItemUI>) : Message

    data class MonthChanged(val month: Date, val isNextMonthAvailable: Boolean) : Message
  }

  private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
    override fun invoke() = dispatch(Action.Init)
  }

  private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Message, Label>() {
    private var logsJob: Job? = null

    override fun executeAction(action: Action, getState: () -> State) {
      when (action) {
        Action.Init -> subscribeToLogs(getState().selectedMonth)
      }
    }

    override fun executeIntent(intent: Intent, getState: () -> State) {
      when (intent) {
        is Intent.OnClickAddNewLog -> publish(Label.GoToCreateNewLog(intent.selectedMood))
        is Intent.OnClickByLog -> publish(Label.OpenLogToEdit(intent.logId))
        Intent.OnClickPreviousMonth -> changeMonth(getState().selectedMonth.plusMonths(-1))
        Intent.OnClickNextMonth ->
          if (getState().isNextMonthAvailable) {
            changeMonth(getState().selectedMonth.plusMonths(1))
          }
      }
    }

    private fun changeMonth(newMonth: Date) {
      dispatch(Message.MonthChanged(newMonth, isNextMonthAvailable(newMonth)))
      subscribeToLogs(newMonth)
    }

    private fun subscribeToLogs(month: Date) {
      Log.d("MoodLogStoreFactory", "Subscribing to logs for month $month")
      logsJob?.cancel()
      logsJob =
        scope.launch {
          getLogsUseCase(month).collect { logs ->
            dispatch(Message.MoodLogUpdated(mapper.map(logs)))
          }
        }
    }
  }

  private object ReducerImpl : Reducer<State, Message> {
    override fun State.reduce(msg: Message): State {
      return when (msg) {
        is Message.MoodLogUpdated -> copy(logs = msg.logs)
        is Message.MonthChanged ->
          copy(
            selectedMonth = msg.month,
            logs = emptyList(),
            isNextMonthAvailable = msg.isNextMonthAvailable,
          )
      }
    }
  }
}
