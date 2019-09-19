package com.morozov.psychology

import android.app.Application
import com.morozov.psychology.di.examples.DaggerExamplesComponent
import com.morozov.psychology.di.examples.ExamplesComponent
import com.morozov.psychology.di.examples.ExamplesModule

class DefaultApplication: Application() {

    companion object {
        lateinit var examplesComponent: ExamplesComponent
    }

    override fun onCreate() {
        super.onCreate()

        examplesComponent = DaggerExamplesComponent
                            .builder()
                            .examplesModule(ExamplesModule())
                            .build()
    }
}