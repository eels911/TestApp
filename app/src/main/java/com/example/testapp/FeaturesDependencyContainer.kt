package com.example.testapp

import androidx.lifecycle.ViewModel
import com.example.testapp.episodes.presentation.EpisodeViewModel
import com.example.testapp.main.MainModule
import com.example.testapp.main.presentation.MainViewModel
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.Module

class FeaturesDependencyContainer(
    private val coreModule: CoreModule,
    private val dependencyContainer: DependencyContainer
) : DependencyContainer {

    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        MainViewModel::class.java -> MainModule(coreModule)
        EpisodeViewModel::class.java -> EpisodeModule(coreModule)
        else -> dependencyContainer.module(clazz)
    }
}
