package com.example.sisvitafrontend.api.responses

data class QuestionResponse(
    val id: Long,
    val idTemplateTest: Long,
    val image: Any,
    val inverted: Boolean,
    val statement: String
)