package com.morozov.psychology.domain.implementation.examples

import com.morozov.psychology.domain.interfaces.examples.ExperimentsLoader
import com.morozov.psychology.mvp.models.examples.ExperimentModel

class ExperimentsLoaderImpl: ExperimentsLoader {

    override fun getExperiments(): List<ExperimentModel> {
        val expM1 = ExperimentModel("Эксперимент №1",
            "Многие думаю, что эмоции напрямую вызваны событием. Например, «этот ответ меня расстроил», «меня пугает поездка в метро», «ты меня разозлила». Как будто определенное событие программирует определенный ответ. Жизнь доказывает обратное. Я предложу вам одну и ту же ситуацию, но с разными эмоциями в ответ на неё. Попробуйте угадать, о чем думали эти люди, чтобы возникла именно эта эмоция.",
            "<b>Ситуация:</b><br/><br/>Друг обещал прийти в 14.00. Уже 15.00, а его все нет. И на звонки он не отвечает.",
            listOf("<b>Реакции разных людей на эту ситуацию:</b><br/><br/>Сергей разозлился. О чем он мог думать, чтобы разозлиться?",
                "Нина расстроилась. О чем она могла думать, чтобы расстроиться?",
                "Олег удивился. О чем он мог думать, чтобы удивиться?",
                "Максим испугался. О чем он мог подумать, чтобы испугаться?",
                "Марина обрадовалась. О чем она могла думать, чтобы обрадоваться?"),
            "Было бы очень печально, если бы мы все были вынуждены реагировать одинаково на одинаковые события. Мы были бы обречены. Человечеству повезло, что у нас есть возможность реагировать по-разному. Спасибо нашим мыслям. Хотите еще раз убедиться в том, что мысли влияют на эмоции? Добро пожаловать в эксперимент №2.")

        val expM2 = ExperimentModel("Эксперимент №2",
            "Человек постоянно о чем-либо думает. Этот процесс происходит автоматически. Если вы думаете, что, засыпая, перестаете думать, то вы глубоко ошибаетесь. Пока тело отдыхает, мозг активно обрабатывает информацию. И от того, какие мысли возникают во время сна, будет зависеть, как вы себя будете чувствовать. С этим и будет связан наш следующий эксперимент.",
            "Человек мыслит словами и образами. Наиболее ярко образное мышление представлено в наших снах. Вспомните, снился ли вам когда-либо кошмарный, эротический или какой-либо другой яркий сон, во время которого вы проснулись и четко помнили содержание сна?",
            listOf("Ответьте на вопрос. Вы испытывали в тот момент эмоции или телесные ощущения, связанные именно со сном, а не с тем, что окружало вас в реальности?",
                "Что вызвало эти эмоции или ощущения в теле?"),
            "Если вы только что написали, что ваши эмоции и ощущения в теле вызвал сон, тонапомню, что сон - это тоже ваши мысли. То есть то, о чем вы думаете, осознанно или автоматически сказывается на вашем настроении, поведении и ощущениях в теле. Хотите ещё раз убедиться в силе влияния мыслей? Переходите к эксперименту №3")

        val expM3 = ExperimentModel("Эксперимент №3",
            "Говорят, что время лечит. Если приглядеться внимательнее к этому процессу, то оказывается, что со временем мы начинаем по другому смотреть на произошедшие ситуации. Например, при потере близкого человека сначала горюют, сосредоточившись на собственной потере, а спустя некоторое время могут вспоминать человека и улыбаться с благодарностью, что этот человек встретился на жизненном пути.",
            "В вашей жизни наверняка было хотя бы одно событие, на которое вначале вы реагировали очень негативно, а со временем негатив ушел. Обычно про такие ситуации говорят «я изменил свое отношение и стало легче». Вспомните такую ситуацию.",
            listOf("Что вы испытывали в прошлом в этой ситуации? Запишите свою эмоцию.",
                "Как вы воспринимали ситуацию тогда в прошлом? Опишите, о чем вы думали?  ",
                "Что вы испытываете, по поводу этой ситуации теперь?",
                "Как вы воспринимаете эту ситуацию теперь? Опишите, что вы о ней думаете?"),
            "Только что вы убедились в том, что изменение восприятия ситуации, того, что выдумаете о ней, какой она для вас кажется, напрямую повлияло на ваши эмоции по отношению к произошедшему. Это удивительное свойство нашей психики помогает нам принимать свое прошлое. Не все, что происходит в жизни, радует. И все же, в наших силах менять свои мысли, а вместе с ними свои эмоции и свое поведение. Если хотите ещё раз убедиться, переходите к эксперименту №4")

        val expM4 = ExperimentModel("Эксперимент №4",
            "Давайте более пристально посмотрим, как мысли влияют на ощущения в теле. Конечно, можно было бы попросить вас представить лимон и ощутить как слюни быстро заполняют ваш рот, или вспомнить, как вы сдавали кровь из пальца и ощутить характерные изменения в безымянном пальце руки. Это все о том, как мысли влияют на ощущения в теле. Но этот эксперимент будет гораздо интереснее.",
            "",
            listOf("Найдите в своем теле любое неприятное ощущение. Например, покалывание в пятке, напряжение в плечах, боль в боку и т.п. Закройте глаза и мысленно многократно скажите этому ощущению вот эти слова: «Вот это да! Как интересно! Это я тебя вызвал! Значит, ты мне зачем-то нужно! Останься со мной, и я смогу лучше понять, зачем ты мне. Оставайся, это очень интересно». Делайте это как можно более искренне до изменения ощущений в теле. Запишите, как изменились ваши ощущения и ваши эмоции.",
                "Найдите в своем теле любое приятное ощущение. Например, тепло в ладошке, расслабленность в груди и т.п. Закройте глаза и мысленно многократно скажите этому ощущению вот эти слова: «Уходи прочь! Я тебя не вызывал! Ты мне не нужно! Зачем ты появилось?» Делайте это как можно более искренне до изменения ощущений в теле. Запишите, как изменились ваши ощущения и ваши эмоции."),
            "Представьте, как выраженно влияет на тело длительное негативное отношение к себе, и как можно улучшить свое здоровье, корректируя собственные мысли. Уверена, что вы заметили изменения в теле в ответ на изменения своих мыслей. Но если хотите ещё раз убедиться, переходите к эксперименту №5")

        val expM5 = ExperimentModel("Эксперимент №5",
            "Когнитивно-поведенческая терапия работает с изменением  глубинных убеждений, являющихся причиной повторяющихся негативных эмоций и телесных симптомов.  Этот процесс занимает достаточно много времени. А сейчас я предлагаю эксперимент попроще. Вы  убедитесь в том, что вы можете создавать свои эмоции самостоятельно в любой момент времени. Для этого достаточно лишь изменить свои мысли. Попробуем?",
            "",
            listOf("Запишите, какую эмоцию вы сейчас испытываете (радость, спокойствие, грусть, тревога, раздражение и т.п.)",
                "Закройте глаза и представьте ситуацию с противоположной эмоцией. Например, если вам сейчас тревожно, вспомните ситуацию, в которой вы радовались. Мысленно погрузитесь в ту ситуацию, побудьте в ней. Отметьте, как изменилась ваша эмоция. Напишите, эмоцию, которую вы испытали при воспоминании.  "),
            "В большинстве случаев эксперимент проходит удачно, и вы заметите, что эмоция поменялась. Что повлияло? Только ваше воспоминание. Вы вызвали мысли и образы  и в результате эмоция изменилась. Именно так то, как вы думаете, влияет на ваше настроение и ваши желания.")

        return listOf(expM1, expM2, expM3, expM4, expM5)
    }
}