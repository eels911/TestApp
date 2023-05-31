package com.example.testapp.viewedepisodes.presentation

import android.view.View
import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.github.johnnysc.coremvvm.presentation.ShowStrategy

class ViewedNavigationScreen(
    showStrategy: ShowStrategy = ShowStrategy.REPLACE
) : NavigationScreen(
    "ViewedNavigationScreen",
    ViewedFragment::class.java,
    showStrategy
)