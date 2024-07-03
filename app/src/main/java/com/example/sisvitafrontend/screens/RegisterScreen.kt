package com.example.sisvitafrontend.screens

import android.util.Patterns
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.components.CustomEditTextField
import com.example.sisvitafrontend.components.CustomTextField
import com.example.sisvitafrontend.components.DatePickerComponent
import com.example.sisvitafrontend.components.SelectList
import com.example.sisvitafrontend.components.TextButton
import com.example.sisvitafrontend.components.global.CustomDialog
import com.example.sisvitafrontend.components.global.CustomFooter
import com.example.sisvitafrontend.navigation.ContextAplication
import com.example.sisvitafrontend.viewmodels.RegisterViewModel
import com.example.sisvitafrontend.viewmodels.UbigeoViewModel
import java.sql.Date

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = viewModel(modelClass = RegisterViewModel::class.java),
    ubigeoViewModel: UbigeoViewModel = viewModel(modelClass = UbigeoViewModel::class),
    showScreen: MutableIntState
) {
    Formulary(
        registerViewModel = registerViewModel,
        ubigeoViewModel = ubigeoViewModel,
    )
    Spacer(modifier = Modifier.height(15.dp))
    CustomFooter(
        showScreen = showScreen,
        value = R.string.login,
        blackText = R.string.already_account,
        redText = R.string.login
    )
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
private fun Formulary1(
    registerViewModel: RegisterViewModel,
) {
    val user by registerViewModel.user.observeAsState("")
    val password by registerViewModel.password.observeAsState("")
    CustomEditTextField(
        label = R.string.username, keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ), value = user, onValueChanged = { registerViewModel.onUserChanged(it) }, type = 0
    )
    Spacer(modifier = Modifier.padding(bottom = 15.dp))
    CustomEditTextField(
        label = R.string.password, keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ), value = password, onValueChanged = { registerViewModel.onPasswordChanged(it) }, type = 1
    )

}

@Composable
private fun Formulary2(
    registerViewModel: RegisterViewModel,
) {
    val name by registerViewModel.name.observeAsState("")
    val lastName by registerViewModel.lastName.observeAsState("")
    val secondLastName by registerViewModel.secondLastName.observeAsState("")
    CustomEditTextField(
        label = R.string.name, keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ), value = name, onValueChanged = { registerViewModel.onNameChanged(it) }, type = 0
    )
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    CustomEditTextField(
        label = R.string.last_name, keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ), value = lastName, onValueChanged = { registerViewModel.onLastNameChanged(it) }, type = 0
    )
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    CustomEditTextField(
        label = R.string.second_last_name,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        value = secondLastName,
        onValueChanged = { registerViewModel.onSecondLastNameChanged(it) },
        type = 0
    )

}

@Composable
private fun Formulary3(
    registerViewModel: RegisterViewModel,
) {
    val document by registerViewModel.document.observeAsState("")
    val documentType by registerViewModel.documentType.observeAsState("")
    val birthdate by registerViewModel.birthdate.observeAsState(Date(System.currentTimeMillis()))
    val documentList by registerViewModel.documentList.observeAsState(emptyList())

    SelectList(label = R.string.document_type,
        selectedItem = documentType,
        items = documentList.map { it.type },
        onItemSelected = { registerViewModel.onDocumentTypeChanged(it) })
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    CustomTextField(label = R.string.document,
        placeholder = R.string.empty_placeholder,
        keyboardType = KeyboardType.Text,
        value = document,
        onValueChanged = { registerViewModel.onDocumentChanged(it) },
        alert = R.string.document_alert,
        pattern = { it ->
            val documentLength = documentList.find { it.type == documentType }?.length ?: 0
            documentLength == it.length
        })
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    DatePickerComponent(label = R.string.birth_date,
        selectedDate = birthdate,
        onDateSelected = { registerViewModel.onBirthdateChanged(it) })
}

