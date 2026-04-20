package com.ekaterinael.howareyou.di

import android.content.Context
import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.di.RoomModule
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [RoomModule::class]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun build(): ApplicationComponent
    }
}