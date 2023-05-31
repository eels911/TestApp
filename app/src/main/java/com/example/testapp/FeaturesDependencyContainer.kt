package com.example.testapp

import androidx.lifecycle.ViewModel
import com.example.testapp.episodes.EpisodeModule
import com.example.testapp.episodes.data.EpisodesCache
import com.example.testapp.episodes.data.ViewedCacheDataSource
import com.example.testapp.episodes.presentation.EpisodeViewModel
import com.example.testapp.main.MainModule
import com.example.testapp.main.presentation.MainViewModel
import com.example.testapp.viewedepisodes.ViewedModule
import com.example.testapp.viewedepisodes.data.ViewedCache
import com.example.testapp.viewedepisodes.presentation.ViewedViewModel
import com.github.johnnysc.coremvvm.data.PreferenceDataStore
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.Module

class FeaturesDependencyContainer(
    private val coreModule: CoreModule,
    private val dependencyContainer: DependencyContainer
) : DependencyContainer {

    private val viewedCacheDataSource = ViewedCacheDataSource.Base(
        ViewedCache.Base(
            PreferenceDataStore.Base(coreModule.sharedPreferences(PREFS_KEY))
        )
    )

    private val cache = EpisodesCache.Base()
    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        MainViewModel::class.java -> MainModule(coreModule)
        EpisodeViewModel::class.java -> EpisodeModule(coreModule,
            viewedCacheDataSource,
            cache)
        ViewedViewModel::class.java -> ViewedModule(
            coreModule,
            viewedCacheDataSource,
            cache
        )
        else -> dependencyContainer.module(clazz)
    }

    companion object {
        private const val PREFS_KEY = "viewed"
    }
}
