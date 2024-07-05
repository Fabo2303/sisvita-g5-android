package com.example.sisvitafrontend.screens.consultarresultados

import com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.data.response.AnswerResponseDataPatient

data class ResolvedTestResponseViewPatient(
    val id: Long,
    val date: String,
    val nameTemplateTest: String,
    val colorClassification: String,
    val result: Int,
    val interpretation: String,
    val answers: List<AnswerResponseDataPatient>,
    val consignation: ConsignationResponse
)
