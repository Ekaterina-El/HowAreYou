package com.ekaterinael.mode_statistic

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.ekaterinael.mode_statistic.MoodStatisticStore.State
import javax.inject.Inject

class MoodStatisticStoreFactory @Inject constructor(private val storeFactory: StoreFactory) {
    fun create(): MoodStatisticStore =
        object : MoodStatisticStore, Store<Nothing, State, Nothing> by storeFactory.create(
            name = MoodStatisticStore::class.simpleName,
            initialState = State(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl,
        ) {}

    sealed interface Action

    sealed interface Message

    private class ExecutorImpl: CoroutineExecutor<Nothing, Action, State, Message, Nothing>()

    private object ReducerImpl: Reducer<State, Message> {
        override fun State.reduce(msg: Message): State {
            /*when (msg) {

            }*/
            return this
        }

    }
}