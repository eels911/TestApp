package com.example.testapp.main.presentation

import android.view.MenuItem
import com.google.android.material.navigation.NavigationBarView

abstract class SimpleOnItemSelectedListener: NavigationBarView.OnItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
}
