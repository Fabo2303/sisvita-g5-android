package com.example.sisvitafrontend.api.responses

data class ResolvedTestResponse(
    val id: Long,
    val result: Int,
    val interpretation: String,
    val date: String,
    val templateTest: TemplateTestResponse,
    val classification: ClassificationResponse,
    val answers: List<AnswerResponse>,
    val paciente: PatientResponse
)