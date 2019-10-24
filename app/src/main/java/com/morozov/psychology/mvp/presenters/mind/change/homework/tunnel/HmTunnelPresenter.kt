package com.morozov.psychology.mvp.presenters.mind.change.homework.tunnel

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.views.mind.change.homework.main.HmMainView
import com.morozov.psychology.mvp.views.mind.change.homework.tunnel.HmTunnelView

@InjectViewState
class HmTunnelPresenter: MvpPresenter<HmTunnelView>() {

    fun loadData() {
        viewState.showRecycler(listOf(
            "1. Позитивный момент",
            "2. Позитивный момент",
            "3. Позитивный момент",
            "4. Позитивный момент",
            "5. Позитивный момент",
            "6. Позитивный момент",
            "7. Позитивный момент",
            "8. Позитивный момент",
            "9. Позитивный момент",
            "10. Позитивный момент"
        ))
    }
}