package com.udacity.asteroidradar

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.udacity.asteroidradar.databinding.ActivityMainBinding
import com.udacity.asteroidradar.utils.AndroidUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null
    private var currentVideoPosition: Int? = null
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        startVideoBackground()
    }

    private fun startVideoBackground() {
        binding.videoView.setVideoURI(AndroidUtils.getVideoUri())
        binding.videoView.start()
        binding.videoView.setOnPreparedListener {
            mediaPlayer = it
            mediaPlayer?.isLooping = true
            if (currentVideoPosition != 0){
                mediaPlayer?.seekTo(currentVideoPosition ?: 0)
                mediaPlayer?.start()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.videoView.start()
        viewModel.resumeMusic()
    }

    override fun onPause() {
        super.onPause()
        currentVideoPosition = mediaPlayer?.currentPosition
        binding.videoView.pause()
        viewModel.pauseMusic()
    }

    override fun onDestroy() {
        binding.videoView.stopPlayback()
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }
}
