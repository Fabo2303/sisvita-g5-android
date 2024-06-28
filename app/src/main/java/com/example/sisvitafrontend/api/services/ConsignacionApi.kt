package com.example.sisvitafrontend.api.services

import com.example.sisvitafrontend.api.requests.ConsignacionRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ConsignacionApi {
    @POST("api/consignacion")
    suspend fun saveConsignacion(@Body consignacionRequest: ConsignacionRequest, @Query("idtest") idTest: Long):
            Response<String>
}