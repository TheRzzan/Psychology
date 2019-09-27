package com.morozov.psychology.di.diary

import com.morozov.psychology.domain.implementation.diary.ThinkLoaderImpl
import com.morozov.psychology.domain.interfaces.diary.ThinkDeleter
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.domain.interfaces.diary.ThinkSaver
import dagger.Module
import dagger.Provides

@Module
class ThinkModule {

    @Provides
    fun thinkLoader(): ThinkLoader = ThinkLoaderImpl()

    @Provides
    fun thinkSaver(): ThinkSaver = ThinkLoaderImpl()

    @Provides
    fun thinkDeleter(): ThinkDeleter = ThinkLoaderImpl()
}