package com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.data.service

import com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.data.request.ConsignacionRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ConsignationApi {
    @POST("api/consignation/insert")
    suspend fun saveConsignacion(@Body consignacionRequest: ConsignacionRequest): Response<String>
}