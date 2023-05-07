package com.example.testapp.presentation

import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface EpisodeCommunication: Communication.Mutable<List<ItemUi>> {

    class Base : Communication.UiUpdate<List<ItemUi>>(), EpisodeCommunication
}