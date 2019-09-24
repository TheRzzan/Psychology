package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.examples.FixingLoader
import com.morozov.psychology.mvp.views.examples.ExFixResultsView
import javax.inject.Inject

@InjectViewState
class ExFixResultsPresenter: MvpPresenter<ExFixResultsView>() {

    @Inject
    lateinit var fixingLoader: FixingLoader

    init {
        DefaultApplication.examplesComponent.inject(this)
    }

    fun loadResult(position: Int) {
        val fixings = fixingLoader.getFixings()

        viewState.showTitle(fixings[position].title)
        viewState.showResult(fixings[position].description)

        if (position + 1 >= fixings.size)
            viewState.hideButtonNext()
        else
            viewState.showButtonNext(fixings[position + 1].title, position + 1)
    }
}