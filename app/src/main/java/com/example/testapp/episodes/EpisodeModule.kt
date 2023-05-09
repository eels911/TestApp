package com.example.testapp.episodes

import com.example.testapp.episodes.data.DataModule
import com.example.testapp.episodes.domain.EpisodeInteractor
import com.example.testapp.episodes.domain.PaginationConfig
import com.example.testapp.episodes.presentation.*
import com.github.johnnysc.coremvvm.presentation.HandleUiError
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

class EpisodeModule(
    private val coreModule: CoreModule
): Module<EpisodeViewModel> {
    override fun viewModel(): EpisodeViewModel {
        val episodeRepository = DataModule(coreModule).provideRepository()
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
        val refreshEpisode = RefreshEpisode.Base()
        val episodeUiMapper = BaseEpisodeUiMapper(refreshEpisode)
        val errorMapper = ErrorItemUi.BaseMapper(refreshEpisode)
        val progressMapper = ProgressItemUi.BaseMapper()
        val episodeCommunication = EpisodeCommunication.Base()

        return EpisodeViewModel(
            coreModule.provideCanGoBack(),
            refreshEpisode,
            episodeUiMapper,
            errorMapper,
            progressMapper,
            episodeInteractor,
            dispatchers,
            errorCommunication,
            episodeCommunication
        )
    }
}