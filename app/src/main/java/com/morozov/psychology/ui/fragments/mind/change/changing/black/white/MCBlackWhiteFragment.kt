package com.morozov.psychology.ui.fragments.mind.change.changing.black.white

import androidx.lifecycle.MutableLiveData
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.diary.EmotionModel
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.black.white.MCBlackWhitePresenter
import com.morozov.psychology.mvp.views.mind.change.changing.black.white.MCBlackWhiteView
import com.morozov.psychology.ui.adapters.mind.change.aback.white.MCBlackWhiteAdapter
import com.morozov.psychology.ui.fragments.mind.change.MindChangeTest
import kotlinx.android.synthetic.main.mind_change_black_and_white_layout.*

class MCBlackWhiteFragment: MvpAppCompatFragment(), MCBlackWhiteView, MindChangeTest {

    @InjectPresenter
    lateinit var mPresenter: MCBlackWhitePresenter
    lateinit var mActivityPresenter: MainPresenter

    val adapter = MCBlackWhiteAdapter()

    private val selectedEmotion = MutableLiveData<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_black_and_white_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddNewThink.setOnClickListener {
            mPresenter.saveNewThink(
                editNewThink.text.toString(),
                when (selectedEmotion.value) {
                    0 -> EmotionModel(EmotionModel.Emotion.JOY, seekBarBWEmotions.progress)
                    1 -> EmotionModel(EmotionModel.Emotion.SADNESS, seekBarBWEmotions.progress)
                    2 -> EmotionModel(EmotionModel.Emotion.ANNOYANCE, seekBarBWEmotions.progress)
                    3 -> EmotionModel(EmotionModel.Emotion.ANXIETY, seekBarBWEmotions.progress)
                    4 -> EmotionModel(EmotionModel.Emotion.DISGUST, seekBarBWEmotions.progress)
                    5 -> EmotionModel(EmotionModel.Emotion.INTEREST, seekBarBWEmotions.progress)
                    6 -> EmotionModel(EmotionModel.Emotion.GUILT, seekBarBWEmotions.progress)
                    7 -> EmotionModel(EmotionModel.Emotion.RESENTMENT, seekBarBWEmotions.progress)
                    else -> EmotionModel(EmotionModel.Emotion.RESENTMENT, 0)
                })
            mActivityPresenter.showMindChangeSection()
        }

        recyclerBW.adapter = adapter
        recyclerBW.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context)

        adapter.isAllFilled.observeForever {
            verifyIsReadyToSave()
        }

        selectedEmotion.observeForever {
            verifyIsReadyToSave()

            if (it != null) {
                setSelectedEmotion(it)
                textBWEmotion.visibility = View.VISIBLE
                textBWEmotionsPercent.visibility = View.VISIBLE
                seekBarBWEmotions.visibility = View.VISIBLE
                seekBarBWEmotions.progress = 1
            } else {
                setSelectedEmotion(-1)
                textBWEmotion.visibility = View.GONE
                textBWEmotionsPercent.visibility = View.GONE
                seekBarBWEmotions.visibility = View.GONE
            }
        }

        seekBarBWEmotions.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textBWEmotionsPercent.text = "$progress%"

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

        editNewThink.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                verifyIsReadyToSave()
            }
        })

        setEmotionsOnClick()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
        selectedEmotion.value = null
        adapter.isAllFilled.value = false
    }

    /*
    * Helper functions
    *
    * */
    private fun verifyIsReadyToSave() {
        when(isReadyToSave()) {
            true -> buttonAddNewThink.setBackgroundResource(R.drawable.rectangle_button)
            false -> buttonAddNewThink.setBackgroundResource(R.drawable.rectangle_button_disable)
        }
        buttonAddNewThink.isEnabled = isReadyToSave()
    }

    private fun isReadyToSave(): Boolean {
        return editNewThink.text.toString().isNotEmpty() && adapter.isAllFilled.value == true &&
                selectedEmotion.value != null
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
                           image: ImageView, text: TextView
    ) {
        when (b) {
            true -> {
                text.visibility = View.VISIBLE
                textBWEmotion.text = name
                text.text = "1%"
                image.setImageDrawable(draw1)
            }
            false -> {
                image.setImageDrawable(draw2)
                text.visibility = View.GONE
            }
        }
    }

    /*
    * MCBlackWhiteView implementation
    *
    * */
    override fun showRecycler(data: List<String>) {
        adapter.setData(data)
        adapter.notifyDataSetChanged()
    }

    override fun showThink(situation: String, newThink: String) {
        editBWSituation.setText(situation)
        editBWThink.setText(newThink)
    }
}