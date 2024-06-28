package com.example.sisvitafrontend.api.requests

import java.sql.Date

data class ResolvedTestRequest(
    val result: Int = 0,
    val date: Date,
    val templateTestId: Long,
    val classificationId: Long,
    val idPaciente: Long,
    val answers: List<AnswerRequest>
)