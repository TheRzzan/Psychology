package com.morozov.psychology.ui.fragments.mind.change.homework.personalization

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
import com.morozov.psychology.mvp.presenters.mind.change.homework.personalization.HmPersonalizationPresenter
import com.morozov.psychology.mvp.views.mind.change.homework.personalization.HmPersonalizationView
import kotlinx.android.synthetic.main.homework_personalization_layout.*

class HmPersonalizationFragment: MvpAppCompatFragment(), HmPersonalizationView {

    @InjectPresenter
    lateinit var mPresenter: HmPersonalizationPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_personalization_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddNewThink.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
        }

        buttonChooseAnother.setOnClickListener {
            mActivityPresenter.showHmMain()
        }

        addEditVerifyListnr(editHomAlter)
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
        return editHomAlter.text.toString().isNotEmpty()
    }
}