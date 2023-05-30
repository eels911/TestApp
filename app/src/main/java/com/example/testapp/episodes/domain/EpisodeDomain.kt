package com.example.testapp.episodes.domain

import com.example.testapp.episodes.data.ViewedCacheDataSource
import com.example.testapp.episodes.presentation.EpisodeUi
import com.example.testapp.episodes.presentation.EpisodesUi
import com.example.testapp.viewedepisodes.data.ChangeViewed
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface EpisodeDomain {
    fun <T : Any> map(mapper: Mapper<T>): T

    class Base(private val list: List<Pair<String,String>>):EpisodeDomain{
        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.map(list)

    }
    interface Mapper<out T : Any> {

        fun map(input: List<Pair<String, String>>): T

        class Base(
            private val cacheDataSource: ViewedCacheDataSource,
            private val viewed: ChangeViewed
        )
            : Mapper<EpisodesUi>
         {
            override fun map(input: List<Pair<String, String>>): EpisodesUi {
                val episodesList = mutableListOf<ItemUi>()
                episodesList.addAll(input.map {
                    EpisodeUi(it.first,it.second, cacheDataSource.viewed(it.first), viewed)
                })
                return EpisodesUi.Base(episodesList)
            }
        }
    }
}
