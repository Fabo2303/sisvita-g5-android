package com.example.sisvitafrontend.screens.realizarvigilancia.realizarvigilanciatable

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.data.response.ResolvedTestResponseDataPatient
import com.example.sisvitafrontend.screens.realizarvigilancia.realizarvigilanciatable.data.ResolvedTestResponseTableFormat
import com.example.sisvitafrontend.config.retrofit.ApiRetrofit
import kotlinx.coroutines.launch
import java.sql.Date

class RealizarVigilanciaViewModel: ViewModel() {
    private val resolvedTestApi = ApiRetrofit.resolvedTestApi

    private val _listTest = MutableLiveData<List<ResolvedTestResponseTableFormat>>()
    val listTest: LiveData<List<ResolvedTestResponseTableFormat>> get() = _listTest

    val _testResponse = MutableLiveData<ResolvedTestResponseDataPatient>()
    val testResponse: LiveData<ResolvedTestResponseDataPatient> get() = _testResponse


    init {
        getResolvedTestResponse()
    }

    fun getResolvedTestResponse(){
        viewModelScope.launch {
            try {
                val response = resolvedTestApi.getResolvedTestResponse()
                _listTest.value = response.body()
                Log.i("RealizarVigilanciaViewModel", "getResolvedTestResponse: ${response.body()?.get(0)}")
            } catch (e: Exception) {
                Log.e("RealizarVigilanciaViewModel", "getResolvedTestResponse: ${e.message}")
            }
        }
    }

    fun getResolvedTestById(id: Long){
        viewModelScope.launch {
            try {
                val response = resolvedTestApi.getResolvedTestById(id)
                _testResponse.value = response.body()
                Log.i("RealizarVigilanciaViewModel", "id: $id")
                Log.i("RealizarVigilanciaViewModel", "getResolvedTestById: ${response.body()}")
            } catch (e: Exception) {
                Log.e("RealizarVigilanciaViewModel", "getResolvedTestById: ${e.message}")
            }
        }
    }

    fun getResolvedTestByFilter(tipoTest: String, fechaInicial: Date, fechaFinal: Date, ansiedad:
    String, textDate: String){
        if (tipoTest.isNotEmpty() && textDate.isEmpty() && ansiedad.isEmpty()) {
            getResolvedTestByTemplateTestName(tipoTest)
        } else if (tipoTest.isEmpty() && textDate.isNotEmpty() && ansiedad.isEmpty()) {
            getResolvedTestByDateBetween(fechaInicial, fechaFinal)
        } else if (tipoTest.isEmpty() && textDate.isEmpty() && ansiedad.isNotEmpty()) {
            getResolvedTestByClassificationInterpretation(ansiedad)
        } else if (tipoTest.isNotEmpty() && textDate.isNotEmpty() && ansiedad.isEmpty()) {
            getResolvedTestByDateBetweenAndTemplateTestName(fechaInicial, fechaFinal, tipoTest)
        } else if (tipoTest.isEmpty() && textDate.isNotEmpty() && ansiedad.isNotEmpty()) {
            getResolvedTestByDateBetweenAndClassificationInterpretation(fechaInicial, fechaFinal, ansiedad)
        } else if (tipoTest.isNotEmpty() && textDate.isEmpty() && ansiedad.isNotEmpty()) {
            getResolvedTestByTemplateTestNameAndClassificationInterpretation(tipoTest, ansiedad)
        } else if (tipoTest.isNotEmpty() && textDate.isNotEmpty() && ansiedad.isNotEmpty()) {
            getResolvedTestByDateBetweenAndTemplateTestNameAndClassificationInterpretation(fechaInicial, fechaFinal, tipoTest, ansiedad)
        }
    }

    private fun getResolvedTestByTemplateTestName(tipoTest: String) {
        viewModelScope.launch {
            try {
                val response = resolvedTestApi.getResolvedTestByTemplateTestName(tipoTest)
                _listTest.value = response.body()
                Log.i("RealizarVigilanciaViewModel", "getResolvedTestByTipoTest: ${response.body()?.get(0)}")
            } catch (e: Exception) {
                Log.e("RealizarVigilanciaViewModel", "getResolvedTestByTipoTest: ${e.message}")
            }
        }
    }

