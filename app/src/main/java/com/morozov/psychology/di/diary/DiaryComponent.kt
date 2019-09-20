package com.morozov.psychology.di.diary

import com.morozov.psychology.di.AppComponent
import com.morozov.psychology.di.examples.ExamplesModule
import com.morozov.psychology.di.examples.FixingModule
import dagger.Component

@Component(modules = arrayOf(ThinkModule::class))
interface DiaryComponent: AppComponent {
}