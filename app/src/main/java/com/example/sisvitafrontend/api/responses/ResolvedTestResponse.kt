package com.example.sisvitafrontend.api.responses

import com.example.sisvitafrontend.screens.realizartest.data.TemplateTestResponse
import com.example.sisvitafrontend.screens.realizartest.data.response.AnswerResponse

data class ResolvedTestResponse(
    val id: Long,
    val result: Int,
    val interpretation: String,
    val color: String,
    val date: String,
    val templateTest: TemplateTestResponse,
    val classification: ClassificationResponse,
    val answers: List<AnswerResponse>,
    val paciente: PatientResponse
)