package com.example.starwarsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.R
import com.example.starwarsapp.data.models.Pilot
import com.example.starwarsapp.databinding.PilotCardBinding

class PilotAdapter : ListAdapter<Pilot, RecyclerView.ViewHolder>(MyDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PilotCardHolder(
            PilotCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return (holder as PilotCardHolder).bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.pilot_card
    }

    inner class PilotCardHolder(private val binding: PilotCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pilot: Pilot) = with(binding) {
            character.name.text = pilot.name
            character.sex.text = pilot.sex
            character.starships.text = pilot.starshipCount.toString()
        }
    }

    class MyDiffCallback : DiffUtil.ItemCallback<Pilot>() {

        override fun areItemsTheSame(oldItem: Pilot, newItem: Pilot): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Pilot, newItem: Pilot): Boolean {
            return oldItem == newItem
        }
    }
}