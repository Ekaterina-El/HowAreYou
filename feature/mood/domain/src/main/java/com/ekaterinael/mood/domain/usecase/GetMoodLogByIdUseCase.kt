package com.ekaterinael.mood.domain.usecase

import com.ekaterinael.mood.domain.repository.MoodRepository

class GetMoodLogByIdUseCase(private val repository: MoodRepository) {
    suspend operator fun invoke(id: Long) = repository.getLogById(id)
}