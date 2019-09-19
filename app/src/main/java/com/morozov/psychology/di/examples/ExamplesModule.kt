package com.morozov.psychology.di.examples

import com.morozov.psychology.domain.implementation.examples.ExperimentsLoaderImpl
import com.morozov.psychology.domain.interfaces.examples.ExperimentsLoader
import dagger.Module
import dagger.Provides

@Module
class ExamplesModule {

    @Provides
    fun experimentsLoader(): ExperimentsLoader = ExperimentsLoaderImpl()
}