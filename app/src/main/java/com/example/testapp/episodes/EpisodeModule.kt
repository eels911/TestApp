package com.example.testapp.episodes

import com.example.testapp.episodes.data.BaseEpisodeRepository
import com.example.testapp.episodes.data.EpisodeCloudDataSource
import com.example.testapp.episodes.data.EpisodeResponse
import com.example.testapp.episodes.data.EpisodesCache
import com.example.testapp.episodes.data.ProvideEpisodeService
import com.example.testapp.episodes.data.ViewedCacheDataSource
import com.example.testapp.episodes.domain.EpisodeInteractor
import com.example.testapp.episodes.domain.PaginationConfig
import com.example.testapp.episodes.presentation.EpisodeCommunication
import com.example.testapp.episodes.presentation.EpisodeUiMapper
import com.example.testapp.episodes.presentation.EpisodeViewModel
import com.example.testapp.episodes.presentation.ErrorCommunication
import com.example.testapp.episodes.presentation.ErrorItemUi
import com.example.testapp.episodes.presentation.ProgressItemUi
import com.example.testapp.episodes.presentation.RefreshEpisode
import com.example.testapp.viewedepisodes.data.ChangeViewed
import com.example.testapp.viewedepisodes.presentation.UpdateViewedEpisodes
import com.github.johnnysc.coremvvm.domain.HandleDomainError
import com.github.johnnysc.coremvvm.presentation.HandleUiError
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

class EpisodeModule(
    private val coreModule: CoreModule,
    private val cacheDataSource: ViewedCacheDataSource,
    private val cache: EpisodesCache.Save
) : Module<EpisodeViewModel> {
    override fun viewModel(): EpisodeViewModel {
        val episodeRepository = BaseEpisodeRepository(
            EpisodeCloudDataSource.Base(
                ProvideEpisodeService.Base(coreModule).provide(),
                HandleDomainError()
            ),
            EpisodeResponse.Mapper.ToDomain(),
            cacheDataSource,
            cache
        )
        val paginationConfig = PaginationConfig.Base()
        val errorCommunication = ErrorCommunication()
        val handleUiError = HandleUiError(coreModule, errorCommunication)
        val dispatchers = coreModule.dispatchers()
        val episodeInteractor = EpisodeInteractor.Base(
            episodeRepository,
            paginationConfig,
            handleUiError,
            dispatchers
        )
        val update = UpdateViewedEpisodes.Base()
        val viewed = ChangeViewed.Base(cacheDataSource, update)
        val refreshEpisode = RefreshEpisode.Base()
        val errorMapper = ErrorItemUi.BaseMapper(refreshEpisode)
        val progressMapper = ProgressItemUi.BaseMapper()
        val episodeCommunication = EpisodeCommunication.Base()
        val episodeUiMapper = EpisodeUiMapper(refreshEpisode, cacheDataSource, viewed)

        return EpisodeViewModel(
            refreshEpisode,
            coreModule.provideCanGoBack(),
            episodeInteractor,
            progressMapper,
            coreModule.provideProgressCommunication(),

            dispatchers,
            errorMapper,
            episodeUiMapper,
            errorCommunication,
            episodeCommunication
        )
    }
}