package com.example.testapp.episodes.presentation

interface RefreshEpisode {

    fun refreshEpisode()

    class Base(
        private var mutableCommunication: (Unit) -> Unit = {}
    ) : RefreshEpisode, Observe<Unit> {

        override fun observe(communication: (Unit) -> Unit) {
            mutableCommunication = communication
            mutableCommunication.invoke(Unit)
        }

        override fun refreshEpisode() {
            mutableCommunication.invoke(Unit)
        }
    }
}
