package com.morozov.psychology.mvp.presenters.mind.change.think.mintake

import android.content.res.Resources
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.views.mind.change.think.mintake.MCThinkMistake_1_View

@InjectViewState
class MCThinkMistake_1_Presenter: MvpPresenter<MCThinkMistake_1_View>() {

    fun loadThinkMistakes(resources: Resources) {
        val pair_1_2 = Pair(
            "Катастрофизация – будущее предсказывается негативым. Даже если вероятность ужасного последствия минимальна, возникает мысль «а что, если…». ",
            "Если я провалю экзамен, это будет ужасно!"
        )
        val pair_1_1 = Pair(resources.getDrawable(R.drawable.ic_disastrous), pair_1_2)

        viewState.showThinkMistakes(listOf(pair_1_1))
    }
}