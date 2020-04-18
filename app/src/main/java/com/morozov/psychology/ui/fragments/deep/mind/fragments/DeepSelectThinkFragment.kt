package com.morozov.psychology.ui.fragments.deep.mind.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
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
import com.morozov.psychology.utility.CustomYesNoDialog
import com.morozov.psychology.utility.ItemTouchHelperClass
import io.realm.Sort
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

        linearBack.setOnClickListener {
            activity?.onBackPressed()
        }

        buttonRendAdd.setOnClickListener {
            mActivityPresenter.showDeepMindTestShort()
        }

        mAdapter = RendererRecyclerViewAdapter()
        mAdapter.registerRenderer(ViewBinder(R.layout.item_rend_text_on_card, CardAndTextModel::class.java, CardAndTextViewBinder(listener)))
        mAdapter.setItems(mItems)
        mAdapter.enableDiffUtil()

        recyclerRend.layoutManager = LinearLayoutManager(context)
        recyclerRend.adapter = mAdapter

        val callback = ItemTouchHelperClass(itemTouchCallback)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerRend)

        initRecycler()
    }

    private val itemTouchCallback = object : ItemTouchHelperClass.ItemTouchHelperAdapter {
        override fun onItemRemoved(position: Int) {
            CustomYesNoDialog.showDialog("Вы точно хотите удалить?",
                "Удалить", "Отмена",
                Runnable {
                    mItems.removeAt(position)
                    mAdapter.setItems(mItems)
                    removeItemFromRealm(position)
                },
                Runnable {
                    mAdapter.notifyDataSetChanged()
                }, childFragmentManager)
        }
    }

    private val listener = object : OnCardClickListener {
        override fun onClick(position: Int) {
            mActivityPresenter.showMakeContras(mThinks[position].text, true)
        }
    }

    private fun initRecycler() {
        MainActivity.realm.beginTransaction()
        mThinks = MainActivity.realm
            .where(ThinkRealmModel::class.java)
            .sort("timeCreate", Sort.DESCENDING)
            .findAll()
        MainActivity.realm.commitTransaction()
        mItems = mThinks.mapIndexed { index, thinkRealmModel ->
            Log.i("Jeka", "$index time create: ${thinkRealmModel.timeCreate}")
            thinkRealmModel.toCardAndText(index)
        }.toMutableList()
        mAdapter.setItems(mItems)
    }

    private fun removeItemFromRealm(position: Int) {
        val item = mThinks[position]
        Log.i("Jeka", "Size before: ${mThinks.size}")
        MainActivity.realm.beginTransaction()
        MainActivity.realm.where(ContraRealmModel::class.java).equalTo("thinkId", item.id).findAll().deleteAllFromRealm()
        MainActivity.realm.where(ThinkRealmModel::class.java).equalTo("id", item.id).findAll().deleteAllFromRealm()
        MainActivity.realm.commitTransaction()
        Log.i("Jeka", "Size after: ${mThinks.size}")
    }
}