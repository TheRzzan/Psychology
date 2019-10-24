package com.morozov.psychology.di.diary

import com.morozov.psychology.di.AppComponent
import com.morozov.psychology.mvp.presenters.diary.DiaryEditorPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryMainPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryThinkViewingPresenter
import com.morozov.psychology.mvp.presenters.mind.change.MindChangePresenter
import com.morozov.psychology.mvp.presenters.mind.change.MindChangeThinkTestPresenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.black.white.MCBlackWhitePresenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.commitment.MCCommitment_1_Presenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.commitment.MCCommitment_4_Presenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.deprecation.MCDeprecation_1_Presenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.deprecation.MCDeprecation_3_Presenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.disastorous.MCDisastorous_1_Presenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.disastorous.MCDisastorous_3_Presenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.emotional.MCEmotionalPresenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.labeling.MCLabelingPresenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.mind.reading.MCMindReadingPresenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.minimalism.MCMinimalismPresenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.overgeneration.MCOvergenerationPresenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.personalization.MCPersonalizationPresenter
import com.morozov.psychology.mvp.presenters.mind.change.think.mintake.MCThinkMistake_2_Presenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.tunnel.MCTunnelPresenter
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
    fun inject(presenter: MCCommitment_4_Presenter)
    fun inject(presenter: MCDeprecation_3_Presenter)
    fun inject(presenter: MCDisastorous_3_Presenter)
}