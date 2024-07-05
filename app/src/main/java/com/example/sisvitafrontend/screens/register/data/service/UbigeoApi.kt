package com.example.sisvitafrontend.screens.register.data.service

import com.example.sisvitafrontend.screens.register.data.response.UbigeosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UbigeoApi {
    @GET("api/ubigeo/departments")
    suspend fun getDepartamentos(): UbigeosResponse

    @GET("api/ubigeo/provinces")
    suspend fun getProvincias(@Query("department") department: String): UbigeosResponse

    @GET("api/ubigeo/districts")
    suspend fun getDistritos(
        @Query("department") department: String,
        @Query("province") province: String
    ): UbigeosResponse
}