package com.example.sisvitafrontend.api.responses

data class ResolvedTestResponseDataPatient(
    val answers: List<AnswerResponseDataPatient>,
    val colorClassification: String,
    val document: String,
    val documentType: String,
    val id: Long,
    val idPatient: Long,
    val interpretation: String,
    val lastName: String,
    val name: String,
    val nameTemplateTest: String,
    val result: Int,
    val secondLastName: String
)