package com.morozov.psychology.ui.fragments.deep.mind.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.morozov.psychology.R
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit.OnTextUpdatedCallback
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit.TextAndEditModel
import com.morozov.psychology.ui.fragments.deep.mind.renderers.text.and.edit.TextAndEditViewBinder
import kotlinx.android.synthetic.main.fragment_deep_mind_test.*

class DeepMintTest: Fragment() {

    private lateinit var mRecyclerViewAdapter: RendererRecyclerViewAdapter
    private val mItems = mutableListOf<ViewModel>()

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
    }

    private fun RendererRecyclerViewAdapter.registerRenderers() {
        registerRenderer(ViewBinder(R.layout.item_rend_text_and_edit, TextAndEditModel::class.java, TextAndEditViewBinder(textUpdateCallback)))
//        registerRenderer(ViewBinder(R.layout.item_to_me_media_text_message, ToMeMediaAndTextModel::class.java, ToMeMediaAndTextViewBinder(this@RendererDialogChatFragment)))
    }

    private val textUpdateCallback = object: OnTextUpdatedCallback {
        override fun onUpdated(position: Int, newText: String) {

        }
    }

    private fun loadItems() {
        val textEnterThink = "Введите мысль"
        val textEnterAnswer = "Введите ответ"
        mItems.add(
            TextAndEditModel(
                0,
                "За теми мыслями, что автоматически появляются в нашей голове, всегда стоят глубинные убеждения, уходящие своими корнями в далекое прошлое. Готовы приступить к поиску?\n\nЗапишите мысль, глубинное значение которой хотите найти. ",
                textEnterThink
            )
        )
        mItems.add(
            TextAndEditModel(
                1,
                "Вам предстоит отвечать на вопрос, а затем читать ответ и и снова задавать себе вопрос уже применительно к этому ответу. Так с каждым разом вы будете все глубже и глубже погружаться в исследование смысла своего убеждения. Если вы стали повторяться с ответами, то можете остановить свои поиски. \n\n Начнем. Ответьте на вопрос: Что эта мысль значит для вас? ",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                2,
                "Если это так, то что это значит для вас?",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                3,
                "Если это так, то что это значит для вас?",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                4,
                "Если это так, то как при этом вы воспринимаете себя?",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                5,
                "Если это так, то что это значит для вас?",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                6,
                "Если это так, то каким вы воспринимаете мир?",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                7,
                "Если это так, то каким вы воспринимаете окружающих людей? ",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                8,
                "Если это так, то что это значит для вас?",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                9,
                "Если это так, то на чем вы основываете свое убеждение? ",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                10,
                "Если это так, то что это значит для вас?",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                11,
                "Если это так, то что вы думаете о своей жизни?",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                12,
                "Если это так, то что это значит для вас?",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                13,
                "Если это так, то каким вы (или окружающие) должны быть? ",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                14,
                "Если это так, то что это значит для вас?",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                15,
                "Если это так, то что это значит для вас?",
                textEnterAnswer
            )
        )
        mItems.add(
            TextAndEditModel(
                16,
                "Если это так, то что это значит для вас?",
                textEnterAnswer
            )
        )
    }
}