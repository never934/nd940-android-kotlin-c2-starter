package com.udacity.asteroidradar.di

import com.udacity.asteroidradar.db.AsteroidsDao
import com.udacity.asteroidradar.di.module.RoomModule
import dagger.Component


@EnvScope
@Component(dependencies = [AndroidComponent::class], modules = [RoomModule::class])
interface RoomComponent {
    val asteroidsDao: AsteroidsDao
}