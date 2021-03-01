package com.udacity.asteroidradar.screens

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.ActivityMainBinding
import com.udacity.asteroidradar.utils.AndroidUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null
    private var currentVideoPosition: Int? = null
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        startVideoBackground()
        setupActionBar()
    }

    private fun setupActionBar() {
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.nav_host_fragment))
        appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment))
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment), appBarConfiguration)
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

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(findNavController(R.id.nav_host_fragment), appBarConfiguration)
    }

    override fun onBackPressed() {
        if(supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.backStackEntryCount == 0){
            openExitDialog()
        }else{
            super.onBackPressed()
        }
    }

    private fun openExitDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.exit_dialog_title)
                .setPositiveButton(R.string.common_ok) { _, _ ->
                    finishAffinity()
                }
                .setNegativeButton(android.R.string.cancel, null)
        builder.create()
        builder.show()
    }
}
