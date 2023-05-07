package com.example.testapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodesService {
    @GET("episode/")
    suspend fun getAllEpisodes(@Query("page") page: Int? = null): EpisodeResponse.Base
}