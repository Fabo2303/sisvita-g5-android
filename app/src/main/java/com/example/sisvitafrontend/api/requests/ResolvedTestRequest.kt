package com.example.sisvitafrontend.api.requests

import java.sql.Date

data class ResolvedTestRequest(
    val date: Date,
    val templateTestId: Long,
    val idPaciente: Long,
    val answers: List<AnswerRequest>
)