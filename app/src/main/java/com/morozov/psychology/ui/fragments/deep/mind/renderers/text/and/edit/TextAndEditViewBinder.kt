package com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit

import android.widget.EditText
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder
import com.morozov.psychology.R

class TextAndEditViewBinder: ViewBinder.Binder<TextAndEditModel> {
    override fun bindView(model: TextAndEditModel, finder: ViewFinder, payloads: MutableList<Any>) {
        finder.setText(R.id.textRend, model.headerText)
        val edit = finder.find<EditText>(R.id.editRend)
        edit.hint = model.hint
        edit.addTextChangedListener(model.textWatcher)
        if (model.insertedText == null) {
            edit.setText("")
            model.insertedText = "inited"
        }
    }
}