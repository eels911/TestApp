package com.example.testapp.presentation

import com.example.testapp.domain.EpisodeDomain
import com.example.testapp.domain.EpisodeInteractor
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class EpisodeViewModel(
    refreshEpisode: Observe<Unit>,
    private val episodeUiMapper: EpisodeDomain.Mapper<List<ItemUi>>,
    episodeInteractor: EpisodeInteractor,
    canGoBackCallback: CanGoBack.Callback,
    dispatchers: Dispatchers,
    communication: Communication.Mutable<List<ItemUi>>

) :
    BackPress.ViewModel<List<ItemUi>>(
        canGoBackCallback,
        communication,
        dispatchers
    ) {
    private var canGoBack = true

    private val atFinish = suspend {
        canGoBack = true
    }

    private val canGoBackCallbackInner = object : CanGoBack {
        override fun canGoBack() = canGoBack
    }
    private val result: suspend (EpisodeDomain) -> Unit = { episodeDomain ->
        val uiState = episodeDomain.map(episodeUiMapper)
        communication.map(uiState)
    }

    init {
        canGoBack = false
        refreshEpisode.observe {
            handle { episodeInteractor.fetchListOfEpisode(atFinish, result) }
        }
    }

    override fun updateCallbacks() = canGoBackCallback.updateCallback(canGoBackCallbackInner)

}


