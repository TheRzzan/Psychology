package com.morozov.psychology.mvp.views.tests

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.morozov.psychology.mvp.models.tests.about.AboutModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface TestsAboutView: MvpView {

    fun showAbout(about: AboutModel)
}