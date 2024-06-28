package com.example.sisvitafrontend.api.services

import com.example.sisvitafrontend.api.requests.LoginRequest
import com.example.sisvitafrontend.api.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}