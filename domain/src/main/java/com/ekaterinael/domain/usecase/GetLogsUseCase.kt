package com.ekaterinael.domain.usecase

import com.ekaterinael.domain.repository.MoodRepository

/** Use case for return a reactive stream of mood logs */
class GetLogsUseCase(private val repository: MoodRepository) {
    suspend operator fun invoke() = repository.getLogs()
}