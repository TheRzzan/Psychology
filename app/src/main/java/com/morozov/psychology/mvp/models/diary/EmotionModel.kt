package com.morozov.psychology.mvp.models.diary

data class EmotionModel(var emotion: Emotion, var percent: Int) {

    enum class Emotion{
        JOY, SADNESS, ANNOYANCE, ANXIETY, DISGUST, INTEREST, GUILT, RESENTMENT
    }
}