package com.morozov.psychology.ui.adapters.examples.cards.fix

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener

class ExFixCardsAdapter(private val listener: OnItemClickListener) : ListAdapter<String, ExFixCardsViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ExFixCardsViewHolder =
        ExFixCardsViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_example_fixing_card,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: ExFixCardsViewHolder, position: Int) {
        holder.populate(data()[position], position, listener)
    }
}