package com.example.starwarsapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.data.models.StarWarsObject

class StarWarsAdapter : ListAdapter<StarWarsObject, RecyclerView.ViewHolder>(MyDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class MyDiffCallback : DiffUtil.ItemCallback<StarWarsObject>() {

        override fun areItemsTheSame(oldItem: StarWarsObject, newItem: StarWarsObject): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: StarWarsObject, newItem: StarWarsObject): Boolean {
            return oldItem == newItem
        }
    }
}