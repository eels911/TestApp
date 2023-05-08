package com.example.testapp.episodes.presentation

import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.github.johnnysc.coremvvm.presentation.ShowStrategy

class EpisodeNavigationScreen(
    showStrategy: ShowStrategy = ShowStrategy.ADD
) : NavigationScreen(
    "PokemonNavigationScreen",
    EpisodeFragment::class.java,
    showStrategy
)