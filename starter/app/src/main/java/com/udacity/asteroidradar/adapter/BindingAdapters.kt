package com.udacity.asteroidradar.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.customview.ImageOfDayView
import com.udacity.asteroidradar.customview.ViewField
import com.udacity.asteroidradar.network.response.ImageOfDayResponse
import com.udacity.asteroidradar.utils.CalendarUtils

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("responseImage")
fun bindUrlImage(view: ImageOfDayView, response: ImageOfDayResponse?) {
    response?.let {
        if (response.mediaType == Constants.NASA_IMAGE_OF_DAY_TYPE) {
            view.setContent(it)
        }
    }
}


@BindingAdapter("content")
fun bindViewFieldContent(viewField: ViewField, text: String?) {
    viewField.setContent(text)
}

@BindingAdapter("date")
fun bindTextFieldDate(view: TextView, text: String?) {
    text?.let {
        view.text = CalendarUtils.getViewDate(it)
    }
}
