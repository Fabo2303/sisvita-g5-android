package com.example.sisvitafrontend.api.requests

data class UserRequest(
    val password: String,
    val personaRequest: PersonaRequest,
    val role: String,
    val username: String
)