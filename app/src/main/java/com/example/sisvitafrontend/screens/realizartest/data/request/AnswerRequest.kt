package com.example.sisvitafrontend.screens.realizartest.data.request

data class AnswerRequest(
    val idQuestion: Long,
    val idAlternative: Long,
    val inverted: Boolean,
    val score: Int,
    val invertedScore: Int
)
