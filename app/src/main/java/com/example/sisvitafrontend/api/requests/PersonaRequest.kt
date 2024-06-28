package com.example.sisvitafrontend.api.requests

import java.sql.Date

data class PersonaRequest(
    val birthDate: Date,
    val document: String,
    val documentType: String,
    val email: String,
    val lastName: String,
    val name: String,
    val phone: String,
    val secondLastName: String,
    val sex: String,
    val ubigeoRequest: UbigeoRequest
)