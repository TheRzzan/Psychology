package com.morozov.psychology.ui.fragments.deep.mind.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import kotlinx.android.synthetic.main.fragment_deep_make_contra.*

class DeepMakeContrasFragment: Fragment() {

    lateinit var mActivityPresenter: MainPresenter

    lateinit var mThink: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_deep_make_contra, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearBack.setOnClickListener {
            activity?.onBackPressed()
        }

        textHeader.text = mThink

        seekThink.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textPercent.text = "$p1%"
                progressThink.progress = p1
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        seekThink.progress = 0
    }
}