package com.morozov.psychology.mvp.presenters.mind.change.homework.disastorous

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.views.mind.change.homework.disastorous.HmDisastorous_1_View

@InjectViewState
class HmDisastorous_1_Presenter: MvpPresenter<HmDisastorous_1_View>() {

    fun showData() {
        viewState.showRecycler(listOf(
            Pair(
                "Выберите ситуацию. Опишите наихудший ожидаемый ущерб (негативные последствия) и отметьте его на шкале ущербов, где 100% - это максимально возможный ущерб в вашей жизни.",
                "Наихудший ожидаемый ущерб"),
            Pair(
                "Опишите наилучший ожидаемый ущерб и отметьте его на шкале ущербов, где 100% - это максимально возможный ущерб в вашей жизни.",
                "Наилучший ожидаемый ущерб"
            ),
            Pair(
                "Завтра вернитесь к этому заданию и опишите реальный ущерб.\n" +
                        "Отметьте его на шкале ущербов, где 100% - это максимально возможный ущерб в вашей жизни",
                "Реальный ущерб"
            )
        ))
    }
}