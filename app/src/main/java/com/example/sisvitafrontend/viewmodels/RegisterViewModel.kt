package com.example.sisvitafrontend.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisvitafrontend.api.requests.PatientRequest
import com.example.sisvitafrontend.api.requests.PersonaRequest
import com.example.sisvitafrontend.api.requests.UbigeoRequest
import com.example.sisvitafrontend.api.requests.UserRequest
import com.example.sisvitafrontend.api.responses.RegisterResponse
import com.example.sisvitafrontend.network.ApiRetrofit
import com.example.sisvitafrontend.network.NetworkResponse
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.sql.Date

class RegisterViewModel: ViewModel(){

    private val registerApi = ApiRetrofit.registerApi

    private val _user = MutableLiveData("")
    val user: LiveData<String> = _user

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _document = MutableLiveData("")
    val document: LiveData<String> = _document

    private val _documentType = MutableLiveData("")
    val documentType: LiveData<String> = _documentType

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _lastName = MutableLiveData("")
    val lastName: LiveData<String> = _lastName

    private val _secondLastName = MutableLiveData("")
    val secondLastName: LiveData<String> = _secondLastName

    private val _birthdate = MutableLiveData(Date(System.currentTimeMillis()))
    val birthdate: LiveData<Date> = _birthdate

    private val _sex = MutableLiveData("")
    val sex: LiveData<String> = _sex

    private val _phone = MutableLiveData("")
    val phone: LiveData<String> = _phone

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _departamento = MutableLiveData("")
    val departamento: LiveData<String> = _departamento

    private val _provincia = MutableLiveData("")
    val provincia: LiveData<String> = _provincia

    private val _distrito = MutableLiveData("")
    val distrito: LiveData<String> = _distrito

    fun onUserChanged(newEmail: String) {
        _user.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun onDocumentChanged(newDocument: String) {
        _document.value = newDocument
    }

    fun onDocumentTypeChanged(newDocumentType: String) {
        _documentType.value = newDocumentType
    }

    fun onNameChanged(newName: String) {
        _name.value = newName
    }

    fun onLastNameChanged(newLastName: String) {
        _lastName.value = newLastName
    }

    fun onSecondLastNameChanged(newSecondLastName: String) {
        _secondLastName.value = newSecondLastName
    }

    fun onBirthdateChanged(newBirthdate: Date) {
        _birthdate.value = newBirthdate
    }

    fun onSexChanged(newSex: String) {
        _sex.value = newSex
    }

    fun onPhoneChanged(newPhone: String) {
        _phone.value = newPhone
    }

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onDepartamentoChanged(newDepartamento: String) {
        _departamento.value = newDepartamento
    }

    fun onProvinciaChanged(newProvincia: String) {
        _provincia.value = newProvincia
    }

    fun onDistritoChanged(newDistrito: String) {
        _distrito.value = newDistrito
    }

    fun onRegisterClicked() {
        viewModelScope.launch {
            try{
                val response = registerApi.register(buildPatientRequest())
                if (response.isSuccessful){
                    response.body()?.let {
                        val registerResponse = it
                        Log.i("RegisterViewModel", registerResponse.message)
                    }
                }
            }catch (e: Exception) {
                Log.i("RegisterViewModel", e.message.toString())
            }
        }
    }

    private fun buildPatientRequest(): PatientRequest{
        val ubigeoRequest = UbigeoRequest(
            departamento = departamento.value!!,
            provincia = provincia.value!!,
            distrito = distrito.value!!
        )
        val personaRequest = PersonaRequest(
            document = document.value!!,
            documentType = documentType.value!!,
            name = name.value!!,
            lastName = lastName.value!!,
            secondLastName = secondLastName.value!!,
            birthDate = birthdate.value!!,
            sex = sex.value!!,
            phone = phone.value!!,
            email = email.value!!,
            ubigeoRequest = ubigeoRequest
        )
        val userRequest = UserRequest(
            password = password.value!!,
            personaRequest = personaRequest,
            role = "PATIENT",
            username = user.value!!
        )
        val patientRequest = PatientRequest(
            userRequest = userRequest
        )

        return patientRequest
    }
}