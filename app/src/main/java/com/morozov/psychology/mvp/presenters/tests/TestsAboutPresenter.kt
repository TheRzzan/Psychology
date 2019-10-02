package com.morozov.psychology.mvp.presenters.tests

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.tests.AboutLoader
import com.morozov.psychology.domain.interfaces.tests.AboutSaver
import com.morozov.psychology.mvp.models.tests.about.AboutModel
import com.morozov.psychology.mvp.views.tests.TestsAboutView
import javax.inject.Inject

@InjectViewState
class TestsAboutPresenter: MvpPresenter<TestsAboutView>() {

    @Inject
    lateinit var aboutLoader: AboutLoader

    @Inject
    lateinit var aboutSaver: AboutSaver

    init {
        DefaultApplication.testsComponent.inject(this)
    }

    fun loadData() {
        val aboutModel = aboutLoader.getAboutModel() ?: return
        viewState.showAbout(aboutModel)
    }

    fun saveData(about: AboutModel) {
        aboutSaver.saveAboutModel(about)
    }
}