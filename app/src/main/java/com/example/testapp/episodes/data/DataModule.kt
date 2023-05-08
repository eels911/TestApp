package com.example.testapp.episodes.data

import com.example.testapp.episodes.domain.EpisodeRepository
import com.github.johnnysc.coremvvm.domain.HandleDomainError
import com.github.johnnysc.coremvvm.sl.CoreModule

class DataModule(
    private val coreModule: CoreModule
) {
    fun provideRepository(): EpisodeRepository {
        val episodeService = ProvideEpisodeService.Base(coreModule).provide()
        val handleError = HandleDomainError()
        val episodeCloudDataSource = EpisodeCloudDataSource.Base(episodeService, handleError)
        val toDomainMapper = EpisodeResponse.Mapper.ToDomain()
        val cachedDataSource = EpisodeCacheDataSource.Base()
        return BaseEpisodeRepository(
            episodeCloudDataSource,
            toDomainMapper,
            cachedDataSource
        )
    }
}