@Composable
private fun Formulary4(
    registerViewModel: RegisterViewModel,
) {
    val sex by registerViewModel.sex.observeAsState("")
    val phone by registerViewModel.phone.observeAsState("")
    val email by registerViewModel.email.observeAsState("")
    val sexList by registerViewModel.sexList.observeAsState(emptyList())

    SelectList(label = R.string.sex,
        selectedItem = sex,
        items = sexList.map { it.sex },
        onItemSelected = { registerViewModel.onSexChanged(it) })
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    CustomEditTextField(
        label = R.string.phone, keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ), value = phone, onValueChanged = { registerViewModel.onPhoneChanged(it) }, type = 0
    )
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    CustomTextField(label = R.string.email,
        placeholder = R.string.email_placeholder,
        keyboardType = KeyboardType.Email,
        value = email,
        onValueChanged = { registerViewModel.onEmailChanged(it) },
        alert = R.string.email_alert,
        pattern = { Patterns.EMAIL_ADDRESS.matcher(it).matches() })
}

@Composable
private fun Formulary5(
    registerViewModel: RegisterViewModel, ubigeoViewModel: UbigeoViewModel
) {
    val departamento by registerViewModel.departamento.observeAsState("")
    val provincia by registerViewModel.provincia.observeAsState("")
    val distrito by registerViewModel.distrito.observeAsState("")

    val departamentos by ubigeoViewModel.departamentos.observeAsState(emptyList())
    val provincias by ubigeoViewModel.provincias.observeAsState(emptyList())
    val distritos by ubigeoViewModel.distritos.observeAsState(emptyList())

    SelectList(label = R.string.department,
        selectedItem = departamento,
        items = departamentos,
        onItemSelected = {
            registerViewModel.onDepartamentoChanged(it)
            registerViewModel.onProvinciaChanged("")
            registerViewModel.onDistritoChanged("")
            ubigeoViewModel.getProvincias(it)
        })
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    SelectList(
        label = R.string.province,
        selectedItem = provincia,
        items = provincias,
        onItemSelected = {
            registerViewModel.onProvinciaChanged(it)
            registerViewModel.onDistritoChanged("")
            ubigeoViewModel.getDistritos(departamento, it)
        })
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    SelectList(label = R.string.district,
        selectedItem = distrito,
        items = distritos,
        onItemSelected = { registerViewModel.onDistritoChanged(it) })
}

@Composable
private fun Formulary(
    registerViewModel: RegisterViewModel, ubigeoViewModel: UbigeoViewModel
) {
    val numberForm = remember { mutableIntStateOf(1) }
    val title by registerViewModel.title.observeAsState("")
    val message by registerViewModel.message.observeAsState("")

    when (numberForm.intValue) {
        1 -> Formulary1(registerViewModel)
        2 -> Formulary2(registerViewModel)
        3 -> Formulary3(registerViewModel)
        4 -> Formulary4(registerViewModel)
        5 -> Formulary5(registerViewModel, ubigeoViewModel)
    }
    ChooseForm(increment = {
        if (numberForm.intValue < 5) numberForm.intValue += 1
    }, decrement = {
        if (numberForm.intValue > 1) numberForm.intValue -= 1
    })
    Spacer(modifier = Modifier.padding(bottom = 15.dp))
    CustomDialog(
        buttonText = R.string.register_button,
        title = title,
        content = message,
        onClick = { registerViewModel.onRegisterClicked() },
        dialogOnClick = {
            if (title == ContextAplication.applicationContext().getString(R.string.register_success)) {
                numberForm.intValue = 1
                registerViewModel.clearData()
            }
        }
    )
}

@Composable
private fun ChooseForm(
    increment: () -> Unit,
    decrement: () -> Unit,
) {
    Row {
        IconButton(onClick = decrement) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Prev")
        }
        IconButton(onClick = increment) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Next")
        }
    }
}