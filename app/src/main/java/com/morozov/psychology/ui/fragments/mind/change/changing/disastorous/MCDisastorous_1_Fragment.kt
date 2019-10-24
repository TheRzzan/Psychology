package com.morozov.psychology.ui.fragments.mind.change.changing.disastorous

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.changing.disastorous.MCDisastorous_1_Presenter
import com.morozov.psychology.mvp.views.mind.change.changing.disastorous.MCDisastorous_1_View
import kotlinx.android.synthetic.main.mind_change_disastorous_1_layout.*

class MCDisastorous_1_Fragment: MvpAppCompatFragment(), MCDisastorous_1_View {

    @InjectPresenter
    lateinit var mPresenter: MCDisastorous_1_Presenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_disastorous_1_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNext.setOnClickListener {
            mActivityPresenter.showMCDisastorous_2()
        }

        seekBarDis.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textDisPercent.text = "$progress%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    /*
    * MCDisastorous_1_View implementation
    *
    * */
    override fun showThink(situation: String, newThink: String) {
        editDisSituation.setText(situation)
        editDisThink.setText(newThink)
    }
}