package com.example.testapp.episodes.data

import com.example.testapp.episodes.domain.EpisodeDomain
import com.example.testapp.episodes.domain.EpisodeRepository
import com.example.testapp.episodes.domain.PaginationConfig

class BaseEpisodeRepository(
    private val episodeCloudDataSource: EpisodeCloudDataSource,
    private val toDomainMapper: EpisodeResponse.Mapper<EpisodeDomain>,
    private val cacheDataSource: ViewedCacheDataSource,
    private val cache: EpisodesCache.Save
) : EpisodeRepository {
    override suspend fun requestCachedEpisode(paginationConfig: PaginationConfig): EpisodeDomain {
        val cloud = episodeCloudDataSource.requestListOfEpisodes(paginationConfig)
        cache.save(cloud)
        return cloud.map(toDomainMapper)
    }

    override fun changeViewed(id: String) = cacheDataSource.changeViewed(id)

}