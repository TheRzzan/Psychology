package com.morozov.psychology.di.examples

import com.morozov.psychology.di.AppComponent
import com.morozov.psychology.mvp.presenters.examples.*
import dagger.Component

@Component(modules = arrayOf(ExamplesModule::class, FixingModule::class))
interface ExamplesComponent: AppComponent {

    fun inject(presenter: ExCardsPresenter)

    fun inject(presenter: ExDescriptionPresenter)

    fun inject(presenter: ExTestsPresenter)

    fun inject(presenter: ExResultsPresenter)

    fun inject(presenter: ExFixDescriptionPresenter)

    fun inject(presenter: ExFixTestsPresenter)

    fun inject(presenter: ExFixResultsPresenter)
}