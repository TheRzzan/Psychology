package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.ui.adapters.tests.quiz.TstQuizAdapter
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.example_fix_test_layout.*
import kotlinx.android.synthetic.main.tests_quiz_layout.*

class TestsQuizFragment: Fragment() {

    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: TstQuizAdapter

    var checked = 0
    val questionsAmount = 4

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_quiz_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSegmentProgressCount(questionsAmount)
        increaseSegmentProgress()

        adapter = TstQuizAdapter()

        recyclerTestsQuestion.layoutManager = LinearLayoutManager(context)
        recyclerTestsQuestion.adapter = adapter

        adapter.setData(listOf("Полностью согласен", "В основном согласен",
            "Скорее согласен", "Трудно определить", "Скорее не согласен"))

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
            adapter.setData(listOf("Полностью согласен", "В основном согласен",
                "Скорее согласен", "Трудно определить", "Скорее не согласен"))
            adapter.selectedPosition.value = -1
            increaseSegmentProgress()
        }
    }

    fun setSegmentProgressCount(count: Int) {
        segmProgressTestsQuestion.setSegmentCount(count)
    }

    fun increaseSegmentProgress() {
        segmProgressTestsQuestion.incrementCompletedSegments()
        checked++

        if (checked == questionsAmount) {
            buttonTestsFinishQuiz.text = "Завершить"
            buttonTestsFinishQuiz.setOnClickListener {
                val bundle = this.arguments ?: return@setOnClickListener

                val name = bundle.getString(AppConstants.TEST_NAME) ?: return@setOnClickListener
                
                mActivityPresenter.showTestQuizResults(name)
            }
        }
    }
}