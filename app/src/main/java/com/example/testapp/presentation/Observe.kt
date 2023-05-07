package com.example.testapp.presentation

interface Observe<out T : Any> {

    fun observe(communication: (T) -> Unit)
}