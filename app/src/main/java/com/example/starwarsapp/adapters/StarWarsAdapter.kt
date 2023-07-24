package com.example.starwarsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.R
import com.example.starwarsapp.data.models.Character
import com.example.starwarsapp.data.models.Planet
import com.example.starwarsapp.data.models.StarWarsObject
import com.example.starwarsapp.data.models.Starship
import com.example.starwarsapp.databinding.CharacterCardBinding
import com.example.starwarsapp.databinding.PlanetCardBinding
import com.example.starwarsapp.databinding.StarshipCardBinding

class StarWarsAdapter : ListAdapter<StarWarsObject, RecyclerView.ViewHolder>(MyDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.character_card -> {
                val binding = CharacterCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                CharacterCardHolder(parent.context, binding)
            }
            R.layout.starship_card -> {
                val binding = StarshipCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                StarshipCardHolder(parent.context, binding)
            }
            R.layout.planet_card -> {
                val binding = PlanetCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                PlanetCardHolder(parent.context, binding)
            }
            else -> throw IllegalStateException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.character_card -> (holder as CharacterCardHolder).bind(getItem(position) as Character)
            R.layout.starship_card -> (holder as StarshipCardHolder).bind(getItem(position) as Starship)
            R.layout.planet_card -> (holder as PlanetCardHolder).bind(getItem(position) as Planet)
            else -> throw IllegalStateException("Unknown item view type ${holder.itemViewType}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Character -> R.layout.character_card
            is Starship -> R.layout.starship_card
            is Planet -> R.layout.planet_card
            else -> Int.MAX_VALUE
        }
    }

    inner class CharacterCardHolder(
        private val context: Context,
        private val binding: CharacterCardBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private val adapter = MoviesAdapter()
        fun bind(starWarsObject: Character) = with(binding) {
            character.name.text = starWarsObject.name
            character.sex.text = starWarsObject.sex
            character.starships.text = starWarsObject.starshipsCount.toString()
            movies.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            movies.adapter = adapter
            adapter.submitList(starWarsObject.films)
        }
    }

    inner class StarshipCardHolder(
        private val context: Context,
        private val binding: StarshipCardBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private val moviesAdapter = MoviesAdapter()
        private val pilotsAdapter = PilotAdapter()
        fun bind(starWarsObject: Starship) = with(binding) {
            starship.name.text = starWarsObject.name
            starship.model.text = starWarsObject.model
            starship.manufacturer.text = starWarsObject.manufacturer
            movies.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            movies.adapter = moviesAdapter
            moviesAdapter.submitList(starWarsObject.films)

            pilots.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            pilots.adapter = pilotsAdapter
            pilotsAdapter.submitList(starWarsObject.pilots)
        }
    }

    inner class PlanetCardHolder(
        private val context: Context,
        private val binding: PlanetCardBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private val adapter = MoviesAdapter()
        fun bind(starWarsObject: Planet) = with(binding) {
            planet.name.text = starWarsObject.name
            planet.diameter.text = starWarsObject.diameter.toString()
            planet.population.text = starWarsObject.population.toString()
            movies.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            movies.adapter = adapter
            adapter.submitList(starWarsObject.films)
        }
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