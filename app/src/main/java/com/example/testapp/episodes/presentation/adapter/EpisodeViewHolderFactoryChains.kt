package com.example.testapp.episodes.presentation.adapter

import android.view.ViewGroup
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class EmptyViewHolderFactoryChain(
    viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : AbstractViewHolderFactoryChain(viewHolderFactoryChain, viewType = 0, ::EmptyViewHolder)

class ProgressViewHolderFactoryChain(
    viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : AbstractViewHolderFactoryChain(viewHolderFactoryChain, viewType = 1, ::ProgressViewHolder)

class ErrorViewHolderFactoryChain(
    viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : AbstractViewHolderFactoryChain(
    viewHolderFactoryChain,
    viewType = 2,
    ::ErrorViewHolder
)

class EpisodeViewHolderFactoryChain(
    viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>
) : AbstractViewHolderFactoryChain(viewHolderFactoryChain, viewType = 3, ::EpisodeViewHolder)

abstract class AbstractViewHolderFactoryChain(
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>,
    private val viewType: Int,
    private val viewHolder: (ViewGroup) -> AbstractViewHolder
) : ViewHolderFactoryChain<ItemUi> {

    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> =
        if (viewType == this.viewType) viewHolder.invoke(parent)
        else viewHolderFactoryChain.viewHolder(parent, viewType)
}