package com.morozov.psychology.di.tests

import android.content.Context
import com.morozov.psychology.domain.implementation.tests.TestsAllImpl
import com.morozov.psychology.domain.interfaces.tests.*
import dagger.Module
import dagger.Provides

@Module
class TestsModule(private val context: Context) {

    @Provides
    fun descriptionLoader(): DescriptionLoader = TestsAllImpl(context)

    @Provides
    fun questionsLoader(): QuestionsLoader = TestsAllImpl(context)

    @Provides
    fun resultSaver(): ResultSaver = TestsAllImpl(context)

    @Provides
    fun resultsLoader(): ResultsLoader = TestsAllImpl(context)

    @Provides
    fun aboutLoader(): AboutLoader = TestsAllImpl(context)

    @Provides
    fun aboutSaver(): AboutSaver = TestsAllImpl(context)
}