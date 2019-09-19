package com.morozov.psychology.di.examples

import com.morozov.psychology.domain.implementation.examples.FixingLoaderImpl
import com.morozov.psychology.domain.interfaces.examples.FixingLoader
import dagger.Module
import dagger.Provides

@Module
class FixingModule {

    @Provides
    fun fixingLoader(): FixingLoader = FixingLoaderImpl()
}