package com.example.starwarsapp.repository.utils

import android.util.Log

object Converter {
    fun convertUrlToPage(url: String): Int {
        return url.split("=").last().toInt()
    }

    fun convertUrlToId(url: String): Int {
        val splits = url.split("/")
        return if (splits.size > 2) {
            splits[splits.size - 2].toInt()
        } else {
            1
        }
    }
}