package com.example.sisvitafrontend.screens.register

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.config.navigation.ContextAplication
import com.example.sisvitafrontend.screens.register.data.request.PatientRequest
import com.example.sisvitafrontend.screens.register.data.request.PersonaRequest
import com.example.sisvitafrontend.screens.register.data.request.UbigeoRequest
import com.example.sisvitafrontend.screens.register.data.request.UserRequest
import com.example.sisvitafrontend.screens.register.data.response.DocumentResponse
import com.example.sisvitafrontend.screens.register.data.response.GenderResponse
import com.example.sisvitafrontend.config.retrofit.ApiRetrofit
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.sql.Date

class RegisterViewModel : ViewModel() {

    private val registerApi = ApiRetrofit.registerApi

    private val _user = MutableLiveData("")
    val user: LiveData<String> = _user

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _document = MutableLiveData("")
    val document: LiveData<String> = _document

    private val _documentType = MutableLiveData(-1)
    val documentType: LiveData<Int> = _documentType

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _lastName = MutableLiveData("")
    val lastName: LiveData<String> = _lastName

    private val _secondLastName = MutableLiveData("")
    val secondLastName: LiveData<String> = _secondLastName

    private val _birthdate = MutableLiveData(Date(System.currentTimeMillis()))
    val birthdate: LiveData<Date> = _birthdate

    private val _gender = MutableLiveData(-1)
    val gender: LiveData<Int> = _gender

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

    private val _documentList = MutableLiveData<List<DocumentResponse>>()
    val documentList: LiveData<List<DocumentResponse>> = _documentList

    private val _genderList = MutableLiveData<List<GenderResponse>>()
    val genderList: LiveData<List<GenderResponse>> = _genderList

    private val _title = MutableLiveData("")
    val title: LiveData<String> = _title

    private val _message = MutableLiveData("")
    val message: LiveData<String> = _message

    fun onUserChanged(newEmail: String) {
        _user.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun onDocumentChanged(newDocument: String) {
        _document.value = newDocument
    }

    fun onDocumentTypeChanged(newDocumentType: Int) {
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

    fun onGenderChanged(newSex: Int) {
        _gender.value = newSex
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

    fun clearData() {
        _user.value = ""
        _password.value = ""
        _document.value = ""
        _documentType.value = -1
        _name.value = ""
        _lastName.value = ""
        _secondLastName.value = ""
        _birthdate.value = Date(System.currentTimeMillis())
        _gender.value = -1
        _phone.value = ""
        _email.value = ""
        _departamento.value = ""
        _provincia.value = ""
        _distrito.value = ""
    }

    init {
        getDocuments()
        getSex()
    }

    private fun getSex() {
        viewModelScope.launch {
            try {
                val response = registerApi.getSex()
                if (response.isSuccessful) {
                    response.body()?.let {
                        val sexResponse = it
                        _genderList.value = sexResponse
                    }
                }
            } catch (e: Exception) {
                Log.i("RegisterViewModel", e.message.toString())
            }
        }
    }

    private fun getDocuments() {
        viewModelScope.launch {
            try {
                val response = registerApi.getDocuments()
                if (response.isSuccessful) {
                    response.body()?.let {
                        val documentResponse = it
                        _documentList.value = documentResponse
                    }
                }
            } catch (e: Exception) {
                Log.i("RegisterViewModel", e.message.toString())
            }
        }
    }

    fun onRegisterClicked() {
        val context: Context = ContextAplication.applicationContext()
        viewModelScope.launch {
            try {
                if (allIsNotNullOrEmpty()) {
                    _title.value = context.getString(R.string.error)
                    _message.value = context.getString(R.string.please_fill_all_fields)
                    return@launch
                }

                val registerRequest = buildPatientRequest()

                _title.value = context.getString(R.string.register_in_progress)
                _message.value = context.getString(R.string.loading)

                val response = registerApi.register(registerRequest)
                if (response.isSuccessful) {
                    response.body()?.let {
                        val registerResponse = it
                        _title.value = context.getString(R.string.register_success)
                        _message.value = registerResponse.message
                    }
                } else {
                    val errorBody = JSONObject(response.errorBody()?.string()!!)
                    _title.value = context.getString(R.string.error)
                    _message.value = errorBody.getString("error")
                }
            } catch (e: Exception) {
                _title.value = context.getString(R.string.error)
                _message.value = e.message.toString()
            }
        }
    }

    private fun allIsNotNullOrEmpty(): Boolean {
        return user.value.isNullOrEmpty()
                || password.value.isNullOrEmpty()
                || document.value.isNullOrEmpty()
                || documentType.value == -1
                || name.value.isNullOrEmpty()
                || lastName.value.isNullOrEmpty()
                || secondLastName.value.isNullOrEmpty()
                || birthdate.value == null
                || gender.value == -1
                || phone.value.isNullOrEmpty()
                || email.value.isNullOrEmpty()
                || departamento.value.isNullOrEmpty()
                || provincia.value.isNullOrEmpty()
                || distrito.value.isNullOrEmpty()
    }

    private fun buildPatientRequest(): PatientRequest {
        val ubigeoRequest = UbigeoRequest(
            department = departamento.value!!,
            province = provincia.value!!,
            district = distrito.value!!
        )
        val personRequest = PersonaRequest(
            document = document.value!!,
            documentType = documentType.value!! + 1,
            name = name.value!!,
            lastName = lastName.value!!,
            secondLastName = secondLastName.value!!,
            birthDate = birthdate.value!!,
            gender = gender.value!! + 1,
            phone = phone.value!!,
            email = email.value!!,
            ubigeoRequest = ubigeoRequest
        )
        val userRequest = UserRequest(
            password = password.value!!,
            personRequest = personRequest,
            role = "PATIENT",
            username = user.value!!
        )
        val patientRequest = PatientRequest(
            userRequest = userRequest
        )

        return patientRequest
    }
}