package com.morozov.psychology.ui.fragments.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.diary.EmotionModel
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.diary.DiaryThinkViewingPresenter
import com.morozov.psychology.mvp.views.diary.DiaryThinkViewingView
import com.morozov.psychology.utility.AppConstants
import com.morozov.psychology.utility.DateConverter
import kotlinx.android.synthetic.main.diary_think_viewing_layout.*
import java.text.SimpleDateFormat
import java.util.*

class DiaryThinkViewingFragment: MvpAppCompatFragment(), DiaryThinkViewingView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: DiaryThinkViewingPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.diary_think_viewing_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null)
            buttonDiaryEditThink.setOnClickListener {
                mActivityPresenter.showDiaryEditor(
                    false,
                    bundle.getSerializable(AppConstants.DIARY_SELECTED_DAY) as Date,
                    null
                )
            }

        buttonDiaryToMindChange.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments
        if (bundle != null)
            mPresenter.showThink(bundle.getSerializable(AppConstants.DIARY_SELECTED_DAY) as Date)
    }

    /*
        * DiaryThinkViewingView implementation
        *
        * */
    override fun showThink(think: ThinkModel) {
        setDate(think.date)
        textDiaryViewSituation.text = think.situation
        textDiaryViewThink.text = think.think
        textDiaryViewSensation.text = think.sensation
        showEmotions(think.emotion)
    }

    /*
    * Helper functions
    *
    * */
    private fun setDate(date: Date) {
        val dayFormat = SimpleDateFormat("dd")
        val monthFormat = SimpleDateFormat("MM")
        val yearFormat = SimpleDateFormat("yyyy")

        val dateStr =
            dayFormat.format(date) + " " +
                    DateConverter.getStringMonth(monthFormat.format(date)) + ", " +
                    yearFormat.format(date)

        textDiaryViewingTime.text = dateStr
    }

    private fun showEmotions(emotions: ArrayList<EmotionModel>) {
        for (item in emotions) {
            when (item.emotion) {
                EmotionModel.Emotion.JOY -> {
                    linearJoy.visibility = View.VISIBLE
                    textJoyViewing.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.SADNESS -> {
                    linearSadness.visibility = View.VISIBLE
                    textSadnessViewing.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.ANNOYANCE -> {
                    linearAnnoyance.visibility = View.VISIBLE
                    textAnnoyanceViewing.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.ANXIETY -> {
                    linearAnxiety.visibility = View.VISIBLE
                    textAnxietyViewing.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.DISGUST -> {
                    linearDisgust.visibility = View.VISIBLE
                    textDisgustViewing.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.INTEREST -> {
                    linearInterest.visibility = View.VISIBLE
                    textInterestViewing.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.GUILT -> {
                    linearGuilt.visibility = View.VISIBLE
                    textGuiltViewing.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.RESENTMENT -> {
                    linearResentment.visibility = View.VISIBLE
                    textResentmentViewing.text = item.percent.toString() + "%"
                }
            }
        }
    }
}