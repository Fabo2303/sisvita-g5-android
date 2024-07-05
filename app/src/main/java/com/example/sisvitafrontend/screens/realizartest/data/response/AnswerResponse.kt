package com.example.sisvitafrontend.screens.realizartest.data.response

data class AnswerResponse(
    val id: Long,
    val question: QuestionResponse,
    val alternative: AlternativeResponse,
)
