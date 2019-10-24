package com.morozov.psychology.mvp.presenters.mind.change.homework.disastorous

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.views.mind.change.homework.disastorous.HmDisastorous_2_View

@InjectViewState
class HmDisastorous_2_Presenter: MvpPresenter<HmDisastorous_2_View>() {

    fun showData() {
        viewState.showRecycler(listOf(
            Pair(
                "Какова вероятность, что ситуация завершится хорошо?",
                "Хороший исход"
            ),
            Pair(
                "Что подумало или сделало бы подавляющее большинство людей в этой ситуации?",
                "Мысли людей"
            ),
            Pair(
                "Какой прогноз большинство людей сочли бы благоразумным?",
                "Благоразумный прогноз"
            )
        ))
    }
}