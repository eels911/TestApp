package com.example.testapp.episodes.presentation

import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

class EpisodeUi(
    //  private val episode:String,
    private val name: String
) : ItemUi {
    override fun content(): String = id()

    override fun id(): String = name

    override fun show(vararg views: MyView) {
        //views[0].show(episode)
        views[0].show(name)
    }

    override fun type(): Int = 3
}

class ProgressItemUi : ItemUi {

    override fun content() = id()

    override fun id() = "Progress"

    override fun show(vararg views: MyView) = Unit

    override fun type() = 1

    class BaseMapper : Mapper<Unit, List<ItemUi>> {

        override fun map(data: Unit) = listOf(ProgressItemUi())
    }
}

class ErrorItemUi(
    private val refreshEpisode: RefreshEpisode,
    private val errorMessage: String
) : ItemUi {
    override fun content() = id()

    override fun id() = "ItemError"

    override fun show(vararg views: MyView) {
        views[0].handleClick {
            refreshEpisode.refreshEpisode()
        }
        views[1].show(errorMessage)
    }

    override fun type() = 2

    class BaseMapper(
        private val refreshEpisode: RefreshEpisode
    ) : Mapper<String, List<ItemUi>> {

        override fun map(data: String) = listOf(ErrorItemUi(refreshEpisode, data))
    }

}

class EmptyItemUi(
    private val refreshEpisode: RefreshEpisode
) : ItemUi {

    override fun content() = id()

    override fun id() = "Empty"

    override fun show(vararg views: MyView) {
        views[0].handleClick {
            refreshEpisode.refreshEpisode()
        }
    }

    override fun type() = 0
}