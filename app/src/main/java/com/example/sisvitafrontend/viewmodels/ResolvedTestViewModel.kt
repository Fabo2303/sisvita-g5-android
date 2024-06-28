package com.example.sisvitafrontend.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisvitafrontend.api.datastore.DataStoreManager
import com.example.sisvitafrontend.api.requests.AnswerRequest
import com.example.sisvitafrontend.api.requests.ResolvedTestRequest
import com.example.sisvitafrontend.network.ApiRetrofit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.sql.Date
import java.util.Locale

class ResolvedTestViewModel : ViewModel() {
    private val resolvedTestApi = ApiRetrofit.resolvedTestApi

    private val _answersRequest = MutableLiveData<List<AnswerRequest>>()
    val answersRequest: LiveData<List<AnswerRequest>> = _answersRequest

    private val _result = MutableLiveData<Int>()
    val result: LiveData<Int> = _result

    private val _interpretation = MutableLiveData<String>()
    val interpretation: LiveData<String> = _interpretation

    fun setAnswersRequest(answers: List<AnswerRequest>) {
        _answersRequest.value = answers
    }

    fun sendResolvedTest(templateTestId: Long, dataStoreManager: DataStoreManager) {
        viewModelScope.launch {
            try{
                val answers = _answersRequest.value
                if (answers != null) {
                    val resolvedTestRequest = dataStoreManager.id.first()?.let {
                        ResolvedTestRequest(
                            result = 0,
                            date = Date(System.currentTimeMillis()),
                            templateTestId = templateTestId,
                            classificationId = 0,
                            answers = answers,
                            idPaciente = it.toLong()
                        )
                    }
                    if (resolvedTestRequest != null) {
                        val response = resolvedTestApi.sendResolvedTest(resolvedTestRequest)
                        if (response.isSuccessful) {
                            val resolvedTestResponse = response.body()
                            if (resolvedTestResponse != null) {
                                _result.value = resolvedTestResponse.result
                                _interpretation.value = resolvedTestResponse.interpretation
                            }
                        }
                    }
                }
            }catch (
                e: Exception
            ){
                e.printStackTrace()
            }
        }
    }
}