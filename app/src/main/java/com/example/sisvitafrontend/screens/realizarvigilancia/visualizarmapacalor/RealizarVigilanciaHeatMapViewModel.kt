package com.example.sisvitafrontend.screens.realizarvigilancia.visualizarmapacalor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisvitafrontend.config.retrofit.ApiRetrofit
import kotlinx.coroutines.launch
import java.sql.Date

class RealizarVigilanciaHeatMapViewModel : ViewModel(){
    private val resolvedTestApi = ApiRetrofit.resolvedTestApi

    private val _listTest = MutableLiveData<List<ResolveTestResponseHeatMap>>()
    val listTest: LiveData<List<ResolveTestResponseHeatMap>> get() = _listTest

    init {
        getResolvedTestResponse()
    }

    fun getResolvedTestResponse(){
        viewModelScope.launch {
            try {
                val response = resolvedTestApi.getResolvedTestHeatMap()
                _listTest.value = response.body()
                Log.i("RealizarVigilanciaViewModel", "getResolvedTestResponse: ${response.body()?.get(0)}")
            } catch (e: Exception) {
                Log.e("RealizarVigilanciaViewModel", "getResolvedTestResponse: ${e.message}")
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
                val response = resolvedTestApi.getResolvedTestByTemplateTestNameHeatMap(tipoTest)
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
                val response = resolvedTestApi.getResolvedTestByDateBetweenAndTemplateTestNameHeatMap(fechaInicial.toString(), fechaFinal.toString(), tipoTest)
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
                val response = resolvedTestApi.getResolvedTestByDateBetweenHeatMap(fechaInicial.toString(), fechaFinal.toString())
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
                val response = resolvedTestApi.getResolvedTestByClassificationInterpretationHeatMap(ansiedad)
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
                val response = resolvedTestApi.getResolvedTestByDateBetweenAndClassificationInterpretationHeatMap(fechaInicial.toString(), fechaFinal.toString(), ansiedad)
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
                val response = resolvedTestApi.getResolvedTestByTemplateTestNameAndClassificationInterpretationHeatMap(tipoTest, ansiedad)
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
                val response = resolvedTestApi.getResolvedTestByDateBetweenAndTemplateTestNameAndClassificationInterpretationHeatMap(fechaInicial.toString(), fechaFinal.toString(), tipoTest, ansiedad)
                _listTest.value = response.body()
                Log.i("RealizarVigilanciaViewModel", "getResolvedTestByDateBetweenAndTemplateTestNameAndClassificationInterpretation: ${response.body()?.get(0)}")
            } catch (e: Exception) {
                Log.e("RealizarVigilanciaViewModel", "getResolvedTestByDateBetweenAndTemplateTestNameAndClassificationInterpretation: ${e.message}")
            }
        }
    }
}
