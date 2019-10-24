package com.morozov.psychology.ui.fragments.mind.change.changing.disastorous

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.disastorous.MCDisastorous_2_Presenter
import com.morozov.psychology.mvp.views.mind.change.changing.disastorous.MCDisastorous_2_View
import kotlinx.android.synthetic.main.mind_change_disastorous_2_layout.*

class MCDisastorous_2_Fragment: MvpAppCompatFragment(), MCDisastorous_2_View {

    @InjectPresenter
    lateinit var mPresenter: MCDisastorous_2_Presenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_disastorous_2_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNext.setOnClickListener {
            mActivityPresenter.showMCDisastorous_3()
        }

        editDisIdeal.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when(count > 0) {
                    true -> buttonNext.setBackgroundResource(R.drawable.rectangle_button)
                    false -> buttonNext.setBackgroundResource(R.drawable.rectangle_button_disable)
                }
                buttonNext.isEnabled = count > 0
            }
        })

        buttonNext.setBackgroundResource(R.drawable.rectangle_button_disable)
        buttonNext.isEnabled = false
    }
}