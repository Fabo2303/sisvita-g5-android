package com.example.sisvitafrontend.api.responses

data class UbigeoResponse(
    val ubigeo: String,
    val departamento: String,
    val provincia: String,
    val distrito: String,
    val latitud: Double,
    val longitud: Double
)
