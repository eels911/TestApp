package com.example.testapp.episodes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.testapp.episodes.domain.EpisodeDomain
import com.example.testapp.episodes.domain.EpisodeInteractor
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.BaseViewModel
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.presentation.Visibility
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class EpisodeViewModel(
    refreshEpisode: Observe<Unit>,
    canGoBackCallback: CanGoBack.Callback,
    private val episodeInteractor: EpisodeInteractor,
   // private val errorMapper: ErrorItemUi.BaseMapper,
    progressMapper: Mapper<Unit, List<ItemUi>>,
    progressCommunication: ProgressCommunication.Update,
    dispatchers: Dispatchers,
 //   private val errorCommunication: Communication.Mutable<String>,
    communication: EpisodeCommunication
) :
    BackPress.ViewModel<EpisodesUi>(
        canGoBackCallback,
        communication,
        dispatchers
    ) {
    private var canGoBack = true

    private val atFinish = suspend {
        progressCommunication.map(Visibility.Gone())
        canGoBack= true
    }

    private val canGoBackCallbackInner = object : CanGoBack {
        override fun canGoBack() = canGoBack
    }


    init {
        canGoBack = false
        refreshEpisode.observe {
            progressCommunication.map(Visibility.Visible())
            handle {
                episodeInteractor.fetchListOfEpisode(atFinish) { communication.map(it) }
            }
        }
        }


  override fun updateCallbacks() = canGoBackCallback.updateCallback(canGoBackCallbackInner)



}


