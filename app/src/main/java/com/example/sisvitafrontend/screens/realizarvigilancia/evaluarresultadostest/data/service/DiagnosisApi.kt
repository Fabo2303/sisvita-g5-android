package com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.data.service

import com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.data.response.DiagnosisResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DiagnosisApi {
    @GET("api/diagnosis")
    suspend fun getDiagnostic(): List<DiagnosisResponse>

    @POST("api/diagnosis")
    suspend fun postDiagnostic(@Body diagnosisResponse: DiagnosisResponse): DiagnosisResponse

}