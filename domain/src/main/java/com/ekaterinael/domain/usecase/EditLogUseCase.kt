package com.ekaterinael.domain.usecase

import com.ekaterinael.core.Result
import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.domain.repository.MoodRepository

/** Use case for update an existing mood log */
class EditLogUseCase(private val repository: MoodRepository) {
    suspend operator fun invoke(log: MoodLogDTO): Result<Unit> {
        try {
            repository.editLog(log = log)
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}