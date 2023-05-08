package com.example.testapp.episodes.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class EpisodeFragment() : BackPress.Fragment<List<ItemUi>, EpisodeViewModel>() {

    override val layoutResId: Int = R.layout.fragment_episode

    override fun viewModelClass(): Class<EpisodeViewModel> = EpisodeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
    }

}