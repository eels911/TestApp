package com.example.testapp.viewedepisodes.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.episodes.presentation.adapter.EpisodeAdapter
import com.github.johnnysc.coremvvm.presentation.BaseFragment

class ViewedFragment : BaseFragment<ViewedViewModel>() {
    override val layoutResId: Int = R.layout.fragment_episode

    override fun viewModelClass() = ViewedViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val episodesAdapter = EpisodeAdapter.Factory().provide()
        recyclerView.adapter = episodesAdapter

        viewModel.observe(this) { episodesUi ->
            episodesUi.map(episodesAdapter)
        }
        viewModel.observeUpdate(this) {
            viewModel.update()
        }

    }

}