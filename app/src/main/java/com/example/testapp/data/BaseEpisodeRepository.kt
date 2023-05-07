package com.example.testapp.data

import com.example.testapp.domain.EpisodeDomain
import com.example.testapp.domain.EpisodeRepository
import com.example.testapp.domain.PaginationConfig

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