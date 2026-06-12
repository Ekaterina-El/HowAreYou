package com.ekaterinael.mood.domain.usecase

import com.ekaterinael.mood.domain.model.MoodLog
import com.ekaterinael.mood.domain.repository.MoodRepository

/** Use case for adding a new mood log entry */
class SaveMoodLogUseCase(private val repository: MoodRepository) {
    suspend operator fun invoke(moodLog: MoodLog) {
        if (moodLog.id == null) {
            repository.addNewLog(moodLog = moodLog)
        } else {
            repository.editLog(moodLog)
        }
    }
}