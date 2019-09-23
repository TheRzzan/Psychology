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
import kotlinx.android.synthetic.main.diary_think_editor_layout.*
import kotlinx.android.synthetic.main.diary_think_viewing_layout.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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
                mActivityPresenter.showDiaryEditor(false,
                    bundle.getSerializable(AppConstants.DIARY_SELECTED_DAY) as Date)
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
                    DateConverter.getStringMonth(monthFormat.format(date)) + " ," +
                    yearFormat.format(date)

        textDiaryViewingTime.text = dateStr
    }

    private fun showEmotions(emotions: ArrayList<EmotionModel>) {
        for (item in emotions) {
            when (item.emotion) {
                EmotionModel.Emotion.JOY -> imageJoyViewing.visibility = View.VISIBLE
                EmotionModel.Emotion.SADNESS -> imageSadnessViewing.visibility = View.VISIBLE
                EmotionModel.Emotion.ANNOYANCE -> imageAnnoyanceViewing.visibility = View.VISIBLE
                EmotionModel.Emotion.ANXIETY -> imageAnxietyViewing.visibility = View.VISIBLE
                EmotionModel.Emotion.DISGUST -> imageDisgustViewing.visibility = View.VISIBLE
                EmotionModel.Emotion.INTEREST -> imageInterestViewing.visibility = View.VISIBLE
                EmotionModel.Emotion.GUILT -> imageGuiltViewing.visibility = View.VISIBLE
                EmotionModel.Emotion.RESENTMENT -> imageResentmentViewing.visibility = View.VISIBLE
            }
        }
    }
}