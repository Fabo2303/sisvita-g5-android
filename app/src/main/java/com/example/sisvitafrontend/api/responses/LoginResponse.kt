package com.example.sisvitafrontend.api.responses

data class LoginResponse(
    val jwt: String,
    val message: String,
    val error: String
)