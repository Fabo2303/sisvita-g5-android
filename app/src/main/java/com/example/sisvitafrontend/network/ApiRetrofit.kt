package com.example.sisvitafrontend.network

import com.example.sisvitafrontend.api.services.ConsignacionApi
import com.example.sisvitafrontend.api.services.DiagnosticApi
import com.example.sisvitafrontend.api.services.LoginApi
import com.example.sisvitafrontend.api.services.RegisterApi
import com.example.sisvitafrontend.api.services.ResolvedTestApi
import com.example.sisvitafrontend.api.services.TemplateTestApi
import com.example.sisvitafrontend.api.services.TreatmentApi
import com.example.sisvitafrontend.api.services.UbigeoApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiRetrofit {
     private const val BASE_URL = "http://10.0.2.2:8080/"
    // private const val BASE_URL = "http://192.168.0.4:8080/"

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

    val loginApi : LoginApi = getRetrofit().create(LoginApi::class.java)
    val registerApi : RegisterApi = getRetrofit().create(RegisterApi::class.java)
    val ubigeoApi : UbigeoApi = getRetrofit().create(UbigeoApi::class.java)
    val templateTestApi: TemplateTestApi = getRetrofit().create(TemplateTestApi::class.java)
    val resolvedTestApi: ResolvedTestApi = getRetrofit().create(ResolvedTestApi::class.java)
    val consignacionApi: ConsignacionApi = getRetrofit().create(ConsignacionApi::class.java)
    val treatmentApi = getRetrofit().create(TreatmentApi::class.java)
    val diagnosticApi = getRetrofit().create(DiagnosticApi::class.java)
}