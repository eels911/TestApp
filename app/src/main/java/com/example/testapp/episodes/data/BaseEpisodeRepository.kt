package com.example.testapp.episodes.data

import com.example.testapp.episodes.domain.EpisodeDomain
import com.example.testapp.episodes.domain.EpisodeRepository
import com.example.testapp.episodes.domain.PaginationConfig

class BaseEpisodeRepository(
    private val episodeCloudDataSource: EpisodeCloudDataSource,
    private val toDomainMapper: EpisodeResponse.Mapper<EpisodeDomain>,
    private val cacheDataSource: EpisodeCacheDataSource.Mutable
) : EpisodeRepository {
    override suspend fun requestFreshEpisode(paginationConfig: PaginationConfig): EpisodeDomain {
        val cloud = episodeCloudDataSource.requestListOfEpisodes(paginationConfig)
        cacheDataSource.save(cloud)
        return cloud.map(toDomainMapper)
    }

    override suspend fun requestCachedEpisode() = cacheDataSource.read().map(toDomainMapper)
}