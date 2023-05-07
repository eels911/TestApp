package com.example.testapp.domain

interface EpisodeDomain {
    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<out T : Any> {

        fun map(input: List<String>): T
    }

    data class Base(
        private val episodes: List<String>
    ) : EpisodeDomain {

        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.map(episodes)
    }
}
