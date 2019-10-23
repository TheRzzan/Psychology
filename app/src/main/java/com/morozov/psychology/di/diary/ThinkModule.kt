package com.morozov.psychology.di.diary

import android.content.Context
import com.morozov.psychology.domain.implementation.diary.ThinkLoaderImpl
import com.morozov.psychology.domain.interfaces.diary.ThinkDeleter
import com.morozov.psychology.domain.interfaces.diary.ThinkLoader
import com.morozov.psychology.domain.interfaces.diary.ThinkSaver
import dagger.Module
import dagger.Provides

@Module
class ThinkModule(private val context: Context) {

    @Provides
    fun thinkLoader(): ThinkLoader = ThinkLoaderImpl(context)

    @Provides
    fun thinkSaver(): ThinkSaver = ThinkLoaderImpl(context)

    @Provides
    fun thinkDeleter(): ThinkDeleter = ThinkLoaderImpl(context)
}