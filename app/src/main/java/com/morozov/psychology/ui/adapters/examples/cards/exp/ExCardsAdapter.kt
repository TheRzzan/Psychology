package com.morozov.psychology.ui.adapters.examples.cards.exp

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnImageClickListener
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener

class ExCardsAdapter(private val listener: OnImageClickListener) : ListAdapter<String, ExCardsViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ExCardsViewHolder =
        ExCardsViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_example_card,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: ExCardsViewHolder, position: Int) {
        holder.populate(data()[position], position, listener)
    }
}