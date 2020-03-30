package com.morozov.psychology.ui.fragments.deep.mind.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.ui.fragments.deep.mind.renderers.header.HeaderModel
import com.morozov.psychology.ui.fragments.deep.mind.renderers.header.HeaderViewBinder
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.button.TextAndButtonModel
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.button.TextAndButtonViewBinder
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit.OnTextUpdatedCallback
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit.TextAndEditModel
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit.TextAndEditViewBinder
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit.state.save.EditViewState
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit.state.save.EditViewStateProvider
import kotlinx.android.synthetic.main.fragment_deep_mind_test.*

class DeepMintTest: Fragment() {

    lateinit var mActivityPresenter: MainPresenter

    private lateinit var mRecyclerViewAdapter: RendererRecyclerViewAdapter
    private val mItems = mutableListOf<ViewModel>()

    private val mEditNumber = 17
    private var mEditFilled = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_deep_mind_test, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerViewAdapter = RendererRecyclerViewAdapter()
        mRecyclerViewAdapter.registerRenderers()
        mRecyclerViewAdapter.enableDiffUtil()
        mRecyclerViewAdapter.setItems(mItems)

        recyclerRendTest.adapter = mRecyclerViewAdapter
        recyclerRendTest.layoutManager = LinearLayoutManager(context)

        loadItems()
    }

    private fun RendererRecyclerViewAdapter.registerRenderers() {
        registerRenderer(ViewBinder(R.layout.item_rend_text_and_edit, TextAndEditModel::class.java, TextAndEditViewBinder(), EditViewStateProvider()))
        registerRenderer(ViewBinder(R.layout.item_rend_text_and_button, TextAndButtonModel::class.java, TextAndButtonViewBinder(buttonClickListener)))
        registerRenderer(ViewBinder(R.layout.item_rend_text_header, HeaderModel::class.java, HeaderViewBinder()))
//        registerRenderer(ViewBinder(R.layout.item_to_me_media_text_message, ToMeMediaAndTextModel::class.java, ToMeMediaAndTextViewBinder(this@RendererDialogChatFragment)))
    }

    private val buttonClickListener = View.OnClickListener {
        Log.i("Jeka", "Click")
    }

    inner class MyTextUpdatedCallback(private val position: Int): OnTextUpdatedCallback, TextWatcher {

        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onUpdated(position, null)
        }

        override fun onUpdated(position: Int, newText: String?) {
            val item = (mItems[position+1])
            if (item is TextAndEditModel) {
                if (newText != null)
                    item.insertedText = newText
            }
            checkIsReady()
        }
    }

    private fun checkIsReady() {
        var bool = true
        for (state in mRecyclerViewAdapter.states) {
            val value = state.value
            if (value is EditViewState) {
                if (value.getState().isNullOrEmpty()){
                    bool = false
                    break
                }
            }
        }
        if (bool)
            Toast.makeText(context, "Ready", Toast.LENGTH_SHORT).show()
    }

    private fun loadItems() {
        val textEnterThink = "Введите мысль"
        val textEnterAnswer = "Введите ответ"
        mItems.add(
            HeaderModel("Глубинные убеждения")
        )
        mItems.add(
            TextAndEditModel(
                0,
                "За теми мыслями, что автоматически появляются в нашей голове, всегда стоят глубинные убеждения, уходящие своими корнями в далекое прошлое. Готовы приступить к поиску?\n\nЗапишите мысль, глубинное значение которой хотите найти. ",
                textEnterThink,
                MyTextUpdatedCallback(0)
            )
        )
        mItems.add(
            TextAndEditModel(
                1,
                "Вам предстоит отвечать на вопрос, а затем читать ответ и и снова задавать себе вопрос уже применительно к этому ответу. Так с каждым разом вы будете все глубже и глубже погружаться в исследование смысла своего убеждения. Если вы стали повторяться с ответами, то можете остановить свои поиски. \n\n Начнем. Ответьте на вопрос: Что эта мысль значит для вас? ",
                textEnterAnswer,
                MyTextUpdatedCallback(1)
            )
        )
        mItems.add(
            TextAndEditModel(
                2,
                "Если это так, то что это значит для вас?",
                textEnterAnswer,
                MyTextUpdatedCallback(2)
            )
        )
        mItems.add(
            TextAndEditModel(
                3,
                "Если это так, то что это значит для вас?",
                textEnterAnswer,
                MyTextUpdatedCallback(3)
            )
        )
        mItems.add(
            TextAndEditModel(
                4,
                "Если это так, то как при этом вы воспринимаете себя?",
                textEnterAnswer,
                MyTextUpdatedCallback(4)
            )
        )
        mItems.add(
            TextAndEditModel(
                5,
                "Если это так, то что это значит для вас?",
                textEnterAnswer,
                MyTextUpdatedCallback(5)
            )
        )
        mItems.add(
            TextAndEditModel(
                6,
                "Если это так, то каким вы воспринимаете мир?",
                textEnterAnswer,
                MyTextUpdatedCallback(6)
            )
        )
        mItems.add(
            TextAndEditModel(
                7,
                "Если это так, то каким вы воспринимаете окружающих людей? ",
                textEnterAnswer,
                MyTextUpdatedCallback(7)
            )
        )
        mItems.add(
            TextAndEditModel(
                8,
                "Если это так, то что это значит для вас?",
                textEnterAnswer,
                MyTextUpdatedCallback(8)
            )
        )
        mItems.add(
            TextAndEditModel(
                9,
                "Если это так, то на чем вы основываете свое убеждение? ",
                textEnterAnswer,
                MyTextUpdatedCallback(9)
            )
        )
        mItems.add(
            TextAndEditModel(
                10,
                "Если это так, то что это значит для вас?",
                textEnterAnswer,
                MyTextUpdatedCallback(10)
            )
        )
        mItems.add(
            TextAndEditModel(
                11,
                "Если это так, то что вы думаете о своей жизни?",
                textEnterAnswer,
                MyTextUpdatedCallback(11)
            )
        )
        mItems.add(
            TextAndEditModel(
                12,
                "Если это так, то что это значит для вас?",
                textEnterAnswer,
                MyTextUpdatedCallback(12)
            )
        )
        mItems.add(
            TextAndEditModel(
                13,
                "Если это так, то каким вы (или окружающие) должны быть? ",
                textEnterAnswer,
                MyTextUpdatedCallback(13)
            )
        )
        mItems.add(
            TextAndEditModel(
                14,
                "Если это так, то что это значит для вас?",
                textEnterAnswer,
                MyTextUpdatedCallback(14)
            )
        )
        mItems.add(
            TextAndEditModel(
                15,
                "Если это так, то что это значит для вас?",
                textEnterAnswer,
                MyTextUpdatedCallback(15)
            )
        )
        mItems.add(
            TextAndEditModel(
                16,
                "Если это так, то что это значит для вас?",
                textEnterAnswer,
                MyTextUpdatedCallback(16)
            )
        )
        mItems.add(
            TextAndButtonModel(
                "Просмотрите свои записи и выберите мысль, которая в большей степени откликается вам, вызывает негативные эмоции. ",
                "Выбрать мысль"
            )
        )
        mRecyclerViewAdapter.setItems(mItems)
    }
}