package com.example.testapp.episodes.presentation

import com.example.testapp.episodes.domain.EpisodeDomain
import com.example.testapp.episodes.presentation.RefreshEpisode
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class BaseEpisodeUiMapper(
    private val refreshEpisode: RefreshEpisode
):EpisodeDomain.Mapper<List<ItemUi>> {
    override fun map(input: List<String>): List<ItemUi> {
        return listOf(EmptyItemUi(refreshEpisode))
    }
}