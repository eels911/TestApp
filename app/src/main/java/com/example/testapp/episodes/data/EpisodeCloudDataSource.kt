package com.example.testapp.episodes.data

import com.example.testapp.episodes.domain.PaginationConfig
import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError

interface EpisodeCloudDataSource {

    suspend fun requestListOfEpisodes(paginationConfig: PaginationConfig): EpisodeResponse

    class Base(
        private val episodesService: EpisodesService,
        handleError: HandleError
    ) : CloudDataSource.Abstract(handleError), EpisodeCloudDataSource {
        override suspend fun requestListOfEpisodes(
            paginationConfig: PaginationConfig
        ) = handle {
            episodesService.getAllEpisodes(paginationConfig.page())
        }

    }
}