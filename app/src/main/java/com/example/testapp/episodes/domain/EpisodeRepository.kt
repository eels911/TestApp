package com.example.testapp.episodes.domain

interface EpisodeRepository {

    suspend fun requestFreshEpisode(paginationConfig: PaginationConfig): EpisodeDomain

    suspend fun requestCachedEpisode(): EpisodeDomain
}