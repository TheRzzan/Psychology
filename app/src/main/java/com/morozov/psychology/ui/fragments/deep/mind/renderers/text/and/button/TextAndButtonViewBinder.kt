package com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.button

import android.view.View
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder
import com.morozov.psychology.R

class TextAndButtonViewBinder(private val clickListener: View.OnClickListener): ViewBinder.Binder<TextAndButtonModel> {
    override fun bindView(model: TextAndButtonModel, finder: ViewFinder, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            finder.setText(R.id.textRend, model.textHeader)
            finder.setText(R.id.buttonRend, model.textButton)
            finder.setOnClickListener(R.id.buttonRend, clickListener)
        }
    }
}