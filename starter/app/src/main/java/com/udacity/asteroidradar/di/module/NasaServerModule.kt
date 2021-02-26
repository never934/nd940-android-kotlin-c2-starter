package com.udacity.asteroidradar.di.module

import com.udacity.asteroidradar.di.EnvScope
import com.udacity.asteroidradar.network.NasaServerApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NasaServerModule {
    @Provides
    @EnvScope
    fun provideServerApi(retrofit: Retrofit): NasaServerApi {
        return retrofit.create(NasaServerApi::class.java)
    }
}
