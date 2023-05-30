package com.example.testapp.viewedepisodes.presentation

import com.github.johnnysc.coremvvm.presentation.Communication

class UpdateViewedEpisodes {
    interface Observe : Communication.Observe<Boolean>
    interface Update : Communication.Update<Boolean>

    class Base : Communication.SingleUiUpdate<Boolean>(), Observe, Update

}
