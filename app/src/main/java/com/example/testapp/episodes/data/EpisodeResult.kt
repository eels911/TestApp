package com.example.testapp.episodes.data

import com.google.gson.annotations.SerializedName

interface EpisodeResult {

    interface Mapper<T> {
        fun map(id: Int, episode: String, name: String): T

        class ToString : Mapper<Pair<String,String>> {

            override fun map(id: Int, episode: String, name: String): Pair<String, String> {
                return Pair(episode,name)
            }
        }
    }


    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: Int,
        @SerializedName("name")
        private val name: String,
        @SerializedName("episode")
        private val episode: String,
    ) : EpisodeResult {
        override fun <T> map(mapper: Mapper<T>): T =
            mapper.map(id, episode, name)
    }


}
