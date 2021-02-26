package com.udacity.asteroidradar.utils

import com.udacity.asteroidradar.db.entity.AsteroidDB
import com.udacity.asteroidradar.network.response.AsteroidResponse

data class AsteroidResponseContainer(val asteroids: List<AsteroidResponse>)

fun AsteroidResponseContainer.asDatabaseModel() : Array<AsteroidDB> {
    return asteroids.map { AsteroidDB(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}
