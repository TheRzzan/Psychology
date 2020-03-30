package com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.button

import android.view.View
import android.widget.Button
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder
import com.morozov.psychology.R

class TextAndButtonViewBinder(private val clickListener: View.OnClickListener): ViewBinder.Binder<TextAndButtonModel> {
    override fun bindView(model: TextAndButtonModel, finder: ViewFinder, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            finder.setText(R.id.textRend, model.textHeader)
            finder.setText(R.id.buttonRend, model.textButton)
            val button = finder.find<Button>(R.id.buttonRend)
            finder.setOnClickListener(R.id.buttonRend, clickListener)

            if (model.enable)
                finder.setBackgroundColor(R.id.buttonRend, button.resources.getColor(R.color.colorAccent))
            else
                finder.setBackgroundColor(R.id.buttonRend, button.resources.getColor(R.color.grey_color))

            finder.setEnabled(R.id.buttonRend, model.enable)
        }
    }
}