package com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.data.request

import java.sql.Date

data class ConsignacionRequest(
    val date: Date,
    val idDiagnosis: Long,
    val idTreatment: Long,
    val observation: String,
    val fundament: String,
    val idTestResolved: Long,
    val idSpecialist: Long,
    val idPatient: Long
)
