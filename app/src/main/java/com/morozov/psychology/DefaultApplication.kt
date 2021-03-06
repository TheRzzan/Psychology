package com.morozov.psychology

import android.app.Application
import com.android.vending.billing.IInAppBillingService
import com.morozov.psychology.di.diary.DaggerDiaryComponent
import com.morozov.psychology.di.diary.DiaryComponent
import com.morozov.psychology.di.diary.ThinkModule
import com.morozov.psychology.di.examples.DaggerExamplesComponent
import com.morozov.psychology.di.examples.ExamplesComponent
import com.morozov.psychology.di.examples.ExamplesModule
import com.morozov.psychology.di.examples.FixingModule
import com.morozov.psychology.di.tests.DaggerTestsComponent
import com.morozov.psychology.di.tests.TestsComponent
import com.morozov.psychology.di.tests.TestsModule
import io.realm.Realm

class DefaultApplication: Application() {

    companion object {
        lateinit var inAppService: IInAppBillingService
        lateinit var examplesComponent: ExamplesComponent
        lateinit var diaryComponent: DiaryComponent
        lateinit var testsComponent: TestsComponent
    }

    override fun onCreate() {
        super.onCreate()

        Realm.init(applicationContext)

        examplesComponent = DaggerExamplesComponent
                            .builder()
                            .examplesModule(ExamplesModule())
                            .fixingModule(FixingModule())
                            .build()

        diaryComponent = DaggerDiaryComponent
                            .builder()
                            .thinkModule(ThinkModule(applicationContext))
                            .build()

        testsComponent = DaggerTestsComponent
                            .builder()
                            .testsModule(TestsModule(this))
                            .build()
    }
}