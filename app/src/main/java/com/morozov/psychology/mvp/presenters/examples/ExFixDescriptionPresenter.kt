package com.morozov.psychology.mvp.presenters.examples

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.mvp.views.examples.ExFixDescriptionView

@InjectViewState
class ExFixDescriptionPresenter: MvpPresenter<ExFixDescriptionView>() {

    fun loadData() {
        viewState.showData("", "Это упражнение не только укрепит ваше знание о влиянии мыслей на эмоции, но и подготовит вас к ведению дневника собственных мыслей.\n" +
                " \n" +
                "В представленных предложениях найдите описание ситуации, мысли, эмоции и поведение человека.\n")
    }
}