package com.example.testapp.viewedepisodes

import com.example.testapp.episodes.data.EpisodesCache
import com.example.testapp.episodes.data.ViewedCacheDataSource
import com.example.testapp.viewedepisodes.data.ChangeViewed
import com.example.testapp.viewedepisodes.data.ViewedMapper
import com.example.testapp.viewedepisodes.data.ViewedRepository
import com.example.testapp.viewedepisodes.presentation.UpdateViewedEpisodes
import com.example.testapp.viewedepisodes.presentation.ViewedCommunication
import com.example.testapp.viewedepisodes.presentation.ViewedViewModel
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

class ViewedModule(
    private val coreModule: CoreModule,
    private val cacheDataSource: ViewedCacheDataSource,
    private val cache: EpisodesCache.Read
): Module<ViewedViewModel> {
    override fun viewModel(): ViewedViewModel {
        val communication = ViewedCommunication.Base()
        val update = UpdateViewedEpisodes.Base()
        return ViewedViewModel(
            update,ViewedRepository.Base(
                cache,
                ViewedMapper.Base(
                    cacheDataSource,
                    ChangeViewed.Base(cacheDataSource,update)
                )
            ),
            communication,
            coreModule.dispatchers()
        )
    }
}