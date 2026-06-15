package com.ekaterinael.howareyou.di

import android.content.Context
import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.di.RoomModule
import com.ekaterinael.howareyou.MainActivity
import com.ekaterinael.mood.data.di.MoodDataModule
import com.ekaterinael.mood.mood_list.di.MoodListModule
import com.ekaterinael.mood.mood_statistic.di.MoodStatisticModule
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        RoomModule::class,
        PresentationModule::class,
        MoodListModule::class,
        MoodStatisticModule::class,
        MoodDataModule::class
    ]
)
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun build(): ApplicationComponent
    }
}