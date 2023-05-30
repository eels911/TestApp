package com.example.testapp.episodes.domain

import com.example.testapp.episodes.presentation.EpisodesUi
import com.example.testapp.viewedepisodes.data.ChangeViewed

interface EpisodeRepository: ChangeViewed {

    suspend fun requestCachedEpisode(paginationConfig: PaginationConfig): EpisodeDomain
}