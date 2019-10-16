package com.morozov.psychology.mvp.presenters.mind.change.think.mintake

import android.content.res.Resources
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.views.mind.change.think.mintake.MCThinkMistake_1_View

@InjectViewState
class MCThinkMistake_1_Presenter: MvpPresenter<MCThinkMistake_1_View>() {

    fun loadThinkMistakes(resources: Resources) {
        val pair_1_2 = Pair(
            "Катастрофизация – будущее предсказывается негативым. Даже если вероятность ужасного последствия минимальна, возникает мысль «а что, если…». ",
            "Если я провалю экзамен, это будет ужасно!"
        )
        val pair_1_1 = Pair(resources.getDrawable(R.drawable.ic_disastrous), pair_1_2)

        val pair_2_2 = Pair(
            "Обесценивание позитивного – привычка не придавать значения успехам и позитивному опыту. Например, списывать свои или чужие заслуги на везение или сравнивать с другими людьми, у которых этот навык или знания лучше.",
            "То, что я заняла второе место на городской олимпиаде – случайность."
        )
        val pair_2_1 = Pair(resources.getDrawable(R.drawable.ic_depreciation), pair_2_2)

        val pair_3_2 = Pair(
            "Черно-белое мышление – привычка оценивать контрастно, либо плохой, либо хороший, среднего не дано. ",
            "Если я не добьюсь успеха во всем, то я неудачник."
        )
        val pair_3_1 = Pair(resources.getDrawable(R.drawable.ic_black_and_white), pair_3_2)

        val pair_4_2 = Pair(
            "Эмоциональное обоснование – обоснование вывода на чувствах и интуитивной вере, игнорирование или обесценивание доказательств обратного.",
            "Да, он мне совсем не помогает, но я верю, что он хочет заботиться обо мне."
        )
        val pair_4_1 = Pair(resources.getDrawable(R.drawable.ic_emotional_rationale), pair_4_2)

        val pair_5_2 = Pair(
            "Чтение мыслей - уверенность в том, что вы знаете мысли окружающих, без принятия во внимание других, более вероятных возможностей.",
            "Он наверняка думает, что я неуверенная в себе."
        )
        val pair_5_1 = Pair(resources.getDrawable(R.drawable.ic_mind_reading), pair_5_2)

        val pair_6_2 = Pair(
            "Сверхобобщение – привычка делать неоправданные обобщения на избирательных фактах. Её легко найти по словам \"все\", \"никто\", \"ничто\", \"всюду\", \"нигде\",  \"никогда\", \"всегда\", \"вечно\", \"постоянно\".",
            "Вечно ты закатываешь мне истерики."
        )
        val pair_6_1 = Pair(resources.getDrawable(R.drawable.ic_overgeneration), pair_6_2)

        val pair_7_2 = Pair(
            "",
            ""
        )
        val pair_7_1 = Pair(resources.getDrawable(R.drawable.ic_disastrous), pair_7_2)

        viewState.showThinkMistakes(listOf(pair_1_1, pair_2_1, pair_3_1, pair_4_1, pair_5_1, pair_6_1))
    }
}