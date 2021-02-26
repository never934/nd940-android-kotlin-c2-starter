package com.udacity.asteroidradar

import android.app.Application
import com.udacity.asteroidradar.di.AndroidComponent
import com.udacity.asteroidradar.di.DaggerAndroidComponent
import com.udacity.asteroidradar.di.module.AndroidModule

class AppClass: Application() {

    companion object {
       lateinit var androidComponent: AndroidComponent
    }

    override fun onCreate() {
        super.onCreate()
        androidComponent = DaggerAndroidComponent.builder()
            .androidModule(AndroidModule(this))
            .build()
    }
}