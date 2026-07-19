/*
 * Copyright 2026 Ekaterina Elshina
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ekaterinael.data.local.db

import android.icu.util.Calendar
import com.ekaterinael.data.local.entity.MoodLogEntity
import kotlin.random.Random

object MockData {
  private const val MIN_HOURS = 17
  private const val MAX_HOURS = 23
  private val HOURS_RANGE = MIN_HOURS..MAX_HOURS

  private const val MIN_MINUTES = 0
  private const val MAX_MINUTES = 59

  private val MINUTES_RANGE = MIN_MINUTES..MAX_MINUTES

  private const val MIN_MOOD_SCORE = 1
  private const val MAX_MOOD_SCORE = 5

  /** Mock mood log entries generated lazily on first access. */
  val mockLogs by lazy { generateMockLogs() }

  private fun generateMockLogs(): List<MoodLogEntity> {
    val currentDate = Calendar.getInstance()

    val logs = mutableListOf<MoodLogEntity>()

    descriptions.forEach {
      currentDate.add(Calendar.DAY_OF_YEAR, -1)

      val hour = (HOURS_RANGE).random()
      val minute = (MINUTES_RANGE).random()
      currentDate.set(Calendar.HOUR_OF_DAY, hour)
      currentDate.set(Calendar.MINUTE, minute)
      currentDate.set(Calendar.SECOND, 0)
      currentDate.set(Calendar.MILLISECOND, 0)

      logs.add(
        MoodLogEntity(
          id = null,
          date = currentDate.time,
          description = it,
          mood = Random.nextInt(MIN_MOOD_SCORE, MAX_MOOD_SCORE),
        )
      )
    }
    return logs
  }

  private val descriptions: List<String> by lazy {
    listOf(
      "Сегодня проснулся с хорошим настроением, быстро собрался и успел спокойно позавтракать. Есть " +
        "ощущение, что день начался правильно и получится многое сделать без лишней спешки.",
      "День прошёл довольно ровно, без сильных переживаний и неприятных событий. " +
        "Вечером получилось немного отдохнуть, навести порядок дома " +
        "и почувствовать, что всё под контролем.",
      "Сегодня не было ярких эмоций, но это скорее плюс. После прогулки стало легче собраться с " +
        "мыслями, появилась ясность и желание спокойно закончить начатые дела.",
      "Было много мыслей о сроках и задачах, из-за этого сложно было сосредоточиться на чём-то " +
        "одном. Постарался разложить дела по приоритетам, но напряжение всё равно осталось.",
      "К вечеру почувствовал сильную усталость и небольшую грусть без конкретной причины. Кажется, " +
        "накопилось много мелких переживаний, которые сегодня стали особенно заметны.",
      "Удалось продуктивно поработать и обсудить важные вопросы без лишнего напряжения. Особенно " +
        "порадовало, что получилось спокойно объяснить свои идеи и получить поддержку.",
      "Обычный день без заметных подъёмов и спадов. Сделал базовые дела, немного отвлёкся от " +
        "телефона и провёл вечер в спокойном темпе, без ощущения перегруза.",
      "Сегодня получилось закрыть задачу, которая долго висела и вызывала раздражение. После этого " +
        "появилось чувство облегчения, уверенности и желание двигаться дальше.",
      "День был немного беспокойным, потому что приходилось держать в голове слишком много планов " +
        "одновременно. Хочется замедлиться и выбрать только самое важное на завтра.",
      "После прогулки стало заметно легче, настроение выровнялось, а мысли перестали крутиться " +
        "вокруг рабочих задач. Хорошо помогло просто сменить обстановку и подышать воздухом.",
    )
  }
}
