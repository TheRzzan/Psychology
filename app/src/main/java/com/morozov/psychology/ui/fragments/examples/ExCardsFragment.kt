package com.morozov.psychology.ui.fragments.examples

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.examples.ExCardsPresenter
import com.morozov.psychology.mvp.views.examples.ExCardsView
import com.morozov.psychology.ui.adapters.examples.cards.exp.ExCardsAdapter
import com.morozov.psychology.ui.adapters.examples.cards.fix.ExFixCardsAdapter
import com.morozov.psychology.ui.adapters.listeners.OnImageClickListener
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.example_cards_layout.*

class ExCardsFragment: MvpAppCompatFragment(), ExCardsView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: ExCardsPresenter
    lateinit var mActivityPresenter: MainPresenter

    /*
    * Recycler adapters
    *
    * */
    lateinit var adapterExp: ExCardsAdapter
    lateinit var adapterFix: ExFixCardsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.example_cards_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageMainSettings.setOnClickListener {
            mActivityPresenter.showSettingsSection()
        }

        adapterExp = ExCardsAdapter(object : OnImageClickListener {
            override fun onImageClicked(image: ImageView, position: Int) {
                if (position >= 5) {
                    exitTransition = Fade()
                    mActivityPresenter.showExFixDescr(image, position - 5)
                } else {
                    exitTransition = Fade()
                    mActivityPresenter.showExDescr(image, position)
                }
            }
        })
        adapterFix = ExFixCardsAdapter(object : OnImageClickListener {
            override fun onImageClicked(image: ImageView, position: Int) {
                exitTransition = Fade()
                mActivityPresenter.showExFixDescr(image, position)
            }
        })

        if (resources.getBoolean(R.bool.is_tablet))
            recyclerCardsExper.layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(context)
        else
            recyclerCardsExper.layoutManager =
                androidx.recyclerview.widget.GridLayoutManager(context, 2)

        recyclerCardsExper.adapter = adapterExp

        recyclerCardsFixing.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerCardsFixing.adapter = adapterFix
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadDataExperiments()
        mPresenter.loadDataFixing()
    }

    /*
        *ExCardsView implementation
        *
        * */
    override fun showDataExperiments(data: List<String>) {
        adapterExp.setData(data)
    }

    override fun showDataFixing(data: List<String>) {
        adapterFix.setData(data)
    }
}
