package com.example.sisvitafrontend.screens.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisvitafrontend.config.retrofit.ApiRetrofit
import kotlinx.coroutines.launch

class UbigeoViewModel : ViewModel() {
    private val ubigeoApi = ApiRetrofit.ubigeoApi

    private val _departamentos = MutableLiveData<List<String>>()
    val departamentos: LiveData<List<String>> = _departamentos

    private val _provincias = MutableLiveData<List<String>>()
    val provincias: LiveData<List<String>> = _provincias

    private val _distritos = MutableLiveData<List<String>>()
    val distritos: LiveData<List<String>> = _distritos

    init {
        getDepartamentos()
    }

    fun getDepartamentos() {
        viewModelScope.launch {
            try {
                val response = ubigeoApi.getDepartamentos()
                _departamentos.value = response.departments
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getProvincias(departamento: String) {
        viewModelScope.launch {
            try {
                val response = ubigeoApi.getProvincias(departamento)
                _provincias.value = response.provinces
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getDistritos(departamento: String, provincia: String) {
        viewModelScope.launch {
            try {
                val response = ubigeoApi.getDistritos(departamento, provincia)
                _distritos.value = response.districts
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
