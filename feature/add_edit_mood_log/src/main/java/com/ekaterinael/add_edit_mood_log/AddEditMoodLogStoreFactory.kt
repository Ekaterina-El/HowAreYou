package com.ekaterinael.add_edit_mood_log

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogStore.Intent
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogStore.Label
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogStore.State
import com.ekaterinael.core.Result
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.domain.usecase.AddNewLogUseCase
import com.ekaterinael.domain.usecase.EditLogUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class AddEditMoodLogStoreFactory @Inject constructor(
    private val addNewLogUseCase: AddNewLogUseCase,
    private val editLogUseCase: EditLogUseCase,
    private val storeFactory: StoreFactory
) {
    fun create(moodLog: MoodLogDTO): AddEditMoodLogStore =
        object : AddEditMoodLogStore, Store<Intent, State, Label> by storeFactory.create(
            name = AddEditMoodLogStore::class.simpleName,
            initialState = State(
                id = moodLog.id,
                date = moodLog.date,
                description = moodLog.description,
                mood = moodLog.mood
            ),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    sealed interface Action

    private sealed interface Message {
        data class OnChangeDescription(val description: String) : Message
        data class OnChangeMood(val mood: Mood) : Message
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Message, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.OnChangeDescription -> dispatch(Message.OnChangeDescription(intent.description))
                is Intent.OnChangeMood -> dispatch(Message.OnChangeMood(intent.mood))
                Intent.OnGoBack -> publish(Label.OnGoBack)
                Intent.OnSave -> saveMoodLogLog(state = getState())
            }
        }

        private fun saveMoodLogLog(state: State) {
            scope.launch {
                with(state) {
                    val moodLog = MoodLogDTO(
                        id = id,
                        date = date ?: getCurrentDate(),
                        description = description,
                        mood = mood
                    )


                    val result = withContext(Dispatchers.IO) {
                        id?.let { editLogUseCase(moodLog = moodLog) } ?: addNewLogUseCase(moodLog)
                    }

                    if (result is Result.Success) {
                        publish(Label.AfterSave)
                        return@launch
                    }
                    // TODO: добавить обработку ошибок
                }
            }
        }

        private fun getCurrentDate(): Date = Calendar.getInstance().time
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State {
            return when (msg) {
                is Message.OnChangeDescription -> copy(description = msg.description)
                is Message.OnChangeMood -> copy(mood = msg.mood)
            }
        }
    }
}