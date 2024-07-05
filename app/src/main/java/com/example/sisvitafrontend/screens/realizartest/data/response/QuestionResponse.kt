package com.example.sisvitafrontend.screens.realizartest.data.response

data class QuestionResponse(
    val id: Long,
    val idTemplateTest: Long,
    val image: Any,
    val inverted: Boolean,
    val statement: String
)