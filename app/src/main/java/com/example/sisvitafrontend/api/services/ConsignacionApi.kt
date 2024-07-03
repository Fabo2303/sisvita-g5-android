package com.example.sisvitafrontend.api.services

import com.example.sisvitafrontend.api.requests.ConsignacionRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ConsignacionApi {
    @POST("api/consignacion/insert")
    suspend fun saveConsignacion(@Body consignacionRequest: ConsignacionRequest): Response<String>
}