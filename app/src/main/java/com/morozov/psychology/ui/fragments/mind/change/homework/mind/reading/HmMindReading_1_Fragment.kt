package com.morozov.psychology.ui.fragments.mind.change.homework.mind.reading

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.homework.mind.reading.HmMindReading_1_Presenter
import com.morozov.psychology.mvp.views.mind.change.homework.mind.reading.HmMindReading_1_View
import com.morozov.psychology.ui.fragments.mind.change.MindChangeTest
import kotlinx.android.synthetic.main.homework_mind_reading_1_layout.*

class HmMindReading_1_Fragment: MvpAppCompatFragment(), HmMindReading_1_View, MindChangeTest {

    @InjectPresenter
    lateinit var mPresenter: HmMindReading_1_Presenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_mind_reading_1_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNext.setOnClickListener {
            mActivityPresenter.showHmMindReading_2()
        }

        addEditVerifyListnr(editHomResult)
    }

    private fun addEditVerifyListnr(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                verifyIsReadyToSave()
            }
        })
    }

    private fun verifyIsReadyToSave() {
        when(isReadyToSave()) {
            true -> buttonNext.setBackgroundResource(R.drawable.rectangle_button)
            false -> buttonNext.setBackgroundResource(R.drawable.rectangle_button_disable)
        }
        buttonNext.isEnabled = isReadyToSave()
    }

    private fun isReadyToSave(): Boolean {
        return editHomResult.text.toString().isNotEmpty()
    }
}