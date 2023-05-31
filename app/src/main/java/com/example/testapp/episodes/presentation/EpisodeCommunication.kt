package com.example.testapp.episodes.presentation

import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.GlobalErrorCommunication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface EpisodeCommunication : Communication.Mutable<List<ItemUi>> {

    class Base : Communication.UiUpdate<List<ItemUi>>(), EpisodeCommunication
}

class ErrorCommunication : Communication.SinglePostUpdate<String>(),
    GlobalErrorCommunication.Mutable
