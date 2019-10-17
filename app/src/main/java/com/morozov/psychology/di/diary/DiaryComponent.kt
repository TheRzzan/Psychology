package com.morozov.psychology.di.diary

import com.morozov.psychology.di.AppComponent
import com.morozov.psychology.di.examples.ExamplesModule
import com.morozov.psychology.di.examples.FixingModule
import com.morozov.psychology.mvp.presenters.diary.DiaryEditorPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryMainPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryThinkViewingPresenter
import com.morozov.psychology.mvp.presenters.mind.change.MindChangePresenter
import com.morozov.psychology.mvp.presenters.mind.change.MindChangeThinkTestPresenter
import com.morozov.psychology.mvp.presenters.mind.change.black.white.MCBlackWhitePresenter
import com.morozov.psychology.mvp.presenters.mind.change.commitment.MCCommitment_1_Presenter
import com.morozov.psychology.mvp.presenters.mind.change.deprecation.MCDeprecation_1_Presenter
import com.morozov.psychology.mvp.presenters.mind.change.disastorous.MCDisastorous_1_Presenter
import com.morozov.psychology.mvp.presenters.mind.change.emotional.MCEmotionalPresenter
import com.morozov.psychology.mvp.presenters.mind.change.labeling.MCLabelingPresenter
import com.morozov.psychology.mvp.presenters.mind.change.mind.reading.MCMindReadingPresenter
import com.morozov.psychology.mvp.presenters.mind.change.minimalism.MCMinimalismPresenter
import com.morozov.psychology.mvp.presenters.mind.change.overgeneration.MCOvergenerationPresenter
import com.morozov.psychology.mvp.presenters.mind.change.personalization.MCPersonalizationPresenter
import com.morozov.psychology.mvp.presenters.mind.change.think.mintake.MCThinkMistake_2_Presenter
import com.morozov.psychology.mvp.presenters.mind.change.tunnel.MCTunnelPresenter
import dagger.Component

@Component(modules = arrayOf(ThinkModule::class))
interface DiaryComponent: AppComponent {

    fun inject(presenter: DiaryPresenter)

    fun inject(presenter: DiaryEditorPresenter)

    fun inject(presenter: DiaryThinkViewingPresenter)

    fun inject(presenter: DiaryMainPresenter)

    fun inject(presenter: MindChangePresenter)

    fun inject(presenter: MindChangeThinkTestPresenter)

    fun inject(presenter: MCThinkMistake_2_Presenter)

    // Think mistakes
    fun inject(presenter: MCDisastorous_1_Presenter)
    fun inject(presenter: MCDeprecation_1_Presenter)
    fun inject(presenter: MCCommitment_1_Presenter)
    fun inject(presenter: MCBlackWhitePresenter)
    fun inject(presenter: MCEmotionalPresenter)
    fun inject(presenter: MCLabelingPresenter)
    fun inject(presenter: MCMindReadingPresenter)
    fun inject(presenter: MCMinimalismPresenter)
    fun inject(presenter: MCOvergenerationPresenter)
    fun inject(presenter: MCPersonalizationPresenter)
    fun inject(presenter: MCTunnelPresenter)
}