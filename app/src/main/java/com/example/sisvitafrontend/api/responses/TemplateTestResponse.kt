package com.example.sisvitafrontend.api.responses

data class TemplateTestResponse(
    val alternatives: List<AlternativeResponse>,
    val author: String,
    val classifications: List<ClassificationResponse>,
    val description: String,
    val id: Long,
    val name: String,
    val questions: List<QuestionResponse>
)