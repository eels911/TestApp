package com.example.testapp.viewedepisodes.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.testapp.episodes.presentation.EpisodesUi
import com.example.testapp.viewedepisodes.data.ViewedRepository
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BaseViewModel

class ViewedViewModel(
    private val update: UpdateViewedEpisodes.Observe,
    private val repository: ViewedRepository,
    communication: ViewedCommunication,
    dispatchers: Dispatchers
): BaseViewModel<EpisodesUi>(
    communication, dispatchers
) {

    init {
        update()
    }

    fun update() = communication.map(repository.viewedList())

    fun observeUpdate(owner: LifecycleOwner,observer: Observer<Boolean>) =
        update.observe(owner,observer)
}