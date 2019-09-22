package com.morozov.psychology.ui.fragments.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryEditorPresenter
import com.morozov.psychology.mvp.views.diary.DiaryEditorView
import com.morozov.psychology.utility.AppConstants
import com.morozov.psychology.utility.DateConverter
import kotlinx.android.synthetic.main.diary_think_editor_layout.*
import java.text.SimpleDateFormat
import java.util.*

class DiaryEditorFragment: MvpAppCompatFragment(), DiaryEditorView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: DiaryEditorPresenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var mDate: Date

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.diary_think_editor_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments

        buttonDiarySave.setOnClickListener {
            if (bundle != null) {
                if (bundle.getBoolean(AppConstants.DIARY_IS_NEW_ITEM))
                    mPresenter.saveNewThink(getThink())
                else
                    mPresenter.saveOldThink(getThink())

                mActivityPresenter.showDiaryCards()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments

        mDate = if (bundle != null) {
            bundle.getSerializable(AppConstants.DIARY_SELECTED_DAY) as Date
        } else {
            Date()
        }

        if (bundle != null) {
            if (bundle.getBoolean(AppConstants.DIARY_IS_NEW_ITEM))
                mPresenter.initNewThink(mDate)
            else
                mPresenter.loadOldThink(mDate,
                    bundle.getSerializable(AppConstants.DIARY_OVERWRITE_THINK) as ThinkModel)
        }
    }

    /*
    * DiaryEditorView implementation
    *
    * */
    override fun showButtonSave() {
        buttonDiarySave.visibility = View.VISIBLE
    }

    override fun hideButtonSave() {
        buttonDiarySave.visibility = View.GONE
    }

    override fun showThink(think: ThinkModel) {
        setDate(think.date)
        editTextDiarySituation.setText(think.situation)
        editTextDiaryThink.setText(think.think)
        editTextDiarySensation.setText(think.sensation)
    }

    override fun setDate(date: Date) {
        val dayFormat = SimpleDateFormat("dd")
        val monthFormat = SimpleDateFormat("MM")
        val yearFormat = SimpleDateFormat("yyyy")

        val dateStr =
                    dayFormat.format(date) + " " +
                    DateConverter.getStringMonth(monthFormat.format(date)) + " ," +
                    yearFormat.format(date)

        textDiaryEditorTime.text = dateStr
    }

    private fun getThink(): ThinkModel {
        val bundle = this.arguments

        var date = Date()

        if (bundle != null) {
            date = if (bundle.getBoolean(AppConstants.DIARY_IS_NEW_ITEM))
                mPresenter.dateNew
            else
                mPresenter.dateOld
        }

        mDate.hours = date.hours
        mDate.minutes = date.minutes

        return ThinkModel(mDate, editTextDiarySituation.text.toString(),
            editTextDiaryThink.text.toString(),
            "some",
            editTextDiarySensation.text.toString())
    }
}