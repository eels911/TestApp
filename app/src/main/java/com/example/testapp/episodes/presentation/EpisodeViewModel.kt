package com.example.testapp.episodes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.testapp.episodes.domain.EpisodeDomain
import com.example.testapp.episodes.domain.EpisodeInteractor
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.presentation.Visibility
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class EpisodeViewModel(
    refreshEpisode: Observe<Unit>,
    canGoBackCallback: CanGoBack.Callback,
    private val episodeInteractor: EpisodeInteractor,
    progressMapper: Mapper<Unit, List<ItemUi>>,
    progressCommunication: ProgressCommunication.Update,
    dispatchers: Dispatchers,
    private val errorMapper: Mapper<String, List<ItemUi>>,
    episodeUiMapper: EpisodeDomain.Mapper<List<ItemUi>>,
    private val errorCommunication: Communication.Mutable<String>,
    communication: Communication.Mutable<List<ItemUi>>
) :
    BackPress.ViewModel<List<ItemUi>>(
        canGoBackCallback,
        communication,
        dispatchers
    ) {
    private var canGoBack = true

    private val atFinish = suspend {
        progressCommunication.map(Visibility.Gone())
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
            communication.map(progressMapper.map(Unit))
            handle {
                episodeInteractor.fetchListOfEpisode(atFinish, result)
            }
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<ItemUi>>) {
        super.observe(owner, observer)
        errorCommunication.observe(owner, { communication.map(errorMapper.map(it)) })
    }

    override fun updateCallbacks() = canGoBackCallback.updateCallback(canGoBackCallbackInner)

}


