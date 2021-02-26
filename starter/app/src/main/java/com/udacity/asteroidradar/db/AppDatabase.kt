package com.udacity.asteroidradar.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.db.entity.AsteroidDB

@Database(entities = [AsteroidDB::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun asteroidsDao(): AsteroidsDao

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(app: Application): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(app).also { INSTANCE = it }
        }

        private fun buildDatabase(app: Application) =
            Room.databaseBuilder(app,
                AppDatabase::class.java,
                Constants.DB_NAME)
                .build()
    }
}