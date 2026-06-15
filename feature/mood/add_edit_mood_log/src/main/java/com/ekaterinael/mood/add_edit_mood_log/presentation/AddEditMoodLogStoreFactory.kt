package com.ekaterinael.mood.add_edit_mood_log.presentation

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.ekaterinael.mood.core.MoodUI
import com.ekaterinael.mood.core.toUI
import com.ekaterinael.mood.domain.model.MoodLog
import com.ekaterinael.mood.domain.usecase.GetMoodLogByIdUseCase
import com.ekaterinael.mood.domain.usecase.SaveMoodLogUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class AddEditMoodLogStoreFactory @Inject constructor(
    private val saveMoodLogUseCase: SaveMoodLogUseCase,
    private val getMoodLogByIdUseCase: GetMoodLogByIdUseCase,
    private val storeFactory: StoreFactory
) {
    private lateinit var moodLog: MoodLog

    fun create(moodLog: MoodLog): AddEditMoodLogStore {
        this.moodLog = moodLog
        return object : AddEditMoodLogStore,
            Store<AddEditMoodLogStore.Intent, AddEditMoodLogStore.State, AddEditMoodLogStore.Label> by storeFactory.create(
                name = AddEditMoodLogStore::class.simpleName,
                initialState = AddEditMoodLogStore.State.Initial,
                executorFactory = ::ExecutorImpl,
                bootstrapper = BootstrapperImpl(),
                reducer = ReducerImpl,
            ) {}
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                if (!::moodLog.isInitialized) return@launch
                val id = moodLog.id
                if (id == null) {
                    dispatch(Action.ToEditingMode(moodLog = moodLog))
                    return@launch
                }

                dispatch(Action.StartLoading)

                try {
                    val moodLog = withContext(Dispatchers.IO) { getMoodLogByIdUseCase(id) }
                    if (moodLog == null) {
                        dispatch(Action.LoadingException)
                        return@launch
                    }

                    dispatch(Action.ToEditingMode(moodLog))
                } catch (e: Exception) {
                    Log.e(TAG, e.localizedMessage ?: e.message, e)
                    dispatch(Action.LoadingException)
                }
            }
        }
    }

    sealed interface Action {
        data object StartLoading : Action
        data class ToEditingMode(val moodLog: MoodLog) : Action
        data object LoadingException : Action
    }

    private sealed interface Message {
        data object StartLoading: Message

        data object LoadingException: Message

        data class ToEditingMode(val moodLog: MoodLog): Message

        data class OnChangeDescription(val description: String) : Message
        data class OnChangeMood(val mood: MoodUI) : Message
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<AddEditMoodLogStore.Intent, Action, AddEditMoodLogStore.State, Message, AddEditMoodLogStore.Label>() {
        override fun executeAction(action: Action, getState: () -> AddEditMoodLogStore.State) {
            when (action) {
                Action.StartLoading -> dispatch(Message.StartLoading)
                Action.LoadingException -> dispatch(Message.LoadingException)
                is Action.ToEditingMode -> dispatch(Message.ToEditingMode(action.moodLog))

            }
        }

        override fun executeIntent(
            intent: AddEditMoodLogStore.Intent,
            getState: () -> AddEditMoodLogStore.State
        ) {
            when (intent) {
                is AddEditMoodLogStore.Intent.OnChangeDescription -> dispatch(
                    Message.OnChangeDescription(
                        intent.description
                    )
                )

                is AddEditMoodLogStore.Intent.OnChangeMood -> dispatch(Message.OnChangeMood(intent.mood))
                AddEditMoodLogStore.Intent.OnGoBack -> publish(AddEditMoodLogStore.Label.OnGoBack)
                AddEditMoodLogStore.Intent.OnSave -> saveMoodLogLog(state = getState())
            }
        }

        private fun saveMoodLogLog(state: AddEditMoodLogStore.State) {
            scope.launch {
                try {
                    if (state !is AddEditMoodLogStore.State.Editing) {
                        throw IllegalStateException("Can change description only in editing mode")
                    }

                    val moodLog = MoodLog(
                        id = state.id,
                        date = state.date ?: getCurrentDate(),
                        description = state.description,
                        mood = state.selectedMood.mood
                    )

                    withContext(Dispatchers.IO) { saveMoodLogUseCase(moodLog = moodLog) }
                    publish(AddEditMoodLogStore.Label.AfterSave)

                } catch (e: Exception) {
                    Log.e(TAG, e.localizedMessage ?: e.message, e)
                    // TODO: добавить обработку ошибок
                }
            }
        }

        private fun getCurrentDate(): Date = Calendar.getInstance().time
    }

    private object ReducerImpl : Reducer<AddEditMoodLogStore.State, Message> {
        override fun AddEditMoodLogStore.State.reduce(msg: Message): AddEditMoodLogStore.State {
            return when (msg) {
                Message.LoadingException -> AddEditMoodLogStore.State.LoadingException
                Message.StartLoading -> AddEditMoodLogStore.State.Loading

                is Message.ToEditingMode -> {
                    val log = msg.moodLog
                    AddEditMoodLogStore.State.Editing(
                        id = log.id,
                        date = log.date,
                        description = log.description,
                        selectedMood = log.mood.toUI(),
                        moods = MoodUI.all
                    )
                }

                is Message.OnChangeDescription -> {
                    if (this !is AddEditMoodLogStore.State.Editing) {
                        throw IllegalStateException("Can change description only in editing mode")
                    }

                    copy(description = msg.description)
                }
                is Message.OnChangeMood -> {
                    if (this !is AddEditMoodLogStore.State.Editing) { throw NoEditingModeException() }

                    copy(selectedMood = msg.mood)
                }
            }
        }

    }

    companion object {
        private const val TAG = "AddEditMoodLogStoreFactory"

        private class NoEditingModeException: IllegalStateException("Can change data only in editing mode")
    }
}