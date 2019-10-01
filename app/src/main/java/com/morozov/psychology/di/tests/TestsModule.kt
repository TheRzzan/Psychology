package com.morozov.psychology.di.tests

import com.morozov.psychology.domain.implementation.tests.TestsAllImpl
import com.morozov.psychology.domain.interfaces.tests.DescriptionLoader
import com.morozov.psychology.domain.interfaces.tests.QuestionsLoader
import com.morozov.psychology.domain.interfaces.tests.ResultSaver
import com.morozov.psychology.domain.interfaces.tests.ResultsLoader
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
}