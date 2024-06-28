package com.example.sisvitafrontend.api.responses

data class ClassificationResponse(
    val id: Long,
    val idTemplateTest: Int,
    val interpretation: String,
    val maximum: Int,
    val minimum: Int
)