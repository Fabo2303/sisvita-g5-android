package com.example.sisvitafrontend.screens.realizartest.data

import com.example.sisvitafrontend.api.responses.ClassificationResponse
import com.example.sisvitafrontend.screens.realizartest.data.response.AlternativeResponse
import com.example.sisvitafrontend.screens.realizartest.data.response.QuestionResponse

data class TemplateTestResponse(
    val alternatives: List<AlternativeResponse>,
    val author: String,
    val classifications: List<ClassificationResponse>,
    val description: String,
    val id: Long,
    val name: String,
    val questions: List<QuestionResponse>
)