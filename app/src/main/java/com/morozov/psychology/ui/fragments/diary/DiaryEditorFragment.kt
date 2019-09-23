package com.morozov.psychology.ui.fragments.diary

import android.arch.lifecycle.MutableLiveData
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


    /*
    * LiveData
    *
    * */
    var joy = MutableLiveData<Boolean>()
    var sadness = MutableLiveData<Boolean>()
    var annoyance = MutableLiveData<Boolean>()
    var anxiety = MutableLiveData<Boolean>()
    var disgust = MutableLiveData<Boolean>()
    var interest = MutableLiveData<Boolean>()
    var guilt = MutableLiveData<Boolean>()
    var resentment = MutableLiveData<Boolean>()

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

        setEmotionsOnClick()
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

    /*
    *  Emotions controll
    *
    *  */
    override fun setIsActiveJoy(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.JOY, getPercent()))
                imageJoy.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_joy))
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.JOY)
                imageJoy.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_joy))
            }
        }
    }

    override fun setIsActiveSadness(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.SADNESS, getPercent()))
                imageSadness.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_sedness))
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.SADNESS)
                imageSadness.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_sedness))
            }
        }
    }

    override fun setIsActiveAnnoyance(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.ANNOYANCE, getPercent()))
                imageAnnoyance.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_annoyance))
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.ANNOYANCE)
                imageAnnoyance.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_annoyance))
            }
        }
    }

    override fun setIsActiveAnxiety(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.ANXIETY, getPercent()))
                imageAnxiety.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_anxiety))
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.ANXIETY)
                imageAnxiety.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_anxiety))
            }
        }
    }

    override fun setIsActiveDisgust(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.DISGUST, getPercent()))
                imageDisgust.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_disgust))
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.DISGUST)
                imageDisgust.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_disgust))
            }
        }
    }

    override fun setIsActiveInterest(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.INTEREST, getPercent()))
                imageInterest.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_interest))
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.INTEREST)
                imageInterest.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_interest))
            }
        }
    }

    override fun setIsActiveGuilt(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.GUILT, getPercent()))
                imageGuilt.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_guilt))
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.GUILT)
                imageGuilt.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_guilt))
            }
        }
    }

    override fun setIsActiveResentment(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.RESENTMENT, getPercent()))
                imageResentment.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_resentment))
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.RESENTMENT)
                imageResentment.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_resentment))
            }
        }
    }

    /*
    * Helper functions
    *
    * */
    private fun addEmotion(emotion: EmotionModel) {

    }

    private fun removeEmotion(emotion: EmotionModel.Emotion) {

    }

    private fun getPercent(): Int {
        return 0
    }

    private fun setEmotionsOnClick() {
        setEmotionsObservers()

        imageJoy.setOnClickListener {
            joy.value = !joy.value!!
        }
        imageSadness.setOnClickListener {
            sadness.value = !sadness.value!!
        }
        imageAnnoyance.setOnClickListener {
            annoyance.value = !annoyance.value!!
        }
        imageAnxiety.setOnClickListener {
            anxiety.value = !anxiety.value!!
        }
        imageDisgust.setOnClickListener {
            disgust.value = !disgust.value!!
        }
        imageInterest.setOnClickListener {
            interest.value = !interest.value!!
        }
        imageGuilt.setOnClickListener {
            guilt.value = !guilt.value!!
        }
        imageResentment.setOnClickListener {
            resentment.value = !resentment.value!!
        }
    }

    private fun setEmotionsObservers() {
        joy.observeForever {
            if (it != null)
                setIsActiveJoy(it)
        }
        sadness.observeForever {
            if (it != null)
                setIsActiveSadness(it)
        }
        annoyance.observeForever {
            if (it != null)
                setIsActiveAnnoyance(it)
        }
        anxiety.observeForever {
            if (it != null)
                setIsActiveAnxiety(it)
        }
        disgust.observeForever {
            if (it != null)
                setIsActiveDisgust(it)
        }
        interest.observeForever {
            if (it != null)
                setIsActiveInterest(it)
        }
        guilt.observeForever {
            if (it != null)
                setIsActiveGuilt(it)
        }
        resentment.observeForever {
            if (it != null)
                setIsActiveResentment(it)
        }

        joy.value = false
        sadness.value = false
        annoyance.value = false
        anxiety.value = false
        disgust.value = false
        interest.value = false
        guilt.value = false
        resentment.value = false
    }
}