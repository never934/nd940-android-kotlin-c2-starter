package com.udacity.asteroidradar.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.db.entity.AsteroidDB

@Dao
interface AsteroidsDao {
    @Query("select * from asteroiddb")
    fun getAsteroids(): LiveData<List<AsteroidDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: Array<AsteroidDB>)
}