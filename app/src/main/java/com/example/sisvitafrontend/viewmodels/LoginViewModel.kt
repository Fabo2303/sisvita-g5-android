package com.example.sisvitafrontend.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.auth0.android.jwt.JWT
import com.example.sisvitafrontend.api.datastore.DataStoreManager
import com.example.sisvitafrontend.api.requests.LoginRequest
import com.example.sisvitafrontend.api.responses.LoginResponse
import com.example.sisvitafrontend.navigation.Screen
import com.example.sisvitafrontend.network.ApiRetrofit
import com.example.sisvitafrontend.network.NetworkResponse
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class LoginViewModel : ViewModel() {

    private val loginApi = ApiRetrofit.loginApi

    private val _user = MutableLiveData("")
    val user: LiveData<String> = _user

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    fun onUserChanged(newEmail: String) {
        _user.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun onLoginClicked(dataStoreManager: DataStoreManager, navController: NavController) {
        viewModelScope.launch {
            try {
                val response = loginApi.login(LoginRequest(user.value!!, password.value!!))
                if (response.isSuccessful){
                    response.body()?.let {
                        val jwt = it.jwt
                        if (jwt.isNotEmpty()){
                            dataStoreManager.saveJwt(jwt)
                            val decodedJwt = JWT(jwt)
                            val role = decodedJwt.getClaim("role").asString()
                            val id = decodedJwt.getClaim("id").asString()
                            if (role != null) {
                                dataStoreManager.saveRole(role)
                                if (id != null) {
                                    dataStoreManager.saveId(id)
                                    if (role == "SPECIALIST")
                                        navController.navigate(Screen.MenuSpecialistScreen.route)
                                    else
                                        navController.navigate(Screen.MenuPatientScreen.route)
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.i("LoginViewModel", e.message.toString())
            }
        }
    }
}