package com.example.testapp.episodes.data

import com.example.testapp.viewedepisodes.data.ChangeViewed
import com.example.testapp.viewedepisodes.data.ViewedCache

interface ViewedCacheDataSource : ChangeViewed, Viewed {

    class Base(private val viewed: ViewedCache.Mutable) : ViewedCacheDataSource {

        private var cache = viewed.read()


        override fun changeViewed(id: String) {
            val data = cache.toMutableSet()
            if (viewed(id))
                data.remove(id)
            else
                data.add(id)
            viewed.save(data)
            cache = viewed.read()
        }

        override fun viewed(id: String): Boolean = cache.contains(id)
    }
}