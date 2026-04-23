package com.ekaterinael.howareyou.di

import android.content.Context
import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.di.RoomModule
import com.ekaterinael.howareyou.MainActivity
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [RoomModule::class, PresentationModule::class]
)
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun build(): ApplicationComponent
    }
}