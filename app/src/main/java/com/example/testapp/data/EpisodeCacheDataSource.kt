package com.example.testapp.data

import com.github.johnnysc.coremvvm.core.Read
import com.github.johnnysc.coremvvm.core.Save

class EpisodeCacheDataSource {
    interface Mutable : Save<EpisodeResponse>, Read<EpisodeResponse>

    class Base(
        private var data: EpisodeResponse = EpisodeResponse.Empty()
    ) : Mutable {
        override fun save(data: EpisodeResponse) {
            this.data = data
        }

        override fun read() = data
    }

}
