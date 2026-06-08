package com.ekaterinael.domain.usecase

import com.ekaterinael.core.Result
import com.ekaterinael.core.di.AppScope
import com.ekaterinael.domain.model.MoodLog
import com.ekaterinael.domain.repository.MoodRepository
import javax.inject.Inject

/** Use case for adding a new mood log entry */
@AppScope
class SaveMoodLogUseCase @Inject constructor(private val repository: MoodRepository) {
    suspend operator fun invoke(moodLog: MoodLog): Result<Unit> {
        try {
            if (moodLog.id == null) {
                repository.addNewLog(moodLog = moodLog)
            } else {
                repository.editLog(moodLog)
            }

            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}