package com.morozov.psychology.ui.fragments.diary

import android.os.Bundle
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryMainPresenter
import com.morozov.psychology.mvp.views.diary.DiaryMainView
import com.morozov.psychology.ui.adapters.diary.date.DiaryMainDateAdapter
import com.morozov.psychology.ui.adapters.diary.think.list.DiaryThinkAdapter
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.diary_cards_layout.*

class DiaryMainFragment:MvpAppCompatFragment(), DiaryMainView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: DiaryMainPresenter
    lateinit var mActivityPresenter: MainPresenter

    /*
    * Recycler adapters
    *
    * */
    lateinit var adapterDate: DiaryMainDateAdapter
    lateinit var adapterThink: DiaryThinkAdapter
    lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.diary_cards_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterDate = DiaryMainDateAdapter()
        recyclerDiaryDays.setSlideOnFling(true)
        recyclerDiaryDays.adapter = adapterDate
        recyclerDiaryDays.setItemTransformer(
            ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build())
        recyclerDiaryDays.scrollToPosition(adapterDate.itemCount - 1)
    }

    /*
        * DiaryMainView implementation
        *
        * */
    override fun showIsEmptyMessage(b: Boolean) {

    }

    override fun showThinkList(elements: List<ThinkModel>) {

    }

    override fun showCalendar() {

    }
}