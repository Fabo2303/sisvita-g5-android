package com.example.sisvitafrontend.api.requests

import java.sql.Date

data class ConsignacionRequest(
    val date: Date,
    val idDiagnostic: Long,
    val idTreatment: Long,
    val observation: String,
    val fundament: String,
    val idTestResolved: Long,
    val idSpecialist: Long,
    val idPaciente: Long
)
