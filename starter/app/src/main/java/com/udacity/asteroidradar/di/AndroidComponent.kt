package com.udacity.asteroidradar.di

import android.content.Context
import android.content.res.Resources
import com.udacity.asteroidradar.di.module.AndroidModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidModule::class])
interface AndroidComponent {
    val context: Context
    val resources: Resources
}