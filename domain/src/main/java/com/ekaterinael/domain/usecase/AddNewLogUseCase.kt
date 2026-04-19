package com.ekaterinael.domain.usecase

import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.domain.repository.MoodRepository
import com.ekaterinael.core.Result

/** Use case for adding a new mood log entry */
class AddNewLogUseCase(private val repository: MoodRepository) {
    suspend operator fun invoke(log: MoodLogDTO): Result<Unit> {
        try {
            repository.addNewLog(log = log)
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}