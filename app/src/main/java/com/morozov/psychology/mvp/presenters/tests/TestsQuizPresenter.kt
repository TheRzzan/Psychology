package com.morozov.psychology.mvp.presenters.tests

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.domain.interfaces.tests.QuestionsLoader
import com.morozov.psychology.domain.interfaces.tests.ResultSaver
import com.morozov.psychology.mvp.models.tests.ResultModel
import com.morozov.psychology.mvp.views.tests.TestsQuizView
import com.morozov.psychology.utility.TestsResultsGenerator
import java.util.*
import javax.inject.Inject

@InjectViewState
class TestsQuizPresenter: MvpPresenter<TestsQuizView>() {

    @Inject
    lateinit var questionsLoader: QuestionsLoader

    @Inject
    lateinit var resultSaver: ResultSaver

    val selectedAnswers: MutableList<Int> = mutableListOf()

    init {
        DefaultApplication.testsComponent.inject(this)
    }

    fun showQuestion(testName: String, position: Int) {
        viewState.setQuestionNumber(position)
        viewState.increaseSegmentProgress()
        viewState.showQuestion(questionsLoader.getQuestions(testName)[position])
    }

    fun generateResult(context: Context, testName: String) {
        resultSaver.saveResult(testName, TestsResultsGenerator().getResult(context, testName, selectedAnswers))
    }

    fun getQuestionsAmount(testName: String): Int {
        return questionsLoader.getQuestions(testName).size
    }
}