package com.example.sisvitafrontend.api.services

import com.example.sisvitafrontend.api.requests.PatientRequest
import com.example.sisvitafrontend.api.responses.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("api/patient")
    suspend fun register(@Body patientRequest: PatientRequest): Response<RegisterResponse>
}