    private fun getResolvedTestByDateBetweenAndTemplateTestName(fechaInicial: Date, fechaFinal: Date, tipoTest: String) {
        viewModelScope.launch {
            try {
                val response = resolvedTestApi.getResolvedTestByDateBetweenAndTemplateTestName(fechaInicial.toString(), fechaFinal.toString(), tipoTest)
                _listTest.value = response.body()
                Log.i("RealizarVigilanciaViewModel", "getResolvedTestByDateBetweenAndTemplateTestName: ${response.body()?.get(0)}")
            } catch (e: Exception) {
                Log.e("RealizarVigilanciaViewModel", "getResolvedTestByDateBetweenAndTemplateTestName: ${e.message}")
            }
        }
    }

    private fun getResolvedTestByDateBetween(fechaInicial: Date, fechaFinal: Date) {
        viewModelScope.launch {
            try {
                val response = resolvedTestApi.getResolvedTestByDateBetween(fechaInicial.toString(), fechaFinal.toString())
                _listTest.value = response.body()
                Log.i("RealizarVigilanciaViewModel", "getResolvedTestByDateBetween: ${response.body()?.get(0)}")
            } catch (e: Exception) {
                Log.e("RealizarVigilanciaViewModel", "getResolvedTestByDateBetween: ${e.message}")
            }
        }
    }

    private fun getResolvedTestByClassificationInterpretation(ansiedad: String) {
        viewModelScope.launch {
            try {
                val response = resolvedTestApi.getResolvedTestByClassificationInterpretation(ansiedad)
                _listTest.value = response.body()
                Log.i("RealizarVigilanciaViewModel", "getResolvedTestByClassificationInterpretation: ${response.body()?.get(0)}")
            } catch (e: Exception) {
                Log.e("RealizarVigilanciaViewModel", "getResolvedTestByClassificationInterpretation: ${e.message}")
            }
        }
    }

    private fun getResolvedTestByDateBetweenAndClassificationInterpretation(fechaInicial: Date, fechaFinal: Date, ansiedad: String) {
        viewModelScope.launch {
            try {
                val response = resolvedTestApi.getResolvedTestByDateBetweenAndClassificationInterpretation(fechaInicial.toString(), fechaFinal.toString(), ansiedad)
                _listTest.value = response.body()
                Log.i("RealizarVigilanciaViewModel", "getResolvedTestByDateBetweenAndClassificationInterpretation: ${response.body()?.get(0)}")
            } catch (e: Exception) {
                Log.e("RealizarVigilanciaViewModel", "getResolvedTestByDateBetweenAndClassificationInterpretation: ${e.message}")
            }
        }
    }

    private fun getResolvedTestByTemplateTestNameAndClassificationInterpretation(tipoTest: String, ansiedad: String) {
        viewModelScope.launch {
            try {
                val response = resolvedTestApi.getResolvedTestByTemplateTestNameAndClassificationInterpretation(tipoTest, ansiedad)
                _listTest.value = response.body()
                Log.i("RealizarVigilanciaViewModel", "getResolvedTestByTemplateTestNameAndClassificationInterpretation: ${response.body()?.get(0)}")
            } catch (e: Exception) {
                Log.e("RealizarVigilanciaViewModel", "getResolvedTestByTemplateTestNameAndClassificationInterpretation: ${e.message}")
            }
        }
    }

    private fun getResolvedTestByDateBetweenAndTemplateTestNameAndClassificationInterpretation(fechaInicial: Date, fechaFinal: Date, tipoTest: String, ansiedad: String) {
        viewModelScope.launch {
            try {
                val response = resolvedTestApi.getResolvedTestByDateBetweenAndTemplateTestNameAndClassificationInterpretation(fechaInicial.toString(), fechaFinal.toString(), tipoTest, ansiedad)
                _listTest.value = response.body()
                Log.i("RealizarVigilanciaViewModel", "getResolvedTestByDateBetweenAndTemplateTestNameAndClassificationInterpretation: ${response.body()?.get(0)}")
            } catch (e: Exception) {
                Log.e("RealizarVigilanciaViewModel", "getResolvedTestByDateBetweenAndTemplateTestNameAndClassificationInterpretation: ${e.message}")
            }
        }
    }


}