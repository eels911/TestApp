package com.example.testapp.episodes.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

class EpisodeUi(
    private val episode:String,
    private val name:String
): ItemUi{
    override fun content(): String = id()

    override fun id(): String = episode

    override fun show(vararg views: MyView) {
        views[0].show(episode)
        views[1].show(name)
    }

    override fun type(): Int = 4

}