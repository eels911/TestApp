package com.example.testapp.episodes.domain

interface PaginationConfig {
    fun page(): Int


    class Base : PaginationConfig {

        override fun page() = 3

    }
}
