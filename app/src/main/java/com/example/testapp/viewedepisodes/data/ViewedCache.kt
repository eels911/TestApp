package com.example.testapp.viewedepisodes.data

import com.github.johnnysc.coremvvm.core.Read
import com.github.johnnysc.coremvvm.core.Save
import com.github.johnnysc.coremvvm.data.PreferenceDataStore

class ViewedCache {

    interface Save : com.github.johnnysc.coremvvm.core.Save<Set<String>>

    interface Read : com.github.johnnysc.coremvvm.core.Read<Set<String>>

    interface Mutable : Save, Read

    class Base(private val preferences: PreferenceDataStore) : Mutable {
        override fun save(data: Set<String>) = preferences.save(KEY, data)

        override fun read(): Set<String> = preferences.read(KEY)

        companion object {
            private const val KEY = "ViewedEpisodesKey"
        }
    }
}