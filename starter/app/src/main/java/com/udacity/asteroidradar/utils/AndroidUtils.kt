package com.udacity.asteroidradar.utils

import android.net.Uri
import com.udacity.asteroidradar.AppClass
import com.udacity.asteroidradar.R

class AndroidUtils {
    companion object{
        fun getVideoUri() : Uri {
            return Uri.parse("android.resource://" + AppClass.androidComponent.context.packageName + "/" + R.raw.space_stars)
        }
    }
}