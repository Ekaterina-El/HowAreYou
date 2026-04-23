package com.ekaterinael.howareyou

import android.app.Application
import com.ekaterinael.howareyou.di.ApplicationComponent
import com.ekaterinael.howareyou.di.DaggerApplicationComponent

class HowAreYouApp: Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .context(applicationContext)
            .build()
    }
}