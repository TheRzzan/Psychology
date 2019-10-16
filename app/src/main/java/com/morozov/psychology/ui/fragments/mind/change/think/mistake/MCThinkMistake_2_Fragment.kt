package com.morozov.psychology.ui.fragments.mind.change.think.mistake

import android.arch.lifecycle.MutableLiveData
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
        }

        selectedMistake.value = null

        buttonThinkMining.setOnClickListener {
            when (selectedMistake.value) {
                0 -> mActivityPresenter.showMCDisastorous_1()
                1 -> mActivityPresenter.showMCDeprecation_1()
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