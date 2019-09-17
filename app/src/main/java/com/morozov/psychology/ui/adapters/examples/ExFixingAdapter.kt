package com.morozov.psychology.ui.adapters.examples

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.mvp.models.ExFixingResultModel
import com.morozov.psychology.ui.adapters.ListAdapter

class ExFixingAdapter: ListAdapter<ExFixingResultModel, ExFixingViewHolder>() {
    
    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ExFixingViewHolder =
        ExFixingViewHolder(LayoutInflater.from(container.context).inflate(R.layout.item_example_fixing_result, container, false))

    override fun onBindViewHolder(holder: ExFixingViewHolder, position: Int) {
        holder.populate(data()[position])
    }
}