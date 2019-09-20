package com.morozov.psychology

import android.app.Application
import com.morozov.psychology.di.diary.DaggerDiaryComponent
import com.morozov.psychology.di.diary.DiaryComponent
import com.morozov.psychology.di.diary.ThinkModule
import com.morozov.psychology.di.examples.DaggerExamplesComponent
import com.morozov.psychology.di.examples.ExamplesComponent
import com.morozov.psychology.di.examples.ExamplesModule
import com.morozov.psychology.di.examples.FixingModule

class DefaultApplication: Application() {

    companion object {
        lateinit var examplesComponent: ExamplesComponent
        lateinit var diaryComponent: DiaryComponent
    }

    override fun onCreate() {
        super.onCreate()

        examplesComponent = DaggerExamplesComponent
                            .builder()
                            .examplesModule(ExamplesModule())
                            .fixingModule(FixingModule())
                            .build()

        diaryComponent = DaggerDiaryComponent
                            .builder()
                            .thinkModule(ThinkModule())
                            .build()
    }
}