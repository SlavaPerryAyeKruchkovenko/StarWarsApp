package com.example.starwarsapp.data.models

import com.example.starwarsapp.data.interfaces.Likeable

sealed class StarWarsObject(
    val _id: String,
    isLiked: Boolean
) : Likeable {
    var isLike = isLiked
        private set

    override fun dislike() {
        isLike = false
    }

    override fun like() {
        isLike = true
    }
}