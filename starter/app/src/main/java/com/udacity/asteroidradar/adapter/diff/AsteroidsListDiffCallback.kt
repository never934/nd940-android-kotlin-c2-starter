package com.udacity.asteroidradar.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.udacity.asteroidradar.db.entity.AsteroidDB

class AsteroidsListDiffCallback : DiffUtil.ItemCallback<AsteroidDB>() {
    override fun areItemsTheSame(oldItem: AsteroidDB, newItem: AsteroidDB): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AsteroidDB, newItem: AsteroidDB): Boolean {
        return oldItem == newItem
    }
}