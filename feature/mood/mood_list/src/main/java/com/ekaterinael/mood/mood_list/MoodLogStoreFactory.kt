package com.ekaterinael.mood.mood_list

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.ekaterinael.mood.core.MoodUI
import com.ekaterinael.mood.domain.model.MoodLog
import com.ekaterinael.mood.domain.usecase.GetLogsUseCase
import com.ekaterinael.mood.mood_list.MoodLogStore.Intent
import com.ekaterinael.mood.mood_list.MoodLogStore.Label
import com.ekaterinael.mood.mood_list.MoodLogStore.State
import com.ekaterinael.mood.mood_list.di.MoodListScope
import com.ekaterinael.mood.mood_list.mapper.MoodListUiMapper
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@MoodListScope
class MoodLogStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getLogsUseCase: GetLogsUseCase,
    private val mapper: MoodListUiMapper
) {
    fun create(): MoodLogStore =
        object : MoodLogStore, Store<Intent, State, Label> by storeFactory.create(
            name = MoodLogStore::class.simpleName,
            initialState = State(
                moods = MoodUI.all,
                selectedMonth = Calendar.getInstance().time
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class MoodLogUpdated(val logs: List<MoodListItemUI>): Action
    }

    private sealed interface Message {
        data class MoodLogUpdated(val logs: List<MoodListItemUI>): Message
    }

    private inner class BootstrapperImpl: CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                getLogsUseCase().collect { onNewListOfLogs(it) }
            }
        }

        private fun onNewListOfLogs(logs: List<MoodLog>) {
            Log.d("MoodLogStoreFactory", "New List of logs in MoodLogStoreFactory")
            dispatch(Action.MoodLogUpdated(logs = mapper.map(logs)))
        }
    }

    private class ExecutorImpl: CoroutineExecutor<Intent, Action, State, Message, Label>() {
        override fun executeAction(
            action: Action,
            getState: () -> State
        ) {
            when (action) {
                is Action.MoodLogUpdated -> dispatch(Message.MoodLogUpdated(action.logs))
            }
        }

        override fun executeIntent(
            intent: Intent,
            getState: () -> State
        ) {
            when (intent) {
                is Intent.OnClickAddNewLog -> publish(Label.GoToCreateNewLog(intent.selectedMood))
                is Intent.OnClickByLog -> publish(Label.OpenLogToEdit(intent.logId))
            }
        }
    }

    private object ReducerImpl: Reducer<State, Message> {
        override fun State.reduce(msg: Message): State {
            return when (msg) {
                is Message.MoodLogUpdated -> copy(logs = msg.logs)
            }
        }
    }
}