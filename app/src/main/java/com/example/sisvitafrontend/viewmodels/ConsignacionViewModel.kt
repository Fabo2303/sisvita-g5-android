package com.example.sisvitafrontend.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisvitafrontend.api.requests.ConsignacionRequest
import com.example.sisvitafrontend.network.ApiRetrofit
import kotlinx.coroutines.launch
import java.sql.Date

class ConsignacionViewModel: ViewModel() {
    private val consignacionApi = ApiRetrofit.consignacionApi

    private val _date = MutableLiveData(Date(System.currentTimeMillis()))
    val date: LiveData<Date> = _date

    private val _newInterpretation = MutableLiveData("")
    val newInterpretation: LiveData<String> = _newInterpretation

    private val _observation = MutableLiveData("")
    val observation: LiveData<String> = _observation

    private val _message = MutableLiveData("")
    val message: LiveData<String> = _message

    private val _medicalAppointmentRequested = MutableLiveData(false)
    val medicalAppointmentRequested: LiveData<Boolean> = _medicalAppointmentRequested

    fun setDate(value: Date) {
        _date.value = value
    }

    fun setNewInterpretation(value: String) {
        _newInterpretation.value = value
    }

    fun setObservation(value: String) {
        _observation.value = value
    }

    fun setMessage(value: String) {
        _message.value = value
    }

    fun setMedicalAppointmentRequested(value: Boolean) {
        _medicalAppointmentRequested.value = value
    }

    fun saveConsignacion(idtest: Long) {
        viewModelScope.launch {
            try {
                val response = consignacionApi.saveConsignacion(buildConsignacionRequest(), idtest)
                Log.i("ConsignacionViewModel", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun buildConsignacionRequest(): ConsignacionRequest {
        return ConsignacionRequest(
            date = date.value!!,
            newInterpretation = newInterpretation.value!!,
            observation = observation.value!!,
            message = message.value!!,
            medicalAppointmentRequested = medicalAppointmentRequested.value!!
        )
    }
}