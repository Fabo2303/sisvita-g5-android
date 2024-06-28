package com.example.sisvitafrontend.api.services

import com.example.sisvitafrontend.api.requests.ResolvedTestRequest
import com.example.sisvitafrontend.api.responses.ResolvedTestResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ResolvedTestApi {
    @POST("api/resolved-test")
    suspend fun sendResolvedTest(@Body resolvedTestRequest: ResolvedTestRequest):
            Response<ResolvedTestResponse>

    @GET("api/resolved-test")
    suspend fun getResolvedTest(): Response<List<ResolvedTestResponse>>

    @GET("api/resolved-test/findByTemplateTestName")
    suspend fun getResolvedTestByTemplateTestName(@Query("name") templateTestName: String):
            Response<List<ResolvedTestResponse>>

    @GET("api/resolved-test/findByClassificationInterpretation")
    suspend fun getResolvedTestByClassificationInterpretation(
        @Query("interpretation")
        interpretation: String
    ):
            Response<List<ResolvedTestResponse>>

    @GET("api/resolved-test/findByTemplateTestNameAndClassificationInterpretation")
    suspend fun getResolvedTestByTemplateTestNameAndClassificationInterpretation(
        @Query("name")
        templateTestName: String,
        @Query("interpretation")
        interpretation: String
    ):
            Response<List<ResolvedTestResponse>>

    @GET("api/resolved-test/findByDateBetween")
    suspend fun getResolvedTestByDateBetween(
        @Query("fechaInicio")
        initialDate: String,
        @Query("fechaFin")
        finalDate: String
    ):
            Response<List<ResolvedTestResponse>>

    @GET("api/resolved-test/findByDateBetweenAndTemplateTestName")
    suspend fun getResolvedTestByDateBetweenAndTemplateTestName(
        @Query("fechaInicio")
        initialDate: String,
        @Query("fechaFin")
        finalDate: String,
        @Query("name")
        templateTestName: String
    ):
            Response<List<ResolvedTestResponse>>

    @GET("api/resolved-test/findByDateBetweenAndClassificationInterpretation")
    suspend fun getResolvedTestByDateBetweenAndClassificationInterpretation(
        @Query("fechaInicio")
        initialDate: String,
        @Query("fechaFin")
        finalDate: String,
        @Query("interpretation")
        interpretation: String
    ):
            Response<List<ResolvedTestResponse>>

    @GET("api/resolved-test/findByDateBetweenAndTemplateTestNameAndClassificationInterpretation")
    suspend fun getResolvedTestByDateBetweenAndTemplateTestNameAndClassificationInterpretation(
        @Query("fechaInicio")
        initialDate: String,
        @Query("fechaFin")
        finalDate: String,
        @Query("name")
        templateTestName: String,
        @Query("interpretation")
        interpretation: String
    ):
            Response<List<ResolvedTestResponse>>

}