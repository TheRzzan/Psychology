package com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit

import android.text.TextWatcher
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

data class TextAndEditModel(val position: Int, val headerText: String,
                            val hint: String,
                            val textWatcher: TextWatcher,
                            var insertedText: String? = null): ViewModel