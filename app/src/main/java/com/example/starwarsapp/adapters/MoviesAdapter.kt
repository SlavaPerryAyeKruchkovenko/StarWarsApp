package com.example.starwarsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.R
import com.example.starwarsapp.data.models.Movie
import com.example.starwarsapp.databinding.MovieCardBinding

class MoviesAdapter : ListAdapter<Movie, RecyclerView.ViewHolder>(MyDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieCardHolder(
            MovieCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return (holder as MovieCardHolder).bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.movie_card
    }

    inner class MovieCardHolder(private val binding: MovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) = with(binding) {
            name.text = movie.name
            director.text = movie.name
            producer.text = movie.name
        }
    }

    class MyDiffCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}