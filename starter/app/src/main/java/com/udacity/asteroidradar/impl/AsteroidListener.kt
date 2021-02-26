package com.udacity.asteroidradar.impl

import com.udacity.asteroidradar.db.entity.AsteroidDB

class AsteroidListener(val clickListener: (asteroid: AsteroidDB) -> Unit) {
    fun onClick(asteroid: AsteroidDB) = clickListener(asteroid)
}