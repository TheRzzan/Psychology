package com.morozov.psychology.ui.adapters.tests.all.results

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter

class TstAllResultsAdapter: ListAdapter<Pair<String, List<Pair<String, String>>>, TstAllResultsViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): TstAllResultsViewHolder =
        TstAllResultsViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_tests_all_results,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: TstAllResultsViewHolder, position: Int) {
        holder.populate(data()[position])
    }
}