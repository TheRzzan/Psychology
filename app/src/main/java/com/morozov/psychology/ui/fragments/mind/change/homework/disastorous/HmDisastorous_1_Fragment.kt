package com.morozov.psychology.ui.fragments.mind.change.homework.disastorous

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.mind.change.homework.disastorous.HmDisastorous_1_Presenter
import com.morozov.psychology.mvp.views.mind.change.homework.disastorous.HmDisastorous_1_View
import com.morozov.psychology.mvp.views.mind.change.homework.main.HmMainView
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
    var tmpWorstInt = 1
    var tmpBestInt = 1

    lateinit var buttonForVisible: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.homework_disastorous_1_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bestDis: Pair<String, Int>?
        val worstDis: Pair<String, Int>?
        var tmpDis: Pair<Pair<String, Int>, Pair<String, Int>>? = null

        val contTmp = context
        if (contTmp != null) {
            bestDis = DisastorousPreferences.getBestDis(contTmp)
            worstDis = DisastorousPreferences.getWorstDis(contTmp)

            if (bestDis != null && worstDis != null) {
                allTextRecycler.add(true)
                allTextRecycler.add(true)
                allTextRecycler.add(false)
                tmpDis = Pair(bestDis, worstDis)
                buttonAddNewThink.visibility = View.VISIBLE
                buttonChooseAnother.visibility = View.VISIBLE
                buttonAddNewThink.setOnClickListener {
                    DisastorousPreferences.saveBestDis(contTmp)
                    DisastorousPreferences.saveWorstDis(contTmp)
                    HmMainView.date?.let { it1 -> mActivityPresenter.showDiaryEditor(false, it1, false) }
                }

                buttonChooseAnother.setOnClickListener {
                    HmMainView.date?.let { it1 -> mActivityPresenter.showHmMain(it1) }
                }
                buttonForVisible = buttonAddNewThink
            } else {
                allTextRecycler.add(false)
                allTextRecycler.add(false)
                allTextRecycler.add(true)
                buttonAddNewThink.visibility = View.GONE
                buttonChooseAnother.visibility = View.VISIBLE
                buttonChooseAnother.text = "Закончить"
                buttonChooseAnother.setOnClickListener {
                    DisastorousPreferences.saveBestDis(contTmp, tmpBestStr, tmpBestInt)
                    DisastorousPreferences.saveWorstDis(contTmp, tmpWorstStr, tmpWorstInt)
                    mActivityPresenter.showMindChangeSection()
                }
                buttonForVisible = buttonChooseAnother
            }
        }

        adapter = EditSeekAdapter(object : OnTextChangeListener {
            override fun onTextChanged(position: Int, count: Int, symbolSet: String, percent: Int?) {
                when (position) {
                    0 -> {
                        tmpWorstStr = symbolSet
                        if (percent != null)
                            tmpWorstInt = percent
                    }
                    1 -> {
                        tmpBestStr = symbolSet
                        if (percent != null)
                            tmpBestInt = percent
                    }
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
            true -> buttonForVisible.setBackgroundResource(R.drawable.rectangle_button)
            false -> buttonForVisible.setBackgroundResource(R.drawable.rectangle_button_disable)
        }
        buttonForVisible.isEnabled = b
    }

    /*
    * HmDisastorous_1_View implementation
    *
    * */
    override fun showRecycler(data: List<Pair<String, String>>) {
        adapter.setData(data)
    }
}