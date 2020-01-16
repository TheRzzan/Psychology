package com.morozov.psychology.ui.adapters.settings.styles

import androidx.lifecycle.MutableLiveData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morozov.psychology.R
import com.morozov.psychology.ui.adapters.ListAdapter
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener

class StgStyleAdapter: ListAdapter<Pair<String, Int>, StgStyleViewHolder>(), OnItemClickListener {

    var selectedPosition : MutableLiveData<Int> = MutableLiveData()

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): StgStyleViewHolder =
        StgStyleViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_settings_style,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: StgStyleViewHolder, position: Int) {
        if (selectedPosition.value == null)
            holder.populate(data()[position], position, position == 0, this)
        else
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