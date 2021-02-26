package com.udacity.asteroidradar.di.module

import com.udacity.asteroidradar.db.AppDatabase
import com.udacity.asteroidradar.db.AsteroidsDao
import com.udacity.asteroidradar.di.EnvScope
import dagger.Module
import dagger.Provides


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