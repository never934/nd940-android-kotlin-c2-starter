package com.udacity.asteroidradar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.adapter.diff.AsteroidsListDiffCallback
import com.udacity.asteroidradar.databinding.ItemListAsteroidBinding
import com.udacity.asteroidradar.db.entity.AsteroidDB
import com.udacity.asteroidradar.impl.AsteroidListener

class AsteroidsListAdapter(private val clickListener: AsteroidListener) : ListAdapter<AsteroidDB, AsteroidsListAdapter.ViewHolder>(AsteroidsListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemListAsteroidBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: AsteroidListener, item: AsteroidDB) {
            binding.asteroid = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListAsteroidBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


