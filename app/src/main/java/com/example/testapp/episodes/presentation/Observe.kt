package com.example.testapp.episodes.presentation

interface Observe<out T : Any> {

    fun observe(communication: (T) -> Unit)
}