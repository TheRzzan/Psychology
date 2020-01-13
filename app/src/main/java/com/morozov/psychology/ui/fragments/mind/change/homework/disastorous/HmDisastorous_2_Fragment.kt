package com.morozov.psychology.ui.fragments.mind.change.homework.disastorous

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.homework.disastorous.HmDisastorous_2_Presenter
import com.morozov.psychology.mvp.views.mind.change.homework.disastorous.HmDisastorous_2_View
import com.morozov.psychology.mvp.views.mind.change.homework.main.HmMainView
import com.morozov.psychology.ui.adapters.listeners.OnTextChangeListener
import com.morozov.psychology.ui.adapters.mind.change.edit.seekbar.EditSeekAdapter
import kotlinx.android.synthetic.main.homework_disastorous_2_layout.*

class HmDisastorous_2_Fragment: MvpAppCompatFragment(), HmDisastorous_2_View {

    @InjectPresenter
    lateinit var mPresenter: HmDisastorous_2_Presenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: EditSeekAdapter

    var allTextRecycler: MutableList<Boolean> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_disastorous_2_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddNewThink.setOnClickListener {
            HmMainView.date?.let { it1 -> mActivityPresenter.showDiaryEditor(false, it1, false) }
        }

        buttonChooseAnother.setOnClickListener {
            HmMainView.date?.let { it1 -> mActivityPresenter.showHmMain(it1) }
        }

        adapter = EditSeekAdapter(object : OnTextChangeListener {
            override fun onTextChanged(
                position: Int,
                count: Int,
                symbolSet: String,
                percent: Int?
            ) {
                allTextRecycler[position] = count > 0

                var b = true
                for (item in allTextRecycler) {
                    if (!item) {
                        b = false
                        break
                    }
                }

                verifyIsReadyToSave(b)
            }
        }, true)

        recyclerHomework.adapter = adapter
        recyclerHomework.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.showData()
    }

    private fun verifyIsReadyToSave(b: Boolean) {
        when(b) {
            true -> buttonAddNewThink.setBackgroundResource(R.drawable.rectangle_button)
            false -> buttonAddNewThink.setBackgroundResource(R.drawable.rectangle_button_disable)
        }
        buttonAddNewThink.isEnabled = b
    }

    /*
    * HmDeprecationView implementation
    *
    * */
    override fun showRecycler(data: List<Pair<String, String>>) {
        adapter.setData(data)

        allTextRecycler = mutableListOf()

        var i = 0
        while (i < adapter.itemCount) {
            i++
            allTextRecycler.add(false)
        }
    }
}