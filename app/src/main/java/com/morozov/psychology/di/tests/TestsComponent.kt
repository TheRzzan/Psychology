package com.morozov.psychology.di.tests

import com.morozov.psychology.mvp.presenters.tests.TestsDescriptionPresenter
import com.morozov.psychology.mvp.presenters.tests.TestsQuizPresenter
import com.morozov.psychology.ui.fragments.tests.TestsAllResultsCardsFragment
import com.morozov.psychology.ui.fragments.tests.TestsResultsFragment
import dagger.Component

@Component(modules = arrayOf(TestsModule::class))
interface TestsComponent {

    fun inject(presenter: TestsDescriptionPresenter)

    fun inject(presenter: TestsQuizPresenter)

    fun inject(fragment: TestsResultsFragment)

    fun inject(fragment: TestsAllResultsCardsFragment)
}