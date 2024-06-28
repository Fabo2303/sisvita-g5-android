package com.example.sisvitafrontend.api.responses

data class PersonaResponse(
    val document: String,
    val documentType: String,
    val email: String,
    val name: String,
    val phone: String,
    val lastName: String,
    val secondLastName: String,
    val birthDate: String,
    val sex: String,
    val ubigeo: UbigeoResponse
)