package com.example.testapp.episodes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.testapp.R
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyButton
import com.github.johnnysc.coremvvm.presentation.adapter.MyCheckBox
import com.github.johnnysc.coremvvm.presentation.adapter.MyTextView

class EmptyViewHolder(parent: ViewGroup) : RefreshViewHolder(parent, R.layout.item_empty)

class EpisodeViewHolder(parent: ViewGroup) : AbstractViewHolder(parent, R.layout.item_episode) {
    override fun bind(item: ItemUi) {
        item.show(itemView.findViewById<MyTextView>(R.id.number))
        item.show(itemView.findViewById<MyCheckBox>(R.id.markViewed))
    }
}

class ErrorViewHolder(
    parent: ViewGroup
) : AbstractViewHolder(parent, R.layout.item_error) {

    override fun bind(item: ItemUi) {
        item.show(
            itemView.findViewById<MyButton>(R.id.refresh),
            itemView.findViewById<MyTextView>(R.id.text)
        )
    }
}

class ProgressViewHolder(
    parent: ViewGroup
) : AbstractViewHolder(parent, R.layout.item_progress)


abstract class RefreshViewHolder(
    parent: ViewGroup,
    @LayoutRes layoutId: Int
) : AbstractViewHolder(parent, layoutId) {

    override fun bind(item: ItemUi) {
        item.show(itemView.findViewById<MyButton>(R.id.refresh))
    }
}

abstract class AbstractViewHolder(
    parent: ViewGroup,
    @LayoutRes layoutId: Int
) : GenericViewHolder<ItemUi>(
    LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
) {
    override fun bind(item: ItemUi) = Unit
}
