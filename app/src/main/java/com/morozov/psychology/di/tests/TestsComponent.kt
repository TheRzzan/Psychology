package com.morozov.psychology.di.tests

import com.morozov.psychology.mvp.presenters.tests.TestsDescriptionPresenter
import com.morozov.psychology.mvp.presenters.tests.TestsQuizPresenter
import dagger.Component

@Component(modules = arrayOf(TestsModule::class))
interface TestsComponent {

    fun inject(presenter: TestsDescriptionPresenter)

    fun inject(presenter: TestsQuizPresenter)
}