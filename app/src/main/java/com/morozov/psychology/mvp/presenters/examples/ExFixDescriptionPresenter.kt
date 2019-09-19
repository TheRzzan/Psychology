package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.examples.FixingLoader
import com.morozov.psychology.mvp.views.examples.ExFixDescriptionView
import javax.inject.Inject

@InjectViewState
class ExFixDescriptionPresenter: MvpPresenter<ExFixDescriptionView>() {

    @Inject
    lateinit var fixingLoader: FixingLoader

    init {
        DefaultApplication.examplesComponent.inject(this)
    }

    fun showFixing(exercisePos: Int) {
        val fixing = fixingLoader.getFixings()[exercisePos]

        viewState.showData(fixing.title, fixing.description)
    }
}