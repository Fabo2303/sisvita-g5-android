package com.example.sisvitafrontend.api.responses

data class AlternativeResponse(
    val description: String,
    val id: Long,
    val idTemplateTest: Long,
    val invertedScore: Int,
    val score: Int
)