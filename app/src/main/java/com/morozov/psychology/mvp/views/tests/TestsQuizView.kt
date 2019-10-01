package com.morozov.psychology.mvp.views.tests

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.morozov.psychology.mvp.models.tests.QuestionModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface TestsQuizView: MvpView {

    fun setQuestionNumber(position: Int)

    fun setSegmentProgressCount(count: Int)

    fun increaseSegmentProgress()

    fun showQuestion(question: QuestionModel)
}