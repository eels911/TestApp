package com.example.testapp.episodes.presentation

import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.GlobalErrorCommunication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface EpisodeCommunication: Communication.Mutable<EpisodesUi> {

    class Base : Communication.UiUpdate<EpisodesUi>(), EpisodeCommunication
}

class ErrorCommunication : Communication.SinglePostUpdate<String>(), GlobalErrorCommunication.Mutable
