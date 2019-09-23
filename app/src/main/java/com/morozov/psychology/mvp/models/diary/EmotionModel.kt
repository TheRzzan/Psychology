package com.morozov.psychology.mvp.models.diary

import java.io.Serializable

data class EmotionModel(var emotion: Emotion, var percent: Int): Serializable {

    enum class Emotion{
        JOY, SADNESS, ANNOYANCE, ANXIETY, DISGUST, INTEREST, GUILT, RESENTMENT
    }
}