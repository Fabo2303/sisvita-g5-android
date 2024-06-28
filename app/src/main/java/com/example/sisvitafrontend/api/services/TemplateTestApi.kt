package com.example.sisvitafrontend.api.services

import com.example.sisvitafrontend.api.responses.TemplateTestResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TemplateTestApi {
    @GET("api/template-test/dto")
    suspend fun getTipoTest(): List<TemplateTestResponse>

    @GET("api/template-test/{id}")
    suspend fun getTipoTestById(@Path("id") id: Long): TemplateTestResponse

    @GET("api/template-test/name")
    suspend fun getAllName(): List<String>

    @GET("api/template-test/classification")
    suspend fun getAllClassificationByName(@Query("name") name: String): List<String>
}