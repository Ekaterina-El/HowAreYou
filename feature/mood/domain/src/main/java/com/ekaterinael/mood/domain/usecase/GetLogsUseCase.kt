package com.ekaterinael.mood.domain.usecase

import com.ekaterinael.mood.domain.repository.MoodRepository

/** Use case for return a reactive stream of mood logs */
class GetLogsUseCase(private val repository: MoodRepository) {
    operator fun invoke() = repository.getLogs()
}