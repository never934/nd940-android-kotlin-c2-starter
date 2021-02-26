package com.udacity.asteroidradar.di

import com.udacity.asteroidradar.di.module.NasaServerModule
import com.udacity.asteroidradar.di.module.RetrofitModule
import com.udacity.asteroidradar.di.module.RetrofitSettingsModule
import com.udacity.asteroidradar.network.NasaServerApi
import dagger.Component

@EnvScope
@Component(
    dependencies = [AndroidComponent::class],
    modules = [NasaServerModule::class, RetrofitSettingsModule::class, RetrofitModule::class]
)
interface NasaServerComponent {
    val nasaServerApi: NasaServerApi
}