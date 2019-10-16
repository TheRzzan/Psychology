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

        val pair_2_2 = Pair(
            "Обесценивание позитивного – привычка не придавать значения успехам и позитивному опыту. Например, списывать свои или чужие заслуги на везение или сравнивать с другими людьми, у которых этот навык или знания лучше.",
            "То, что я заняла второе место на городской олимпиаде – случайность."
        )
        val pair_2_1 = Pair(resources.getDrawable(R.drawable.ic_depreciation), pair_2_2)

        val pair_3_2 = Pair(
            "",
            ""
        )
        val pair_3_1 = Pair(resources.getDrawable(R.drawable.ic_disastrous), pair_3_2)

        viewState.showThinkMistakes(listOf(pair_1_1, pair_2_1))
    }
}