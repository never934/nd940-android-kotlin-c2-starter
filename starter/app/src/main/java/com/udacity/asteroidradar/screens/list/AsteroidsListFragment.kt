package com.udacity.asteroidradar.screens.list

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.adapter.AsteroidsListAdapter
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.enums.ResponseStatus
import com.udacity.asteroidradar.impl.AsteroidListener
import com.udacity.asteroidradar.screens.MainViewModel
import com.udacity.asteroidradar.utils.SharedPreferencesUtils

class AsteroidsListFragment : Fragment() {

    private val viewModel: AsteroidsListViewModel by lazy {
        ViewModelProvider(this).get(AsteroidsListViewModel::class.java)
    }
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)
        val listAdapter = AsteroidsListAdapter(AsteroidListener {
            findNavController().navigate(AsteroidsListFragmentDirections.actionShowDetail(it))
        })
        binding.asteroidRecycler.adapter = listAdapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.update()
            binding.swipeRefreshLayout.isRefreshing = true
        }
        viewModel.asteroids.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
            binding.swipeRefreshLayout.isRefreshing = false
        })
        viewModel.responseStatus.observe(viewLifecycleOwner, Observer {
            when(it){
                ResponseStatus.loading -> binding.statusLoadingWheel.isVisible = true
                ResponseStatus.error -> binding.statusLoadingWheel.isVisible = false
                ResponseStatus.done -> binding.statusLoadingWheel.isVisible = false
                else -> binding.statusLoadingWheel.isVisible = false
            }
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
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
                    mainViewModel.muteMusic()
                }else{
                    item.setIcon(R.drawable.ic_baseline_volume_up_24)
                    mainViewModel.unmuteMusic()
                }
            }
            R.id.infoFragment -> {
                findNavController().navigate(AsteroidsListFragmentDirections.actionMainFragmentToInfoFragment())
            }
        }
        viewModel.filter(item.itemId)
        return false
    }
}
