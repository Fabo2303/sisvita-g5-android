package com.example.sisvitafrontend.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.api.datastore.DataStoreManager
import com.example.sisvitafrontend.api.requests.AnswerRequest
import com.example.sisvitafrontend.api.requests.ResolvedTestRequest
import com.example.sisvitafrontend.api.responses.ResolvedTestResponse
import com.example.sisvitafrontend.navigation.ContextAplication
import com.example.sisvitafrontend.network.ApiRetrofit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.sql.Date

class ResolvedTestViewModel : ViewModel() {
    private val resolvedTestApi = ApiRetrofit.resolvedTestApi

    private val _answersRequest = MutableLiveData<List<AnswerRequest>>()
    val answersRequest: LiveData<List<AnswerRequest>> = _answersRequest

    private val _title = MutableLiveData("")
    val title: LiveData<String> = _title

    private val _message = MutableLiveData("")
    val message: LiveData<String> = _message

    private val _color = MutableLiveData(R.color.black_900)
    val color: LiveData<Int> = _color

    fun setAnswersRequest(answers: List<AnswerRequest>) {
        _answersRequest.value = answers
    }

    fun sendResolvedTest(
        templateTestId: Long, dataStoreManager: DataStoreManager, questionsSize:
        Int
    ) {
        val context: Context = ContextAplication.applicationContext()
        viewModelScope.launch {
            try {
                if (_answersRequest.value.isNullOrEmpty() || _answersRequest.value?.size != questionsSize) {
                    _title.value = context.getString(R.string.error)
                    _message.value = context.getString(R.string.please_mark_all_questions)
                    _answersRequest.value = emptyList()
                    return@launch
                }

                val answers = _answersRequest.value

                if (answers != null) {
                    val resolvedTestRequest = dataStoreManager.id.first()?.let {
                        buildResolvedTestRequest(templateTestId, answers, it)
                    }

                    _title.value = context.getString(R.string.send_test_in_progress)
                    _message.value = context.getString(R.string.loading)

                    if (resolvedTestRequest != null) {
                        val response = resolvedTestApi.sendResolvedTest(resolvedTestRequest)

                        if (response.isSuccessful) {
                            val resolvedTestResponse = response.body()
                            if (resolvedTestResponse != null) {
                                _title.value = context.getString(R.string.send_test_success)
                                _message.value = resolvedTestResponse.interpretation.uppercase()
                                _color.value = getColor(resolvedTestResponse)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                _title.value = context.getString(R.string.error)
                _message.value = context.getString(R.string.send_test_error)
                //_message.value = e.message
            }
        }
    }

    private fun buildResolvedTestRequest(
        templateTestId: Long, answers: List<AnswerRequest>, id:
        String
    ): ResolvedTestRequest {
        val resolvedTestRequest = ResolvedTestRequest(
            date = Date(System.currentTimeMillis()),
            templateTestId = templateTestId,
            answers = answers,
            idPaciente = id.toLong()
        )

        return resolvedTestRequest
    }

    private fun getColor(resolvedTestResponse: ResolvedTestResponse): Int {
        val context: Context = ContextAplication.applicationContext()
        val green = context.getString(R.string.green)
        val yellow = context.getString(R.string.yellow)
        val red = context.getString(R.string.red)
        val color = when (resolvedTestResponse.color) {
            green -> R.color.green_900
            yellow -> R.color.yellow_900
            red -> R.color.red_900
            else -> R.color.black_900
        }
        return color
    }
}