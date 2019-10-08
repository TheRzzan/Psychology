package com.morozov.psychology.ui.fragments.settings

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.settings.SettingsConsultPresenter
import com.morozov.psychology.mvp.views.settings.SettingsConsultView
import kotlinx.android.synthetic.main.settings_consult_layout.*
import java.text.SimpleDateFormat
import java.util.*

class SettingsConsultFragment: MvpAppCompatFragment(), SettingsConsultView {

    @InjectPresenter
    lateinit var mPresenter: SettingsConsultPresenter
    lateinit var mActivityPresenter: MainPresenter

    private val consultTypeStrs = listOf("Очно в Санкт-Петербурге", "Скайп")

    /*
    * Calendar
    *
    * */
    var calendar = Calendar.getInstance()
    val calendarListener = DatePickerDialog.OnDateSetListener{ datePicker: DatePicker, year: Int, month: Int, day: Int ->
        textDayMonthYear.text = "$day.${month + 1}.$year"
        checkIsReadyToSave()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.settings_consult_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSpinner(consultTypeStrs, spinerConsultFormat)
        initEditText(editName)
        initEditText(editEmailVk)
        initEditText(editQuestion)
        relativeDayMonthYear.setOnClickListener {
            showCalendar()
        }

        buttonSendRequest.setOnClickListener {
            activity?.onBackPressed()
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
                editQuestion.text.toString().isNotEmpty() && spinerConsultFormat.isSelected &&
                textDayMonthYear.text != "ДД.ММ.ГГГГ"

        when(isReady) {
            true -> buttonSendRequest.setBackgroundResource(R.drawable.rectangle_button)
            false -> buttonSendRequest.setBackgroundResource(R.drawable.rectangle_button_disable)
        }
        buttonSendRequest.isEnabled = isReady
    }

    fun showCalendar() {
        val dialog = DatePickerDialog(
            context, calendarListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        val dayMtYrFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateCal = dayMtYrFormat
            .parse("${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}")

        dialog.datePicker.minDate = dateCal.time
        dialog.show()
    }
}