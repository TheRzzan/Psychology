package com.morozov.psychology.ui.adapters.tests.all.results

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.morozov.psychology.ui.adapters.tests.results.TstResultsAdapter
import kotlinx.android.synthetic.main.item_tests_all_results.view.*

class TstAllResultsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(data: Pair<String, List<Pair<String, String>>>) {
        itemView.textTestResultDate.text = data.first
        var adapter = TstResultsAdapter()
        itemView.recyclerTestsResults.layoutManager = LinearLayoutManager(itemView.context)
        itemView.recyclerTestsResults.adapter = adapter
        adapter.setData(data.second)

        var b = true
        itemView.diaryDateCard.setOnClickListener {
            when (b) {
                true -> {
                    adapter = TstResultsAdapter()
                    itemView.recyclerTestsResults.layoutManager = LinearLayoutManager(itemView.context)
                    itemView.recyclerTestsResults.adapter = adapter
                    adapter.setData(data.second)

                    itemView.viewSeparator.visibility = View.VISIBLE
                    itemView.recyclerTestsResults.visibility = View.VISIBLE
                }
                false -> {
                    itemView.viewSeparator.visibility = View.GONE
                    itemView.recyclerTestsResults.visibility = View.GONE
                }
            }

            b = !b
        }
    }
}