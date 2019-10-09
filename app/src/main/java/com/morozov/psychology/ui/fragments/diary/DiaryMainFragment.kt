package com.morozov.psychology.ui.fragments.diary

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
import com.morozov.psychology.ui.adapters.diary.date.DiaryDateViewHolder
import com.morozov.psychology.ui.adapters.diary.date.DiaryMainDateAdapter
import com.morozov.psychology.ui.adapters.diary.think.list.DiaryMainThinkAdapter
import com.morozov.psychology.ui.adapters.diary.think.list.DiaryThinkAdapter
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import com.morozov.psychology.utility.ItemTouchHelperClass
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.diary_cards_layout.*
import java.util.*

class DiaryMainFragment:MvpAppCompatFragment(), DiaryMainView,
    DiscreteScrollView.OnItemChangedListener<DiaryDateViewHolder>, OnItemClickListener {

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
    lateinit var adapterThink: DiaryMainThinkAdapter
    lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.diary_cards_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterDate = DiaryMainDateAdapter()
        recyclerDiaryDays.setSlideOnFling(true)
        recyclerDiaryDays.adapter = adapterDate
        recyclerDiaryDays.addOnItemChangedListener(this)
        recyclerDiaryDays.setItemTransformer(
            ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build())

        if (DiaryMainPresenter.currentDate == -1)
            recyclerDiaryDays.scrollToPosition(adapterDate.itemCount - 1)
        else
            recyclerDiaryDays.scrollToPosition(DiaryMainPresenter.currentDate)

        adapterThink = DiaryMainThinkAdapter(this, mPresenter, layoutDiaryCards)
        recyclerDiaryThinks.layoutManager = LinearLayoutManager(context)
        recyclerDiaryThinks.adapter = adapterThink

        val callback = ItemTouchHelperClass(adapterThink)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerDiaryThinks)

        buttonDiaryAdd.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.time = Date(0)
            calendar.add(Calendar.DATE, recyclerDiaryDays.currentItem)
            mActivityPresenter.showDiaryEditor(true, calendar.time)
        }
    }

    /*
    * DiaryMainView implementation
    *
    * */
    override fun showIsEmptyMessage(b: Boolean) {
        when (!b) {
            true -> {
                textDiaryEmptyDay.visibility = View.GONE
                arrowEmpty.visibility = View.GONE
            }
            false -> {
                textDiaryEmptyDay.visibility = View.VISIBLE
                arrowEmpty.visibility = View.VISIBLE
            }
        }
    }

    override fun showThinkList(elements: List<ThinkModel>) {
        adapterThink.setData(elements)
        adapterThink.notifyDataSetChanged()
    }

    override fun showCalendar() {

    }

    /*
    * OnItemClickListener implementation
    *
    * */
    override fun onItemClick(view: View, position: Int) {
        mActivityPresenter.showDiaryViewing(
            mPresenter.tmpThinkList[position].date
        )
    }

    /*
    * OnItemChangedListener implementation
    *
    * */
    override fun onCurrentItemChanged(viewHolder: DiaryDateViewHolder?, adapterPosition: Int) {
        mPresenter.showThinkList(adapterPosition)
    }
}