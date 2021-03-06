package com.morozov.psychology.ui.fragments.diary

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.applandeo.materialcalendarview.builders.DatePickerBuilder
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
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
import kotlinx.android.synthetic.main.item_diary_date_card.*
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.MutableDateTime
import java.text.SimpleDateFormat
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

    /*
    * Calendar
    *
    * */
    var calendar = Calendar.getInstance()
    val calendarListener = DatePickerDialog.OnDateSetListener{ datePicker: DatePicker, year: Int, month: Int, day: Int ->
        val epoch = MutableDateTime()
        epoch.setDate(0)

        val now = MutableDateTime()
        now.year = year
        now.monthOfYear = month + 1
        now.dayOfMonth = day

        mPresenter.showThinkList(Days.daysBetween(epoch, now).days)
        if (DiaryMainPresenter.currentDate == -1)
            recyclerDiaryDays.scrollToPosition(adapterDate.itemCount - 1)
        else
            recyclerDiaryDays.scrollToPosition(DiaryMainPresenter.currentDate)
    }

    private val selectedDayListener = OnSelectDateListener { calendarList ->
        if (calendarList.isNotEmpty()) {
            val calendarTmp = calendarList.first()

            val epoch = MutableDateTime()
            epoch.setDate(0)

            val now = MutableDateTime()
            now.setDate(calendarTmp.timeInMillis)

            mPresenter.showThinkList(Days.daysBetween(epoch, now).days+1)
            if (DiaryMainPresenter.currentDate == -1)
                recyclerDiaryDays.scrollToPosition(adapterDate.itemCount - 1)
            else
                recyclerDiaryDays.scrollToPosition(DiaryMainPresenter.currentDate)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.diary_cards_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageMainSettings.setOnClickListener {
            mActivityPresenter.showSettingsSection()
        }

        adapterDate = DiaryMainDateAdapter(this)
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

        adapterThink = DiaryMainThinkAdapter(this, mPresenter, layoutDiaryCards, childFragmentManager)
        recyclerDiaryThinks.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerDiaryThinks.adapter = adapterThink

        val callback = ItemTouchHelperClass(adapterThink)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerDiaryThinks)

        buttonDiaryAdd.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.time = Date(0)
            calendar.add(Calendar.DATE, recyclerDiaryDays.currentItem)
            mActivityPresenter.showDiaryEditor(true, calendar.time, null)
        }

        imageDiaryCalendar.setOnClickListener {
            showCalendar()
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
                viewEmpty.visibility = View.GONE
                linearArrow.visibility = View.GONE
            }
            false -> {
                textDiaryEmptyDay.visibility = View.VISIBLE
                arrowEmpty.visibility = View.VISIBLE
                viewEmpty.visibility = View.VISIBLE
                linearArrow.visibility = View.VISIBLE
            }
        }
    }

    override fun showThinkList(elements: List<ThinkModel>) {
        adapterThink.setData(elements)
        adapterThink.notifyDataSetChanged()
    }

    override fun showCalendar() {
//        val dialog = DatePickerDialog(
//            context, calendarListener,
//            calendar.get(Calendar.YEAR),
//            calendar.get(Calendar.MONTH),
//            calendar.get(Calendar.DAY_OF_MONTH)
//        )
//
//        val dayMtYrFormat = SimpleDateFormat("dd/MM/yyyy")
//        val dateCal = dayMtYrFormat
//            .parse("${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}")
//
//        dialog.datePicker.maxDate = dateCal.time
//        dialog.show()
        DatePickerBuilder(context!!, selectedDayListener)
            .setEvents(mPresenter.thinkLoader.getThinks().map {
                it.toEvent()
            })
            .setHeaderColor(R.color.colorAccent)
            .setSelectionColor(R.color.colorAccent)
            .setDate(calendar)
            .setMaximumDate(Calendar.getInstance())
            .build()
            .show()
    }

    /*
    * OnItemClickListener implementation
    *
    * */
    override fun onItemClick(view: View, position: Int) {
        if (view.id == diaryDateCard.id) {
            recyclerDiaryDays.smoothScrollToPosition(position)
        } else {
            mActivityPresenter.showDiaryViewing(
                mPresenter.tmpThinkList[position].date
            )
        }
    }

    /*
    * OnItemChangedListener implementation
    *
    * */
    override fun onCurrentItemChanged(viewHolder: DiaryDateViewHolder?, adapterPosition: Int) {
        mPresenter.showThinkList(adapterPosition)
    }
}