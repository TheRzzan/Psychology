package com.morozov.psychology.ui.fragments.diary

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryPresenter
import com.morozov.psychology.mvp.views.diary.DiaryView
import com.morozov.psychology.ui.adapters.diary.date.DiaryDateAdapter
import com.morozov.psychology.ui.adapters.diary.date.DiaryDateViewHolder
import com.morozov.psychology.ui.adapters.diary.think.list.DiaryThinkAdapter
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import com.morozov.psychology.utility.ItemTouchHelperClass
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.diary_cards_layout.*
import kotlinx.android.synthetic.main.item_diary_date_card.*
import java.text.SimpleDateFormat
import java.util.*

class DiaryFragment:
    MvpAppCompatFragment(), DiaryView, View.OnClickListener,
    DiscreteScrollView.OnItemChangedListener<DiaryDateViewHolder>, OnItemClickListener {

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
    lateinit var itemTouchHelper: ItemTouchHelper

    /*
    * Calendar
    *
    * */
    var calendar = Calendar.getInstance()
    val calendarListener = DatePickerDialog.OnDateSetListener{ datePicker: DatePicker, year: Int, month: Int, day: Int ->
        val dayMtYrFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateCal = dayMtYrFormat.parse("$day/${month + 1}/$year")

        mPresenter.calendarDateSelected(dateCal)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.diary_cards_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterDate = DiaryDateAdapter(this)
        recyclerDiaryDays.setSlideOnFling(true)
        recyclerDiaryDays.adapter = adapterDate
        recyclerDiaryDays.addOnItemChangedListener(this)
        recyclerDiaryDays.setItemTransformer(
            ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build())

        adapterThink = DiaryThinkAdapter(this, mPresenter, layoutDiaryCards)
        recyclerDiaryThinks.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerDiaryThinks.adapter = adapterThink

        buttonDiaryAdd.setOnClickListener {
            mActivityPresenter.showDiaryEditor(true, mPresenter.dateList[recyclerDiaryDays.currentItem], null)
        }

        val callback = ItemTouchHelperClass(adapterThink)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerDiaryThinks)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    /*
    * OnClickListener implementation
    *
    * */
    override fun onClick(v: View?) {
        showCalendar()
    }

    /*
    * OnItemClickListener implementation
    *
    * */
    override fun onItemClick(view: View, position: Int) {
        if (view.id == diaryDateCard.id) {
            showCalendar()
        } else {
            mActivityPresenter.showDiaryViewing(
                mPresenter.lastMonthData[recyclerDiaryDays.currentItem][position].date
            )
        }
    }

    /*
    * DiaryView implementation
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

    override fun showDates(elements: List<Date>) {
        adapterDate.setData(elements)
        adapterDate.notifyDataSetChanged()
        recyclerDiaryDays.scrollToPosition(DiaryPresenter.currentDate)
    }

    override fun showThinkList(elements: List<ThinkModel>) {
        showIsEmptyMessage(elements.isEmpty())

        adapterThink.setData(elements)
        adapterThink.notifyDataSetChanged()
        if (elements.isNotEmpty())
            recyclerDiaryThinks.scrollToPosition(0)
    }

    override fun showCalendar() {
        val dialog = DatePickerDialog(
            context, calendarListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        val dayMtYrFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateCal = dayMtYrFormat
            .parse("${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}")

        dialog.datePicker.maxDate = dateCal.time
        dialog.show()
    }

    /*
    * OnItemChangedListener implementation
    *
    * */
    override fun onCurrentItemChanged(viewHolder: DiaryDateViewHolder?, adapterPosition: Int) {
        mPresenter.selectDay(adapterPosition)
    }
}