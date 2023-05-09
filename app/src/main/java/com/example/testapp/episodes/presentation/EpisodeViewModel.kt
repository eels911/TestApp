package com.example.testapp.episodes.presentation

import android.content.ClipData.Item
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.testapp.episodes.domain.EpisodeDomain
import com.example.testapp.episodes.domain.EpisodeInteractor
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.*
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class EpisodeViewModel(
    canGoBackCallback: CanGoBack.Callback,
    refreshEpisode: Observe<Unit>,
    episodeUiMapper: EpisodeDomain.Mapper<List<ItemUi>>,
    private val errorMapper: Mapper<String, List<ItemUi>>,
    progressMapper: Mapper<Unit,List<ItemUi>>,
    episodeInteractor: EpisodeInteractor,
    dispatchers: Dispatchers,
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
            handle { episodeInteractor.fetchListOfEpisode(atFinish, result) }
        }
    }

    override fun updateCallbacks() = canGoBackCallback.updateCallback(canGoBackCallbackInner)

    override fun observe(owner: LifecycleOwner, observer: Observer<List<ItemUi>>) {
        super.observe(owner, observer)
        errorCommunication.observe(owner,{communication.map(errorMapper.map(it))})
    }

}


