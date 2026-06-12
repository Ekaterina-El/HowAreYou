package com.ekaterinael.mood.mood_statistic

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.ekaterinael.mood.mood_statistic.MoodStatisticStore.State
import com.ekaterinael.mood.mood_statistic.di.MoodStatisticScope
import com.ekaterinael.mood.mood_statistic.mapper.MoodStatisticUIMapper
import javax.inject.Inject

@MoodStatisticScope
class MoodStatisticStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val mapper: MoodStatisticUIMapper
) {
    fun create(): MoodStatisticStore =
        object : MoodStatisticStore, Store<Nothing, State, Nothing> by storeFactory.create(
            name = MoodStatisticStore::class.simpleName,
            initialState = State(data = mapper.map("[In development]")),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl,
        ) {}

    sealed interface Action

    sealed interface Message

    private class ExecutorImpl: CoroutineExecutor<Nothing, Action, State, Message, Nothing>()

    private object ReducerImpl: Reducer<State, Message> {
        override fun State.reduce(msg: Message): State {
            return this
        }

    }
}