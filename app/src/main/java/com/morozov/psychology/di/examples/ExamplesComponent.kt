package com.morozov.psychology.di.examples

import com.morozov.psychology.di.AppComponent
import com.morozov.psychology.mvp.presenters.examples.ExCardsPresenter
import com.morozov.psychology.mvp.presenters.examples.ExDescriptionPresenter
import com.morozov.psychology.mvp.presenters.examples.ExResultsPresenter
import com.morozov.psychology.mvp.presenters.examples.ExTestsPresenter
import dagger.Component

@Component(modules = arrayOf(ExamplesModule::class, FixingModule::class))
interface ExamplesComponent: AppComponent {

    fun inject(presenter: ExCardsPresenter)

    fun inject(presenter: ExDescriptionPresenter)

    fun inject(presenter: ExTestsPresenter)

    fun inject(presenter: ExResultsPresenter)
}