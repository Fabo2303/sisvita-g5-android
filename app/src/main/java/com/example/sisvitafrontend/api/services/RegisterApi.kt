package com.example.sisvitafrontend.api.services

import com.example.sisvitafrontend.api.requests.PatientRequest
import com.example.sisvitafrontend.api.responses.DocumentResponse
import com.example.sisvitafrontend.api.responses.RegisterResponse
import com.example.sisvitafrontend.api.responses.SexResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RegisterApi {
    @POST("api/patient")
    suspend fun register(@Body patientRequest: PatientRequest): Response<RegisterResponse>

    @GET("api/document")
    suspend fun getDocuments(): Response<List<DocumentResponse>>

    @GET("api/sex")
    suspend fun getSex(): Response<List<SexResponse>>
}