package com.example.sisvitafrontend.screens.realizarvigilancia.visualizarmapacalor

data class ResolveTestResponseHeatMap(
    val id: Int,
    val ubigeo: String,
    val department: String,
    val latitude: Double,
    val longitude: Double,
    val intensity: Int,
    val ubigeoIntensityCount: Int,
    val totalIntensity: Int
)
