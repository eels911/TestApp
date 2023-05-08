package com.example.testapp.main

import com.example.testapp.main.presentation.MainViewModel
import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

class MainModule(
    private val coreModule: CoreModule
) : Module<MainViewModel> {

    override fun viewModel() = MainViewModel(
        NavigationCommunication.Base(),
        coreModule.provideCanGoBack(),
        coreModule.dispatchers(),
        coreModule.provideGlobalErrorCommunication()
    )
}