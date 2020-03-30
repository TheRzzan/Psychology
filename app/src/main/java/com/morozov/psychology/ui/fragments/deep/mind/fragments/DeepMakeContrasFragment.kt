package com.morozov.psychology.ui.fragments.deep.mind.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.ui.fragments.deep.mind.fragments.models.ContraRealmModel
import com.morozov.psychology.ui.fragments.deep.mind.fragments.models.ThinkRealmModel
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.percent.OnItemClickListener
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.percent.TextAndPercentModel
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.percent.TextAndPercentViewBinder
import kotlinx.android.synthetic.main.fragment_deep_make_contra.*

class DeepMakeContrasFragment: Fragment() {

    lateinit var mActivityPresenter: MainPresenter

    lateinit var mThink: String

    private lateinit var mAdapter: RendererRecyclerViewAdapter
    private val mItems = mutableListOf<ViewModel>()

    private lateinit var mThinkRealmModel: ThinkRealmModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_deep_make_contra, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearBack.setOnClickListener {
            activity?.onBackPressed()
        }

        buttonAnotherThink.setOnClickListener {
            activity?.onBackPressed()
        }

        buttonMindChangeMain.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
        }

        buttonSelectContr.setOnClickListener {
            mActivityPresenter.showEditContra(mThinkRealmModel, ContraRealmModel())
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

        mAdapter = RendererRecyclerViewAdapter()
        mAdapter.registerRenderer(ViewBinder(R.layout.item_rend_text_and_precent, TextAndPercentModel::class.java, TextAndPercentViewBinder(listener)))
        mAdapter.setItems(mItems)
        mAdapter.enableDiffUtil()

        recyclerContras.layoutManager = LinearLayoutManager(context)
        recyclerContras.adapter = mAdapter

        loadItems()
    }

    private fun loadItems() {
        // Load from cache
        mThinkRealmModel = ThinkRealmModel(
            -1,
            mThink
        )
    }

    private val listener = object : OnItemClickListener {
        override fun onClick(position: Int) {

        }
    }
}