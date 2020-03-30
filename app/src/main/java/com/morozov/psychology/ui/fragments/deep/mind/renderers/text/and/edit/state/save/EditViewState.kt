package com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit.state.save

import android.widget.EditText
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder
import com.github.vivchar.rendererrecyclerviewadapter.ViewState
import com.morozov.psychology.R

class EditViewState(holder: ViewHolder): ViewState<ViewHolder> {

    private val mEnteredText: String

    init {
        val editText: EditText = holder.viewFinder.find(R.id.editRend)
        mEnteredText = editText.text.toString()
    }

    override fun restore(holder: ViewHolder) {
        holder.viewFinder.setText(R.id.editRend, mEnteredText)
    }
}