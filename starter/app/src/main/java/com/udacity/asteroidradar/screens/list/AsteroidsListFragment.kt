package com.udacity.asteroidradar.screens.list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.adapter.AsteroidsListAdapter
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.impl.AsteroidListener
import com.udacity.asteroidradar.screens.MainViewModel

class AsteroidsListFragment : Fragment() {

    private val viewModel: AsteroidsListViewModel by lazy {
        ViewModelProvider(this).get(AsteroidsListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)
        val listAdapter = AsteroidsListAdapter(AsteroidListener {

        })
        binding.asteroidRecycler.adapter = listAdapter
        viewModel.asteroids.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
