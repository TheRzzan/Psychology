package com.morozov.psychology.ui.adapters.examples.test

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnTextChangeListener


class ExTestAdapter(private val listener: OnTextChangeListener): ListAdapter<String, ExTestViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ExTestViewHolder =
        ExTestViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_example_test,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: ExTestViewHolder, position: Int) {
        holder.populate(data().get(position), position, listener)
    }
}