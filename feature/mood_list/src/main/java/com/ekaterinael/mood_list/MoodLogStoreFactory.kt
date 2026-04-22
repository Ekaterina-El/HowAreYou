package com.ekaterinael.mood_list

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.domain.usecase.GetLogsUseCase
import com.ekaterinael.mood_list.MoodLogStore.Intent
import com.ekaterinael.mood_list.MoodLogStore.Label
import com.ekaterinael.mood_list.MoodLogStore.State
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoodLogStoreFactory(
    private val storeFactory: StoreFactory,
    private val getLogsUseCase: GetLogsUseCase
) {
    fun create(): MoodLogStore =
        object : MoodLogStore, Store<Intent, State, Label> by storeFactory.create(
            name = MoodLogStore::class.simpleName,
            initialState = State(),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class MoodLogUpdated(val logs: List<MoodLogDTO>): Action
    }

    private sealed interface Message {
        data class MoodLogUpdated(val logs: List<MoodLogDTO>): Message
    }

    private inner class BootstrapperImpl: CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                getLogsUseCase().collectLatest {
                    dispatch(Action.MoodLogUpdated(it))
                }
            }
        }
    }

    private class ExecutorImpl: CoroutineExecutor<Intent, Action, State, Message, Label>() {
        override fun executeIntent(intent: Intent) {
            when (intent) {
                is Intent.OnClickAddNewLog -> publish(Label.GoToCreateNewLog(intent.selectedMood))
                is Intent.OnClickByLog -> publish(Label.OpenLogToEdit(intent.log))
            }
        }

        override fun executeAction(action: Action) {
            when (action) {
                is Action.MoodLogUpdated -> dispatch(Message.MoodLogUpdated(action.logs))
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