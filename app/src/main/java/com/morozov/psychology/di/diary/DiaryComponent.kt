package com.morozov.psychology.di.diary

import com.morozov.psychology.di.AppComponent
import com.morozov.psychology.di.examples.ExamplesModule
import com.morozov.psychology.di.examples.FixingModule
import com.morozov.psychology.mvp.presenters.diary.DiaryEditorPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryMainPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryThinkViewingPresenter
import com.morozov.psychology.mvp.presenters.mind.change.MindChangePresenter
import dagger.Component

@Component(modules = arrayOf(ThinkModule::class))
interface DiaryComponent: AppComponent {

    fun inject(presenter: DiaryPresenter)

    fun inject(presenter: DiaryEditorPresenter)

    fun inject(presenter: DiaryThinkViewingPresenter)

    fun inject(presenter: DiaryMainPresenter)

    fun inject(presenter: MindChangePresenter)
}