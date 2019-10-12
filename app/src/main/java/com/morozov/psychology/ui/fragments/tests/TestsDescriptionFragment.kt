package com.morozov.psychology.ui.fragments.tests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.R
import com.morozov.psychology.domain.interfaces.tests.AboutLoader
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.tests.TestsDescriptionPresenter
import com.morozov.psychology.mvp.views.tests.TestsDescriptionView
import com.morozov.psychology.utility.AppConstants
import kotlinx.android.synthetic.main.tests_description_layout.*
import javax.inject.Inject

class TestsDescriptionFragment: MvpAppCompatFragment(), TestsDescriptionView {

    @InjectPresenter
    lateinit var mPresenter: TestsDescriptionPresenter
    lateinit var mActivityPresenter: MainPresenter

    @Inject
    lateinit var aboutLoader: AboutLoader

    init {
        DefaultApplication.testsComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tests_description_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments ?: return
        val name = bundle.getString(AppConstants.TEST_NAME) ?: return

        if (name == AppConstants.INTEGRATIVE_TEST && aboutLoader.getAboutModel() == null) {
            buttonTestsStart.isEnabled = false
            buttonTestsStart.background = resources.getDrawable(R.drawable.rectangle_button_disable)
            Toast.makeText(context, "Перед прохождением этого теста пройдите тест \"Немного о вас\"", Toast.LENGTH_LONG).show()
        } else {
            buttonTestsStart.setOnClickListener {
                mActivityPresenter.showTestQuiz(name)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments ?: return

        val name = bundle.getString(AppConstants.TEST_NAME) ?: return

        mPresenter.loadData(name)
    }

    /*
    * TestsDescriptionView implementation
    *
    * */
    override fun showDescription(name: String, description: String) {
        textTestsDescrName.text = name
        textTestsDescription.text = description
    }
}