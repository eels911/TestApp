package com.example.testapp.episodes.presentation

import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface EpisodesUi : Mapper.Unit<Mapper.Unit<List<ItemUi>>> {

    class Base(private val list: List<ItemUi>) : EpisodesUi {
        override fun map(data: Mapper.Unit<List<ItemUi>>) = data.map(list)
    }
}