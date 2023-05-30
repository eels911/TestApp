package com.example.testapp.episodes.data

class EpisodesCache {
    interface Save : com.github.johnnysc.coremvvm.core.Save<EpisodeResponse>
    interface Read : com.github.johnnysc.coremvvm.core.Read<EpisodeResponse>
    interface Mutable : Save, Read

    class Base(
        private var data: EpisodeResponse = EpisodeResponse.Empty()
    ) : Mutable {
        override fun save(data: EpisodeResponse) {
            this.data = data
        }

        override fun read() = data
    }

}
