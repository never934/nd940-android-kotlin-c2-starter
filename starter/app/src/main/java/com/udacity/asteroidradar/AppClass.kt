package com.udacity.asteroidradar

import android.app.Application
import com.udacity.asteroidradar.db.AppDatabase
import com.udacity.asteroidradar.di.*
import com.udacity.asteroidradar.di.module.AndroidModule
import com.udacity.asteroidradar.di.module.RoomModule

class AppClass: Application() {

    companion object {
       lateinit var androidComponent: AndroidComponent
       lateinit var nasaServerComponent: NasaServerComponent
       lateinit var roomComponent: RoomComponent
    }

    override fun onCreate() {
        super.onCreate()
        androidComponent = DaggerAndroidComponent.builder()
            .androidModule(AndroidModule(this, this))
            .build()
        nasaServerComponent = DaggerNasaServerComponent.builder()
            .androidComponent(androidComponent)
            .build()
        roomComponent = DaggerRoomComponent.builder()
            .androidComponent(androidComponent)
            .roomModule(RoomModule(AppDatabase.getInstance(this)))
            .build()
    }
}