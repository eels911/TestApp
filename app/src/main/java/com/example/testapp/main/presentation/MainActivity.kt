package com.example.testapp.main.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.testapp.R
import com.example.testapp.episodes.presentation.EpisodeFragment
import com.example.testapp.viewedepisodes.presentation.ViewedFragment
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.BaseFragment
import com.github.johnnysc.coremvvm.sl.ProvideViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BackPress.Activity<MainViewModel>(), ProvideViewModel {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentFactory = BaseFragmentFactory(R.id.container, supportFragmentManager)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener(object : SimpleOnItemSelectedListener(){
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                item.title?.let { viewModel.chooseItem(it) }
                return true
            }
        })


        viewModel = provideViewModel(MainViewModel::class.java, this)

        viewModel.observeNavigation(owner = this, fragmentFactory::fragment)
    }




    override fun <T : androidx.lifecycle.ViewModel> provideViewModel(
        clazz: Class<T>,
        owner: ViewModelStoreOwner
    ): T = (application as ProvideViewModel).provideViewModel(clazz, owner)
}