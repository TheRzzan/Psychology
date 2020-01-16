package com.morozov.psychology.utility

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper

class ItemTouchHelperClass(private val adapter: ItemTouchHelperAdapter): ItemTouchHelper.Callback() {

    interface ItemTouchHelperAdapter {
        fun onItemRemoved(position: Int)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(recycler: androidx.recyclerview.widget.RecyclerView, holder: androidx.recyclerview.widget.RecyclerView.ViewHolder): Int {
        val upFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END

        return makeMovementFlags(upFlags, swipeFlags)
    }

    override fun onMove(recycler: androidx.recyclerview.widget.RecyclerView, holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, target: androidx.recyclerview.widget.RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemRemoved(holder.getAdapterPosition())
    }
}