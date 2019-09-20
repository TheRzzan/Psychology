package com.morozov.psychology.ui.adapters.examples.results

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.examples.ExFixingResultModel
import com.morozov.psychology.ui.adapters.ListAdapter

class ExFixResultAdapter: ListAdapter<ExFixingResultModel, ExFixResultViewHolder>() {
    
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ExFixResultViewHolder =
        ExFixResultViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_example_fixing_result,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: ExFixResultViewHolder, position: Int) {
        holder.populate(data()[position])
    }
}