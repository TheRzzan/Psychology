package com.morozov.psychology.di.tests

import com.morozov.psychology.mvp.presenters.tests.TestsDescriptionPresenter
import dagger.Component

@Component(modules = arrayOf(TestsModule::class))
interface TestsComponent {

    fun inject(presenter: TestsDescriptionPresenter)
}