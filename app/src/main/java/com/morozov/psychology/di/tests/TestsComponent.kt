package com.morozov.psychology.di.tests

import dagger.Component

@Component(modules = arrayOf(TestsModule::class))
interface TestsComponent {
}