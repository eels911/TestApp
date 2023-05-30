package com.example.testapp.main.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.testapp.episodes.presentation.EpisodeNavigationScreen
import com.example.testapp.viewedepisodes.presentation.ViewedNavigationScreen
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.*

class MainViewModel(
    private val navigationCommunication: NavigationCommunication.Mutable,
    canGoBack: CanGoBack,
    dispatchers: Dispatchers,
    communication: GlobalErrorCommunication.Mutable
) : BackPress.Activity.ViewModel<String>(
    canGoBack,
    communication,
    dispatchers
) {

    private val episodeNavigationScreen = EpisodeNavigationScreen()
    private val viewedNavigationScreen = ViewedNavigationScreen()

    init {
        navigationCommunication.map(episodeNavigationScreen)
        chooseItem("Episodes")
    }

    fun observeNavigation(owner: LifecycleOwner, observer: Observer<NavigationScreen>) {
        navigationCommunication.observe(owner, observer)
    }

    fun chooseItem(title: CharSequence) = navigationCommunication.map(
        if (title == "Episodes") episodeNavigationScreen else viewedNavigationScreen
    )
}