package com.morozov.psychology.ui.adapters.mind.change.aback.white

import android.arch.lifecycle.MutableLiveData
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter

class MCBlackWhiteAdapter: ListAdapter<String, MCBlackWhiteViewHolder>(){

    val isAllFilled = MutableLiveData<Boolean>()
    var filledCount: Int = 0

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): MCBlackWhiteViewHolder =
        MCBlackWhiteViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_example_test,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: MCBlackWhiteViewHolder, position: Int) {
        holder.populate(data()[position], this)
    }
}