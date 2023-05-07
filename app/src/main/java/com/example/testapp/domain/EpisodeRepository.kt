package com.example.testapp.domain

interface EpisodeRepository {

    suspend fun requestFreshEpisode(paginationConfig: PaginationConfig): EpisodeDomain

    suspend fun requestCachedEpisode(): EpisodeDomain
}