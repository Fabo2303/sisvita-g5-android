package com.example.sisvitafrontend.api.requests

import java.sql.Date

data class ConsignacionRequest(
    val date: Date,
    val newInterpretation: String,
    val observation: String,
    val message: String,
    val medicalAppointmentRequested: Boolean,
)
