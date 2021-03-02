package com.udacity.asteroidradar.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.db.entity.AsteroidDB

@Dao
interface AsteroidsDao {
    @Query("select * from asteroiddb order by closeApproachDate ASC")
    fun getAsteroids(): LiveData<List<AsteroidDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: Array<AsteroidDB>)

    @Delete
    fun deleteAsteroids(asteroids: List<AsteroidDB>)
}