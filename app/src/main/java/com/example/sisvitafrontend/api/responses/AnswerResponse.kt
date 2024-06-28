package com.example.sisvitafrontend.api.responses

data class AnswerResponse(
    val id: Long,
    val question: QuestionResponse,
    val alternative: AlternativeResponse,
)
