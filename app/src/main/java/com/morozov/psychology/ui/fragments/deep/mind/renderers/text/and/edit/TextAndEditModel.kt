package com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

data class TextAndEditModel(val position: Int, val headerText: String,
                            val hint: String, val insertedText: String? = null): ViewModel