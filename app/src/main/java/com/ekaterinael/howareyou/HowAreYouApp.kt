package com.ekaterinael.howareyou

import android.app.Application
import com.ekaterinael.howareyou.di.DaggerApplicationComponent

class HowAreYouApp: Application() {
    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            .context(applicationContext)
            .build()
    }
}