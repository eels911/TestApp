package com.example.testapp.main.presentation

import androidx.fragment.app.FragmentManager
import com.example.testapp.episodes.presentation.EpisodeNavigationScreen
import com.github.johnnysc.coremvvm.presentation.FragmentFactory
import com.github.johnnysc.coremvvm.presentation.NavigationScreen

class BaseFragmentFactory(
    containerId: Int,
    fragmentManager: FragmentManager,
) : FragmentFactory.Abstract(
    containerId,
    fragmentManager,
) {

    override val screens: List<NavigationScreen> = listOf(
        EpisodeNavigationScreen()
    )
}