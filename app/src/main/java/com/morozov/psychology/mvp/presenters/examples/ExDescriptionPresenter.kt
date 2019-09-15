package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.views.examples.ExDescriptionView

@InjectViewState
class ExDescriptionPresenter: MvpPresenter<ExDescriptionView>() {

    fun loadData() {
        viewState.showData("Эксперимент №1", "Многие думаю, что эмоции напрямую вызваны событием. Например, «этот ответ меня расстроил», «меня пугает поездка в метро», «ты меня разозлила». Как будто определенное событие программирует определенный ответ. Жизнь доказывает обратное. Я предложу вам одну и ту же ситуацию, но с разными эмоциями в ответ на неё. Попробуйте угадать, о чем думали эти люди, чтобы возникла именно эта эмоция.")
    }
}