package com.example.testapp.data

import com.example.testapp.domain.EpisodeRepository
import com.github.johnnysc.coremvvm.core.Read
import com.github.johnnysc.coremvvm.core.Save

class EpisodeCacheDataSource {
    interface Mutable: Save<EpisodeResponse>,Read<EpisodeResponse>

    class Base(
        private var data: EpisodeResponse = EpisodeResponse.Empty()
    )

}
