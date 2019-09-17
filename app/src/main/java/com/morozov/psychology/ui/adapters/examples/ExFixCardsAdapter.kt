package com.morozov.psychology.ui.adapters.examples

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter

class ExFixCardsAdapter(private val listener: View.OnClickListener) : ListAdapter<String, ExFixCardsViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ExFixCardsViewHolder =
        ExFixCardsViewHolder(LayoutInflater.from(container.context).inflate(R.layout.item_example_fixing_card, container, false))

    override fun onBindViewHolder(holder: ExFixCardsViewHolder, position: Int) {
        holder.populate(data()[position], listener)
    }
}