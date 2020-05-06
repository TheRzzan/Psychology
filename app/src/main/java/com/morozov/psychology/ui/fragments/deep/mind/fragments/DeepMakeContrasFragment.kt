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
import com.morozov.psychology.ui.activities.MainActivity
import com.morozov.psychology.ui.fragments.deep.mind.fragments.models.ContraRealmModel
import com.morozov.psychology.ui.fragments.deep.mind.fragments.models.ThinkRealmModel
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.percent.OnItemClickListener
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.percent.TextAndPercentModel
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.percent.TextAndPercentViewBinder
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_deep_make_contra.*

class DeepMakeContrasFragment(private val realm: Realm) : Fragment() {

    lateinit var mActivityPresenter: MainPresenter

    lateinit var mThink: String

    var showSelectAnother = true

    private lateinit var mAdapter: RendererRecyclerViewAdapter
    private var mItems = mutableListOf<ViewModel>()
    private var mContras = mutableListOf<ContraRealmModel>()

    private var mThinkRealmModel: ThinkRealmModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_deep_make_contra, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearBack.setOnClickListener {
            activity?.onBackPressed()
        }

        if (showSelectAnother) {
            buttonAnotherThink.setOnClickListener {
                activity?.onBackPressed()
            }
        } else
            buttonAnotherThink.visibility = View.GONE

        buttonMindChangeMain.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
        }

        buttonSelectContr.setOnClickListener {
            mActivityPresenter.showEditContra(mThinkRealmModel!!, ContraRealmModel())
        }

        textHeader.text = mThink

        seekThink.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textPercent.text = "$p1%"
                progressThink.progress = p1
                if (realm.isInTransaction.not())
                    realm.beginTransaction()
                mThinkRealmModel?.percent = p1
                realm.commitTransaction()
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        mAdapter = RendererRecyclerViewAdapter()
        mAdapter.registerRenderer(ViewBinder(R.layout.item_rend_text_and_precent, TextAndPercentModel::class.java, TextAndPercentViewBinder(listener)))
        mAdapter.setItems(mItems)
        mAdapter.enableDiffUtil()

        recyclerContras.layoutManager = LinearLayoutManager(context)
        recyclerContras.adapter = mAdapter

        loadItems()
    }

    private fun loadItems() {
        realm.beginTransaction()
        val resultsThink = realm
            .where(ThinkRealmModel::class.java)
            .equalTo("text", mThink)
            .findAll()
        mThinkRealmModel = if (resultsThink.isNotEmpty())
            resultsThink.first()!!
        else
            ThinkRealmModel(-1, mThink, 0, System.currentTimeMillis())
        if (mThinkRealmModel!!.id != -1L) {
            mContras = realm
                .where(ContraRealmModel::class.java)
                .equalTo("thinkId", mThinkRealmModel!!.id)
                .findAll()
            mItems = mContras.mapIndexed { index, contraRealmModel ->
                contraRealmModel.toTextAndPercentModel(index)
            }.toMutableList()
            mAdapter.setItems(mItems)
        }
        realm.commitTransaction()
        seekThink.progress = mThinkRealmModel!!.percent
    }

    private val listener = object : OnItemClickListener {
        override fun onClick(position: Int) {
            mActivityPresenter.showEditContra(mThinkRealmModel!!, mContras[position])
        }
    }
}