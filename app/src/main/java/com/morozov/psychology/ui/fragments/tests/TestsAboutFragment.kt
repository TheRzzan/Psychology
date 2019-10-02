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

        mAboutModel = AboutModel(SexEnum.MAN, 0, MaritalStatusEnum.WIDOWER,
            EducationEnum.SECONDARY_VOCATIONAL, 1, FrequencyOfUseEnum.LESS_OFTEN,
            true, 1, FrequencyOfTherapyEnum.ONE_TIME_A_WEEK,
            null)

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
            true -> buttonYes.background = resources.getDrawable(R.drawable.rectangle_edit_text_white)
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
    }

    /*
    * Helper functions
    *
    * */
    private fun checkIsReadyToSave() {

    }

    private fun getAboutModel(): AboutModel {
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
        })

        initOnClicks()

        initOnTextChange()
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

            checkIsReadyToSave()
        }

        buttonNo.setOnClickListener {
            mAboutModel.isVisitTherapy = false

            buttonYes.background = resources.getDrawable(R.drawable.rectangle_button_white)
            buttonNo.background = resources.getDrawable(R.drawable.rectangle_edit_text_white)

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
                }
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
                }
                checkIsReadyToSave()
            }
        })
    }
}