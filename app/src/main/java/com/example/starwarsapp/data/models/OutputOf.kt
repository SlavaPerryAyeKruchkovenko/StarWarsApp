package com.example.starwarsapp.data.models

sealed class OutputOf<out T> {
    data class Success<out R>(val value: R) : OutputOf<R>()
    data class Failure<out R>(
        val message: String?
    ) : OutputOf<R>()

    data class Error<out R>(val message: String) : OutputOf<R>()
    class Loader<out R> : OutputOf<R>()
    class Nothing<out R> : OutputOf<R>()
}