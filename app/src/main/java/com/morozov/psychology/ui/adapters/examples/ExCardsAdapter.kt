package com.morozov.psychology.ui.adapters.examples

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter

class ExCardsAdapter(private val listener: View.OnClickListener) : ListAdapter<String, ExCardsViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ExCardsViewHolder =
        ExCardsViewHolder(LayoutInflater.from(container.context).inflate(R.layout.item_example_card, container, false))

    override fun onBindViewHolder(holder: ExCardsViewHolder, position: Int) {
        holder.populate(data()[position], listener)
    }
}