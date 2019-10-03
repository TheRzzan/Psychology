package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.tests.about.AboutModel
import com.morozov.psychology.mvp.models.tests.about.enums.*
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.tests.TestsAboutPresenter
import com.morozov.psychology.mvp.views.tests.TestsAboutView
import kotlinx.android.synthetic.main.diary_think_editor_layout.*
import kotlinx.android.synthetic.main.tests_about_layout.*

class TestsAboutFragment: MvpAppCompatFragment(), TestsAboutView {

    @InjectPresenter
    lateinit var mPresenter: TestsAboutPresenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var mAboutModel: AboutModel

    /*
    * Spinner data
    *
    * */
    private val maritalStatusArr = listOf("холост", "в браке", "разведен", "вдовец")
    private val educationArr = listOf("начальное", "среднее", "среднее профессиональное", "высшее профессиональное", "другое")
    private val freqOfUseArr = listOf("ежедневно", "1 или несколько раз в неделю", "1 или несколько раз в месяц", "реже")
    private val freqOfTherapyArr = listOf("не обращаюсь", "несколько раз в неделю", "1 раз в неделю", "2-3 раза в месяц", "реже")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_about_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonTestsAboutSave.setOnClickListener {
            mPresenter.saveData(getAboutModel())
            activity?.onBackPressed()
        }

        prepareFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAboutModel = AboutModel(null, null, null,
            null, null, null,
            null, null, null,
            Pair(mutableListOf(), mutableListOf())
        )

