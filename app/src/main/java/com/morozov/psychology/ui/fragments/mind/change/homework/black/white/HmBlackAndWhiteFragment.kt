package com.morozov.psychology.ui.fragments.mind.change.homework.black.white

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
import com.morozov.psychology.mvp.presenters.mind.change.homework.black.white.HmBlackAndWhitePresenter
import com.morozov.psychology.mvp.views.mind.change.MindChangeThinkTestView
import com.morozov.psychology.mvp.views.mind.change.homework.black.white.HmBlackAndWhiteView
import com.morozov.psychology.mvp.views.mind.change.homework.main.HmMainView
import kotlinx.android.synthetic.main.homework_black_and_white_layout.*

class HmBlackAndWhiteFragment: MvpAppCompatFragment(), HmBlackAndWhiteView {

    @InjectPresenter
    lateinit var mPresenter: HmBlackAndWhitePresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_black_and_white_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddNewThink.setOnClickListener {
            HmMainView.date?.let { it1 -> mActivityPresenter.showDiaryEditor(false, it1, false) }
        }

        buttonChooseAnother.setOnClickListener {
            HmMainView.date?.let { it1 -> mActivityPresenter.showHmMain(it1) }
        }

        addEditVerifyListnr(editHomThink)
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
            true -> buttonAddNewThink.setBackgroundResource(R.drawable.rectangle_button)
            false -> buttonAddNewThink.setBackgroundResource(R.drawable.rectangle_button_disable)
        }
        buttonAddNewThink.isEnabled = isReadyToSave()
    }

    private fun isReadyToSave(): Boolean {
        return editHomThink.text.toString().isNotEmpty()
    }
}