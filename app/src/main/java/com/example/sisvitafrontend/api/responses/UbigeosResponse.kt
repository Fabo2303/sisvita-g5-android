package com.example.sisvitafrontend.api.responses

data class UbigeosResponse(
    val departamentos: List<String>,
    val distritos: List<String>,
    val provincias: List<String>,
    val ubigeo: String,
)
