package com.ekaterinael.domain.usecase

import com.ekaterinael.core.Result
import com.ekaterinael.core.di.AppScope
import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.domain.repository.MoodRepository
import javax.inject.Inject

/** Use case for update an existing mood log */
@AppScope
class EditLogUseCase @Inject constructor(private val repository: MoodRepository) {
    suspend operator fun invoke(moodLog: MoodLogDTO): Result<Unit> {
        try {
            repository.editLog(moodLog)
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}