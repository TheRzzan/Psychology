package com.morozov.psychology.ui.fragments.mind.change.homework.commitment

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
import com.morozov.psychology.mvp.presenters.mind.change.homework.commitment.HmCommitment_3_Presenter
import com.morozov.psychology.mvp.views.mind.change.homework.commitment.HmCommitment_3_View
import com.morozov.psychology.mvp.views.mind.change.homework.main.HmMainView
import kotlinx.android.synthetic.main.homework_commitment_3_layout.*

class HmCommitment_3_Fragment: MvpAppCompatFragment(), HmCommitment_3_View {

    @InjectPresenter
    lateinit var mPresenter: HmCommitment_3_Presenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_commitment_3_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddNewThink.setOnClickListener {
            HmMainView.date?.let { it1 -> mActivityPresenter.showDiaryEditor(false, it1, false) }
        }

        buttonChooseAnother.setOnClickListener {
            HmMainView.date?.let { it1 -> mActivityPresenter.showHmMain(it1) }
        }

        addEditVerifyListnr(editHomRequire)
        addEditVerifyListnr(editHomExcept)
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
        return editHomRequire.text.toString().isNotEmpty() &&
                editHomExcept.text.toString().isNotEmpty()
    }
}