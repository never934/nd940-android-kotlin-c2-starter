package com.udacity.asteroidradar.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.network.response.AsteroidResponse

@Entity
data class AsteroidDB(
    @PrimaryKey
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)