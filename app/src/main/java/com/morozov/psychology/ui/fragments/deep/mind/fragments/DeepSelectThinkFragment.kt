package com.morozov.psychology.ui.fragments.deep.mind.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.morozov.psychology.ui.fragments.deep.mind.renderers.card.and.text.CardAndTextModel
import com.morozov.psychology.ui.fragments.deep.mind.renderers.card.and.text.CardAndTextViewBinder
import com.morozov.psychology.ui.fragments.deep.mind.renderers.card.and.text.OnCardClickListener
import kotlinx.android.synthetic.main.fragment_deep_select_think.*

class DeepSelectThinkFragment: Fragment() {

    lateinit var mActivityPresenter: MainPresenter

    private lateinit var mAdapter: RendererRecyclerViewAdapter
    private var mItems = mutableListOf<ViewModel>()
    private var mThinks = mutableListOf<ThinkRealmModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_deep_select_think, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = RendererRecyclerViewAdapter()
        mAdapter.registerRenderer(ViewBinder(R.layout.item_rend_text_on_card, CardAndTextModel::class.java, CardAndTextViewBinder(listener)))
        mAdapter.setItems(mItems)
        mAdapter.enableDiffUtil()

        recyclerRend.layoutManager = LinearLayoutManager(context)
        recyclerRend.adapter = mAdapter

        initRecycler()
    }

    private val listener = object : OnCardClickListener {
        override fun onClick(position: Int) {
            mActivityPresenter.showMakeContras(mThinks[position].text)
        }
    }

    private fun initRecycler() {
        MainActivity.realm.beginTransaction()
        mThinks = MainActivity.realm
            .where(ThinkRealmModel::class.java)
            .findAll()
        MainActivity.realm.commitTransaction()
        mItems = mThinks.mapIndexed { index, thinkRealmModel ->
            thinkRealmModel.toCardAndText(index)
        }.toMutableList()
        mAdapter.setItems(mItems)
    }
}