package com.example.sisvitafrontend.api.requests

data class AnswerRequest(
    val idQuestion: Long,
    val idAlternative: Long,
    val inverted: Boolean,
    val score: Int,
    val invertedScore: Int
)
