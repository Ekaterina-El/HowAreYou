package com.ekaterinael.add_edit_mood_log

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogStore.Intent
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogStore.Label
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogStore.State
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.domain.model.MoodLogDTO
import java.util.Date
import javax.inject.Inject

class AddEditMoodLogStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory
) {
    fun create(moodLog: MoodLogDTO): AddEditMoodLogStore =
        object : AddEditMoodLogStore, Store<Intent, State, Label> by storeFactory.create(
            name = AddEditMoodLogStore::class.simpleName,
            initialState = State(
                id = moodLog.id,
                date = moodLog.date,
                title = moodLog.title,
                description = moodLog.description,
                mood = moodLog.mood
            ),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action

    private sealed interface Message {
        data class OnChangeDate(val date: Date): Message
        data class OnChangeTitle(val title: String): Message
        data class OnChangeDescription(val description: String): Message
        data class OnChangeMood(val mood: Mood): Message
    }

    private class ExecutorImpl: CoroutineExecutor<Intent, Action, State, Message, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.OnChangeDate -> dispatch(Message.OnChangeDate(intent.date))
                is Intent.OnChangeDescription -> dispatch(Message.OnChangeDescription(intent.description))
                is Intent.OnChangeMood -> dispatch(Message.OnChangeMood(intent.mood))
                is Intent.OnChangeTitle -> dispatch(Message.OnChangeTitle(intent.title))
                Intent.OnGoBack -> publish(Label.OnGoBack)
                Intent.OnSave -> {
                    // todo: Save to db
                    publish(Label.AfterSave)
                }
            }
        }
    }

    private object ReducerImpl: Reducer<State, Message> {
        override fun State.reduce(msg: Message): State {
            return when (msg) {
                is Message.OnChangeDate -> copy(date = msg.date)
                is Message.OnChangeDescription -> copy(description = msg.description)
                is Message.OnChangeMood -> copy(mood = msg.mood)
                is Message.OnChangeTitle -> copy(title = msg.title)
            }
        }
    }
}