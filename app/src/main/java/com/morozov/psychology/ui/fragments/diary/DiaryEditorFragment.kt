package com.morozov.psychology.ui.fragments.diary

import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
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
import kotlinx.android.synthetic.main.example_test_layout.*
import kotlinx.android.synthetic.main.item_diary_think_card.*
import java.text.SimpleDateFormat
import java.util.*

class DiaryEditorFragment: MvpAppCompatFragment(), DiaryEditorView, TextWatcher {

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

    /*
    * Emotions
    *
    * */
    var selectedEmotions = arrayListOf<EmotionModel>()
    var currentEmotion= MutableLiveData<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.diary_think_editor_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments

        if (bundle != null) {
            when (bundle.getBoolean(AppConstants.DIARY_THINK_EDITOR_SHOW_BUTTONS)) {
                true -> {
                    buttonDiaryMindChange.visibility = View.VISIBLE
                    buttonDiaryHomeWork.visibility = View.VISIBLE
                    buttonDiaryThinks.visibility = View.VISIBLE
                    buttonDiarySave.visibility = View.GONE

                    editTextDiarySituation.isEnabled = false
                    editTextDiarySensation.isEnabled = false
                    editTextDiaryThink.isEnabled = false

                    setEmotionsOnClick(false)
                }
                false -> {
                    buttonDiaryMindChange.visibility = View.GONE
                    buttonDiaryHomeWork.visibility = View.GONE
                    buttonDiaryThinks.visibility = View.GONE
                    buttonDiarySave.visibility = View.VISIBLE

                    setEmotionsOnClick(true)
                }
            }
        }

        buttonDiarySave.setOnClickListener {
            if (bundle != null) {
                if (bundle.getBoolean(AppConstants.DIARY_IS_NEW_ITEM))
                    mPresenter.saveNewThink(getThink())
                else
                    mPresenter.saveOldThink(getThink())

                mActivityPresenter.showDiaryCards()
            }
        }

