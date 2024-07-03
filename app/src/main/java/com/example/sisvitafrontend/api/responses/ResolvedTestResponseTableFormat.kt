package com.example.sisvitafrontend.api.responses

data class ResolvedTestResponseTableFormat(
    val id: Long,
    val namePatient: String,
    val lastNamePatient: String,
    val date: String,
    val nameTemplateTest: String,
    val colorClassification: String
)
