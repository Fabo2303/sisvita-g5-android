package com.example.sisvitafrontend.screens.register.data.service

import com.example.sisvitafrontend.screens.register.data.request.PatientRequest
import com.example.sisvitafrontend.screens.register.data.response.DocumentResponse
import com.example.sisvitafrontend.screens.register.data.response.GenderResponse
import com.example.sisvitafrontend.screens.register.data.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RegisterApi {
    @POST("api/patient")
    suspend fun register(@Body patientRequest: PatientRequest): Response<RegisterResponse>

    @GET("api/document-type")
    suspend fun getDocuments(): Response<List<DocumentResponse>>

    @GET("api/gender")
    suspend fun getSex(): Response<List<GenderResponse>>
}