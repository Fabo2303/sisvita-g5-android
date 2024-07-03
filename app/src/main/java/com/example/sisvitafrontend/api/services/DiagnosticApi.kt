package com.example.sisvitafrontend.api.services

import com.example.sisvitafrontend.api.responses.DiagnosticResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DiagnosticApi {
    @GET("api/diagnostic")
    suspend fun getDiagnostic(): List<DiagnosticResponse>

    @POST("api/diagnostic")
    suspend fun postDiagnostic(@Body diagnosticResponse: DiagnosticResponse): DiagnosticResponse

}