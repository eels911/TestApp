package com.example.testapp.episodes.domain

import com.example.testapp.episodes.presentation.EpisodeUi
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.Interactor


interface EpisodeInteractor {
    suspend fun fetchListOfEpisode(
        atFinish: suspend () -> Unit,
        result: suspend (EpisodeDomain) -> Unit
    )

    class Base(
        private val episodeRepository: EpisodeRepository,
        private val paginationConfig: PaginationConfig,
        handleError: HandleError,
        dispatchers: Dispatchers
    ) : Interactor.Abstract(dispatchers, handleError), EpisodeInteractor {
        override suspend fun fetchListOfEpisode(
            atFinish: suspend () -> Unit,
            result: suspend (EpisodeDomain) -> Unit
        ) = handle(result, atFinish) {
            episodeRepository.requestFreshEpisode(paginationConfig)
        }

    }
}