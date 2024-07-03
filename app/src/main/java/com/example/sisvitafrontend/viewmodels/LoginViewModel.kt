package com.example.sisvitafrontend.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.auth0.android.jwt.JWT
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.api.datastore.DataStoreManager
import com.example.sisvitafrontend.api.requests.LoginRequest
import com.example.sisvitafrontend.api.responses.LoginResponse
import com.example.sisvitafrontend.navigation.ContextAplication
import com.example.sisvitafrontend.navigation.Screen
import com.example.sisvitafrontend.network.ApiRetrofit
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val loginApi = ApiRetrofit.loginApi

    private val _user = MutableLiveData("")
    val user: LiveData<String> = _user

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _title = MutableLiveData("")
    val title: LiveData<String> = _title

    private val _message = MutableLiveData("")
    val message: LiveData<String> = _message

    private val _role = MutableLiveData("")
    private val role: LiveData<String> = _role

    fun onUserChanged(newEmail: String) {
        _user.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    private fun clearData() {
        _user.value = ""
        _password.value = ""
        _role.value = ""
    }

    fun onLoginClicked(dataStoreManager: DataStoreManager) {
        val context: Context = ContextAplication.applicationContext()
        viewModelScope.launch {
            try {
                if (user.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
                    _title.value = context.getString(R.string.error)
                    _message.value = context.getString(R.string.please_fill_all_fields)
                    return@launch
                }

                val loginRequest = LoginRequest(user.value!!, password.value!!)

                _title.value = context.getString(R.string.login_in_progress)
                _message.value = context.getString(R.string.loading)

                val response = loginApi.login(loginRequest)
                login(response, dataStoreManager, context)
            } catch (e: Exception) {
                _title.value = context.getString(R.string.error)
                _message.value = e.message.toString()
            }

        }
    }

    private suspend fun login(
        response: Response<LoginResponse>,
        dataStoreManager: DataStoreManager,
        context: Context
    ) {
        if (response.isSuccessful) {
            response.body()?.let {
                val jwt = it.jwt
                val decodedJwt = JWT(jwt)
                dataStoreManager.saveJwt(jwt)
                decodedJwt.getClaim("id").asString()
                    ?.let { it1 -> dataStoreManager.saveId(it1) }
                decodedJwt.getClaim("role").asString()
                    ?.let { it1 -> dataStoreManager.saveRole(it1) }
                _title.value = context.getString(R.string.login_success)
                _message.value = context.getString(R.string.welcome_to_sisvita)
                _role.value = decodedJwt.getClaim("role").asString()
            }
        } else {
            val errorBody = JSONObject(response.errorBody()?.string()!!)
            _title.value = context.getString(R.string.error)
            _message.value = errorBody.getString("error")
        }
    }

    fun goToMenu(navController: NavController) {
        val context: Context = ContextAplication.applicationContext()
        if (role.value.isNullOrEmpty()) {
            return
        }
        if (role.value == context.getString(R.string.specialist)) {
            clearData()
            navController.navigate(Screen.MenuSpecialistScreen.route)
        } else {
            clearData()
            navController.navigate(Screen.MenuPatientScreen.route)
        }
    }
}