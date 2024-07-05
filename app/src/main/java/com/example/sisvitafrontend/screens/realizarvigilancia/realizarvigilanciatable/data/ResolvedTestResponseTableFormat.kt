package com.example.sisvitafrontend.screens.realizarvigilancia.realizarvigilanciatable.data

data class ResolvedTestResponseTableFormat(
    val id: Long,
    val namePatient: String,
    val lastNamePatient: String,
    val date: String,
    val nameTemplateTest: String,
    val colorClassification: String
)
