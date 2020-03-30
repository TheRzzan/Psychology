package com.morozov.psychology.ui.fragments.deep.mind.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import kotlinx.android.synthetic.main.fragment_deep_mind_test_new.*

class DeepMindTestFragment: Fragment() {

    lateinit var mActivityPresenter: MainPresenter

    private val listOfThinks = mutableListOf<String>()

    private lateinit var button: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_deep_mind_test_new, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearBack.setOnClickListener {
            activity?.onBackPressed()
        }

        val textEnterThink = "Введите мысль"
        val textEnterAnswer = "Введите ответ"

        linearRoot.addTextAndEdit(
            "За теми мыслями, что автоматически появляются в нашей голове, всегда стоят глубинные убеждения, уходящие своими корнями в далекое прошлое. Готовы приступить к поиску?\n\nЗапишите мысль, глубинное значение которой хотите найти. ",
            textEnterThink
        )
        linearRoot.addTextAndEdit(
                "Вам предстоит отвечать на вопрос, а затем читать ответ и и снова задавать себе вопрос уже применительно к этому ответу. Так с каждым разом вы будете все глубже и глубже погружаться в исследование смысла своего убеждения. Если вы стали повторяться с ответами, то можете остановить свои поиски. \n\nНачнем. Ответьте на вопрос: Что эта мысль значит для вас? ",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то что это значит для вас?",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то что это значит для вас?",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то как при этом вы воспринимаете себя?",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то что это значит для вас?",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то каким вы воспринимаете мир?",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то каким вы воспринимаете окружающих людей? ",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то что это значит для вас?",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то на чем вы основываете свое убеждение? ",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то что это значит для вас?",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то что вы думаете о своей жизни?",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то что это значит для вас?",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то каким вы (или окружающие) должны быть? ",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то что это значит для вас?",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то что это значит для вас?",
            textEnterAnswer
        )
        linearRoot.addTextAndEdit(
                "Если это так, то что это значит для вас?",
            textEnterAnswer
        )
        linearRoot.addTextView(
            "Просмотрите свои записи и выберите мысль, которая в большей степени откликается вам, вызывает негативные эмоции. "
            )
        linearRoot.addButton(
            "Выбрать мысль"
        )
    }

    private var addPosition = 0
    private fun LinearLayout.addTextAndEdit(header: String, editHint: String) {
        addTextView(header)
        addEditText(editHint, addPosition)
        addPosition++
    }

    private fun LinearLayout.addTextView(text: String) {
        val textView = TextView(context)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT, 6f)
        params.setMargins(32, 0, 32, 32)
        textView.layoutParams = params
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.text_secondary_size))
        textView.setTextColor(resources.getColor(R.color.second_header_text))
        textView.setTypeface(null, Typeface.BOLD)
        textView.text = text
        this.addView(textView)
    }

    private fun LinearLayout.addEditText(hint: String, position: Int) {
        val editText = EditText(context)
        editText.setPadding(32, 8, 32, 8)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(32, 0, 32, 48)
        editText.layoutParams = params
        editText.hint = hint
        editText.setTextColor(resources.getColor(R.color.second_header_text))
        try {
            val f = TextView::class.java.getDeclaredField("textCursorDrawable")
            f.isAccessible = true
            f.set(editText, R.drawable.cursor_color)
        } catch (ignore: Exception) {}
        editText.background = resources.getDrawable(R.drawable.rectangle_long_edit_text_with_shadow)
        editText.addTextChangedListener(object: MyTextWatcher(){
            override fun getPosition(): Int = position
        })
        listOfThinks.add("")
        this.addView(editText)
    }

    private fun LinearLayout.addButton(text: String) {
        button = Button(context)
        button.text = text
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.button_font_size))
        button.setTextColor(resources.getColor(R.color.primary_text))
        button.setBackgroundResource(R.drawable.rectangle_button_disable)
        button.isEnabled = false
        button.isAllCaps = false
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(32, 0, 32, 32)
        button.layoutParams = params
        button.setOnClickListener {
            mActivityPresenter.showSelectMind()
        }
        try {
            val f = TextView::class.java.getDeclaredField("textStyle")
            f.isAccessible = true
            f.set(button, "bold")
        } catch (ignore: Exception) {}
        this.addView(button)
    }

    private abstract inner class MyTextWatcher: TextWatcher{
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            var isReady = true
            listOfThinks[getPosition()] = p0?.toString() ?: ""
            for (think in listOfThinks) {
                if (think.isEmpty()){
                    isReady = false
                    break
                }
            }
            when(isReady) {
                true -> button.setBackgroundResource(R.drawable.rectangle_button)
                false -> button.setBackgroundResource(R.drawable.rectangle_button_disable)
            }
            button.isEnabled = isReady
        }
        abstract fun getPosition(): Int
    }

    fun View.setMargins(l: Int, t: Int, r: Int, b: Int) {
        if (this.layoutParams is MarginLayoutParams) {
            val p = this.layoutParams as MarginLayoutParams
            p.setMargins(l, t, r, b)
            this.requestLayout()
        }
    }
}