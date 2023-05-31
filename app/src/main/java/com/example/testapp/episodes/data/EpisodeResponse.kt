package com.example.testapp.episodes.data

import com.example.testapp.episodes.domain.EpisodeDomain
import com.google.gson.annotations.SerializedName

interface EpisodeResponse {

    data class Base(
        @SerializedName("results")
        private val results: List<EpisodeResult.Base>
    ) : EpisodeResponse {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(results)
    }
    interface Mapper<T> {
        fun map(results: List<EpisodeResult>): T

        class ToDomain : Mapper<EpisodeDomain> {

            override fun map(
                results: List<EpisodeResult>
            ): EpisodeDomain {
                val mapper = EpisodeResult.Mapper.ToString()
                val listOfEpisode = results.map { it.map(mapper) }
                return EpisodeDomain.Base(listOfEpisode)
            }
        }
    }

    fun <T> map(mapper: Mapper<T>): T



    class Empty : EpisodeResponse {

        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(emptyList())
        }
    }
}
