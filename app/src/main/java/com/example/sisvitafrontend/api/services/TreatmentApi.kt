package com.example.sisvitafrontend.api.services

import com.example.sisvitafrontend.api.responses.TreatmentResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TreatmentApi {
    @GET("api/treatment")
    suspend fun getTreatment(): List<TreatmentResponse>

    @POST("api/treatment")
    suspend fun postTreatment(@Body treatmentResponse: TreatmentResponse) : TreatmentResponse
}