package com.morozov.psychology.ui.adapters.examples

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter

class ExAdapter: ListAdapter<String, ExViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ExViewHolder =
        ExViewHolder(LayoutInflater.from(container.context).inflate(R.layout.item_example_card, container, false))

    override fun onBindViewHolder(holder: ExViewHolder, position: Int) {
        holder.populate(data()[position])
    }
}