        mPresenter.loadData()
    }

    /*
    * TestsAboutView implementation
    *
    * */
    override fun showAbout(about: AboutModel) {
        mAboutModel = about

        when (about.sex) {
            SexEnum.MAN -> buttonTestsAboutManSex.background = resources.getDrawable(R.drawable.rectangle_edit_text_white)
            SexEnum.WOMAN ->buttonTestsAboutWomanSex.background = resources.getDrawable(R.drawable.rectangle_edit_text_white)
        }

        when (about.isVisitTherapy) {
            true -> {
                buttonYes.background = resources.getDrawable(R.drawable.rectangle_edit_text_white)
                editMonthOfTherapy.setText(about.timeOfPsychologistVisit.toString())
            }
            false ->buttonNo.background = resources.getDrawable(R.drawable.rectangle_edit_text_white)
        }

        editAge.setText(about.age.toString())

        editMonthOfUse.setText(about.timeOfUse.toString())

        when (about.maritalStatus) {
            MaritalStatusEnum.DIVORCED -> spinerMaritalStatus.setSelection(2)
            MaritalStatusEnum.MARRIED -> spinerMaritalStatus.setSelection(1)
            MaritalStatusEnum.SINGLE -> spinerMaritalStatus.setSelection(0)
            MaritalStatusEnum.WIDOWER -> spinerMaritalStatus.setSelection(3)
        }

        when (about.education) {
            EducationEnum.HIGHER_VOCATIONAL -> spinnerEducation.setSelection(3)
            EducationEnum.PRIMARY -> spinnerEducation.setSelection(0)
            EducationEnum.SECONDARY -> spinnerEducation.setSelection(1)
            EducationEnum.SECONDARY_VOCATIONAL -> spinnerEducation.setSelection(2)
        }

        when (about.frequencyOfUse) {
            FrequencyOfUseEnum.EVERYDAY -> spinnerFreqOfUs.setSelection(0)
            FrequencyOfUseEnum.EVERYMONTH -> spinnerFreqOfUs.setSelection(2)
            FrequencyOfUseEnum.EVERYWEEK -> spinnerFreqOfUs.setSelection(1)
            FrequencyOfUseEnum.LESS_OFTEN -> spinnerFreqOfUs.setSelection(3)
        }

        when (about.frequencyOfTherapy) {
            FrequencyOfTherapyEnum.DONT_APPEAL -> spinnerFreqOfTherapy.setSelection(0)
            FrequencyOfTherapyEnum.FEW_TIMES_A_MONTH -> spinnerFreqOfTherapy.setSelection(3)
            FrequencyOfTherapyEnum.FEW_TIMES_A_WEEK -> spinnerFreqOfTherapy.setSelection(1)
            FrequencyOfTherapyEnum.LESS_OFTEN -> spinnerFreqOfTherapy.setSelection(4)
            FrequencyOfTherapyEnum.ONE_TIME_A_WEEK -> spinnerFreqOfTherapy.setSelection(2)
        }

        val medicines = about.medicines
        if (medicines != null) {
            if (medicines.first.isNotEmpty()) {
                val tmpMedicines: MutableList<MedicinesEnum> = mutableListOf()

                for (item in medicines.first) {
                    tmpMedicines.add(item)
                }

                for (item in tmpMedicines) {
                    when (item) {
                        MedicinesEnum.NORMOTIMICS -> checkNormo.isChecked = true
                        MedicinesEnum.ANTIPSYCHOTICS -> checkNeiro.isChecked = true
                        MedicinesEnum.TRANQUILIZERS -> checkTrank.isChecked = true
                        MedicinesEnum.ANTIDEPRESSANTS -> checkAnt.isChecked = true
                    }
                }
            }

            if (medicines.second.isNotEmpty()) {
                editClarification.setText(medicines.second[0])
                checkAnother.isChecked = true
            }
        } else
            checkNo.isChecked = true
    }

    /*
    * Helper functions
    *
    * */
    private fun checkIsReadyToSave() {
        var isReady: Boolean = mAboutModel.sex != null && mAboutModel.age != null &&
                mAboutModel.maritalStatus != null && mAboutModel.education != null &&
                mAboutModel.timeOfUse != null && mAboutModel.frequencyOfUse != null &&
                mAboutModel.isVisitTherapy != null

        if (mAboutModel.isVisitTherapy == true)
            if (mAboutModel.timeOfPsychologistVisit == null)
                isReady = false

        if (!checkNo.isChecked) {
            val medicines = mAboutModel.medicines
            if (medicines == null)
                isReady = false
            else{
                if (checkAnother.isChecked) {
                    if (editClarification.text.isEmpty())
                        isReady = false
                } else if (medicines.first.isEmpty())
                    isReady = false
            }
        }

        when(isReady) {
            true -> buttonTestsAboutSave.setBackgroundResource(R.drawable.rectangle_button)
            false -> buttonTestsAboutSave.setBackgroundResource(R.drawable.rectangle_button_disable)
        }
        buttonTestsAboutSave.isEnabled = isReady
    }

    private fun getAboutModel(): AboutModel {
        if (checkNo.isChecked)
            mAboutModel.medicines = null
        else {
            val tmpList = mutableListOf<MedicinesEnum>()
            if (checkNormo.isChecked)
                tmpList.add(MedicinesEnum.NORMOTIMICS)
            if (checkAnt.isChecked)
                tmpList.add(MedicinesEnum.ANTIDEPRESSANTS)
            if (checkTrank.isChecked)
                tmpList.add(MedicinesEnum.TRANQUILIZERS)
            if (checkNeiro.isChecked)
                tmpList.add(MedicinesEnum.ANTIPSYCHOTICS)

            var s = ""
            if (checkAnother.isChecked)
                s = editClarification.text.toString()

            mAboutModel.medicines = Pair(tmpList, mutableListOf(s))
        }

        return mAboutModel
    }

    private fun prepareFragment() {
        initSpinner(maritalStatusArr, spinerMaritalStatus, Runnable {
            when (spinerMaritalStatus.selectedItemId) {
                2L -> mAboutModel.maritalStatus = MaritalStatusEnum.DIVORCED
                1L -> mAboutModel.maritalStatus = MaritalStatusEnum.MARRIED
                0L -> mAboutModel.maritalStatus = MaritalStatusEnum.SINGLE
                3L -> mAboutModel.maritalStatus = MaritalStatusEnum.WIDOWER
            }
        })

        initSpinner(educationArr, spinnerEducation, Runnable {
            when (spinnerEducation.selectedItemId) {
                3L -> mAboutModel.education = EducationEnum.HIGHER_VOCATIONAL
                0L -> mAboutModel.education = EducationEnum.PRIMARY
                1L -> mAboutModel.education = EducationEnum.SECONDARY
                2L -> mAboutModel.education = EducationEnum.SECONDARY_VOCATIONAL
            }
        })

        initSpinner(freqOfUseArr, spinnerFreqOfUs, Runnable {
            when (spinnerFreqOfUs.selectedItemId) {
                0L -> mAboutModel.frequencyOfUse = FrequencyOfUseEnum.EVERYDAY
                2L -> mAboutModel.frequencyOfUse = FrequencyOfUseEnum.EVERYMONTH
                1L -> mAboutModel.frequencyOfUse = FrequencyOfUseEnum.EVERYWEEK
                3L -> mAboutModel.frequencyOfUse = FrequencyOfUseEnum.LESS_OFTEN
            }
        })

        initSpinner(freqOfTherapyArr, spinnerFreqOfTherapy, Runnable {
            when (spinnerFreqOfTherapy.selectedItemId) {
                0L -> mAboutModel.frequencyOfTherapy = FrequencyOfTherapyEnum.DONT_APPEAL
                3L -> mAboutModel.frequencyOfTherapy = FrequencyOfTherapyEnum.FEW_TIMES_A_MONTH
                1L -> mAboutModel.frequencyOfTherapy = FrequencyOfTherapyEnum.FEW_TIMES_A_WEEK
                4L -> mAboutModel.frequencyOfTherapy = FrequencyOfTherapyEnum.LESS_OFTEN
                2L -> mAboutModel.frequencyOfTherapy = FrequencyOfTherapyEnum.ONE_TIME_A_WEEK
            }

            if (mAboutModel.frequencyOfTherapy == FrequencyOfTherapyEnum.DONT_APPEAL) {
                buttonNo.callOnClick()
            } else {
                buttonYes.callOnClick()
            }
        })

        initOnClicks()

        initOnTextChange()

        initOnCheckSelect()
    }

    private fun initSpinner(data: List<String>, spinner: Spinner, runnable: Runnable) {
        val adapter1 = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter1.clear()
        adapter1.addAll(data)

        spinner.adapter = adapter1
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                checkIsReadyToSave()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                runnable.run()
                checkIsReadyToSave()
            }
        }
    }

    private fun initOnClicks() {
        buttonTestsAboutManSex.setOnClickListener {
            mAboutModel.sex = SexEnum.MAN

            buttonTestsAboutManSex.background = resources.getDrawable(R.drawable.rectangle_edit_text_white)
            buttonTestsAboutWomanSex.background = resources.getDrawable(R.drawable.rectangle_button_white)

            checkIsReadyToSave()
        }

        buttonTestsAboutWomanSex.setOnClickListener {
            mAboutModel.sex = SexEnum.WOMAN

            buttonTestsAboutManSex.background = resources.getDrawable(R.drawable.rectangle_button_white)
            buttonTestsAboutWomanSex.background = resources.getDrawable(R.drawable.rectangle_edit_text_white)

            checkIsReadyToSave()
        }

        buttonYes.setOnClickListener {
            mAboutModel.isVisitTherapy = true

            buttonYes.background = resources.getDrawable(R.drawable.rectangle_edit_text_white)
            buttonNo.background = resources.getDrawable(R.drawable.rectangle_button_white)

            if (spinnerFreqOfTherapy.selectedItemId == 0L)
                spinnerFreqOfTherapy.setSelection(4)

            textTherapyMonths.visibility = View.VISIBLE
            linearTherapyMonths.visibility = View.VISIBLE

            checkIsReadyToSave()
        }

        buttonNo.setOnClickListener {
            mAboutModel.isVisitTherapy = false

            buttonYes.background = resources.getDrawable(R.drawable.rectangle_button_white)
            buttonNo.background = resources.getDrawable(R.drawable.rectangle_edit_text_white)

            spinnerFreqOfTherapy.setSelection(0)

            textTherapyMonths.visibility = View.GONE
            linearTherapyMonths.visibility = View.GONE
            editMonthOfTherapy.text.clear()

            checkIsReadyToSave()
        }
    }

    private fun initOnTextChange() {
        editAge.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if (s.isNotEmpty())
                        mAboutModel.age = s.toString().toInt()
                    else
                        mAboutModel.age = null
                } else
                    mAboutModel.age = null

                checkIsReadyToSave()
            }
        })

        editMonthOfUse.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if (s.isNotEmpty())
                        mAboutModel.timeOfUse = s.toString().toInt()
                    else
                        mAboutModel.timeOfUse = null
                }else
                    mAboutModel.timeOfUse = null

                checkIsReadyToSave()
            }
        })

        editMonthOfTherapy.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if (s.isNotEmpty())
                        mAboutModel.timeOfPsychologistVisit = s.toString().toInt()
                    else
                        mAboutModel.timeOfPsychologistVisit = null
                }else
                    mAboutModel.timeOfPsychologistVisit = null

                checkIsReadyToSave()
            }
        })

        editClarification.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkIsReadyToSave()
            }
        })
    }

    private fun initOnCheckSelect() {
        checkNo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkAnt.isChecked = false
                checkTrank.isChecked = false
                checkNeiro.isChecked = false
                checkNormo.isChecked = false
                checkAnother.isChecked = false

                mAboutModel.medicines = null
            } else
                mAboutModel.medicines = Pair(mutableListOf(), mutableListOf(""))

            checkIsReadyToSave()
        }

        checkAnt.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkNo.isChecked = false
                mAboutModel.medicines?.first?.remove(MedicinesEnum.ANTIDEPRESSANTS)
                mAboutModel.medicines?.first?.add(MedicinesEnum.ANTIDEPRESSANTS)
            } else
                mAboutModel.medicines?.first?.remove(MedicinesEnum.ANTIDEPRESSANTS)

            checkIsReadyToSave()
        }

        checkTrank.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkNo.isChecked = false
                mAboutModel.medicines?.first?.remove(MedicinesEnum.TRANQUILIZERS)
                mAboutModel.medicines?.first?.add(MedicinesEnum.TRANQUILIZERS)
            } else
                mAboutModel.medicines?.first?.remove(MedicinesEnum.TRANQUILIZERS)

            checkIsReadyToSave()
        }

        checkNeiro.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkNo.isChecked = false
                mAboutModel.medicines?.first?.remove(MedicinesEnum.ANTIPSYCHOTICS)
                mAboutModel.medicines?.first?.add(MedicinesEnum.ANTIPSYCHOTICS)
            }else
                mAboutModel.medicines?.first?.remove(MedicinesEnum.ANTIPSYCHOTICS)

            checkIsReadyToSave()
        }

        checkNormo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkNo.isChecked = false
                mAboutModel.medicines?.first?.remove(MedicinesEnum.NORMOTIMICS)
                mAboutModel.medicines?.first?.add(MedicinesEnum.NORMOTIMICS)
            } else
                mAboutModel.medicines?.first?.remove(MedicinesEnum.NORMOTIMICS)

            checkIsReadyToSave()
        }

        checkAnother.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkNo.isChecked = false
                editClarification.visibility = View.VISIBLE
            } else {
                editClarification.text.clear()
                editClarification.visibility = View.GONE
            }

            checkIsReadyToSave()
        }
    }
}