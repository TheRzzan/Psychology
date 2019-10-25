package com.morozov.psychology.ui.fragments.mind.change.homework.tunnel

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.homework.tunnel.HmTunnelPresenter
import com.morozov.psychology.mvp.views.mind.change.homework.main.HmMainView
import com.morozov.psychology.mvp.views.mind.change.homework.tunnel.HmTunnelView
import com.morozov.psychology.ui.adapters.mind.change.aback.white.MCBlackWhiteAdapter
import com.morozov.psychology.ui.fragments.mind.change.MindChangeTest
import kotlinx.android.synthetic.main.homework_tunnel_layout.*

class HmTunnelFragment: MvpAppCompatFragment(), HmTunnelView, MindChangeTest {

    @InjectPresenter
    lateinit var mPresenter: HmTunnelPresenter
    lateinit var mActivityPresenter: MainPresenter

    val adapter = MCBlackWhiteAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_tunnel_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddNewThink.setOnClickListener {
            HmMainView.date?.let { it1 -> mActivityPresenter.showDiaryEditor(false, it1, false) }
        }

        buttonChooseAnother.setOnClickListener {
            HmMainView.date?.let { it1 -> mActivityPresenter.showHmMain(it1) }
        }

        adapter.isAllFilled.observeForever {
            when(it) {
                true -> buttonAddNewThink.setBackgroundResource(R.drawable.rectangle_button)
                false -> buttonAddNewThink.setBackgroundResource(R.drawable.rectangle_button_disable)
            }
            buttonAddNewThink.isEnabled = it == true
        }

        recyclerHomework.adapter = adapter
        recyclerHomework.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
        adapter.isAllFilled.value = false
    }

    /*
    * HmTunnelView implementation
    *
    * */
    override fun showRecycler(data: List<String>) {
        adapter.setData(data)
        adapter.notifyDataSetChanged()
    }
}