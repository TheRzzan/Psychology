package com.morozov.psychology.ui.adapters

import androidx.recyclerview.widget.RecyclerView

abstract class ListAdapter<T, VH : androidx.recyclerview.widget.RecyclerView.ViewHolder>: androidx.recyclerview.widget.RecyclerView.Adapter<VH>() {

    private var data: List<T> = ArrayList<T>()

    protected fun data(): List<T> = data

    fun setData(data: List<T>) {
        this.data = data
    }

    override fun getItemCount(): Int = data.size
}