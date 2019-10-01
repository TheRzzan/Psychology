package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.ui.adapters.tests.results.TstResultsAdapter
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.tests_quiz_results_layout.*

class TestsResultsFragment: Fragment() {

    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: TstResultsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_quiz_results_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TstResultsAdapter()

        recyclerTestQuiz.layoutManager = LinearLayoutManager(context)
        recyclerTestQuiz.adapter = adapter

        adapter.setData(loadData())

        buttonNextQuiz.setOnClickListener {
            val bundle = this.arguments ?: return@setOnClickListener

            val name = bundle.getString(AppConstants.TEST_NAME) ?: return@setOnClickListener

            mActivityPresenter.showTestQuiz(name)
        }

        buttonChoseAnotherQuiz.setOnClickListener {
            mActivityPresenter.showTestSection()
        }

        buttonDiary.setOnClickListener {
            mActivityPresenter.showDiaryCards()
        }

    }

    private fun loadData(): List<Pair<String, String>> {
        return listOf(Pair("Вы набрали 36 баллов по шкале катастрофизации ", "Вы склоны в некоторой степени преувеливать степень негативных последствий ситуаций. Порой неблагоприятные события кажутся вам ужасными и невыносимыми. Это может повышать уровень тревоги. Во время работы с собственными мыслями, рекомендуем вам обратить внимание на проработку ошибки мышления «катастрофизация»."))
    }
}