package com.example.testapp.data

import com.github.johnnysc.coremvvm.data.MakeService
import com.github.johnnysc.coremvvm.data.ProvideRetrofitBuilder

interface ProvideEpisodeService {
    fun provide(): EpisodesService

    class Base(
        retrofitBuilder: ProvideRetrofitBuilder
    ) : MakeService.Abstract(retrofitBuilder), ProvideEpisodeService {

        override fun baseUrl(): String = "https://rickandmortyapi.com/api/"

        override fun provide(): EpisodesService = service(EpisodesService::class.java)
    }
}