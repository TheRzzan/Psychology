package com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder
import com.morozov.psychology.R

class TextAndEditViewBinder(private val listener: OnTextUpdatedCallback): ViewBinder.Binder<TextAndEditModel> {
    override fun bindView(model: TextAndEditModel, finder: ViewFinder, payloads: MutableList<Any>) {
        finder.setText(R.id.textRend, model.headerText)
        val edit = finder.find<EditText>(R.id.editRend)
        edit.hint = model.hint
        if (model.insertedText != null)
            edit.setText(model.insertedText)
        edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                listener.onUpdated(model.position, edit.text.toString())
            }
        })
    }
}