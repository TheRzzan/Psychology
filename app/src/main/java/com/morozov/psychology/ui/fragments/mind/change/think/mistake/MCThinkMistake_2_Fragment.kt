package com.morozov.psychology.ui.fragments.mind.change.think.mistake

import androidx.lifecycle.MutableLiveData
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.diary.ThinkModel
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.think.mintake.MCThinkMistake_2_Presenter
import com.morozov.psychology.mvp.views.mind.change.MindChangeThinkTestView
import com.morozov.psychology.mvp.views.mind.change.think.mintake.MCThinkMistake_2_View
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.mind_change_thinking_mistake_2_layout.*
import java.util.*

class MCThinkMistake_2_Fragment: MvpAppCompatFragment(), MCThinkMistake_2_View {

    @InjectPresenter
    lateinit var mPresenter: MCThinkMistake_2_Presenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var mDate: Date

    private val selectedMistake = MutableLiveData<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_thinking_mistake_2_layout, container, false)

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
                buttonThinkMining.setBackgroundResource(R.drawable.rectangle_button_disable)
                buttonThinkMining.isEnabled = false
            } else {
                buttonThinkMining.setBackgroundResource(R.drawable.rectangle_button)
                buttonThinkMining.isEnabled = true
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

        buttonThinkMining.setOnClickListener {
            when (selectedMistake.value) {
                0 -> mActivityPresenter.showMCDisastorous_1()
                1 -> mActivityPresenter.showMCDeprecation_1()
                2 -> mActivityPresenter.showMCBlackWhite()
                3 -> mActivityPresenter.showMCEmotional()
                4 -> mActivityPresenter.showMCMindReading()
                5 -> mActivityPresenter.showMCOvergeneration()
                6 -> mActivityPresenter.showMCMinimalism()
                7 -> mActivityPresenter.showMCLabeling()
                8 -> mActivityPresenter.showMCCommitment_1()
                9 -> mActivityPresenter.showMCTunnel()
                10 -> mActivityPresenter.showMCPersonalization()
            }
        }

        buttonChoseAnother.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
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

        mPresenter.loadThink(mDate)
    }

    /*
    * MCThinkMistake_2_View implementation
    *
    * */
    override fun showThink(thinkModel: ThinkModel) {
        editThinkMistakeFirstText.setText(thinkModel.situation)
        editThinkMistakeSecondText.setText(MindChangeThinkTestView.newThink)
    }

    private fun selectMistake(image: ImageView, b: Boolean) {
        if (b)
            image.background = resources.getDrawable(R.drawable.rectangle_edit_text_with_shadow)
        else
            image.setBackgroundResource(0)
    }
}