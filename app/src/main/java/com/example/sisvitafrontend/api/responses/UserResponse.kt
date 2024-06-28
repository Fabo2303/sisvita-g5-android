package com.example.sisvitafrontend.api.responses

data class UserResponse(
    val id: Long,
    val username: String,
    val persona: PersonaResponse,
)
