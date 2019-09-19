package com.morozov.psychology.di.examples

import com.morozov.psychology.di.AppComponent
import com.morozov.psychology.mvp.presenters.examples.ExCardsPresenter
import dagger.Component

@Component(modules = arrayOf(ExamplesModule::class))
interface ExamplesComponent: AppComponent {

    companion object {
        val EXAMPLES_COMPONENT = "EXAMPLES_COMPONENT"
    }

    fun inject(presenter: ExCardsPresenter)
}