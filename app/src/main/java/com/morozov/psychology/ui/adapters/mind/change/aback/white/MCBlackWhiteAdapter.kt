package com.morozov.psychology.ui.adapters.mind.change.aback.white

import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter

class MCBlackWhiteAdapter(private val textWatcher: TextWatcher): ListAdapter<String, MCBlackWhiteViewHolder>(){

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): MCBlackWhiteViewHolder =
        MCBlackWhiteViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_example_test,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: MCBlackWhiteViewHolder, position: Int) {
        holder.populate(data()[position], textWatcher)
    }
}