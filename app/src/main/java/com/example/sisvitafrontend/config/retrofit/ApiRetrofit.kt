package com.example.sisvitafrontend.config.retrofit

import com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.data.service.ConsignationApi
import com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.data.service.DiagnosisApi
import com.example.sisvitafrontend.screens.login.data.LoginApi
import com.example.sisvitafrontend.screens.register.data.service.RegisterApi
import com.example.sisvitafrontend.screens.realizartest.data.ResolvedTestApi
import com.example.sisvitafrontend.screens.realizartest.data.TemplateTestApi
import com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.data.service.TreatmentApi
import com.example.sisvitafrontend.screens.register.data.service.UbigeoApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiRetrofit {
    // private const val BASE_URL = "http://10.0.2.2:8080/"
    private const val BASE_URL = "https://sisvita-g5-backend-1.onrender.com/"

    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val loginApi: LoginApi = getRetrofit().create(LoginApi::class.java)
    val registerApi: RegisterApi = getRetrofit().create(RegisterApi::class.java)
    val ubigeoApi: UbigeoApi = getRetrofit().create(UbigeoApi::class.java)
    val templateTestApi: TemplateTestApi = getRetrofit().create(TemplateTestApi::class.java)
    val resolvedTestApi: ResolvedTestApi = getRetrofit().create(ResolvedTestApi::class.java)
    val consignationApi: ConsignationApi = getRetrofit().create(ConsignationApi::class.java)
    val treatmentApi = getRetrofit().create(TreatmentApi::class.java)
    val diagnosisApi = getRetrofit().create(DiagnosisApi::class.java)
}