        seekBarDiaryEditor.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textDiaryPercent.text = "$progress%"
                if (currentEmotion.value!! >= 0 && currentEmotion.value!! < selectedEmotions.size) {
                    selectedEmotions[currentEmotion.value!!].percent = seekBarDiaryEditor.progress

                    when (selectedEmotions[currentEmotion.value!!].emotion) {
                        EmotionModel.Emotion.JOY -> textJoy.text = "$progress%"
                        EmotionModel.Emotion.SADNESS -> textSadness.text = "$progress%"
                        EmotionModel.Emotion.ANNOYANCE -> textAnnoyance.text = "$progress%"
                        EmotionModel.Emotion.ANXIETY -> textAnxiety.text = "$progress%"
                        EmotionModel.Emotion.DISGUST -> textDisgust.text = "$progress%"
                        EmotionModel.Emotion.INTEREST -> textInterest.text = "$progress%"
                        EmotionModel.Emotion.GUILT -> textGuilt.text = "$progress%"
                        EmotionModel.Emotion.RESENTMENT -> textResentment.text = "$progress%"
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        editTextDiarySituation.addTextChangedListener(this)
        editTextDiarySensation.addTextChangedListener(this)
        editTextDiaryThink.addTextChangedListener(this)

        currentEmotion.observeForever {
            if (it != null)
            when (it) {
                -1 -> hideSeekBar()
                else -> {
                    showSeekBar()
                }
            }
        }

        currentEmotion.value = -1

        buttonDiaryMindChange.setOnClickListener {
            mActivityPresenter.showMindChangeThinkTest(mDate)
        }
        buttonDiaryHomeWork.setOnClickListener {

        }
        buttonDiaryThinks.setOnClickListener {
            mActivityPresenter.showDiaryCards()
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
                mPresenter.loadOldThink(mDate)
        }
    }

    /*
    * TextWatcher implementation
    *
    * */
    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        verifyIsReadyToSave()
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

    override fun showSeekBar() {
        seekBarDiaryEditor.progress = 1
        textDiaryPercent.text = "1%"

        textDiaryPercent.visibility = View.VISIBLE
        textDiaryEmotion.visibility = View.VISIBLE
        seekBarDiaryEditor.visibility = View.VISIBLE
    }

    override fun hideSeekBar() {
        textDiaryPercent.visibility = View.GONE
        textDiaryEmotion.visibility = View.GONE
        seekBarDiaryEditor.visibility = View.GONE
    }

    override fun showThink(think: ThinkModel) {
        setDate(think.date)
        editTextDiarySituation.setText(think.situation)
        editTextDiaryThink.setText(think.think)
        editTextDiarySensation.setText(think.sensation)
        showEmotions(think.emotion)
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

        date.date = mDate.date
        date.month = mDate.month
        date.year = mDate.year

        for (item in selectedEmotions) {
            println(item.percent)
        }

        return ThinkModel(date, editTextDiarySituation.text.toString(),
            editTextDiaryThink.text.toString(),
            selectedEmotions,
            editTextDiarySensation.text.toString())
    }

    /*
    *  Emotions controll
    *
    *  */
    override fun setIsActiveJoy(b: Boolean) {
        when (b) {
            true -> {
                if (selectedEmotions.size > 2)
                    return

                addEmotion(EmotionModel(EmotionModel.Emotion.JOY, getPercent()))
                imageJoy.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_joy))
                textJoy.text = "1%"
                textJoy.visibility = View.VISIBLE

                textDiaryEmotion.text = getString(R.string.joy)
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.JOY)
                imageJoy.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_joy))
                textJoy.visibility = View.GONE
            }
        }

        verifyIsReadyToSave()
    }

    override fun setIsActiveSadness(b: Boolean) {
        when (b) {
            true -> {
                if (selectedEmotions.size > 2)
                    return

                addEmotion(EmotionModel(EmotionModel.Emotion.SADNESS, getPercent()))
                imageSadness.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_sedness))
                textSadness.text = "1%"
                textSadness.visibility = View.VISIBLE

                textDiaryEmotion.text = getString(R.string.sadness)
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.SADNESS)
                imageSadness.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_sedness))
                textSadness.visibility = View.GONE
            }
        }

        verifyIsReadyToSave()
    }

    override fun setIsActiveAnnoyance(b: Boolean) {
        when (b) {
            true -> {
                if (selectedEmotions.size > 2)
                    return

                addEmotion(EmotionModel(EmotionModel.Emotion.ANNOYANCE, getPercent()))
                imageAnnoyance.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_annoyance))
                textAnnoyance.text = "1%"
                textAnnoyance.visibility = View.VISIBLE

                textDiaryEmotion.text = getString(R.string.annoyance)
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.ANNOYANCE)
                imageAnnoyance.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_annoyance))
                textAnnoyance.visibility = View.GONE
            }
        }

        verifyIsReadyToSave()
    }

    override fun setIsActiveAnxiety(b: Boolean) {
        when (b) {
            true -> {
                if (selectedEmotions.size > 2)
                    return

                addEmotion(EmotionModel(EmotionModel.Emotion.ANXIETY, getPercent()))
                imageAnxiety.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_anxiety))
                textAnxiety.text = "1%"
                textAnxiety.visibility = View.VISIBLE

                textDiaryEmotion.text = getString(R.string.anxiety)
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.ANXIETY)
                imageAnxiety.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_anxiety))
                textAnxiety.visibility = View.GONE
            }
        }

        verifyIsReadyToSave()
    }

    override fun setIsActiveDisgust(b: Boolean) {
        when (b) {
            true -> {
                if (selectedEmotions.size > 2)
                    return

                addEmotion(EmotionModel(EmotionModel.Emotion.DISGUST, getPercent()))
                imageDisgust.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_disgust))
                textDisgust.text = "1%"
                textDisgust.visibility = View.VISIBLE

                textDiaryEmotion.text = getString(R.string.disgust)
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.DISGUST)
                imageDisgust.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_disgust))
                textDisgust.visibility = View.GONE
            }
        }

        verifyIsReadyToSave()
    }

    override fun setIsActiveInterest(b: Boolean) {
        when (b) {
            true -> {
                if (selectedEmotions.size > 2)
                    return

                addEmotion(EmotionModel(EmotionModel.Emotion.INTEREST, getPercent()))
                imageInterest.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_interest))
                textInterest.text = "1%"
                textInterest.visibility = View.VISIBLE

                textDiaryEmotion.text = getString(R.string.interest)
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.INTEREST)
                imageInterest.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_interest))
                textInterest.visibility = View.GONE
            }
        }

        verifyIsReadyToSave()
    }

    override fun setIsActiveGuilt(b: Boolean) {
        when (b) {
            true -> {
                if (selectedEmotions.size > 2)
                    return

                addEmotion(EmotionModel(EmotionModel.Emotion.GUILT, getPercent()))
                imageGuilt.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_guilt))
                textGuilt.text = "1%"
                textGuilt.visibility = View.VISIBLE

                textDiaryEmotion.text = getString(R.string.guilt)
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.GUILT)
                imageGuilt.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_guilt))
                textGuilt.visibility = View.GONE
            }
        }

        verifyIsReadyToSave()
    }

    override fun setIsActiveResentment(b: Boolean) {
        when (b) {
            true -> {
                if (selectedEmotions.size > 2)
                    return

                addEmotion(EmotionModel(EmotionModel.Emotion.RESENTMENT, getPercent()))
                imageResentment.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_resentment))
                textResentment.text = "1%"
                textResentment.visibility = View.VISIBLE

                textDiaryEmotion.text = getString(R.string.resentment)
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.RESENTMENT)
                imageResentment.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_resentment))
                textResentment.visibility = View.GONE
            }
        }

        verifyIsReadyToSave()
    }

    /*
    * Helper functions
    *
    * */
    fun verifyIsReadyToSave() {
        when(isReadyToSave()) {
            true -> buttonDiarySave.setBackgroundResource(R.drawable.rectangle_button)
            false -> buttonDiarySave.setBackgroundResource(R.drawable.rectangle_button_disable)
        }
        buttonDiarySave.isEnabled = isReadyToSave()
    }

    fun isReadyToSave(): Boolean =
                editTextDiarySituation.text.isNotEmpty() &&
                editTextDiarySensation.text.isNotEmpty() &&
                editTextDiaryThink.text.isNotEmpty() &&
                selectedEmotions.isNotEmpty()

    private fun showEmotions(items: List<EmotionModel>) {
        for (item in items) {
            when (item.emotion) {
                EmotionModel.Emotion.JOY -> {
                    joy.value = true
                    textJoy.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.SADNESS -> {
                    sadness.value = true
                    textSadness.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.ANNOYANCE -> {
                    annoyance.value = true
                    textAnnoyance.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.ANXIETY -> {
                    anxiety.value = true
                    textAnxiety.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.DISGUST -> {
                    disgust.value = true
                    textDisgust.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.INTEREST -> {
                    interest.value = true
                    textInterest.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.GUILT -> {
                    guilt.value = true
                    textGuilt.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.RESENTMENT -> {
                    resentment.value = true
                    textResentment.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
            }
        }
    }

    private fun addEmotion(emotion: EmotionModel) {
        selectedEmotions.add(emotion)
        currentEmotion.value = selectedEmotions.size - 1
    }

    private fun removeEmotion(emotion: EmotionModel.Emotion) {
        val newList = arrayListOf<EmotionModel>()

        for (item in selectedEmotions) {
            if (item.emotion != emotion) {
                newList.add(item)
            } else {
                currentEmotion.value = -1
            }
        }
        selectedEmotions = newList
    }

    private fun getPercent(): Int {
        return 0
    }

    private fun setEmotionsOnClick(b: Boolean) {
        setEmotionsObservers()

        if (!b)
            return

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