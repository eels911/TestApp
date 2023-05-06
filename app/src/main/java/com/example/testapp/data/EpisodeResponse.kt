package com.example.testapp.data

import com.google.gson.annotations.SerializedName

interface EpisodeResponse {
    interface Mapper<T> {
        fun map(count: Int, next: String?, previous: String?,results: List<ResultEpisode>): T
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
        private val results:List<ResultEpisode>
    ): EpisodeResponse{
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(count, next, previous, results)

    }
}
