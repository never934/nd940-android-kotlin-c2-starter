package com.udacity.asteroidradar.screens.detail


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentDetailBinding
import com.udacity.asteroidradar.screens.MainActivity
import com.udacity.asteroidradar.screens.MainViewModel
import com.udacity.asteroidradar.utils.SharedPreferencesUtils


class AsteroidDetailFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        val asteroid = AsteroidDetailFragmentArgs.fromBundle(requireArguments()).selectedAsteroid
        (requireActivity() as MainActivity).supportActionBar?.title = asteroid.codename
        binding.asteroid = asteroid
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val volumeItem = menu.findItem(R.id.action_volume)
        volumeItem.setIcon(if(SharedPreferencesUtils().getVolumeEnabledState())R.drawable.ic_baseline_volume_up_24 else R.drawable.ic_baseline_volume_off_24)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_volume -> {
                if(SharedPreferencesUtils().getVolumeEnabledState()){
                    item.setIcon(R.drawable.ic_baseline_volume_off_24)
                    viewModel.muteMusic()
                }else{
                    item.setIcon(R.drawable.ic_baseline_volume_up_24)
                    viewModel.unmuteMusic()
                }
            }
        }
        return false
    }
}
