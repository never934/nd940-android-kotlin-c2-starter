package com.udacity.asteroidradar.di.module

import android.app.Application
import androidx.room.Room
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.db.AppDatabase
import com.udacity.asteroidradar.db.AsteroidsDao
import com.udacity.asteroidradar.di.EnvScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(private val database: AppDatabase){

    @Provides
    @EnvScope
    fun providesRoomDatabase(): AppDatabase {
        return database
    }

    @Provides
    @EnvScope
    fun providesAsteroidsDao(database: AppDatabase): AsteroidsDao {
        return database.asteroidsDao()
    }

}