package com.example.testapp.episodes.presentation

import com.example.testapp.viewedepisodes.data.ChangeViewed
import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

class EpisodeUi(
    private val id: String,
    private val episode: String,
    private val isViewed: Boolean,
    private val viewed: ChangeViewed
) : ItemUi {
    override fun content(): String = id() + episode + isViewed


    override fun show(vararg views: MyView) {
        views[0].show("$id  $episode")
        views[0].check(isViewed)
        views[0].handleClick {
            viewed.changeViewed(id)
        }
    }

    override fun id(): String = episode

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