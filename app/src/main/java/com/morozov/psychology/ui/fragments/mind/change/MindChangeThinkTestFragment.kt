package com.morozov.psychology.ui.fragments.mind.change

import android.arch.lifecycle.MutableLiveData
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.MindChangeThinkTestPresenter
import com.morozov.psychology.mvp.views.mind.change.MindChangeThinkTestView
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.mind_change_think_test_layout.*
import java.util.*

class MindChangeThinkTestFragment: MvpAppCompatFragment(), MindChangeThinkTestView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: MindChangeThinkTestPresenter
    lateinit var mActivityPresenter: MainPresenter

    private val selectedEmotion = MutableLiveData<Int>()

    lateinit var mDate: Date

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_think_test_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonMindChangeMain.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
        }

        setEmotionsOnClick()

        selectedEmotion.observeForever {
            verifyIsReadyToSave()

            if (it != null) {
                setSelectedEmotion(it)
                textMindChangeEmotion.visibility = View.VISIBLE
                textMindChangePercent.visibility = View.VISIBLE
                seekBarMindChangeEditor.visibility = View.VISIBLE
                seekBarMindChangeEditor.progress = 1
            } else {
                setSelectedEmotion(-1)
                textMindChangeEmotion.visibility = View.GONE
                textMindChangePercent.visibility = View.GONE
                seekBarMindChangeEditor.visibility = View.GONE
            }
        }

        editMindChangeTmp.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                verifyIsReadyToSave()
            }
        })

        seekBarMindChangeEditor.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textMindChangePercent.text = "$progress%"

                when (selectedEmotion.value) {
                    0 -> textJoy.text = "$progress%"
                    1 -> textSadness.text = "$progress%"
                    2 -> textAnnoyance.text = "$progress%"
                    3 -> textAnxiety.text = "$progress%"
                    4 -> textDisgust.text = "$progress%"
                    5 -> textInterest.text = "$progress%"
                    6 -> textGuilt.text = "$progress%"
                    7 -> textResentment.text = "$progress%"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments

        mDate = if (bundle != null) {
            bundle.getSerializable(AppConstants.DIARY_SELECTED_DAY) as Date
        } else {
            Date()
        }

        mPresenter.loadThink(mDate)
        selectedEmotion.value = null
    }

    /*
    * MindChangeThinkTestView implementation
    *
    * */
    override fun showProfitability(think: ThinkModel) {
        editMindChangeFirstText.setText(think.situation)
        editMindChangeSecondText.setText(think.think)

        buttonMindChangeMindChange.setOnClickListener {
            showCredibility(editMindChangeTmp.text.toString())
        }
    }

    override fun showCredibility(newThink: String) {
        textMindChangeThinkTest.text = "Достоверность"
        textMindChangeSecondText.text = "Новая мысль"
        editMindChangeSecondText.setText(newThink)
        textMindChangeTmp.text = "Проверьте достоверность этой мысли. Пересмотрите факты. Насколько эта мысль доказуема, точна? Верны ли выводы, которые вы сделали? Если мысль недостоверна, попробуйте придумать новую мысль. \n" +
                "Например, вместо мысли «он вечно опаздывает», вернее будет записать «сегодня он пришел на 10 минут позднее обещанного времени. Он порой опаздывал и раньше». "
        editMindChangeTmp.text.clear()
        selectedEmotion.value = null

        scrollMindChange.scrollTo(0, 0)

        buttonMindChangeMindChange.setOnClickListener {
            MindChangeThinkTestView.newThink = editMindChangeTmp.text.toString()
            mActivityPresenter.showMCThinkMistake_1(mDate)
        }
    }

    private fun verifyIsReadyToSave() {
        when(isReadyToSave()) {
            true -> buttonMindChangeMindChange.setBackgroundResource(R.drawable.rectangle_button)
            false -> buttonMindChangeMindChange.setBackgroundResource(R.drawable.rectangle_button_disable)
        }
        buttonMindChangeMindChange.isEnabled = isReadyToSave()
    }

    private fun isReadyToSave(): Boolean {
        return editMindChangeTmp.text.isNotEmpty() && selectedEmotion.value != null
    }

    private fun setEmotionsOnClick() {
        imageJoy.setOnClickListener {
            if (selectedEmotion.value == 0)
                selectedEmotion.value = null
            else
                selectedEmotion.value = 0
        }
        imageSadness.setOnClickListener {
            if (selectedEmotion.value == 1)
                selectedEmotion.value = null
            else
                selectedEmotion.value = 1
        }
        imageAnnoyance.setOnClickListener {
            if (selectedEmotion.value == 2)
                selectedEmotion.value = null
            else
                selectedEmotion.value = 2
        }
        imageAnxiety.setOnClickListener {
            if (selectedEmotion.value == 3)
                selectedEmotion.value = null
            else
                selectedEmotion.value = 3
        }
        imageDisgust.setOnClickListener {
            if (selectedEmotion.value == 4)
                selectedEmotion.value = null
            else
                selectedEmotion.value = 4
        }
        imageInterest.setOnClickListener {
            if (selectedEmotion.value == 5)
                selectedEmotion.value = null
            else
                selectedEmotion.value = 5
        }
        imageGuilt.setOnClickListener {
            if (selectedEmotion.value == 6)
                selectedEmotion.value = null
            else
                selectedEmotion.value = 6
        }
        imageResentment.setOnClickListener {
            if (selectedEmotion.value == 7)
                selectedEmotion.value = null
            else
                selectedEmotion.value = 7
        }
    }

    private fun setSelectedEmotion(num: Int) {
        setEmotion(num == 0, getString(R.string.joy), resources.getDrawable(R.drawable.emotion_active_joy),
            resources.getDrawable(R.drawable.emotion_non_active_joy), imageJoy, textJoy)

        setEmotion(num == 1, getString(R.string.sadness), resources.getDrawable(R.drawable.emotion_active_sedness),
            resources.getDrawable(R.drawable.emotion_non_active_sedness), imageSadness, textSadness)

        setEmotion(num == 2, getString(R.string.annoyance), resources.getDrawable(R.drawable.emotion_active_annoyance),
            resources.getDrawable(R.drawable.emotion_non_active_annoyance), imageAnnoyance, textAnnoyance)

        setEmotion(num == 3, getString(R.string.anxiety), resources.getDrawable(R.drawable.emotion_active_anxiety),
            resources.getDrawable(R.drawable.emotion_non_active_anxiety), imageAnxiety, textAnxiety)

        setEmotion(num == 4, getString(R.string.disgust), resources.getDrawable(R.drawable.emotion_active_disgust),
            resources.getDrawable(R.drawable.emotion_non_active_disgust), imageDisgust, textDisgust)

        setEmotion(num == 5, getString(R.string.interest), resources.getDrawable(R.drawable.emotion_active_interest),
            resources.getDrawable(R.drawable.emotion_non_active_interest), imageInterest, textInterest)

        setEmotion(num == 6, getString(R.string.guilt), resources.getDrawable(R.drawable.emotion_active_guilt),
            resources.getDrawable(R.drawable.emotion_non_active_guilt), imageGuilt, textGuilt)

        setEmotion(num == 7, getString(R.string.resentment), resources.getDrawable(R.drawable.emotion_active_resentment),
            resources.getDrawable(R.drawable.emotion_non_active_resentment), imageResentment, textResentment)
    }

    private fun setEmotion(b: Boolean, name: String, draw1: Drawable, draw2: Drawable,
                           image: ImageView, text: TextView) {
        when (b) {
            true -> {
                text.visibility = View.VISIBLE
                textMindChangeEmotion.text = name
                text.text = "1%"
                image.setImageDrawable(draw1)
            }
            false -> {
                image.setImageDrawable(draw2)
                text.visibility = View.GONE
            }
        }
    }
}