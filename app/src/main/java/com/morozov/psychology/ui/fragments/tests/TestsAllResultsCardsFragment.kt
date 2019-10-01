package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.ui.adapters.tests.all.results.TstAllResultsAdapter
import kotlinx.android.synthetic.main.tests_all_results_cards_layout.*

class TestsAllResultsCardsFragment: Fragment() {

    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: TstAllResultsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_all_results_cards_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TstAllResultsAdapter()
        recyclerAllTestsResultsDays.layoutManager = LinearLayoutManager(context)
        recyclerAllTestsResultsDays.adapter = adapter
        adapter.setData(getData())
    }

    private fun getData(): List<Pair<String, List<Pair<String, String>>>> {
        val pair1 = Pair(
            "Вы набрали 36 баллов по шкале катастрофизации ",
            "Вы склоны в некоторой степени преувеливать степень негативных последствий ситуаций. Порой неблагоприятные события кажутся вам ужасными и невыносимыми. Это может повышать уровень тревоги. Во время работы с собственными мыслями, рекомендуем вам обратить внимание на проработку ошибки мышления «катастрофизация»."
        )
        return listOf(Pair("20.06.2019", listOf(pair1)))
    }
}