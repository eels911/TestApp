package com.example.testapp.viewedepisodes.data

import com.example.testapp.episodes.data.EpisodeResponse
import com.example.testapp.episodes.data.EpisodeResult
import com.example.testapp.episodes.data.Viewed
import com.example.testapp.episodes.presentation.EpisodeUi
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface ViewedMapper : EpisodeResponse.Mapper<List<ItemUi>> {

    class Base(
        private val viewed: Viewed,
        private val changeViewed: ChangeViewed
    ) : ViewedMapper {

        override fun map(results: List<EpisodeResult>): List<ItemUi> {
            val mapper = EpisodeResult.Mapper.ToString()
            val filteredList = results.map { it.map(mapper) }.filter { viewed.viewed(it.first) }
            return filteredList.map {
                EpisodeUi(
                    it.first,
                    it.second,
                    true,
                    changeViewed
                )
            }
        }
    }
}


