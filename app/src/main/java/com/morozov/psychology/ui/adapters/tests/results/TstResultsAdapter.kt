package com.morozov.psychology.ui.adapters.tests.results

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter

class TstResultsAdapter: ListAdapter<Pair<String, String>, TstResultsViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): TstResultsViewHolder =
        TstResultsViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_tests_quiz_result,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: TstResultsViewHolder, position: Int) {
        holder.populate(data()[position].first, data()[position].second)
    }
}