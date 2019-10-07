package com.morozov.psychology.ui.adapters.settings.styles

import android.arch.lifecycle.MutableLiveData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener

class StgStyleAdapter: ListAdapter<Pair<String, Int>, StgStyleViewHolder>(), OnItemClickListener {

    var selectedPosition : MutableLiveData<Int> = MutableLiveData()

    init {
        selectedPosition.value = 0
    }

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): StgStyleViewHolder =
        StgStyleViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_settings_style,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: StgStyleViewHolder, position: Int) {
        holder.populate(data()[position], position, position == selectedPosition.value, this)
    }

    /*
    * OnItemClickListener implementation
    *
    * */
    override fun onItemClick(view: View, position: Int) {
        selectedPosition.value = position
        notifyDataSetChanged()
    }
}