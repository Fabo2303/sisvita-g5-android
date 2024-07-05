package com.example.sisvitafrontend.screens.realizartest.data.request

import java.sql.Timestamp

data class ResolvedTestRequest(
    val date: Timestamp,
    val idTemplateTest: Long,
    val idPatient: Long,
    val answers: List<AnswerRequest>
)