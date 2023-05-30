package com.example.testapp.episodes.domain

import com.example.testapp.episodes.presentation.EpisodesUi
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.Interactor


interface EpisodeInteractor {
    suspend fun fetchListOfEpisode(
        atFinish: suspend () -> Unit,
        result: suspend (EpisodesUi) -> Unit
    )

    class Base(
        private val mapper: EpisodeDomain.Mapper<EpisodesUi>,
        private val episodeRepository: EpisodeRepository,
        private val paginationConfig: PaginationConfig,
        handleError: HandleError,
        dispatchers: Dispatchers
    ) : Interactor.Abstract(dispatchers, handleError), EpisodeInteractor {
        override suspend fun fetchListOfEpisode(
            atFinish: suspend () -> Unit,
            result: suspend (EpisodesUi) -> Unit
        ) = handle(result, atFinish) {
            val data = episodeRepository.requestCachedEpisode(paginationConfig)
            return@handle data.map(mapper)
        }

    }
}