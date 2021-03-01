package com.udacity.asteroidradar.adapter

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.customview.ImageOfDayView
import com.udacity.asteroidradar.customview.ViewField
import com.udacity.asteroidradar.network.response.ImageOfDayResponse

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
        view.setContent(it)
    }
}


@BindingAdapter("content")
fun bindViewFieldContent(viewField: ViewField, text: String?) {
    viewField.setContent(text)
}
