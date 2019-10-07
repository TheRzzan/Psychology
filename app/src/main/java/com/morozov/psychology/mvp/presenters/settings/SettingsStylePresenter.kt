package com.morozov.psychology.mvp.presenters.settings

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.R
import com.morozov.psychology.mvp.views.settings.SettingsStyleView

@InjectViewState
class SettingsStylePresenter: MvpPresenter<SettingsStyleView>() {

    fun loadColors(context: Context) {
        viewState.showColors(listOf(
            Pair("Стандартный", context.resources.getColor(R.color.colorPrimaryDark)),
            Pair("Зеленый", context.resources.getColor(R.color.green)),
            Pair("Бирюзовый", context.resources.getColor(R.color.turquoise)),
            Pair("Синий", context.resources.getColor(R.color.blue)),
            Pair("Жёлтый", context.resources.getColor(R.color.yellow)),
            Pair("Оранжевый", context.resources.getColor(R.color.orange)),
            Pair("Красный", context.resources.getColor(R.color.red))
        ))
    }
}