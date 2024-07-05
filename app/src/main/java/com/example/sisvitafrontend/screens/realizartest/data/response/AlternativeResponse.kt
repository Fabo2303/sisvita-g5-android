package com.example.sisvitafrontend.screens.realizartest.data.response

data class AlternativeResponse(
    val description: String,
    val id: Long,
    val idTemplateTest: Long,
    val invertedScore: Int,
    val score: Int
)