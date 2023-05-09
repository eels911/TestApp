package com.example.testapp.episodes.presentation.adapter

import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class EpisodeAdapter(
    factoryChain: ViewHolderFactoryChain<ItemUi>
) : GenericAdapter.Base(factoryChain) {

    class Factory {

        fun provide(): EpisodeAdapter {
            val exceptionChain = ViewHolderFactoryChain.Exception<ItemUi>()
            val errorChain = ErrorViewHolderFactoryChain(exceptionChain)
            val emptyChain = EmptyViewHolderFactoryChain(errorChain)
            val progressChain = ProgressViewHolderFactoryChain(emptyChain)
            val episodeChain = EpisodeViewHolderFactoryChain(progressChain)
            return EpisodeAdapter(episodeChain)

        }
    }
}