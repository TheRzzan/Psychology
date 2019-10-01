package com.morozov.psychology.mvp.presenters.tests

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.tests.QuestionsLoader
import com.morozov.psychology.mvp.views.tests.TestsQuizView
import javax.inject.Inject

@InjectViewState
class TestsQuizPresenter: MvpPresenter<TestsQuizView>() {

    @Inject
    lateinit var questionsLoader: QuestionsLoader

    val selectedAnswers: MutableList<Int> = mutableListOf()

    init {
        DefaultApplication.testsComponent.inject(this)
    }

    fun showQuestion(testName: String, position: Int) {
        viewState.setQuestionNumber(position)
        viewState.increaseSegmentProgress()
        viewState.showQuestion(questionsLoader.getQuestions(testName)[position])
    }

    fun generateResult() {

    }

    fun getQuestionsAmount(testName: String): Int {
        return questionsLoader.getQuestions(testName).size
    }
}