package com.example.testapp.viewedepisodes.presentation

import com.example.testapp.episodes.presentation.EpisodesUi
import com.github.johnnysc.coremvvm.presentation.Communication

interface ViewedCommunication : Communication.Mutable<EpisodesUi> {

    class Base : Communication.UiUpdate<EpisodesUi>(), ViewedCommunication
}