package com.morozov.psychology.mvp.presenters.mind.change.black.white

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.views.mind.change.black.white.MCBlackWhiteView

@InjectViewState
class MCBlackWhitePresenter: MvpPresenter<MCBlackWhiteView>() {

    fun loadData() {
        viewState.showRecycler(listOf("0%","10%","20%","30%","40%","50%","60%","70%","80%","90%","100%"))
    }
}