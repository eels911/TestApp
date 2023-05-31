package com.example.testapp.episodes.data

import com.example.testapp.episodes.domain.EpisodeDomain

interface EpisodesCloud {

    fun <T> map(mapper: Mapper<T>): T

    class Empty : EpisodesCloud {
        override fun <T> map(mapper: Mapper<T>): T =
            mapper.map(emptyMap())
    }

    interface Mapper<T> {
        fun map(episodes: Map<String,String>): T

        class Base : Mapper<EpisodeDomain> {

            override fun map(episodes: Map<String,String>): EpisodeDomain {
                val result = episodes.map { Pair(it.key,it.value) }.toList()
                return EpisodeDomain.Base(result)
            }
        }
    }

}