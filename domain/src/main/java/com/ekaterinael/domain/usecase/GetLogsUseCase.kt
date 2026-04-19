package com.ekaterinael.domain.usecase

import com.ekaterinael.core.di.AppScope
import com.ekaterinael.domain.repository.MoodRepository
import javax.inject.Inject

/** Use case for return a reactive stream of mood logs */
@AppScope
class GetLogsUseCase @Inject constructor(private val repository: MoodRepository) {
    suspend operator fun invoke() = repository.getLogs()
}