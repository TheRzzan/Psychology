package com.morozov.psychology.ui.fragments.deep.mind.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.arellomobile.mvp.MvpAppCompatFragment
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.ui.activities.MainActivity
import com.morozov.psychology.ui.fragments.deep.mind.fragments.models.ContraRealmModel
import com.morozov.psychology.ui.fragments.deep.mind.fragments.models.ThinkRealmModel
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_deep_edit_contra.*

class DeepEditContraFragment(private val realm: Realm) : MvpAppCompatFragment() {

    lateinit var mActivityPresenter: MainPresenter

    lateinit var mContraModel: ContraRealmModel
    lateinit var mThinkModel: ThinkRealmModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_deep_edit_contra, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearBack.setOnClickListener {
            activity?.onBackPressed()
        }

        editRend.setText(mContraModel.text)

        editRend.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val isReady = p0.isNullOrEmpty().not()
                when(isReady) {
                    true -> buttonSave.setBackgroundResource(R.drawable.rectangle_button)
                    false -> buttonSave.setBackgroundResource(R.drawable.rectangle_button_disable)
                }
                buttonSave.isEnabled = isReady
            }
        })

        seekThink.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textPercent.text = "$p1%"
                progressThink.progress = p1

                val isReady = editRend.text.isNullOrEmpty().not()
                when(isReady) {
                    true -> buttonSave.setBackgroundResource(R.drawable.rectangle_button)
                    false -> buttonSave.setBackgroundResource(R.drawable.rectangle_button_disable)
                }
                buttonSave.isEnabled = isReady
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        seekThink.progress = mContraModel.percent

        buttonSave.setOnClickListener {
            val model = if (mThinkModel.id == -1L) {
                realm.beginTransaction()
                val thinkRealm = realm.createObject(ThinkRealmModel::class.java)
                thinkRealm.id = realm.where(ThinkRealmModel::class.java).count()
                thinkRealm.text = mThinkModel.text
                thinkRealm.percent = mThinkModel.percent
                thinkRealm.timeCreate = System.currentTimeMillis()
                realm.commitTransaction()
                thinkRealm
            } else {
                mThinkModel
            }
            realm.beginTransaction()
            val contra: ContraRealmModel = if (mContraModel.thinkId == -1L) {
                val contraRealm = realm.createObject(ContraRealmModel::class.java)
                contraRealm.thinkId = model.id
                contraRealm
            } else {
                realm.where(ContraRealmModel::class.java).equalTo("thinkId", model.id).findAll()?.first()!!
            }
            contra.text = editRend.text.toString()
            contra.percent = seekThink.progress
            realm.commitTransaction()
            // save to realm
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}