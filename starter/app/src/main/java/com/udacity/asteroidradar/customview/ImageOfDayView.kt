package com.udacity.asteroidradar.customview

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.base.BaseCustomView
import com.udacity.asteroidradar.databinding.ItemImageOfDayBinding
import com.udacity.asteroidradar.network.response.ImageOfDayResponse


class ImageOfDayView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        BaseCustomView(context, attrs, defStyleAttr) {

    private lateinit var binding: ItemImageOfDayBinding

    init {
        init(attrs, context)
    }

    private fun init(attrs: AttributeSet?, context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.item_image_of_day, this, true)
        this.visibility = View.GONE
    }

    fun setContent(response: ImageOfDayResponse){
        binding.imageResponse = response
        Picasso.with(context).load(Uri.parse(response.url)).into(binding.imageView, object : Callback {
            override fun onSuccess() {
                this@ImageOfDayView.visibility = View.VISIBLE
            }

            override fun onError() {
            }
        })
    }
}