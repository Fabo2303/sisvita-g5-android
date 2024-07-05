package com.example.sisvitafrontend.screens.register.data.request

data class UserRequest(
    val password: String,
    val personRequest: PersonaRequest,
    val role: String,
    val username: String
)