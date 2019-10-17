package com.morozov.psychology.ui.fragments.mind.change.tunnel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.tunnel.MCTunnelPresenter
import com.morozov.psychology.mvp.views.mind.change.tunnel.MCTunnelView
import kotlinx.android.synthetic.main.mind_change_tunnel_layout.*

class MCTunnelFragment: MvpAppCompatFragment(), MCTunnelView {

    @InjectPresenter
    lateinit var mPresenter: MCTunnelPresenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_tunnel_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddNewThink.setOnClickListener {
            mActivityPresenter.showMindChangeSection()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    /*
    * MCTunnelView implementation
    *
    * */
    override fun showThink(situation: String, newThink: String) {
        editTunnelSituation.setText(situation)
        editTunnelThink.setText(newThink)
    }
}