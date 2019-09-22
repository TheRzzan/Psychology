package com.morozov.psychology.ui.fragments.diary

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryPresenter
import com.morozov.psychology.mvp.views.diary.DiaryView
import com.morozov.psychology.ui.adapters.diary.date.DiaryDateAdapter
import com.morozov.psychology.ui.adapters.diary.date.DiaryDateViewHolder
import com.morozov.psychology.ui.adapters.diary.think.list.DiaryThinkAdapter
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.diary_cards_layout.*

class DiaryFragment: MvpAppCompatFragment(), DiaryView, DiscreteScrollView.OnItemChangedListener<DiaryDateViewHolder> {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: DiaryPresenter
    lateinit var mActivityPresenter: MainPresenter

    /*
    * Recycler adapters
    *
    * */
    lateinit var adapterDate: DiaryDateAdapter
    lateinit var adapterThink: DiaryThinkAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.diary_cards_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterDate = DiaryDateAdapter()
        recyclerDiaryDays.setSlideOnFling(true)
        recyclerDiaryDays.adapter = adapterDate
        recyclerDiaryDays.addOnItemChangedListener(this)
        recyclerDiaryDays.setItemTransformer(
            ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build())

        adapterThink = DiaryThinkAdapter()
        recyclerDiaryThinks.layoutManager = LinearLayoutManager(context)
        recyclerDiaryThinks.adapter = adapterThink

        buttonDiaryAdd.setOnClickListener {
            mActivityPresenter.showDiaryEditor(true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    /*
    * DiaryView implementation
    *
    * */
    override fun showDates(elements: List<Pair<Int, String>>) {
        adapterDate.setData(elements)
        adapterDate.notifyDataSetChanged()
        recyclerDiaryDays.scrollToPosition(elements.size - 1)
    }

    override fun showThinkList(elements: List<Pair<String, String>>) {
        adapterThink.setData(elements)
        adapterThink.notifyDataSetChanged()
    }

    /*
    * OnItemChangedListener implementation
    *
    * */
    override fun onCurrentItemChanged(viewHolder: DiaryDateViewHolder?, adapterPosition: Int) {
        mPresenter.selectDay(adapterPosition)
    }
}