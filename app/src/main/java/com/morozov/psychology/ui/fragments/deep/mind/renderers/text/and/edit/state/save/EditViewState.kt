package com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit.state.save

import android.widget.EditText
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder
import com.github.vivchar.rendererrecyclerviewadapter.ViewState
import com.morozov.psychology.R
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit.OnTextUpdatedCallback

class EditViewState(private val holder: ViewHolder): ViewState<ViewHolder> {

    private val mEnteredText: String

    init {
        val editText: EditText = holder.viewFinder.find(R.id.editRend)
        mEnteredText = editText.text.toString()
    }

    override fun restore(holder: ViewHolder) {
        holder.viewFinder.setText(R.id.editRend, mEnteredText)
    }

    fun getState(): String? = holder.viewFinder.find<EditText>(R.id.editRend).text.toString()
}