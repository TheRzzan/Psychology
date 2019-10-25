package com.morozov.psychology.ui.fragments.mind.change.homework.main

import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.homework.main.HmMainPresenter
import com.morozov.psychology.mvp.views.mind.change.homework.main.HmMainView
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.homework_main_layout.*
import java.util.*

class HmMainFragment: MvpAppCompatFragment(), HmMainView {

    @InjectPresenter
    lateinit var mPresenter: HmMainPresenter
    lateinit var mActivityPresenter: MainPresenter

    private val selectedMistake = MutableLiveData<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_main_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageDisastrous.setOnClickListener {
            if (selectedMistake.value == 0)
                selectedMistake.value = null
            else
                selectedMistake.value = 0
        }

        imageDepreciation.setOnClickListener {
            if (selectedMistake.value == 1)
                selectedMistake.value = null
            else
                selectedMistake.value = 1
        }

        imageBlackWhite.setOnClickListener {
            if (selectedMistake.value == 2)
                selectedMistake.value = null
            else
                selectedMistake.value = 2
        }

        imageEmotional.setOnClickListener {
            if (selectedMistake.value == 3)
                selectedMistake.value = null
            else
                selectedMistake.value = 3
        }

        imageMindReading.setOnClickListener {
            if (selectedMistake.value == 4)
                selectedMistake.value = null
            else
                selectedMistake.value = 4
        }

        imageOvergeneration.setOnClickListener {
            if (selectedMistake.value == 5)
                selectedMistake.value = null
            else
                selectedMistake.value = 5
        }

        imageMinimalism.setOnClickListener {
            if (selectedMistake.value == 6)
                selectedMistake.value = null
            else
                selectedMistake.value = 6
        }

        imageLabeling.setOnClickListener {
            if (selectedMistake.value == 7)
                selectedMistake.value = null
            else
                selectedMistake.value = 7
        }

        imageCommitment.setOnClickListener {
            if (selectedMistake.value == 8)
                selectedMistake.value = null
            else
                selectedMistake.value = 8
        }

        imageTunnel.setOnClickListener {
            if (selectedMistake.value == 9)
                selectedMistake.value = null
            else
                selectedMistake.value = 9
        }

        imagePersonalization.setOnClickListener {
            if (selectedMistake.value == 10)
                selectedMistake.value = null
            else
                selectedMistake.value = 10
        }

        selectedMistake.observeForever {
            if (it == null) {
                buttonStart.setBackgroundResource(R.drawable.rectangle_button_disable)
                buttonStart.isEnabled = false
            } else {
                buttonStart.setBackgroundResource(R.drawable.rectangle_button)
                buttonStart.isEnabled = true
            }

            selectMistake(imageDisastrous, 0 == it)
            selectMistake(imageDepreciation, 1 == it)
            selectMistake(imageBlackWhite, 2 == it)
            selectMistake(imageEmotional, 3 == it)
            selectMistake(imageMindReading, 4 == it)
            selectMistake(imageOvergeneration, 5 == it)
            selectMistake(imageMinimalism, 6 == it)
            selectMistake(imageLabeling, 7 == it)
            selectMistake(imageCommitment, 8 == it)
            selectMistake(imageTunnel, 9 == it)
            selectMistake(imagePersonalization, 10 == it)
        }

        selectedMistake.value = null

        buttonContinue.setOnClickListener {
            when (selectedMistake.value) {
                0 -> mActivityPresenter.showHmDisastorous_1()
                1 -> mActivityPresenter.showHmDeprecation()
                2 -> mActivityPresenter.showHmBlackWhite()
                3 -> mActivityPresenter.showHmEmotional()
                4 -> mActivityPresenter.showHmMindReading_1()
                5 -> mActivityPresenter.showHmOvergeneration()
                6 -> mActivityPresenter.showHmMinimalism()
                7 -> mActivityPresenter.showHmLabeling()
                8 -> mActivityPresenter.showHmCommitment_1()
                9 -> mActivityPresenter.showHmTunnel()
                10 -> mActivityPresenter.showHmPersonalization()
            }
        }

        buttonStart.setOnClickListener {
            when (selectedMistake.value) {
                0 -> mActivityPresenter.showHmDisastorous_1()
                1 -> mActivityPresenter.showHmDeprecation()
                2 -> mActivityPresenter.showHmBlackWhite()
                3 -> mActivityPresenter.showHmEmotional()
                4 -> mActivityPresenter.showHmMindReading_1()
                5 -> mActivityPresenter.showHmOvergeneration()
                6 -> mActivityPresenter.showHmMinimalism()
                7 -> mActivityPresenter.showHmLabeling()
                8 -> mActivityPresenter.showHmCommitment_1()
                9 -> mActivityPresenter.showHmTunnel()
                10 -> mActivityPresenter.showHmPersonalization()
            }
        }

        buttonDiary.setOnClickListener {
            mActivityPresenter.showDiaryCards()
        }

        val bundle = this.arguments

        HmMainView.date = if (bundle != null) {
            bundle.getSerializable(AppConstants.DIARY_SELECTED_DAY) as Date
        } else {
            Date()
        }
    }

    private fun selectMistake(image: ImageView, b: Boolean) {
        val minPadding = 0
        val maxPadding = 5
        if (b) {
            image.background = resources.getDrawable(R.drawable.rectangle_edit_text_with_shadow)
            image.setPadding(maxPadding, maxPadding, maxPadding, maxPadding)
        }
        else {
            image.setBackgroundResource(0)
            image.setPadding(minPadding, minPadding, minPadding, minPadding)
        }
    }
}