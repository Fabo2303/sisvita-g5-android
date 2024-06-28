package com.example.sisvitafrontend.api.services

import com.example.sisvitafrontend.api.responses.UbigeosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UbigeoApi {
    @GET("api/ubigeo/departamentos")
    suspend fun getDepartamentos(): UbigeosResponse

    @GET("api/ubigeo/provincias")
    suspend fun getProvincias(@Query("departamento") departamento: String): UbigeosResponse

    @GET("api/ubigeo/distritos")
    suspend fun getDistritos(
        @Query("departamento") departamento: String,
        @Query("provincia") provincia: String
    ): UbigeosResponse

    @GET("api/ubigeo/ubigeo")
    suspend fun getUbigeo(
        @Query("departamento") departamento: String,
        @Query("provincia") provincia: String,
        @Query("distrito") distrito: String
    ): UbigeosResponse
}