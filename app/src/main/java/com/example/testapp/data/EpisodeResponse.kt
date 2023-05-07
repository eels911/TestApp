package com.example.testapp.data

import com.example.testapp.domain.EpisodeDomain
import com.google.gson.annotations.SerializedName

interface EpisodeResponse {
    interface Mapper<T> {
        fun map(count: Int, next: String?, previous: String?, results: List<EpisodeResult>): T

        class ToDomain : Mapper<EpisodeDomain> {

            override fun map(
                count: Int,
                next: String?,
                previous: String?,
                results: List<EpisodeResult>
            ): EpisodeDomain {
                val mapper = EpisodeResult.Mapper.ToDomain()
                val listOfEpisode = results.map { it.map(mapper) }
                return EpisodeDomain.Base(listOfEpisode)
            }
        }
    }

    fun <T> map(mapper: Mapper<T>): T


    data class Base(
        @SerializedName("count")
        private val count: Int,
        @SerializedName("next")
        private val next: String?,
        @SerializedName("previous")
        private val previous: String?,
        @SerializedName("results")
        private val results: List<EpisodeResult>
    ) : EpisodeResponse {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(count, next, previous, results)
    }

    class Empty : EpisodeResponse {

        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(count = 0, next = "", previous = "", emptyList())
        }
    }
}
