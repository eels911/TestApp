package com.example.testapp.viewedepisodes.data

import com.example.testapp.episodes.data.EpisodesCache
import com.example.testapp.episodes.presentation.EpisodesUi

interface ViewedRepository {

    fun viewedList(): EpisodesUi

    class Base(
        private val cache: EpisodesCache.Read,
        private val mapper: ViewedMapper
    ) : ViewedRepository {
        override fun viewedList(): EpisodesUi {
            val list = cache.read().map(mapper)
            return EpisodesUi.Base(list)
        }
    }
}