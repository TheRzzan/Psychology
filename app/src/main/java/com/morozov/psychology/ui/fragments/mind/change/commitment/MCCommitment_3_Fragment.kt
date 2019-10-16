package com.morozov.psychology.ui.fragments.mind.change.commitment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.commitment.MCCommitment_3_Presenter
import com.morozov.psychology.mvp.views.mind.change.commitment.MCCommitment_3_View
import kotlinx.android.synthetic.main.mind_change_commitment_3_layout.*

class MCCommitment_3_Fragment: MvpAppCompatFragment(), MCCommitment_3_View {

    @InjectPresenter
    lateinit var mPresenter: MCCommitment_3_Presenter
    lateinit var mActivityPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.mind_change_commitment_3_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNext.setOnClickListener {
            mActivityPresenter.showMCCommitment_4()
        }
    }
}