package com.morozov.psychology.ui.fragments.settings

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.settings.SettingsConsultPresenter
import com.morozov.psychology.mvp.views.settings.SettingsConsultView
import com.morozov.psychology.ui.fragments.settings.email.EmailSender
import kotlinx.android.synthetic.main.settings_consult_layout.*

class SettingsConsultFragment: MvpAppCompatFragment(), SettingsConsultView {

    @InjectPresenter
    lateinit var mPresenter: SettingsConsultPresenter
    lateinit var mActivityPresenter: MainPresenter

    private val consultTypeStrs = listOf("Очно в Санкт-Петербурге", "Скайп")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.settings_consult_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSpinner(consultTypeStrs, spinerConsultFormat)
        initEditText(editName)
        initEditText(editEmailVk)
        initEditText(editQuestion)
        initEditText(editPreferedTime)

        imageMainSettings.setOnClickListener {
            mActivityPresenter.showSettingsSection()
        }

        buttonSendRequest.setOnClickListener {
            var textEmail = "Как к вам обращаться: ${editName.text}. "
            textEmail += "Как связаться: ${editEmailVk.text}. "
            textEmail += "Вопрос: ${editQuestion.text}. "
            textEmail += "Удобное время для консультации: ${editPreferedTime.text}. "
            textEmail += "Формат консультации: ${spinerConsultFormat.selectedItem}."
            EmailSender.text = textEmail
            activity?.let { it1 -> EmailSender.send(it1) }
//            mActivityPresenter.showSettingsConsult()
        }
    }

    private fun initSpinner(data: List<String>, spinner: Spinner) {
        val adapter1 = ArrayAdapter<String>(activity, R.layout.custom_spinner_item)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter1.clear()
        adapter1.addAll(data)

        spinner.adapter = adapter1
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                checkIsReadyToSave()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                checkIsReadyToSave()
            }
        }
    }

    private fun initEditText(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkIsReadyToSave()
            }
        })
    }

    private fun checkIsReadyToSave() {
        val isReady = editName.text.toString().isNotEmpty() && editEmailVk.text.toString().isNotEmpty() &&
                editQuestion.text.toString().isNotEmpty() && editPreferedTime.text.toString().isNotEmpty()

        when(isReady) {
            true -> buttonSendRequest.setBackgroundResource(R.drawable.rectangle_button)
            false -> buttonSendRequest.setBackgroundResource(R.drawable.rectangle_button_disable)
        }
        buttonSendRequest.isEnabled = isReady
    }
}