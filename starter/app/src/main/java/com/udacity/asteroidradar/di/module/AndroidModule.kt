package com.udacity.asteroidradar.di.module

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule(private val mContext: Context) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return mContext
    }

    @Provides
    @Singleton
    internal fun provideResources(): Resources {
        return mContext.resources
    }
}