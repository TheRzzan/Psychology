package com.morozov.psychology.di.tests

import com.morozov.psychology.domain.implementation.tests.TestsAllImpl
import com.morozov.psychology.domain.interfaces.tests.*
import dagger.Module
import dagger.Provides

@Module
class TestsModule {

    @Provides
    fun descriptionLoader(): DescriptionLoader = TestsAllImpl()

    @Provides
    fun questionsLoader(): QuestionsLoader = TestsAllImpl()

    @Provides
    fun resultSaver(): ResultSaver = TestsAllImpl()

    @Provides
    fun resultsLoader(): ResultsLoader = TestsAllImpl()

    @Provides
    fun aboutLoader(): AboutLoader = TestsAllImpl()

    @Provides
    fun aboutSaver(): AboutSaver = TestsAllImpl()
}