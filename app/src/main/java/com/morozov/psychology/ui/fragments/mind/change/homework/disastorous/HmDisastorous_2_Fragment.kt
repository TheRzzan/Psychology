package com.morozov.psychology.ui.fragments.mind.change.homework.disastorous

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.homework.disastorous.HmDisastorous_2_Presenter
import com.morozov.psychology.mvp.views.mind.change.homework.disastorous.HmDisastorous_2_View
import com.morozov.psychology.ui.adapters.listeners.OnTextChangeListener
import com.morozov.psychology.ui.adapters.mind.change.edit.seekbar.EditSeekAdapter
import com.morozov.psychology.utility.DisastorousPreferences
import kotlinx.android.synthetic.main.homework_disastorous_2_layout.*

class HmDisastorous_2_Fragment: MvpAppCompatFragment(), HmDisastorous_2_View {

    @InjectPresenter
    lateinit var mPresenter: HmDisastorous_2_Presenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: EditSeekAdapter

    var allTextRecycler: MutableList<Boolean> = mutableListOf()
    var insertedTextRecycler: MutableList<String> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_disastorous_2_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contextTmp = context

        var dis1: DisastorousPreferences.Dis1? = null
        if (contextTmp != null) {
            dis1 = DisastorousPreferences.getDis1(contextTmp)
        }

        if (dis1 != null) {
            allTextRecycler.add(true)
            allTextRecycler.add(true)
            allTextRecycler.add(true)
        }

        buttonNext.setOnClickListener {
            saveAnswers()
            mActivityPresenter.showHmDisastorous_2()
        }

        adapter = EditSeekAdapter(object : OnTextChangeListener {
            override fun onTextChanged(
                position: Int,
                count: Int,
                symbolSet: String,
                percent: Int?
            ) {
                allTextRecycler[position] = count > 0
                insertedTextRecycler[position] = symbolSet

                var b = true
                for (item in allTextRecycler) {
                    if (!item) {
                        b = false
                        break
                    }
                }

                verifyIsReadyToSave(b)
            }
        }, true, null, dis1)

        recyclerHomework.adapter = adapter
        recyclerHomework.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.showData()
    }

    private fun verifyIsReadyToSave(b: Boolean) {
        when(b) {
            true -> buttonNext.setBackgroundResource(R.drawable.rectangle_button)
            false -> buttonNext.setBackgroundResource(R.drawable.rectangle_button_disable)
        }
        buttonNext.isEnabled = b
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
            insertedTextRecycler.add("")
        }
    }

    private fun saveAnswers() {
        if (insertedTextRecycler.size >= 3) {
            val good = insertedTextRecycler[0]
            val think = insertedTextRecycler[1]
            val forecast = insertedTextRecycler[2]
            context?.let { DisastorousPreferences.saveDis1(it, DisastorousPreferences.Dis1(good, think, forecast)) }
        }
    }
}