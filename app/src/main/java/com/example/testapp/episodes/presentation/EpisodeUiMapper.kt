package com.example.testapp.episodes.presentation

import com.example.testapp.episodes.data.ViewedCacheDataSource
import com.example.testapp.episodes.domain.EpisodeDomain
import com.example.testapp.viewedepisodes.data.ChangeViewed
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class EpisodeUiMapper(
    private val refreshEpisode: RefreshEpisode,
    private val cacheDataSource: ViewedCacheDataSource,
    private val viewed: ChangeViewed
) : EpisodeDomain.Mapper<List<ItemUi>> {
    override fun map(input: List<Pair<String, String>>): List<ItemUi> {
        return when (input.isEmpty()) {
            true -> listOf(EmptyItemUi(refreshEpisode))
            false -> input.map { name ->
                EpisodeUi(
                    name.first,
                    name.second,
                    cacheDataSource.viewed(name.first),
                    viewed
                )
            }
        }
    }
}