package com.udacity.asteroidradar

import android.app.Application
import com.udacity.asteroidradar.di.AndroidComponent
import com.udacity.asteroidradar.di.DaggerAndroidComponent
import com.udacity.asteroidradar.di.DaggerNasaServerComponent
import com.udacity.asteroidradar.di.NasaServerComponent
import com.udacity.asteroidradar.di.module.AndroidModule

class AppClass: Application() {

    companion object {
       lateinit var androidComponent: AndroidComponent
       lateinit var nasaServerComponent: NasaServerComponent
    }

    override fun onCreate() {
        super.onCreate()
        androidComponent = DaggerAndroidComponent.builder()
            .androidModule(AndroidModule(this))
            .build()
        nasaServerComponent = DaggerNasaServerComponent.builder()
            .androidComponent(androidComponent)
            .build()
    }
}