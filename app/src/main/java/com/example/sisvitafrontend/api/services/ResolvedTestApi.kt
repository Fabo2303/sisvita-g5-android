package com.example.sisvitafrontend.api.services

import com.example.sisvitafrontend.api.requests.ResolvedTestRequest
import com.example.sisvitafrontend.api.responses.ResolveTestResponseHeatMap
import com.example.sisvitafrontend.api.responses.ResolvedTestResponse
import com.example.sisvitafrontend.api.responses.ResolvedTestResponseDataPatient
import com.example.sisvitafrontend.api.responses.ResolvedTestResponseTableFormat
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ResolvedTestApi {
    @POST("api/resolved-test/request")
    suspend fun sendResolvedTest(@Body resolvedTestRequest: ResolvedTestRequest):
            Response<ResolvedTestResponse>

    @GET("api/resolved-test")
    suspend fun getResolvedTest(): Response<List<ResolvedTestResponse>>

    @GET("api/resolved-test/response")
    suspend fun getResolvedTestResponse(): Response<List<ResolvedTestResponseTableFormat>>

    @GET("api/resolved-test/findById/dto/{id}")
    suspend fun getResolvedTestById(@Path("id") id: Long): Response<ResolvedTestResponseDataPatient>

    @GET("api/resolved-test/findByTemplateTestName")
    suspend fun getResolvedTestByTemplateTestName(@Query("name") templateTestName: String):
            Response<List<ResolvedTestResponseTableFormat>>

    @GET("api/resolved-test/findByClassificationInterpretation")
    suspend fun getResolvedTestByClassificationInterpretation(
        @Query("interpretation")
        interpretation: String
    ):
            Response<List<ResolvedTestResponseTableFormat>>

    @GET("api/resolved-test/findByTemplateTestNameAndClassificationInterpretation")
    suspend fun getResolvedTestByTemplateTestNameAndClassificationInterpretation(
        @Query("name")
        templateTestName: String,
        @Query("interpretation")
        interpretation: String
    ):
            Response<List<ResolvedTestResponseTableFormat>>

    @GET("api/resolved-test/findByDateBetween")
    suspend fun getResolvedTestByDateBetween(
        @Query("fechaInicio")
        initialDate: String,
        @Query("fechaFin")
        finalDate: String
    ):
            Response<List<ResolvedTestResponseTableFormat>>

    @GET("api/resolved-test/findByDateBetweenAndTemplateTestName")
    suspend fun getResolvedTestByDateBetweenAndTemplateTestName(
        @Query("fechaInicio")
        initialDate: String,
        @Query("fechaFin")
        finalDate: String,
        @Query("name")
        templateTestName: String
    ):
            Response<List<ResolvedTestResponseTableFormat>>

    @GET("api/resolved-test/findByDateBetweenAndClassificationInterpretation")
    suspend fun getResolvedTestByDateBetweenAndClassificationInterpretation(
        @Query("fechaInicio")
        initialDate: String,
        @Query("fechaFin")
        finalDate: String,
        @Query("interpretation")
        interpretation: String
    ):
            Response<List<ResolvedTestResponseTableFormat>>

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
            Response<List<ResolvedTestResponseTableFormat>>

    @GET("api/resolved-test/heat-map")
    suspend fun getResolvedTestHeatMap(): Response<List<ResolveTestResponseHeatMap>>

    @GET("api/resolved-test/findByDateBetweenHeatMap")
    suspend fun getResolvedTestByDateBetweenHeatMap(
        @Query("fechaInicio")
        initialDate: String,
        @Query("fechaFin")
        finalDate: String
    ):
            Response<List<ResolveTestResponseHeatMap>>

    @GET("api/resolved-test/findByTemplateTestNameHeatMap")
    suspend fun getResolvedTestByTemplateTestNameHeatMap(
        @Query("name")
        templateTestName: String
    ):
            Response<List<ResolveTestResponseHeatMap>>

    @GET("api/resolved-test/findByClassificationInterpretationHeatMap")
    suspend fun getResolvedTestByClassificationInterpretationHeatMap(
        @Query("interpretation")
        interpretation: String
    ):
            Response<List<ResolveTestResponseHeatMap>>

    @GET("api/resolved-test/findByTemplateTestNameAndClassificationInterpretationHeatMap")
    suspend fun getResolvedTestByTemplateTestNameAndClassificationInterpretationHeatMap(
        @Query("name")
        templateTestName: String,
        @Query("interpretation")
        interpretation: String
    ):
            Response<List<ResolveTestResponseHeatMap>>

    @GET("api/resolved-test/findByDateBetweenAndTemplateTestNameHeatMap")
    suspend fun getResolvedTestByDateBetweenAndTemplateTestNameHeatMap(
        @Query("fechaInicio")
        initialDate: String,
        @Query("fechaFin")
        finalDate: String,
        @Query("name")
        templateTestName: String
    ):
            Response<List<ResolveTestResponseHeatMap>>

    @GET("api/resolved-test/findByDateBetweenAndClassificationInterpretationHeatMap")
    suspend fun getResolvedTestByDateBetweenAndClassificationInterpretationHeatMap(
        @Query("fechaInicio")
        initialDate: String,
        @Query("fechaFin")
        finalDate: String,
        @Query("interpretation")
        interpretation: String
    ):
            Response<List<ResolveTestResponseHeatMap>>

    @GET("api/resolved-test/findByDateBetweenAndTemplateTestNameAndClassificationInterpretationHeatMap")
    suspend fun getResolvedTestByDateBetweenAndTemplateTestNameAndClassificationInterpretationHeatMap(
        @Query("fechaInicio")
        initialDate: String,
        @Query("fechaFin")
        finalDate: String,
        @Query("name")
        templateTestName: String,
        @Query("interpretation")
        interpretation: String
    ):
            Response<List<ResolveTestResponseHeatMap>>

}