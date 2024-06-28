package com.example.sisvitafrontend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sisvitafrontend.network.ApiRetrofit
import kotlinx.coroutines.launch
import java.sql.Date

class FiltrosViewModel: ViewModel() {

    private val templateTestApi = ApiRetrofit.templateTestApi

    private val _tipoTest = mutableStateOf("")
    val tipoTest: State<String> = _tipoTest

    private val _listTipoTest = MutableLiveData<List<String>>()
    val listTipoTest: LiveData<List<String>> = _listTipoTest

    private val _fechaInicial = mutableStateOf(Date(System.currentTimeMillis()))
    val fechaInicial: State<Date> = _fechaInicial

    private val _fechaFinal = mutableStateOf(Date(System.currentTimeMillis()))
    val fechaFinal: State<Date> = _fechaFinal

    private val _ansiedad = mutableStateOf("")
    val ansiedad: State<String> = _ansiedad

    private val _listAnsiedad = MutableLiveData<List<String>>()
    val listAnsiedad: LiveData<List<String>> = _listAnsiedad

    private val _textDate = mutableStateOf("")
    val textDate: State<String> = _textDate


    fun setTipoTest(value: String) {
        _tipoTest.value = value
    }

    fun setFechaInicial(value: Date) {
        _fechaInicial.value = value
    }

    fun setFechaFinal(value: Date) {
        _fechaFinal.value = value
    }

    fun setAnsiedad(value: String) {
        _ansiedad.value = value
    }

    fun setTextDate(value: String) {
        _textDate.value = value
    }

    init {
        getTipoTest()
    }

    fun getTipoTest() {
        viewModelScope.launch {
            try {
                val response = templateTestApi.getAllName()
                _listTipoTest.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getAnsiedad(name: String) {
        viewModelScope.launch {
            try {
                val response = templateTestApi.getAllClassificationByName(name)
                _listAnsiedad.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun clearFilter(){
        _tipoTest.value = ""
        _fechaInicial.value = Date(System.currentTimeMillis())
        _fechaFinal.value = Date(System.currentTimeMillis())
        _ansiedad.value = ""
        _textDate.value = ""
    }
}