package com.example.sisvitafrontend.api.responses

data class ResolveTestResponseHeatMap(
    val id: Int,
    val ubigeo: String,
    val departamento: String,
    val latitud: Double,
    val longitud: Double,
    val intensity: Int,
    val ubigeoIntensityCount: Int,
    val totalIntensity: Int
)
