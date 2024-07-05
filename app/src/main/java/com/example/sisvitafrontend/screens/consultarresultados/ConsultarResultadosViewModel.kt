package com.example.sisvitafrontend.screens.consultarresultados

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisvitafrontend.config.datastore.DataStoreManager
import com.example.sisvitafrontend.config.retrofit.ApiRetrofit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ConsultarResultadosViewModel: ViewModel() {
    private val resolvedTestApi = ApiRetrofit.resolvedTestApi

    private val _listTest = MutableLiveData<List<ResolvedTestResponseViewPatient>>()
    val listTest: LiveData<List<ResolvedTestResponseViewPatient>> = _listTest
    
    fun getResolvedTest(dataStoreManager: DataStoreManager) {
        viewModelScope.launch {
            try {
                var id = -1
                dataStoreManager.id.first()?.let {
                    id = it.toInt()
                }

                if (id == -1) {
                    return@launch
                }

                val response = resolvedTestApi.getResolvedTestByIdPatient(id)

                if (response.isSuccessful) {
                    _listTest.value = response.body()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}