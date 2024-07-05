package com.example.sisvitafrontend.screens.register.data.request

import java.sql.Date

data class PersonaRequest(
    val birthDate: Date,
    val document: String,
    val documentType: Int,
    val email: String,
    val lastName: String,
    val name: String,
    val phone: String,
    val secondLastName: String,
    val gender: Int,
    val ubigeoRequest: UbigeoRequest
)