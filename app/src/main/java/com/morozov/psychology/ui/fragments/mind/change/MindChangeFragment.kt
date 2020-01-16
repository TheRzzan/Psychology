package com.morozov.psychology.ui.fragments.mind.change

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.MindChangePresenter
import com.morozov.psychology.mvp.views.mind.change.MindChangeView
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import com.morozov.psychology.ui.adapters.mind.change.MindChangeThinkAdapter
import kotlinx.android.synthetic.main.mind_change_main_layout.*
import org.joda.time.Days
import org.joda.time.MutableDateTime
import java.text.SimpleDateFormat
import java.util.*

class MindChangeFragment: MvpAppCompatFragment(), MindChangeView, OnItemClickListener {

    @InjectPresenter
    lateinit var mPresenter: MindChangePresenter
    lateinit var mActivityPresenter: MainPresenter

    /*
    * Recycler adapters
    *
    * */
    lateinit var adapterThink: MindChangeThinkAdapter

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

        textDayMonthYear.text = "$day.${month + 1}.$year"

        mPresenter.showThinkList(Days.daysBetween(epoch, now).days)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_main_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageMainSettings.setOnClickListener {
            mActivityPresenter.showSettingsSection()
        }

        adapterThink = MindChangeThinkAdapter(this)
        recyclerMindChangeThinks.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerMindChangeThinks.adapter = adapterThink

        relativeDayMonthYear.setOnClickListener {
            showCalendar()
        }

        cardHomework.setOnClickListener {
            mActivityPresenter.showHmMain(Date())
        }
    }

    /*
    * MindChangeView implementation
    *
    * */
    override fun showSelectDate(b: Boolean) {
        when (b) {
            true -> textPreview.visibility = View.VISIBLE
            false -> textPreview.visibility = View.GONE
        }
    }

    override fun showEmptyDate(b: Boolean) {
        when (b) {
            true -> {
                textMindChangeEmptyDay.visibility = View.VISIBLE
                recyclerMindChangeThinks.minimumHeight = 220
            }
            false -> {
                textMindChangeEmptyDay.visibility = View.GONE
                recyclerMindChangeThinks.minimumHeight = 0
            }
        }
    }

    override fun showThinkList(elements: List<ThinkModel>) {
        adapterThink.setData(elements)
        adapterThink.notifyDataSetChanged()
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
    * OnItemClickListener implementation
    *
    * */
    override fun onItemClick(view: View, position: Int) {
        mActivityPresenter.showDiaryEditor(
            false,
            mPresenter.tmpThinkList[position].date,
            true
        )
    }
}