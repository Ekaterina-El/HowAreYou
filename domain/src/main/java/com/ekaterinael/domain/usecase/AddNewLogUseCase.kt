package com.ekaterinael.domain.usecase

import com.ekaterinael.core.Result
import com.ekaterinael.core.di.AppScope
import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.domain.repository.MoodRepository
import javax.inject.Inject

/** Use case for adding a new mood log entry */
@AppScope
class AddNewLogUseCase @Inject constructor(private val repository: MoodRepository) {
    suspend operator fun invoke(moodLog: MoodLogDTO): Result<Unit> {
        try {
            repository.addNewLog(moodLog = moodLog)
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}