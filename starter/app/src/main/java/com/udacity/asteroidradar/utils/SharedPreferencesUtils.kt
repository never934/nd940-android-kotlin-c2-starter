package com.udacity.asteroidradar.utils

import android.content.Context
import com.udacity.asteroidradar.AppClass
import com.udacity.asteroidradar.Constants

class SharedPreferencesUtils {

    fun saveVolumeEnabledState(state: Boolean){
        AppClass.androidComponent.context
                .getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(Constants.KEY_VOLUME_STATE, state)
                .apply()
    }

    fun getVolumeEnabledState() : Boolean{
        return AppClass.androidComponent.context
                .getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
                .getBoolean(Constants.KEY_VOLUME_STATE, true)
    }
}