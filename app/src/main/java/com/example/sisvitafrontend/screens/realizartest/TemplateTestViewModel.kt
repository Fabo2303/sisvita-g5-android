package com.example.sisvitafrontend.screens.realizartest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisvitafrontend.screens.realizartest.data.TemplateTestResponse
import com.example.sisvitafrontend.config.retrofit.ApiRetrofit
import kotlinx.coroutines.launch

class TemplateTestViewModel: ViewModel() {
    private val templateTestApi = ApiRetrofit.templateTestApi

    private val _templateTests = MutableLiveData<List<TemplateTestResponse>>()
    val templateTests: LiveData<List<TemplateTestResponse>> = _templateTests

    private val _templateTest = MutableLiveData<TemplateTestResponse>()
    val templateTest: LiveData<TemplateTestResponse> = _templateTest

     fun getTemplateTest() {
        viewModelScope.launch {
            try {
                val response = templateTestApi.getTipoTest()
                _templateTests.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
     fun getTemplateTestById(id: Long){
        viewModelScope.launch {
            try {
                val response = templateTestApi.getTipoTestById(id)
                _templateTest.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}