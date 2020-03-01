package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.tests.QuestionModel
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.tests.TestsQuizPresenter
import com.morozov.psychology.mvp.views.tests.TestsQuizView
import com.morozov.psychology.ui.adapters.tests.quiz.TstQuizAdapter
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.example_fix_test_layout.*
import kotlinx.android.synthetic.main.tests_quiz_layout.*

class TestsQuizFragment: MvpAppCompatFragment(), TestsQuizView {

    @InjectPresenter
    lateinit var mPresenter: TestsQuizPresenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: TstQuizAdapter

    var checked = 0
    var questionsAmount = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_quiz_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments ?: return
        val name = bundle.getString(AppConstants.TEST_NAME) ?: return

        questionsAmount = mPresenter.getQuestionsAmount(name)

        setSegmentProgressCount(questionsAmount)

        adapter = TstQuizAdapter()
        recyclerTestsQuestion.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerTestsQuestion.adapter = adapter

        adapter.selectedPosition.observeForever {
            if (it == null)
                return@observeForever

            when(it >= 0) {
                true -> buttonTestsFinishQuiz.setBackgroundResource(R.drawable.rectangle_button)
                false -> buttonTestsFinishQuiz.setBackgroundResource(R.drawable.rectangle_button_disable)
            }
            buttonTestsFinishQuiz.isEnabled = it >= 0

            adapter.notifyDataSetChanged()
        }

        buttonTestsFinishQuiz.setOnClickListener {
            mPresenter.selectedAnswers.add(adapter.selectedPosition.value!!)
            mPresenter.showQuestion(name, checked)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments ?: return
        val name = bundle.getString(AppConstants.TEST_NAME) ?: return

        mPresenter.showQuestion(name, checked)
    }

    /*
    * TestsQuizView implementation
    *
    * */
    override fun setSegmentProgressCount(count: Int) {
        progressBarHorizontal.max = count
    }

    override fun increaseSegmentProgress() {
        progressBarHorizontal.incrementProgressBy(1)
        checked++

        if (checked == questionsAmount) {
            buttonTestsFinishQuiz.text = "Завершить"
            buttonTestsFinishQuiz.setOnClickListener {
                mPresenter.selectedAnswers.add(adapter.selectedPosition.value!!)

                val bundle = this.arguments ?: return@setOnClickListener
                val name = bundle.getString(AppConstants.TEST_NAME) ?: return@setOnClickListener

                context?.let { it1 -> mPresenter.generateResult(it1, name) }
                mActivityPresenter.showTestQuizResults(name)
            }
        }
    }

    override fun setQuestionNumber(position: Int) {
        textTestsQuestionName.text = "Вопрос ${position + 1}"
    }

    override fun showQuestion(question: QuestionModel) {
        textTestsQuestionDescr.text = question.question
        adapter.setData(question.answers)
        adapter.selectedPosition.value = -1
        adapter.notifyDataSetChanged()
    }
}