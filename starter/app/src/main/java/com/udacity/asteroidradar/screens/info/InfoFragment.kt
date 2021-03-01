package com.udacity.asteroidradar.screens.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private val viewModel: InfoViewModel by lazy {
        ViewModelProvider(this).get(InfoViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentInfoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.openMusicUrlEvent.observe(viewLifecycleOwner, Observer {
            if (it) {
                openMusicUrl()
                viewModel.musicUrlOpened()
            }
        })
        viewModel.openBackgroundUrlEvent.observe(viewLifecycleOwner, Observer {
            if (it) {
                openBackground()
                viewModel.backgroundUrlOpened()
            }
        })
        return binding.root
    }

    private fun openMusicUrl(){
        val openUrlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.musicUrl))
        startActivity(openUrlIntent)
    }

    private fun openBackground(){
        val openUrlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.backgroundUrl))
        startActivity(openUrlIntent)
    }
}