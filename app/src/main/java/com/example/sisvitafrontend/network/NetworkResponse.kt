package com.example.sisvitafrontend.network

sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Error(val error: String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}