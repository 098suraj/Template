package com.example.template

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.Forest.plant

@HiltAndroidApp
class Application:Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            plant(Timber.DebugTree())
        }
    }
}