package com.example.testapp.viewedepisodes.data

import com.example.testapp.viewedepisodes.presentation.UpdateViewedEpisodes

interface ChangeViewed {

    fun changeViewed(id: String)

    class Base(
        private val changeViewed: ChangeViewed,
        private val communication: UpdateViewedEpisodes.Update
    ) : ChangeViewed{
        override fun changeViewed(id: String) {
            changeViewed.changeViewed(id)
            communication.map(true)
        }
    }
}