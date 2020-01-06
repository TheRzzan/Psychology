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
import com.morozov.psychology.mvp.presenters.mind.change.homework.disastorous.HmDisastorous_1_Presenter
import com.morozov.psychology.mvp.views.mind.change.homework.disastorous.HmDisastorous_1_View
import com.morozov.psychology.ui.adapters.listeners.OnTextChangeListener
import com.morozov.psychology.ui.adapters.mind.change.edit.seekbar.EditSeekAdapter
import com.morozov.psychology.ui.fragments.mind.change.MindChangeTest
import com.morozov.psychology.utility.DisastorousPreferences
import kotlinx.android.synthetic.main.homework_disastorous_1_layout.*

class HmDisastorous_1_Fragment: MvpAppCompatFragment(), HmDisastorous_1_View, MindChangeTest {

    @InjectPresenter
    lateinit var mPresenter: HmDisastorous_1_Presenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: EditSeekAdapter

    var allTextRecycler: MutableList<Boolean> = mutableListOf()

    var tmpWorstStr = ""
    var tmpBestStr = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_disastorous_1_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bestDis: Pair<String, Int>?
        val worstDis: Pair<String, Int>?
        var tmpDis: Pair<String, String>? = null

        val contTmp = context
        if (contTmp != null) {
            bestDis = DisastorousPreferences.getBestDis(contTmp)
            worstDis = DisastorousPreferences.getWorstDis(contTmp)

            if (bestDis != null && worstDis != null) {
                allTextRecycler.add(true)
                allTextRecycler.add(true)
                allTextRecycler.add(false)
                tmpDis = Pair(bestDis.first, worstDis.first)
                buttonNext.setOnClickListener {
                    DisastorousPreferences.saveBestDis(contTmp)
                    DisastorousPreferences.saveWorstDis(contTmp)
                    mActivityPresenter.showHmDisastorous_2()
                }
            } else {
                allTextRecycler.add(false)
                allTextRecycler.add(false)
                allTextRecycler.add(true)
                buttonNext.text = "Сохранить"
                buttonNext.setOnClickListener {
                    DisastorousPreferences.saveBestDis(contTmp, tmpBestStr)
                    DisastorousPreferences.saveWorstDis(contTmp, tmpWorstStr)
                    mActivityPresenter.showMindChangeSection()
                }
            }
        }

        adapter = EditSeekAdapter(object : OnTextChangeListener {
            override fun onTextChanged(position: Int, count: Int, symbolSet: String) {
                when (position) {
                    0 -> tmpWorstStr = symbolSet
                    1 -> tmpBestStr = symbolSet
                }

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
        }, false, tmpDis)

        recyclerHomework.adapter = adapter
        recyclerHomework.layoutManager = LinearLayoutManager(context)
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
    * HmDisastorous_1_View implementation
    *
    * */
    override fun showRecycler(data: List<Pair<String, String>>) {
        adapter.setData(data)
    }
}