package com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisvitafrontend.config.datastore.DataStoreManager
import com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.data.response.DiagnosisResponse
import com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.data.response.TreatmentResponse
import com.example.sisvitafrontend.config.retrofit.ApiRetrofit
import com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.data.request.ConsignacionRequest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.sql.Date

class ConsignacionViewModel: ViewModel() {
    private val consignacionApi = ApiRetrofit.consignationApi
    private val treatmentApi = ApiRetrofit.treatmentApi
    private val diagnosticApi = ApiRetrofit.diagnosisApi

    private val _date = MutableLiveData(Date(System.currentTimeMillis()))
    val date: LiveData<Date> = _date

    private val _diagnosticId = MutableLiveData(0L)
    val diagnosticId: LiveData<Long> = _diagnosticId

    private val _diagnosticList = MutableLiveData<List<DiagnosisResponse>>()
    val diagnosticList: LiveData<List<DiagnosisResponse>> = _diagnosticList

    private val _diagnostic = MutableLiveData("")
    val diagnostic: LiveData<String> = _diagnostic

    private val _diagnosticFundament = MutableLiveData("")
    val diagnosticFundament: LiveData<String> = _diagnosticFundament

    private val _treatmentId = MutableLiveData(0L)
    val treatmentId: LiveData<Long> = _treatmentId

    private val _treatmentList = MutableLiveData<List<TreatmentResponse>>()
    val treatmentList: LiveData<List<TreatmentResponse>> = _treatmentList

    private val _treatment = MutableLiveData("")
    val treatment: LiveData<String> = _treatment

    private val _treatmentFundament = MutableLiveData("")
    val treatmentFundament: LiveData<String> = _treatmentFundament

    private val _observation = MutableLiveData("")
    val observation: LiveData<String> = _observation

    private val _fundament = MutableLiveData("")
    val fundament: LiveData<String> = _fundament

    fun setDate(value: Date) {
        _date.value = value
    }

    fun setDiagnosticId(value: Long) {
        _diagnosticId.value = value
    }

    fun setDiagnostic(value: String) {
        _diagnostic.value = value
    }

    fun setDiagnosticFundament(value: String) {
        _diagnosticFundament.value = value
    }

    fun setTreatmentId(value: Long) {
        _treatmentId.value = value
    }

    fun setTreatment(value: String) {
        _treatment.value = value
    }

    fun setTreatmentFundament(value: String) {
        _treatmentFundament.value = value
    }

    fun setObservation(value: String) {
        _observation.value = value
    }

    fun setFundament(value: String) {
        _fundament.value = value
    }

    init {
        getDiagnosticList()
        getTreatmentList()
    }

    fun getDiagnosticList() {
        viewModelScope.launch {
            try {
                val response = diagnosticApi.getDiagnostic()
                _diagnosticList.value = response
            } catch (e: Exception) {
                Log.e("ConsignacionViewModel", "getDiagnosticList: ${e.message}")
            }
        }
    }

    fun getTreatmentList() {
        viewModelScope.launch {
            try {
                val response = treatmentApi.getTreatment()
                _treatmentList.value = response
            } catch (e: Exception) {
                Log.e("ConsignacionViewModel", "getTreatmentList: ${e.message}")
            }
        }
    }

    fun saveConsignacion(testResolvedId: Long, patientId: Long, dataStoreManager: DataStoreManager) {
        viewModelScope.launch {
            try {
                if( diagnosticId.value == 0L || treatmentId.value == 0L) {
                    val diagnosticIdPost = diagnosticApi.postDiagnostic(
                        DiagnosisResponse(
                            id = 0,
                            name = diagnostic.value!!,
                            fundament = diagnosticFundament.value!!
                        )
                    ).id

                    val treatmentIdPost = treatmentApi.postTreatment(
                        TreatmentResponse(
                            id = 0,
                            name = treatment.value!!,
                            fundament = treatmentFundament.value!!
                        )
                    ).id

                    setDiagnosticId(diagnosticIdPost)
                    setTreatmentId(treatmentIdPost)
                }
                val consignacionRequest = dataStoreManager.id.first()?.let {
                    buildConsignacionRequest(
                        testResolvedId,
                        patientId,
                        it.toLong()
                    )
                }
                val response = consignacionApi.saveConsignacion(consignacionRequest!!)
                Log.i("ConsignacionViewModel", "saveConsignacion: ${response.body()}")
            } catch (e: Exception) {
                Log.e("ConsignacionViewModel", "saveConsignacion: ${e.message}")
            }
        }
    }

    private fun buildConsignacionRequest(
        testResolvedId: Long,
        patientId: Long,
        specialisId: Long
    ): ConsignacionRequest {
        return ConsignacionRequest(
            date = date.value!!,
            idDiagnosis = diagnosticId.value!!,
            idTreatment = treatmentId.value!!,
            observation = observation.value!!,
            fundament = fundament.value!!,
            idTestResolved = testResolvedId,
            idSpecialist = specialisId,
            idPatient = patientId
        )
    }
}