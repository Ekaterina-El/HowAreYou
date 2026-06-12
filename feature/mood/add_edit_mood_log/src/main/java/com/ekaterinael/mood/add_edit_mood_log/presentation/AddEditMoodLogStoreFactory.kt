package com.ekaterinael.mood.add_edit_mood_log.presentation

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.ekaterinael.mood.core.MoodUI
import com.ekaterinael.mood.core.toUI
import com.ekaterinael.mood.domain.model.MoodLog
import com.ekaterinael.mood.domain.usecase.SaveMoodLogUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class AddEditMoodLogStoreFactory @Inject constructor(
    private val saveMoodLogUseCase: SaveMoodLogUseCase,
    private val storeFactory: StoreFactory
) {
    fun create(moodLog: MoodLog): AddEditMoodLogStore =
        object : AddEditMoodLogStore, Store<AddEditMoodLogStore.Intent, AddEditMoodLogStore.State, AddEditMoodLogStore.Label> by storeFactory.create(
            name = AddEditMoodLogStore::class.simpleName,
            initialState = AddEditMoodLogStore.State(
                id = moodLog.id,
                date = moodLog.date,
                description = moodLog.description,
                selectedMood = moodLog.mood.toUI(),
                moods = MoodUI.all
            ),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    sealed interface Action

    private sealed interface Message {
        data class OnChangeDescription(val description: String) : Message
        data class OnChangeMood(val mood: MoodUI) : Message
    }

    private inner class ExecutorImpl : CoroutineExecutor<AddEditMoodLogStore.Intent, Action, AddEditMoodLogStore.State, Message, AddEditMoodLogStore.Label>() {
        override fun executeIntent(intent: AddEditMoodLogStore.Intent, getState: () -> AddEditMoodLogStore.State) {
            when (intent) {
                is AddEditMoodLogStore.Intent.OnChangeDescription -> dispatch(Message.OnChangeDescription(intent.description))
                is AddEditMoodLogStore.Intent.OnChangeMood -> dispatch(Message.OnChangeMood(intent.mood))
                AddEditMoodLogStore.Intent.OnGoBack -> publish(AddEditMoodLogStore.Label.OnGoBack)
                AddEditMoodLogStore.Intent.OnSave -> saveMoodLogLog(state = getState())
            }
        }

        private fun saveMoodLogLog(state: AddEditMoodLogStore.State) {
            scope.launch {
                try {
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
                is Message.OnChangeDescription -> copy(description = msg.description)
                is Message.OnChangeMood -> copy(selectedMood = msg.mood)
            }
        }
    }

    companion object {
        private const val TAG = "AddEditMoodLogStoreFactory"
    }
}